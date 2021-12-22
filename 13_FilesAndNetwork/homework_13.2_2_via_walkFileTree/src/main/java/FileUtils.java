import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;

public class FileUtils {

    public static void copyFolder(String sourceDirectory, String destinationDirectory) throws IOException {

        Files.walkFileTree(Paths.get(sourceDirectory),
                new MyVisitorFile(Paths.get(sourceDirectory), Paths.get(destinationDirectory)));
        }
    }


