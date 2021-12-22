import java.io.File;
import java.io.FileInputStream;

public class Main {
    public static void main(String[] args) {

        String path = "/Users/aleksandrshabalin/Desktop/ForWorkFiles/text.txt";

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
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
