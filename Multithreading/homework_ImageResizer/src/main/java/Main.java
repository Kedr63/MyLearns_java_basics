import java.io.File;

public class Main {
// До этого рассмотрели создание потоков с помощью расширения класса Thread
  //Есть еще способ - это создавать классы реализующие интерфейс Runnable, зачем это нужно
  // Дело в том что в Java нет множественного наследования, и если нужно отнаследоваться от какого
  // класса и создать поток от наследованного класса, то удобней это сделать с потоком который
  // имплементирует интерфейс Runnable (здесь есть только один метод run() который нужно реализовать

  private static int newWidth = 300;
  private static String srcFolder = "/home/kedr/Рабочий стол/java_for_course/src";
  private static String dstFolder = "/home/kedr/Рабочий стол/java_for_course/dst";

  public static void main(String[] args) {

    File srcDir = new File(srcFolder);

    int coresCountProcessor = Runtime.getRuntime().availableProcessors();

    long star = System.currentTimeMillis();

    File[] scrArrayFiles = srcDir.listFiles();

     int lengthOfArrayForThread = scrArrayFiles.length / coresCountProcessor;
     int lengthLastArrayForThread = scrArrayFiles.length % coresCountProcessor;

        int indexStartArray = 0; // с какого индекса будет начинаться массив
        for (int i = 1; i <= coresCountProcessor; i++){
          if (indexStartArray == scrArrayFiles.length){
            break;
          }
          if (i == coresCountProcessor && scrArrayFiles.length > coresCountProcessor && lengthLastArrayForThread != 0){ // если количество фото больше количества ядер и не кратно массиву
            lengthOfArrayForThread = lengthOfArrayForThread + lengthLastArrayForThread;
          }
          if (lengthOfArrayForThread == 0 && indexStartArray < scrArrayFiles.length){ // если количество фото меньше чем ядер процессора
            lengthOfArrayForThread = 1;
          }
          File[] files1 = createFilesArray(scrArrayFiles, lengthOfArrayForThread, indexStartArray);
          ImageResizer imageResizer = new ImageResizer(files1, newWidth, dstFolder, star);
          new Thread(imageResizer).start();
          indexStartArray = indexStartArray + lengthOfArrayForThread;
        }
  }

  private static File[] createFilesArray(File[] srcFiles, int lengthArray, int indexStarArray){
    File[] files = new File[lengthArray];
    System.arraycopy(srcFiles, indexStarArray, files, 0, files.length);
    return files;
  }
}
