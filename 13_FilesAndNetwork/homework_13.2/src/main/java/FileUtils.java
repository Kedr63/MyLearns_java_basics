import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FileUtils {
    private static Path pathSource;
    private static Path pathDestination;

    public static void copyFolder(String sourceDirectory, String destinationDirectory) throws IOException {
        // TODO: write code copy content of sourceDirectory to destinationDirectory
        pathSource = Paths.get(sourceDirectory);
        pathDestination = Paths.get(destinationDirectory);

        if (!Files.exists(pathDestination)) {
            Files.createDirectory(pathDestination);
        } else System.out.println("Такая директория уже существует");


        File folderSource = new File(sourceDirectory);
        File[] files = folderSource.listFiles();
        for (File file : files){
            if (file.isDirectory()){
                copyFolder(file.getPath(), getNewDestination(file).toString()); // выполним рекурсию для погружения
                // в глубину файлового дерева

            } else if (file.isFile()){
                byte[] bytesOfReadFileForCopy = Files.readAllBytes(Paths.get(file.getPath()));
                Files.write(getNewDestination(file), bytesOfReadFileForCopy);
            }
        }
    }

    // на вход будут идти файлы или директории и возвращать свой новый путь после копирования
    private static Path getNewDestination(File file){
        Path pathCurrentFile = Paths.get(file.getPath());
        Path relativeOfSource = pathSource.relativize(pathCurrentFile); // путь текущего file относительно источника
        return pathDestination.resolve(relativeOfSource); //к пути назначения прибавится
        // относительный путь текущего file
    }
}
