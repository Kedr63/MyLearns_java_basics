import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Callable_and_Future_by_Zaur_2 {
  /*  */
}

class CallableFactorial3 {

  static int factorialResult3;

  public static void main(String[] args) {
    ExecutorService executorService3 = Executors.newSingleThreadExecutor();
    Factorial3 factorial3 = new Factorial3(5);
    Future<Integer> future = executorService3.submit(factorial3);
    try {
      System.out.println(future.isDone());
      System.out.println("Xotim poluchit rezultat");
      factorialResult3 = future.get();
      System.out.println("Poluchili rezultat");
      System.out.println(future.isDone());
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    } catch (ExecutionException e) {
      System.out.println(e.getCause());
    } finally {
      executorService3.shutdown();
    }
    System.out.println(factorialResult3);
  }
}

class Factorial3 implements Callable<Integer> {  /* будет возвращать Integer */

  int f;

  public Factorial3(int f) {
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
      Thread.sleep(1000);
    }
    return result;
  }
}
/**
 * 📌 Что же такое Future? Когда мы делаем submit и он нам возвращает Future, -> результата работы
 * нашего таска во Future пока что нет - он будет в будущем, когда таск полностью выполнится,
 * поэтому так и называется - Future (будущее). И когда мы пытаемся вытащить из Future результат
 * нашего таска, используя метод get(), а этот таск еще не закончил свою работу, наш Thread, который
 * вызвал get(), т.е main в нашем случае, будет заблокирован до тех пор, пока таск не завершится,
 * т.е. пока факториал не будет найден и Future не вернет нам результат.  Почему здесь уже не надо
 * использовать awaitTermination() метод? Когда мы дойдем до System.out.println(factorialResult3);
 * factorialResult3 уже будет хранить значение, поэтому awaitTermination мы не используем
 *
 * 📌 Метод submit() мы можем использовать когда работаем с Runnable тоже, но он будет возращать null,
 * т.к. run() ничего не возращает. Какой тогда смысл использовать submit с Runnable? С его помощью мы
 * можем делать cancel нашего таска или узнавать закончилась ли его работа с помощью isDone()

 */

/* Пока весь таск не будет завершен, наш поток заблокируется */
/*📍 консоль */
/* false
   Xotim poluchit rezultat
   Poluchili rezultat
   true
   120   */

/* Это еще не все для чего нужен Future, с помощью него мы можем проверить завершен ли наш таск, это проверяется с помощью метода
 * isDone() */


/** 📌 Момент, который хотелось бы отметить:
 *  📍 Runnable мы можем использовать как с ExecutorService-ми так и при отдельном создании Thread
 *  📍 Callable только с ExecutorService-ми
 * Если вам не нужно чтоб таск возвращал результат - используйте Runnable, если нужно чтоб возвращал - Callable */

/* Еще один пример на Callable */
/* Мы будем искать сумму чисел от 1 до 1 милиарда не используя формулы и будем делать это 10-ю потоками чтобы каждый делал сложение:
1 поток складывает от 1 до 100_000_000, 2 поток от 100_000_001 доя 200_000_000 и тд и 10 поток от 900_000_001 до 1_000_000_000 */
class SumNumbers {

  private static long value = 1_000_000_000L;
  private static long sum = 0;

  public static void main(String[] args) throws ExecutionException, InterruptedException {
    ExecutorService executorService = Executors.newFixedThreadPool(10);
    List<Future<Long>> futureResult = new ArrayList<>();
    /* Понадобится дополнительная переменная чтоб разбросать задание на 10 тасков */
    long valueDividedDy10 = value / 10;
    for (int i = 0; i < 10; i++) {
      long from = valueDividedDy10 * i + 1;
      long to = valueDividedDy10 * (i + 1);
      PartialSum partialSum = new PartialSum(from, to);
      Future<Long> futurePartSum = executorService.submit(partialSum);
      futureResult.add(futurePartSum);
    }
    for (Future<Long> result : futureResult) {
      sum += result.get();
    }
    executorService.shutdown();
    System.out.println("Total sum = " + sum);
  }
}

class PartialSum implements Callable<Long> {

  long from;
  long to;
  long localeSum;

  public PartialSum(long from, long to) {
    this.from = from;
    this.to = to;
  }

  public Long call() {
    for (long i = from; i <= to; i++) {
      localeSum += i;
    }
    System.out.println("Sum from " + from + " to " + to + "= " + localeSum);
    return localeSum;
  }
}
/* console:
Sum from 300000001 to 400000000= 35000000050000000
Sum from 200000001 to 300000000= 25000000050000000
Sum from 1 to 100000000= 5000000050000000
Sum from 100000001 to 200000000= 15000000050000000
Sum from 600000001 to 700000000= 65000000050000000
Sum from 900000001 to 1000000000= 95000000050000000
Sum from 700000001 to 800000000= 75000000050000000
Sum from 800000001 to 900000000= 85000000050000000
Total sum = 500000000500000000
 */