import java.util.Scanner;

public class Main {
    //создадим наш emailList, где будут храниться уникальные множества email
    private static final EmailList emailList = new EmailList();

    /* TODO:
        Пример вывода списка Email, после ввода команды LIST в консоль:
        test@test.com
        hello@mail.ru
        - каждый адрес с новой строки
        - список должен быть отсортирован по алфавиту
        - email в разных регистрах считается одинаковыми
           hello@skillbox.ru == HeLLO@SKILLbox.RU
        - вывод на печать должен быть в нижнем регистре
           hello@skillbox.ru
        Пример вывода сообщения об ошибке при неверном формате Email:
        "Неверный формат email"
    */

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.nextLine();
            if (input.equals("0")) {
                break;
            }

            //TODO: write code here
            if (input.contains("ADD")) {
                String cutToFormatEmail = input.replaceAll("ADD(\\s+)?", "").trim(); //обрезаем строку до формата Email
                emailList.add(cutToFormatEmail);
            }

            if (input.contains("LIST")) {
                for (String email : emailList.getSortedEmails()) {
                    System.out.println(email);
                }
            }
        }
    }
}


