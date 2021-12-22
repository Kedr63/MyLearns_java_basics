package work_with_file;

import java.io.FileReader;
import java.io.IOException;

public class _2_FileReaderEx {
    public static void main(String[] args) throws IOException {
        FileReader reader = null;
        try {
            reader = new FileReader("file2.txt");
            int character;
            while ((character = reader.read()) != -1) { // когда наступает конец файла - read возвращает /-1/
                //   /read/ return int
                System.out.print((char) character);
            }
            System.out.println();
            System.out.println("Done");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            reader.close();
        }
        /**  D консоль выведется содержимое файла "file2.txt" */
    }
       /** FileReader и FileWriter используются для работы с текстовыми файлами

       📌 FileReader reader = new FileReader("file2.txt");
       📌 FileWriter writer = new FileWriter("file2.txt");

        ❗️Никогда не забывайте закрывать стримы после использования

        */
}
