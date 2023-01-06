import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class Main {
  /*     ForkJoinPool и RecursiveTask  */
/** Fork и Join это ответвление и объединение потоков. Оно нужно для динамического увеличения уменьшения количества потоков
 * в приложении в рекурсивных алгоритмах. Есть специальный ForkJoinPool, это специальный класс который является центральным
 * для ForkJoinPool-фреймворка в java. В этом классе реализована логика распределения нагрузки м/у реальными потоками.
 * Снаружи он выглядит как обычный пул потоков и особенностей в его использовании нету. В ForkJoin-фреймворке любой поток
 * может развлетвляться на подзадачи, т.е ветвится (Fork) и дожидаться их выполнения с помощью метода (Join)   */

/* если у нас есть какая-то задача, с помощью ForkJoinPool мы сначала делим её на подзадачи, выполняем их,
потом объединяем результаты и делаем это всё рекурсивно */

  /* Рассмотрим пример: представьте что у вас есть дерево состоящее из узлов, есть корневой узел и есть дети и у каждого узла может
   * быть задано значение и нужно подсчитать сумму значений во всем дереве.  */
}

interface Node {

  Collection<Node> getChildren();

  long getValue();
}

/* Для того чтобы это сделать нужно описать класс рекурсивной задачи.
 * RecursiveTask - это разновидность Future, который возращает значение по окончанию своего выполнения.
 * И здесь надо реализовать метод compute(), который высчитывает значение <Long> для всех подзадач */

class NodeValueSumCalculator extends RecursiveTask<Long> {

  private Node node;

  public NodeValueSumCalculator(Node node) {
    this.node = node;
  }

  @Override
  protected Long compute() {
    long sum = node.getValue();
    List<NodeValueSumCalculator> taskList = new ArrayList<>();
    for (Node child : node.getChildren()) {
      NodeValueSumCalculator task = new NodeValueSumCalculator(child);
      /* и ответвляем эту задачу task с помощью fork() */
      task.fork();
      taskList.add(task);
    }
    for (NodeValueSumCalculator node : taskList) {
      sum += node.join();  /* join дожидается выполнения каждой из этих задач и возвращает ее обратно в этот поток */
    }
    return sum; /* вернется сумма по всем детям. Пока все подзадачи здесь не выполнились этот метод будет ожидать выполнения */
  }
}

/**
 * Запустим теперь
 */
class Test {

  public static void main(String[] args) {
    Node root = null;
    Long sum = new ForkJoinPool().invoke(
        new NodeValueSumCalculator(root)); /* вызовем задачу от корневого узла root*/
    System.out.println(sum); /* Получим общую сумму по всем узлам */
  }
}

/* https://habr.com/ru/post/565924/ */
/** Вызов метода ForkJoinPool.invoke() передает задачу на выполнение в один из потоков данного пула.
 * Важно отметить 📌, что метод fork() отправляет задачу в какой-либо поток, но при этом не запускает
 * её выполнения. Для получения результата служит метод join().📍
 *
 * Ответ другого пользователя
 * Ещё один момент:
 *________________________
 * Важно отметить, что метод fork() отправляет задачу в какой-либо поток, но при этом не запускает её выполнения.
 *____________________________
 * На самом деле, fork() отправляя задачу в поток на выполнение (пусть и без гарантии того, когда именно он ей займётся)
 * и вызов join() не обязателен для того, чтобы она была завершена, что можно увидеть на следующем синтетическом примере:
 *
 * */
