import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        try {
            System.out.println("Введите путь до папки:");

            Scanner scanner = new Scanner(System.in);
            String path = scanner.nextLine();

            long size = FileUtils.calculateFolderSize(path);

            System.out.printf("Размер папки %s составляет %s", path, FileUtils.getHumanReadableSize(size));

        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }
    }
}


