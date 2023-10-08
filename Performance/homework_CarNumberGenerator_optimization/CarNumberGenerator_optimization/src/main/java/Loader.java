import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Loader {

  private static final int regionCode = 199;
  private static final char[] letters = {'У', 'К', 'Е', 'Н', 'Х', 'В', 'А', 'Р', 'О', 'С', 'М', 'Т'};
  private static final int RANGE_OF_DIGITS_IN_NUMBERS = 999; // диапазон цифр для авто номеров от [1 - 999]

  public static void main(String[] args) throws Exception {

    int countCoresProcessor = Runtime.getRuntime().availableProcessors(); // определит количество ядер в комп
    ExecutorService executorService = Executors.newFixedThreadPool(countCoresProcessor); // создадим pool потоков

    long start = System.currentTimeMillis();

    int rangeForHandling = RANGE_OF_DIGITS_IN_NUMBERS / countCoresProcessor; // диапазон обработки для потока
    int lastRangeForHandling = RANGE_OF_DIGITS_IN_NUMBERS % countCoresProcessor; // последний остаточный диапазон номеров для задачи

    int indexForStartRange = 1;

    for (int i = 0; i < countCoresProcessor; i++) {
      CarNumberFactory numberFactory = new CarNumberFactory(letters, indexForStartRange,
          rangeForHandling, regionCode, i + 1, start);
      executorService.execute(numberFactory);   // запустим задачу

      indexForStartRange = indexForStartRange + rangeForHandling; // увеличим начальный индекс следующего диапазона номеров

      if (lastRangeForHandling != 0 && i == countCoresProcessor - 1) {
        executorService.execute(
            new CarNumberFactory(letters, indexForStartRange, lastRangeForHandling, regionCode,
                i + 2, start));
      }
    }

    executorService.shutdown();

    // блокирует поток до тех пор, пока все задачи не завершат свое выполнение или не будет достигнуто указанное время ожидания. (похож на join)
    executorService.awaitTermination(2, TimeUnit.SECONDS);

    System.out.println("total time finished after start:  " + (System.currentTimeMillis() - start)
        + " ms. Работал поток: " + Thread.currentThread().getName());

  }

  /**📌 После оптимизации программа стала работать примерно в 13-14 раз быстрее (с 3500 мс до 260 мс).
   * 1. Оптимизирован метод padNumber(): убран for loop, который каждый раз инициализировал переменную
   *    int i = 0, и проводил при соблюдении условия конкатенацию строк сложением строк (что затратно).
   *    Вместо for loop идет проверка условия и при его выполнении отрабатывает StringBuilder.
   *    Скорость после этой оптимизации увеличивается совсем немного, но всеравно это более рационально.
   *
   * 2. Основная оптимизация проведена при подключении в работу всех имеющихся на компьютере ядер,
   *    в данном случае применил thread pool, который задействовал 8 потоков. Наша задача поделилась
   *    на подзадачи, и эти подзадачи расхватали потоки и выполнили, каждый поток записал результаты
   *    в свой созданный файл (записалось 9 файлов)  */



}
