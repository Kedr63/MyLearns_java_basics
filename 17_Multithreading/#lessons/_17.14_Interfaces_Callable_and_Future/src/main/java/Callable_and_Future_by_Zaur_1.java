import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class Callable_and_Future_by_Zaur_1 {
  /* Предисловие:
   * факториал 5! = 1*2*3*4*5 = 120*/
}

class RunnableFactorial {

  static int factorialResult;

  public static void main(String[] args) throws InterruptedException {
    ExecutorService executorService = Executors.newSingleThreadExecutor();
    Factorial factorial = new Factorial(5);
    executorService.execute(factorial);
    executorService.shutdown();
    executorService.awaitTermination(10,
        TimeUnit.SECONDS); /* Если не задержим 10 сек, то результат будет = 0 */
    System.out.println(factorialResult);

  }
}

class Factorial implements Runnable {
  int f;
  public Factorial(int f) {
    this.f = f;
  }
  @Override
  public void run() {
    if (f <= 0) {
      System.out.println("vi veli ne vernoe chislo");
      return;       /* заканчиваем работу нашего метода */
    }
    int result = 1;
    for (int i = 1; i <= f; i++) {
      result *= i;
    }
    RunnableFactorial.factorialResult = result;
  }
}

/** Какие недостатки есть у этого кода? А именно чего нам не хватает в Runnable? Приходится использовать переменную
 * вне Runnable, чтобы передать значение result, используем переменныу класса RunnableFactorial, а все потому что
 * return type Runnable - void (он ничего не возвращает). Также мы не можем выбросить в нем Exception, а очень хотелось бы.
 * Это не достатки интерфейса Runnable.
 *  📍 Хорошая новость: есть интерфейс Callable 📍, который очень похож на Runnable, но может в output-е возвращать значение
 * и позволяет выбрасывать исключения. Он с дженериком, при его создании надо указать с каким типом будем работать, т.е какой
 * тип данных будет в output-е у метода call(). Т.е интерфейс Callable как и интерфейс Runnable представляет собой
 * какое-то задание (таск), КОТОРОЕ ДОЛЖНО БЫТЬ ВЫПОЛНЕННО ПОТОКОМ
 *
 * И рассмотрим тот же пример нахождения факториала с помощью Callable
 *   */
class CallableFactorial {

  static int factorialResult2;

  public static void main(String[] args) {
    ExecutorService executorService2 = Executors.newSingleThreadExecutor();
    Factorial2 factorial2 = new Factorial2(5);
    Future<Integer> future = executorService2.submit(factorial2);  /* этот метод как и метод execute
   добавляет наш таск в Thread pool, но помимо этого он еще и возвращает результат нашего таска - (Integer).
   Этот результат хранится в объекте типа Future. И из него результат получим с помощью метода get  */
    try {
      factorialResult2 = future.get();
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    } catch (ExecutionException e) {
      System.out.println(e.getCause()); /** getCause помощет узнать причину исключения. А что это за причина покажет
       вот это сообщение /throw new Exception("vi veli ne vernoe chislo");/ из метода call()   */
    }
    /* После этого мы закрываем executorService, и лучше его закрывать finally-блоком */
    finally {
      executorService2.shutdown();
    }
    System.out.println(factorialResult2);
  }
}

class Factorial2 implements Callable<Integer> {  /* будет возвращать Integer */

  int f;

  public Factorial2(int f) {
    this.f = f;
  }

  @Override
  public Integer call() throws Exception {
    if (f <= 0) {
      throw new Exception("vi veli ne vernoe chislo");
    }
    int result = 1;
    for (int i = 1; i <= f; i++) {
      result *= i;
    }
    return result;
  }
}
/* 📌 Callable, так же как и Runnable, представляем собой определенное задание, которое выполняется потоком.
*     В отличии от Runnable Callable:
*        📍 имеет return type не void;
*        📍 может выбрасывать исключения;
*
*  📌 Метод submit передает наше задание (task) в Thread pool, для выполнения его одним из потоков,
*     и возращает тип Future, в котором и хранится результат выполнения нашего задания.
*
*  📌 Метод get позволяет получить результат выполнения нашего задания из объекта Future. */



