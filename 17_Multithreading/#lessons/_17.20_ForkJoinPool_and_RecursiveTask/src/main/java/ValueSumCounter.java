import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import lombok.SneakyThrows;

/**
 * Допустим, у нас есть некий массив большого размера, заполненный числами, и наша задача - найти
 * сумму всех этих чисел.
 */

public class ValueSumCounter extends RecursiveTask<Integer> {

  private int[] array;

  public ValueSumCounter(int[] array) {
    this.array = array;
  }

  @SneakyThrows
  @Override
  protected Integer compute() {
    int res = 0;

    if (array.length <= 2) {

      System.out.printf(System.currentTimeMillis() + " Task %s execute in thread %s%n", this, Thread.currentThread().getName());
       Thread.sleep(2000);
      System.out.println(System.currentTimeMillis() + " Arrays.stream(array).sum() " + Arrays.stream(array).sum());

     return Arrays.stream(array).sum();  // если это закомментировать то программа будет идти бесконечно

    }
    System.out.println(System.currentTimeMillis() + " Перед тасками");

    ValueSumCounter firstHalfArrayValueSumCounter = new ValueSumCounter(
        Arrays.copyOfRange(array, 0, array.length / 2));
    Thread.sleep(2000);
    ValueSumCounter secondHalfArrayValueSumCounter = new ValueSumCounter(
        Arrays.copyOfRange(array, array.length / 2, array.length));
    Thread.sleep(2000);

    System.out.println(
       System.currentTimeMillis() + " Сработал: firstHalfArrayValueSumCounter.fork() " + firstHalfArrayValueSumCounter.fork());
    Thread.sleep(2000);
    System.out.println(
       System.currentTimeMillis() + " Сработал: secondHalfArrayValueSumCounter.fork() " + secondHalfArrayValueSumCounter.fork());
    Thread.sleep(2000);
    System.out.println(
      System.currentTimeMillis()+  " firstHalfArrayValueSumCounter.join() + secondHalfArrayValueSumCounter.join() "
            + firstHalfArrayValueSumCounter.join() + " + " + secondHalfArrayValueSumCounter.join());
    return firstHalfArrayValueSumCounter.join() + secondHalfArrayValueSumCounter.join();
  }
}

class Test2 {

  public static void main(String[] args) {
    int[] array = getInitArray(8);
    System.out.println(new Date());

    ValueSumCounter counter = new ValueSumCounter(array);
    ForkJoinPool forkJoinPool = new ForkJoinPool();
    System.out.println(forkJoinPool.invoke(counter));

    System.out.println(new Date());
  }

  public static int[] getInitArray(int capacity) {
    int[] array = new int[capacity];
    for (int i = 0; i < capacity; i++) {
      /* допустим массив будет заполнен цифрами 3 */
      array[i] = 3;
    }
    return array;
  }
}
/** Сначала мы будем рекурсивно делить наш массив на всё более мелкие части, пока не получим массивы, состоящие всего из 2 элементов.
 *  Почему именно из 2? Потому что наш условный «слабенький» процессор может условно «быстро» выполнить именно такую
 *  условно «маленькую» задачу. Почему рекурсивно? Просто потому, что применение рекурсии в данном случае действительно удобно.
 *  Это позволяет сначала выполнить некую работу по подготовке, а потом получить результат.
    После того, как мы получим «100500» маленьких массивов, состоящих всего из 2 элементов, мы запустим «100500» маленьких задач
    на выполнение и суммируем их результаты. И для этого нам не придётся создавать 100500 отдельных нитей выполнения.

    При создании экземпляра класса ValueSumCounter мы передаём в него массив. В методе compute() сначала проверяется длинна массива,
    и если он «слишком большой», то разбивается пополам на 2 части, на основе каждой из которых в свою очередь создаётся своя задача
    и отправляется на выполнение путём вызова метода fork(). Когда разбивка будет закончена, наступает время «собирать камни»,
    метод join() запускает каждую задачу на выполнение и возвращает полученный результат.
 */

/*  console
1668151328084 Перед тасками
1668151328089 Сработал: firstHalfArrayValueSumCounter.fork() ValueSumCounter@1c5e9a99
1668151328090 Сработал: firstHalfArrayValueSumCounter.fork() ValueSumCounter@78c4869
1668151328090 Task ValueSumCounter@78c4869 execute in thread ForkJoinPool-1-worker-3
1668151328090 Перед тасками
1668151328093 Arrays.stream(array).sum() 6
1668151328097 Сработал: secondHalfArrayValueSumCounter.fork() ValueSumCounter@5bbb65a
1668151328097 Сработал: secondHalfArrayValueSumCounter.fork() ValueSumCounter@33a751d0
1668151328097 Task ValueSumCounter@5bbb65a execute in thread ForkJoinPool-1-worker-4
1668151328097 Arrays.stream(array).sum() 6
1668151328097 firstHalfArrayValueSumCounter.join() + secondHalfArrayValueSumCounter.join() 6 + 6
1668151328097 firstHalfArrayValueSumCounter.join() + secondHalfArrayValueSumCounter.join() 12 + 12
1668151328098 Перед тасками
1668151328098 Сработал: firstHalfArrayValueSumCounter.fork() ValueSumCounter@7bc6571
1668151328099 Task ValueSumCounter@7bc6571 execute in thread ForkJoinPool-1-worker-6
1668151328099 Arrays.stream(array).sum() 6
1668151328100 Сработал: secondHalfArrayValueSumCounter.fork() ValueSumCounter@22d732be
1668151328100 Task ValueSumCounter@22d732be execute in thread ForkJoinPool-1-worker-5
1668151328100 Arrays.stream(array).sum() 6
1668151328100 firstHalfArrayValueSumCounter.join() + secondHalfArrayValueSumCounter.join() 6 + 6
24
*/