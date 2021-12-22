package work_with_file;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class _FileTreeCopy {
    // ❤️ Копирование не пустой папки гулянием по дереву с помощью метода walkFileTree

    // Для копирования не пустых папок мы опять таки будем гулять по дереву, и поэтому мы должны имплементировать
    // интерфейс /FileVisitor/ или же extend-ить /SimpleFileVisitor/.
    // Мы в данном примере будем extend-ить /SimpleFileVisitor/, потому что из 4-х методов интерфейса
    // /FileVisitor/ - мы будем использовать всего два:
    // 📍 preVisitDirectory - для того чтобы копировать саму папку
    // 📍 visitFile - чтобы копировать сам файл

    // И создадим класс MyFileVisitor


    public static void main(String[] args) throws IOException {
        Path source = Paths.get("/Users/aleksandrshabalin/Desktop/ForWorkFiles/X");
        Path destination = Paths.get("/Users/aleksandrshabalin/Desktop/ForWorkFiles/CopyHere");
        //  И вызываем наш метод /walkFileTree/ и передаем ему откуда мы начинаем гуляние по файловому дереву
        // и что должно происходить при хождении по этому файловому дереву. А что должно происходить описывается
        // в классе MyFileVisitor2 (мы описываем здесь что мы будем копировать и вставлять необходимую
        // директорию при хождении по файловому дереву)
        Files.walkFileTree(source, new MyFileVisitor2(source, destination));
        System.out.println("Done!");
    }
}

class MyFileVisitor2 extends SimpleFileVisitor<Path> {
    // Для того чтобы производить процесс копирования, мы должны знать что мы копируем, т/е откуда копируем и
    // куда копируем (в нашем примере будем копировать папку "Х" в папку "CopyHere")
    Path source;
    Path destination;

    // И передадим все это в конструктор
    public MyFileVisitor2(Path source, Path destination) {
        this.source = source;
        this.destination = destination;
    }

    // И переопределим методы
    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        Path newDestination = destination.resolve(source.relativize(dir));
        Files.copy(dir, newDestination, StandardCopyOption.REPLACE_EXISTING);
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        Path newDestination = destination.resolve(source.relativize(file));
        Files.copy(file, newDestination, StandardCopyOption.REPLACE_EXISTING);
        return FileVisitResult.CONTINUE;
    }
}
// Этот процесс, который рисовали на листке (по копированию папок и файлов), относится к самой папке "Х" тоже.
// Эта папка "Х" также будет скопирована и вставлена с новым именем "CopyHere" по тем же самым правилам что и при
// копировании папок Y1, Y2 и т.д., поэтому чтобы мы умели перезаписать эту папку "CopyHere" будем использовать
// в методе /copy/ -> StandardCopyOption.REPLACE_EXISTING
