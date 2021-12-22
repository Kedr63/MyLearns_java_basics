import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Input path of directory for copied: ");
        String source = scanner.nextLine();

        System.out.println("Path where copied directory: ");
        String destination = scanner.nextLine();

        FileUtils.copyFolder(source, destination);
    }
}
