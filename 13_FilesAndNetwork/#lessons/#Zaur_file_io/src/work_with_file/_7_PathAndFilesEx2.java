package work_with_file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class _7_PathAndFilesEx2 {
    public static void main(String[] args) throws IOException {
        Path filePath = Paths.get("test15.txt");
        Path directoryPath = Paths.get("/home/kedr/Рабочий стол/ForWork_JAVA/M");
        // оба Path ^ существуют и в файле есть строка
        Path directoryZPath = Paths.get("/home/kedr/Рабочий стол/ForWork_JAVA/Z");
        Path filePath1 = Paths.get("test18.txt");




        // просто скопировать файл в папку не получиться, мы должны указать не только папку куда копируем,
        // но и имя файла (скопируем с таким же именем).
        // Мы можем здесь использовать метод /resolve/, который объединит пути. Т/е этот filePath ("test15.txt")
        // скопируется по пути ("/home/kedr/Рабочий стол/ForWork_JAVA/M") и к этому пути прибавится
        // еще ("test15.txt")  ⬇

        Files.copy(filePath, directoryPath.resolve(filePath)); // если второй раз запустим этот код,
        // то будет FileAlreadyExistsException, так как файл уже существует
        Files.copy(filePath, directoryPath.resolve("test16.txt")); // или скопируем с другим именем
        // мы можем копировать файл ⬆ и назначать ему другое имя

        // Если хотим перезаписать имеющийся файл, то нужно использовать дополн. параметр метода copy
        /*Files.copy(filePath, directoryPath.resolve(filePath), StandardCopyOption.REPLACE_EXISTING);*/

        // теперь папку Z скопируем в папку М (Z пустая)
        Files.copy(directoryZPath, directoryPath.resolve("Z")); // копируемая папка будет называться "Z"

        // теперь папку Z скопируем в папку М (Z с файлом)
        /*Files.copy(directoryZPath, directoryPath.resolve("Z")); */// но после копирования она станет пустая
        // с помощью метода copy мы не можем скопировать папку с каким-то содержанием (папка скопируется, но
        // без содержимого)
        // Если хотим скопировать и содержимое папки тоже, то мы должны сначала скопировать папку саму, а потом
        // ее содержимое, т/е делать все это вручную (рассмотрим на следующих уроках)

        /* Files.move(filePath1, directoryPath.resolve("test18.txt"));*/ // переместится из проекта в папку М
        /** C помощью метода /move/ мы можем не только перемещать файлы и директории, но мы можем их
         переименовывать (у Files нет метода rename). Мы должны как бы переместить этот файл test20.txt в
         эту же папку (в рут проекта), но просто переместить его уже с другим именем   */
       /* Files.move(Paths.get("test20.txt"), Paths.get("test21.txt"));*/  // test20.txt переименовался в test21.txt

       /* Files.delete(Paths.get("file4.txt"));*/ // удалился file4.txt

        // попробуем удалить папку М (не пустая)
        Files.delete(directoryPath); // выбросится исключение что папка не пустая, так можно удалить только
        // пустую папку. А как удалить не пустую папку? Будем разбираться на следующих уроках

        System.out.println("Done!"); // если все нормально скопируется

        // Далее создадим _8_PathAndFilesEx3 (см. далее)
    }
}
