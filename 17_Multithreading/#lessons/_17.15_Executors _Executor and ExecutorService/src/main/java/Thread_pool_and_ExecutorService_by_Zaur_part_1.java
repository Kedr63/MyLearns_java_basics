import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Thread_pool_and_ExecutorService_by_Zaur_part_1 {

  /** 📍 Часто на практике нам нужно создавать не один или два потока, а большее количество потоков.
   * Управлять отдельно созданными потоками неудобно, на помощь приходит механизм Thread pool-ов.
   *
   *   Thread pool - это множество потоков, каждый из которых предназначен для выполнения той или иной задачи.
   *
   *  В Java мы работаем с Thread pool-ми посредством объектов типа Executor (это интерфейс). Мы рассмотрим два вида
   * Executor-ов. На этом уроке мы рассмотрим также интерфейс - ExecutorService (выполняет сервисы).
   *  Executor переводится как /испольнитель/.
   * В Java с Thread pool-ами удобнее всего работать посредством ExecutorService.
   *   */
}

/* Пример */
class ThreadPoolEx1 {
  /* Thread pool удобнее всего создавать используя factory методы класса Executors:
   * Executors.newFixedThreadPool(int count) - создаст pool с "n" потоками
   * Executors.newSingleThreadExecutor() - создаст pool с одним потоками  */

  public static void main(String[] args) throws InterruptedException {
    ExecutorService executorService = Executors.newFixedThreadPool(5);
 /* Здесь мы создали пул не с помощью конструктора, а с помощью метода, поэтому он и называется factory (фабрика
    по производству объектов), в нашем случае по производству Thread pool-ов */
    for (int i = 0; i < 10; i++) {
      executorService.execute(new RunnableImp1100());
    }
    executorService.shutdown();
    executorService.awaitTermination(5, TimeUnit.SECONDS);
    System.out.println("Main ends");
  }
}

class RunnableImp1100 implements Runnable {

  @Override
  public void run() {
    System.out.println(Thread.currentThread().getName());
    try {
      Thread.sleep(500);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }
}  /* Консоль
pool-1-thread-1
pool-1-thread-3
pool-1-thread-2
pool-1-thread-4
pool-1-thread-5
Main ends
pool-1-thread-3
pool-1-thread-1
pool-1-thread-2
pool-1-thread-4
pool-1-thread-5 */
/* Разберемся что происходит в этом примере: внутри потока main был создан /Thread pool/ из пяти потоков,
потом с  помощью метода /.execute(new RunnableImp1100())/ десять раз передали какое-то задание - new RunnableImp1100().
📍 На что стоит обратить внимание: Как происходит работа пула? Мы видим что каждый поток выполнил по 2 задания - 5 потоков
по 2 задания - 10 task-ов. И время исполнения нашего задания-таска всего 0,5с (Thread.sleep(500)).
Чуть подробней: /Thread pool-ом/ получено первое задание, 1-й поток начал выполнять этот таск, к нам еще поступил этот таск,
идет проверка есть ли свободный поток в нашем /Thread pool-е/ -> да, есть -> thread-3 взялся за выполнения 2-го задания ->
поступило еще задание, есть свободный поток который может его выполнить? -> да -> thread-2 -> потом thread-4, потом
6-е задание поступает и /Thread pool/ говорит- "подождите, пока что свободных потоков нет, как первый освободится
я назначу ему шестое задание" -> освобождается thread-1 и ему назначается шестое задание и тд и тому подобное,
т.е. 5 потоков работают постоянно пока есть задание, освободился поток опять взял задание и начал работать
  */

/* А если уберем Thread.sleep(500);
Консоль:
pool-1-thread-1
pool-1-thread-4
pool-1-thread-3
pool-1-thread-2
Main ends
pool-1-thread-5
pool-1-thread-5
pool-1-thread-5
pool-1-thread-5
pool-1-thread-5
pool-1-thread-5     Получится что некоторые потоки сделают работу больше остальных, это нормально.
 Некоторые таски выполнил только 5-й поток, почему? 5-й поток взял 5-й таск и пока 6-й таск не поступил
 еще к нашему Thread pool-у, 5-й поток уже выполнил 5-й таск, поступает 6-й таск -> говорит: "Кто возьмет
 его?" -> 5-й поток взял этот таск и он тоже моментально выполняет 6-й таск.
 */

/** Обратите внимание что наша программа не заканчивает свою работу, а все потому что ExecutorService ждет
* новых тасков чтобы справится и с ними, поэтому программа не заканчивается. Если мы не намерены давать новые таски,
* то обязательно нужно заканчивать работу ExecutorService-а добавив метод shutdown(). Вызвав этот метод мы говорим
* что задач больше не будет, и программа завершится только когда все полученные задания выполнятся   */

/* Метод execute передает наше задание (task) в thread pool, где оно выполняется одним из потоков  */

/* После выполнения метода shutdown() ExecutorService понимает, что новых заданий больше не будет и,
* выполнив поступившие до этого задания, прекращает работу. */

/* awaitTermination (похож на метод join) заставляет ожидать поток в котором он вызывается. В данном случае вызываем
 внутри потока main, значит поток main будет ждать, пока ExecutorService не закончит всю свою работу либо не пройдет
  указанное время в методе (здесь 5с). Если первым сработает по времени awaitTermination, то main поток завершится, а
  ExecutorService еще продолжит выполнять свои таски  */

/* Есть еще factory метод Executors.newSingleThreadExecutor(), он будет выполнять только один поток */