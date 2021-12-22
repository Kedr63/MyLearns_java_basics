package work_with_file;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class _8_PathAndFilesEx3 {
    public static void main(String[] args) throws IOException {
        // Мы можем с помощью класса Files записывать информацию в файл и читать ее из нее
        // Рассмотрим
        /*  Files.write(path, byte_array);
            Files.readAllLines(path);   */

        Path filePath = Paths.get("file300.txt"); // такого файла нет, создадим
        Files.createFile(filePath);

        /* String dialog = "-Privet\n -Privet\n-Kak dela?\n-Xorosho! Kak y tebya?\n-Toje xorosho!\n";

        Files.write(filePath, dialog.getBytes());*/

        // Теперь прочитаем этот диалог ⬇
        /* Files.readAllLines(filePath); */
        // и присвоим это значение list
        List<String> list = Files.readAllLines(filePath);
        for (String s : list){
            System.out.println(s);
        }


    }
}
