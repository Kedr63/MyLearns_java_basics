package work_with_file;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class _4_FileInputStream_FileOutputStream_CopyEx {
    // FileInputStream_FileOutputStream  ❤️

    /**
     * Для работы с бинарными файлами у нас есть специальные стримы: FileInputStream и FileOutputStream
     * Если будем использовать для копирования картинки стримы (FileWriter и FileReader) предназначенные для работы
     * с читабельным для человека текстом, то возникнет проблема (картинка нормально не скопируется).
     * Мы не можем использовать стримы предназначенные для текстовых файлов для работы с бинарными файлами
     * <p>
     * ❤️ FileInputStream и FileOutputStream используются для работы с бинарными файлами ❤️
     * <p>
     * 📍 FileInputStream inputStream = new FileInputStream("test2.bin");
     * 📍 FileOutputStream outputStream = new FileOutputStream("test2.bin");
     */
    public static void main(String[] args) {
        try (FileInputStream inputStream = new FileInputStream("/home/kedr/Рабочий стол/ForWork_JAVA/dinozavr.JPG");
                FileOutputStream outputStream = new FileOutputStream("dinozavr.JPG");) {
            int i;
            while ((i = inputStream.read()) != -1) { // пока файл, который читаем не закончился,
                outputStream.write(i);         // будем записывать с помощью outputStream-а в файл
            }
            System.out.println("Done!");  // в корне проекта появится файл /dinozavr.JPG/
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        /** В FileInputStream и FileOutputStream мы тоже можем использовать буферизацию. Это достигается с помощью
         * классов BufferedInputStream и BufferedOutputStream, здесь суть работы буферизации такая же как и
         * для классов /BufferedReader и BufferWriter/
         *
         * */
    }
}
