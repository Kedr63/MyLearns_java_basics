package work_with_file;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class _FileTreeDelete {
    // До этого когда мы использовали метод /delete/ класса Files, мы могли удалить только определенные файлы
    // и пустые папки, мы не могли удалять папки с содержимым, но благодаря методу /walkFileTree/ мы можем обойти
    // содержимое папки которую хотим удалить: удалить вначале все содержимое и после этого удалить саму папку
    // тоже.
    // Когда мы делали копирование, мы вначале в методе /preVisitDirectory/ копировали саму папку и потом уже
    // в нее вставляли уже скопированные файлы с помощью метода /visitFile/.
    // Когда мы будем удалять папку с содержимым нам сначала нужно удалить все файлы, которые содержатся в папке,
    // с помощью метода /visitFile/ и только после этого удалять саму папку тоже, т/е мы должны использовать
    // не метод /preVisitDirectory/, а метод /postVisitDirectory/.
    //       Реализуем этот пример ⬇

    public static void main(String[] args) throws IOException {
        // ⬇ папка, которую будем удалять
        Path path = Paths.get("/Users/aleksandrshabalin/Desktop/ForWorkFiles/CopyHere");

        Files.walkFileTree(path, new MyFileVisitor3());

    }
}

class MyFileVisitor3 extends SimpleFileVisitor<Path>{
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        // когда будем получать доступ к файлу, будем его удалять
        System.out.println("Delete file : " + file.getFileName());
        Files.delete(file);
        return FileVisitResult.CONTINUE;
    }

    // после того как файлы будут удалены мы можем удалить саму папку или sub-папку
    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
        System.out.println("Delete directory: " + dir.getFileName());
        // и удаляем уже опусташенную папку
        Files.delete(dir);
        return FileVisitResult.CONTINUE;
    }
}  // folder deleted
    /*  Delete file : .DS_Store
        Delete file : .DS_Store
        Delete directory: O
        Delete file : test1.txt
        Delete file : test2.txt
        Delete file : test3.txt
        Delete directory: Y1
        Delete file : test4.txt
        Delete directory: Y2
        Delete directory: Z
        Delete directory: CopyHere    */
