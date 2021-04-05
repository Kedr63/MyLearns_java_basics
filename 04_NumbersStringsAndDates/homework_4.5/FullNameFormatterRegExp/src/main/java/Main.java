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
            //При невалидном ФИО вывести в консоль: Введенная строка не является ФИО
            String search = "([а-яА-Я]+-?[а-яА-Я]+)\\s([а-яА-Я]+)\\s([а-яА-Я]+)";
            if (input.matches(search)) {
                System.out.println(input.replaceAll(search,
                        "Фамилия\\: $1\nИмя\\: $2\nОтчество\\: $3"));
            } else System.out.println("Введенная строка не является ФИО");
        }
    }
}

