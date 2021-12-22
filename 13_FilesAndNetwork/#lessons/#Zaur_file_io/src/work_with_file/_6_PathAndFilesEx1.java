package work_with_file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class _6_PathAndFilesEx1 {
    // Interface Path & class Files ❤️

    /** Еще больше можем делать операций с нашими файлами (чем в классе File). И вместо единого класса File,
     *  появились Interface Path & class Files.
     *  Объект типа Path представляет собой путь к файлу или директории
     *  📌Path path = Paths.get("test1.txt"); 📌
     *   Используя класс Paths и его метод get, мы в параметре у get указываем адрес этого path-а, и таким
     *   образом метод get возвращает нам путь.
     *   Files в свою очередь это utility класс, который содержит много очень полезных статических методов
     *   для работы с файлами и директориями*/

    public static void main(String[] args) throws IOException {
      // Примеры
        Path filePath = Paths.get("test45.txt"); // такого файла в проекте нет, но мы можем создать такой путь
        Path directoryPath = Paths.get("/Users/aleksandrshabalin/Desktop/ForWorkFiles/L"); // такой папки тоже
        // в проекте нет, но это не мешает создавать пути

        System.out.println("filePath.getFileName() " + filePath.getFileName());
        System.out.println("directoryPath.getFileName() " + directoryPath.getFileName());
        System.out.println("------------------------------------------");

        System.out.println("filePath.getParent() " + filePath.getParent()); // null (потому что путь относительный)
        System.out.println("directoryPath.getParent() " + directoryPath.getParent()); // /Users/aleksandrshabalin/Desktop/ForWorkFiles
        System.out.println("------------------------------------------");

        System.out.println("filePath.getRoot() " + filePath.getRoot()); // null (потому что путь относительный)
        System.out.println("directoryPath.getRoot() " + directoryPath.getRoot()); //   /
        System.out.println("------------------------------------------");

        System.out.println("filePath.isAbsolute() " + filePath.isAbsolute());  // false
        System.out.println("directoryPath.isAbsolute() " + directoryPath.isAbsolute());  // true
        System.out.println("------------------------------------------");

        System.out.println("filePath.toAbsolutePath() " + filePath.toAbsolutePath());
        // /Users/aleksandrshabalin/Skillbox/MyLearns_java_basics/test45.txt
        System.out.println("directoryPath.toAbsolutePath() " + directoryPath.toAbsolutePath());
        //  /Users/aleksandrshabalin/Desktop/ForWorkFiles/L
        System.out.println("------------------------------------------");

        // Получив абс путь теперь можем получить парент путь
        System.out.println("filePath.toAbsolutePath.getParent() " + filePath.toAbsolutePath().getParent());
        // /Users/aleksandrshabalin/Skillbox/MyLearns_java_basics
        System.out.println("directoryPath.toAbsolutePath().getRoot " + directoryPath.toAbsolutePath().getRoot());
        //  /
        System.out.println("------------------------------------------");

        //❗️ Теперь рассмотрим два очень важных метода, которые часто встречаются на практике ⬇:

        // 📌 resolve - объединяет два пути в один
        System.out.println("directoryPath.resolve(filePath) " + directoryPath.resolve(filePath));
        // /Users/aleksandrshabalin/Desktop/ForWorkFiles/L/test45.txt <-> Вот был наш directoryPath ->
        // -> /Users/aleksandrshabalin/Desktop/ForWorkFiles/L   и мы присоединили к нему (прибавили) filePath
        // и получили такой путь /Users/aleksandrshabalin/Desktop/ForWorkFiles/L/test45.txt __  как бут-то бы файл
        // test45.txt лежит в папке "L". Естественно у нас пока ни того ни другого нет пока что, просто речь пока
        // идет о путях, а не самих каких-то файлах
        System.out.println("------------------------------------------");

        // 📌 relativize - возвращает относительный путь относительного другого пути
        Path anotherPath = Paths.get("/Users/aleksandrshabalin/" +
                "Desktop/ForWorkFiles/L/Q/J/test20.txt");
        // теперь нам нужно найти относительный путь относительно нашего directoryPath
        System.out.println("directoryPath.relativize(filePath) "
                + directoryPath.relativize(anotherPath)); // Q/J/test20.txt
        // т/е мы ищем относительный путь anotherPath относительно пути directoryPath
        System.out.println("------------------------------------------");


        //❗️ Теперь рассмотрим методы класса Files ❤️

        if (!Files.exists(filePath)){
            Files.createFile(filePath);
        }
        if (!Files.exists(directoryPath)){
            Files.createFile(directoryPath);
        }
        // ⬆ так как файл и путь не существуют - они будут созданы

        // Права на действия с файлами
        System.out.println("Files.isReadable(filePath) " + Files.isReadable(filePath)); // может ли читать файл?
        System.out.println("Files.isWritable(filePath) " + Files.isWritable(filePath)); // может ли записывать в файл?
        System.out.println("Files.isExecutable(filePath) " + Files.isExecutable(filePath)); // может ли запускать файл?
        System.out.println("------------------------------------------");

        // метод: ссылаются ли оба пути на один и тот же файл
        Path filePath2 = Paths.get("/Users/aleksandrshabalin/Skillbox/MyLearns_java_basics/test45.txt");
        System.out.println("Files.isSameFile(filePath, filePath2) "
                + Files.isSameFile(filePath, filePath2));  // true
        System.out.println("------------------------------------------");


        System.out.println("Files.size(filePath) " + Files.size(filePath)); // 45 (byte)
        System.out.println("------------------------------------------");

        // узнать об атрибутах файла
        System.out.println("Files.getAttribute(filePath) "
                + Files.getAttribute(filePath, "creationTime")); // 2021-12-17T10:13:36Z
        System.out.println("Files.getAttribute(filePath) "
                + Files.getAttribute(filePath, "size")); // 45
        System.out.println("------------------------------------------");

        // если нам нужны все атрибуты
        Map<String, Object> attributes = Files.readAttributes(filePath, "*");
        for (Map.Entry<String, Object> entry : attributes.entrySet()){
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
        // возращаются самые важные аттрибуты
        /*lastAccessTime : 2021-12-17T10:45:19Z
        lastModifiedTime : 2021-12-17T10:30:30Z
        size : 45
        creationTime : 2021-12-17T10:13:36Z
        isSymbolicLink : false
        isRegularFile : true
        fileKey : (dev=1000003,ino=3389857)
        isOther : false
        isDirectory : false      */







    }
}
