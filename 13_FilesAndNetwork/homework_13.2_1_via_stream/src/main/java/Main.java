import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Input path of directory for copied: ");
        String source = scanner.nextLine();

        System.out.println("Path where copied directory: ");
        String destination = scanner.nextLine();

        try {
            FileUtils.copyFolder(source, destination);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
