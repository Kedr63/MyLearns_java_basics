import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class Test1 {
    public static void main(String[] args) {

        String path = "/Users/aleksandrshabalin/Desktop/ForWorkFiles/text.txt";
        String path1 = "/Users/aleksandrshabalin/Desktop/ForWorkFiles/textForWrite.txt";

        File file = new File(path);

        try {
            StringBuilder stringBuilder = new StringBuilder();
            FileInputStream inputStream = new FileInputStream(file); // можно читать побайтно
            while (true) {
                int code = inputStream.read();
                if (code < 0) {
                    break;
                }
                stringBuilder.append((char) code);
            }
            inputStream.close();
            System.out.println(stringBuilder); // это сложный способ чтения файлов
            // 📌 FileInputStream FileOutputStream теперь редко используется
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            FileOutputStream outputStream = new FileOutputStream(path1); //конструктор FileOutputStream создаст
            // файл по этому пути /path1/
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < 20; i++) {
                builder.append(i + "\n");
            }
            outputStream.write(builder.toString().getBytes());
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Простой способ чтения файла (с 7 java появился) с помощью Files ❤️
        try {
            String data = Files.readString(Paths.get(path));
            System.out.println(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // И запись
        try {
            Files.writeString(Paths.get(path1), "Темно за окошком не звука", StandardOpenOption.APPEND);
            //.APPEND - эта опция добавит к имеющимся записям в существующем файле запись этой строки    ⬆,
            // а если .CREATE - то создаст файл
        } catch (Exception e) {
            e.printStackTrace();
        }

        // ❤️❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤




    }
}
