import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Thread_pool_and_ExecutorService_by_Zaur_part_2 {

  /** 📍 ScheduledExecutorService мы используем тогда, когда хотим установить расписание на запуск
  * потоков из пула.
  * Данный pool создается, используя factory метод класса Executors:
  * Executors.newScheduledThreadPool(int count)
  *
  * И рассмотрим некоторые его методы:
  * 📍 schedule
  * 📍 scheduleAtFixedRate
  * 📍 scheduleWithFixedDelay */
}

class ThreadPoolSchedule{
  public static void main(String[] args) {
    ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
    /*for (int i = 0; i<10; i++){
      scheduledExecutorService.execute(new RunnableImp1200());
    }   смысла использовать здесь execute нет, т.к. для него тогда просто подойдет ExecutorService  */

    scheduledExecutorService.schedule(new RunnableImp1200(), 3, TimeUnit.SECONDS);  /* Здесь мы говорим
    scheduledExecutorService-су выполни этот таск - /new RunnableImp1200()/ через 3 сек  */
    scheduledExecutorService.shutdown();
  }
}

class ThreadPoolExScheduleAtFixedRate{
  public static void main(String[] args) throws InterruptedException {
    ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
    scheduledExecutorService.scheduleAtFixedRate(new RunnableImp1200(), 3, 1, TimeUnit.SECONDS);
    /* Этот метод планирует задачу для периодического выполнения.
    * Впервые эта задача обработается через 3 сек после запуска метода scheduleAtFixedRate и потом будет выполняться
    * с периодичностью в 1 сек (см. картинку scheduleAtFixedRate.png)*/

    /* закомментируем в run() - Thread.sleep(500); */

    Thread.sleep(20000); /* Если поток main не усыпим, таск выполнится только один раз и метод shutdown сразу завершит программу */
    scheduledExecutorService.shutdown();
  }
}

class ThreadPoolExScheduleWithFixedDelay{
  public static void main(String[] args) throws InterruptedException {
    ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
    scheduledExecutorService.scheduleWithFixedDelay(new RunnableImp1200(), 3, 1, TimeUnit.SECONDS);
    /* Отличается от предыдущего метода тем, что периодичность здесь начинает от завершения таска и до начала следующего таска
    * (см. картинку scheduleWithFixedDelay.png) */

    Thread.sleep(20000); /* Если поток main не усыпим, таск выполнится только один раз и метод shutdown сразу завершит программу */
    scheduledExecutorService.shutdown();
  }
}

/* Еще можем создать такой кэшированный Thread Pool, он будет создавать в себе новые потоки по надобности */
class ThreadPoolExNewCachedThreadPool{
  public static void main(String[] args) {
    ExecutorService executorService = Executors.newCachedThreadPool();

  }
}

class RunnableImp1200 implements Runnable {
  @Override
  public void run() {
    System.out.println(Thread.currentThread().getName() + "begins work");
    try {
      Thread.sleep(3000);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
    System.out.println(Thread.currentThread().getName() + "ends work");
  }
}