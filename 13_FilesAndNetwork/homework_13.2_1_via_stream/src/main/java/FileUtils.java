import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUtils {
    private static Path pathSource;
    private static Path pathDestination;

    public static void copyFolder(String sourceDirectory, String destinationDirectory) throws IOException {
        // TODO: write code copy content of sourceDirectory to destinationDirectory
        pathSource = Paths.get(sourceDirectory);
        pathDestination = Paths.get(destinationDirectory);

      /** Сначала прогуляемся по директориям FileTree (начиная от pathSource) и создадим их пустые копии
        * в директории назначения pathDestination */
        Files.walk(pathSource, Integer.MAX_VALUE)  // 2-й параметр глубина погружения в файловое дерево
                .filter(f -> f.toFile().isDirectory())
                .forEach(s -> {
            try {
                Files.createDirectory(getNewDestination(s.toFile()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        /** Теперь прогуляемся по файлам FileTree (начиная от pathSource) и, читая байты каждого файла, будем
         * сразу записывать эти байты в файл, располагая этот файл в директории назначения pathDestination */
        Files.walk(pathSource, Integer.MAX_VALUE)
                .filter(f -> f.toFile().isFile())
                .forEach(s -> {
            try {
                byte[] bytesOfReadFileForCopy = Files.readAllBytes(Paths.get(s.toFile().getPath()));
                Files.write(getNewDestination(s.toFile()), bytesOfReadFileForCopy);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        });
    }

    private static Path getNewDestination(File file) {
        Path pathCurrentFile = Paths.get(file.getPath());
        Path relativeOfSource = pathSource.relativize(pathCurrentFile);
        return pathDestination.resolve(relativeOfSource);
    }
}
