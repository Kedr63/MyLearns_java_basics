import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class RecursiveTaskEx {

  public static void main(String[] args) {
    int[] array = getArray(101);
    CustomRecursiveTask customRecursiveTask = new CustomRecursiveTask(array);
    ForkJoinPool forkJoinPool = new ForkJoinPool();
    System.out.println(forkJoinPool.invoke(customRecursiveTask));

  }

  public static int[] getArray(int capacity) {
    int[] array = new int[capacity];
    for (int i = 0; i < capacity; i++) {
      /* допустим массив будет заполнен цифрами 3 */
      array[i] = (int) (Math.random() * 10) + 5;
    }
    return array;
  }
}

class CustomRecursiveTask extends RecursiveTask<Integer> {

  private int[] arr;
  private static final int THRESHOLD = 20;

  public CustomRecursiveTask(int[] arr) {
    this.arr = arr;
  }

  @Override
  protected Integer compute() {
    if (arr.length > THRESHOLD) {

      System.out.printf(System.currentTimeMillis() + " Task %s execute in thread %s%n", this, Thread.currentThread().getName());
     return ForkJoinTask.invokeAll(createSubtasks()).stream().mapToInt(ForkJoinTask::join).sum();
    } else {
      System.out.printf(System.currentTimeMillis() + " AFTER ELSE!!!! Task %s execute in thread %s%n", this, Thread.currentThread().getName() + " "+processing(arr));
      return processing(arr);
    }
  }

  private Collection<CustomRecursiveTask> createSubtasks() {
    List<CustomRecursiveTask> dividedTasks = new ArrayList<>();
    dividedTasks.add(new CustomRecursiveTask(Arrays.copyOfRange(arr, 0, arr.length / 2)));
    dividedTasks.add(new CustomRecursiveTask(Arrays.copyOfRange(arr, arr.length / 2, arr.length)));
    return dividedTasks;
  }

  private Integer processing(int[] arr) {
    return Arrays.stream(arr).filter(a -> a > 5 && a < 7).map(a -> a * 10).sum();
  }
}
/** В этом примере мы используем массив, хранящийся в поле arr класса CustomRecursiveTask, для представления работы.
 *  Метод createSubtasks() рекурсивно делит задачу на более мелкие части работы, пока каждая часть не станет меньше
 *  порогового значения. Затем метод invokeAll() отправляет подзадачи в общий пул и возвращает список будущих.
    Для запуска выполнения для каждой подзадачи вызывается метод join().
    Мы выполнили это здесь, используя Stream API Java 8. Мы используем метод sum() как представление объединения
    промежуточных результатов в конечный результат.

 https://www.baeldung.com/java-fork-join */