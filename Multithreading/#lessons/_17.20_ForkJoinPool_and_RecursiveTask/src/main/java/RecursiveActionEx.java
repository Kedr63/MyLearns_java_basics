import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;
import java.util.logging.Logger;

public class RecursiveActionEx {

  /** В приведенном ниже примере мы используем String вызывается рабочая нагрузка для представления единицы работы, подлежащей обработке.
   *  Для демонстрационных целей задача бессмысленна: она просто вводит ввод в верхний регистр и регистрирует его.
      Для демонстрации поведения фреймворка при разветвлении, в примере задача разбивается, если .length() больше
      заданного порога (THRESHOLD) используя createSubtask().
      Строка рекурсивно делится на подстроки, создавая Экземпляры CustomRecursiveTask, основанные на этих подстроках.
      В результате метод возвращает список<CustomRecursiveAction> .
      Список отправляется в ForkJoinPool используя invokeAll(): */

  public static void main(String[] args) {
    CustomRecursiveAction recursiveAction = new CustomRecursiveAction("Темно за окошком не звука, луна из-за леса встает");
    ForkJoinPool forkJoinPool = new ForkJoinPool();
    System.out.println(forkJoinPool.invoke(recursiveAction));
  }
}

class CustomRecursiveAction extends RecursiveAction {

  private String workload = "";
  private static final int THRESHOLD = 4;

  private static Logger logger =
      Logger.getAnonymousLogger();

  public CustomRecursiveAction(String workload) {
    this.workload = workload;
  }

  @Override
  protected void compute() {
    if (workload.length() > THRESHOLD) {
      ForkJoinTask.invokeAll(createSubtasks());
    } else {
      processing(workload);
    }
  }

  private List<CustomRecursiveAction> createSubtasks() {
    List<CustomRecursiveAction> subtasks = new ArrayList<>();

    String partOne = workload.substring(0, workload.length() / 2);
    String partTwo = workload.substring(workload.length() / 2, workload.length());

    subtasks.add(new CustomRecursiveAction(partOne));
    subtasks.add(new CustomRecursiveAction(partTwo));

    return subtasks;
  }

  private void processing(String work) {
    String result = work.toUpperCase();
    logger.info("This result - (" + result + ") - was processed by "
        + Thread.currentThread().getName());
  }
}
/** Мы можем использовать этот шаблон для разработки нашего собственного RecursiveAction.
 * Для этого мы создаем объект, который представляет общий объем работы, выбираем подходящий порог,
 * определяем метод для разделения работы и определяем метод для выполнения работы.
 *
 * https://www.baeldung.com/java-fork-join */