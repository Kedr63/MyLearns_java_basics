import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import org.redisson.api.RScoredSortedSet;

public class RedisStarter {

  private static final int COUNT_USERS = 20;
  private static final long TWENTY_FIVE_YEARS = 788_400_000_000L;


  public static void main(String[] args) {

    RedisStorage redis = new RedisStorage();
    redis.unit();

    // получим пользователей нашего сайта
    Set<User> userSet = GenerationUser.generateUsersSite(COUNT_USERS);

    for (;;){
      System.out.println("\n");
      // С каждым новым циклом будем возвращать пользователям их начальные даты регистрации
      RScoredSortedSet<String> usersSortedSet = redis.addUsersToSortedSet(userSet);

      int randomNumberOfHalfCycle1 = 1 + new Random().nextInt(COUNT_USERS / 2); // random [1-10] для первых 10 итераций цикла
      int randomNumberOfHalfCycle2 = 11 + new Random().nextInt(COUNT_USERS / 2); // random [11-20] для вторых 10 итераций цикла
      int randomIdUser1 = 1 + new Random().nextInt(COUNT_USERS);
      int randomIdUser2 = 1 + new Random().nextInt(COUNT_USERS);

      // для подчета id, когда соберем сюда все id - цикл завершим
      Set<String> setCounterId = new HashSet<>();

      for (int i = 0; ; i++) {
        if (i == randomNumberOfHalfCycle1) {
          String randomId = String.valueOf(randomIdUser1);
          // понизим score (отбросив на 25 лет назад), чтоб user переместился в начало usersSortedSet
          usersSortedSet.add((double) (System.currentTimeMillis() - TWENTY_FIVE_YEARS), randomId);
          userPaidServicePrint(randomId);
        }
        if (i == randomNumberOfHalfCycle2) {
          String randomId = String.valueOf(randomIdUser2);
          usersSortedSet.add((double) (System.currentTimeMillis() - TWENTY_FIVE_YEARS), randomId);
          userPaidServicePrint(randomId);
        }

        //заберем (paste) из usersSortedSet первого пользователя (с самой низкой score)
        String id = usersSortedSet.takeFirst();
        logPrint(id);

        // и снова добавим в usersSortedSet этого пользователя, которого забирали с usersSortedSet, и добавим его в конец набора
        // с помощью того что временная метка у него будет самая высокая
        usersSortedSet.add((double) System.currentTimeMillis(), id);

        try {
          Thread.sleep(200);
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }

        setCounterId.add(id);
        if (setCounterId.size() >= userSet.size()) {
          break;
        }
      }

      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }

      usersSortedSet.clear();
    }
  }

  private static void logPrint(String id) {
    System.out.println("— На главной странице показываем пользователя " + id);
  }

  private static void userPaidServicePrint(String id) {
    String log = String.format("> Пользователь %s оплатил платную услугу", id);
    System.out.println(log);
  }

}
