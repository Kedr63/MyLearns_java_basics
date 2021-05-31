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

            String text = input.trim();      //обрежем введеную строку от наружных пробелов (если их случайно ввели)
            int spaceCount = 0;             //объявим переменную для подсчета пробелов
            int hyphenCount = 0;            //объявим переменную для подсчета дефисов
            int allowCharCount = 0;         //объявим переменную для подсчета разрешенных символов

            //с помощью цикла переберем каждый символ строки и посчитаем нужные нам символы (пробелы, дефисы,
            // и в третьем условии - количество разрешенных символов
            char ch;
            for (int i = 0; i < text.length(); i++) {
                ch = text.charAt(i);
                if (ch == ' ') {
                    spaceCount++;
                }
                if (ch == '-') {
                    hyphenCount++;
                }
                if (ch >= (int) 'А' && ch <= (int) 'я' || ch == (int) ' ' || ch == (int) '-') {
                    allowCharCount++;
                }
            }

            if (spaceCount == 2 && hyphenCount <= 1 && allowCharCount == text.length()) {
                String family = text.substring(0, text.indexOf(' '));
                String name = text.substring(text.indexOf(' '), text.lastIndexOf(' ')).trim();
                String otchestvo = text.substring(text.lastIndexOf(' ')).trim();

                System.out.println("Фамилия: " + family);
                System.out.println("Имя: " + name);
                System.out.println("Отчество: " + otchestvo);
            } else
                System.out.println("Введенная строка не является ФИО");

        }
    }
}

