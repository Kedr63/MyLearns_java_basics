

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class Main {

private static int newWidth = 300;

// Этот код копирует фото из одной папки в другую в одном потоке
  public static void main(String[] args) {

    String srcFolder = "/home/kedr/Рабочий стол/java_for_course/src";
    String dstFolder = "/home/kedr/Рабочий стол/java_for_course/dst";

   /* File srcDir = new File(srcFolder);

    long star = System.currentTimeMillis();

    File[] files = srcDir.listFiles();

    try {
      for (File file : files){
        BufferedImage image = ImageIO.read(file);
        if (image == null){
          continue;
        }
        int newHeight = (int) Math.round(image.getHeight() / (image.getWidth() / (double)newWidth));

        BufferedImage newImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);

        int widthStep = image.getWidth() / newWidth;
        int heightStep = image.getHeight() / newHeight;

        for (int x = 0; x < newWidth; x++){
          for (int y = 0; y < newHeight; y++){
            int rgb = image.getRGB(x * widthStep, y * heightStep);
            newImage.setRGB(x, y, rgb);
          }
        }
        File newFile = new File(dstFolder + "/" +file.getName());
        ImageIO.write(newImage, "jpg", newFile);
      }
    } catch (Exception ex){
      ex.printStackTrace();
    }
    System.out.println("Duration: " + (System.currentTimeMillis() - star));*/

    // 📍 Теперь разделим на два потока
    // Чтоб создать поток нужно создать какой-нибудь класс, например ImageResizer и отнаследовать его
    // от класса Tread и переопределить в нем единственный метод run() и написать в нем то что должно выполняться
    // в отдельном потоке, в этом классе также добавим конструктор
    File srcDir = new File(srcFolder);

    long star = System.currentTimeMillis();

    File[] files = srcDir.listFiles();

    int middle = files.length / 2;

    File[] files1 = new File[middle];
    System.arraycopy(files, 0, files1, 0, files1.length);

    // И создадим 1 поток
    ImageResizer imageResizer1 = new ImageResizer(files1, newWidth, dstFolder, star);
    imageResizer1.start();


    File[] files2 = new File[files.length - middle];
    System.arraycopy(files, middle, files2, 0, files2.length);

    // И создадим 2 поток
    ImageResizer imageResizer2 = new ImageResizer(files2, newWidth, dstFolder, star);
    imageResizer2.start();
  }
}
