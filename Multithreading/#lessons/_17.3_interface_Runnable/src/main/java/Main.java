import java.io.File;

public class Main {
// До этого рассмотрели создание потоков с помощью расширения класса Thread
  //Есть еще способ - это создавать классы реализующие интерфейс Runnable, зачем это нужно
  // Дело в том что в Java нет множественного наследования, и если нужно отнаследоваться от какого
  // класса и создать поток от наследованного класса, то удобней это сделать с потоком который
  // имплементирует интерфейс Runnable (здесь есть только один метод run() который нужно реализовать

private static int newWidth = 300;

  public static void main(String[] args) {

    String srcFolder = "/home/kedr/Рабочий стол/java_for_course/src";
    String dstFolder = "/home/kedr/Рабочий стол/java_for_course/dst";

    File srcDir = new File(srcFolder);

    long star = System.currentTimeMillis();

    File[] files = srcDir.listFiles();

    int middle = files.length / 2;

    File[] files1 = new File[middle];
    System.arraycopy(files, 0, files1, 0, files1.length);
    // И здесь уже немного по другому создадим  поток
    ImageResizer imageResizer1 = new ImageResizer(files1, newWidth, dstFolder, star);
    new Thread(imageResizer1).start();

    File[] files2 = new File[files.length - middle];
    System.arraycopy(files, middle, files2, 0, files2.length);
    ImageResizer imageResizer2 = new ImageResizer(files2, newWidth, dstFolder, star);
    new Thread(imageResizer2).start();

    // можно еще с помощью лямда выражения запустить поток и например печатать какие-то цифры
    new Thread(()->{
      for (int i=0; i < 10000; i++){
        System.out.println(i);
      }
    }).start();
  }

}
