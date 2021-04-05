import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("0")) {
                break;
            }
            //TODO:напишите ваш код тут, результат вывести в консоль.
            String number = input.replaceAll("[^0-9]", "");
            if (number.matches("[7|8]\\d{10}")) {
                System.out.println(number.replaceAll("[7|8](\\d{10})", "7$1"));
                break;
            }
            if (number.matches("\\d{10}")) {
                System.out.println(number.replaceAll("(\\d+)", "7$1"));
                break;
            } else
                System.out.println("Неверный формат номера");

        }
    }

}
