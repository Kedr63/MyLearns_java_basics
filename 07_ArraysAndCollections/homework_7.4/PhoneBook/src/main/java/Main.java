import java.util.Scanner;

public class Main {
    private static final PhoneBook phoneBook = new PhoneBook();

    public static void main(String[] args) {
        String searchName = "[a-zA-Zа-яА-Я]+";       // regular для поиска при вводе "имени" (name)
        String searchPhoneNumber = "[7|8]\\d{10}";   // regular для поиска при вводе "телефона" (phone)


        while (true) {
            System.out.println("Введите номер, имя или команду:");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();

            if (input.equals("0")) {
                break;
            }

            if (input.equals("LIST")) {
                System.out.println(phoneBook.getAllContacts());

            } else if (input.matches(searchName)) {
                validateInputName(input);

            } else if (input.matches(searchPhoneNumber)) {
                validateInputPhone(input);

            } else System.out.println(PhoneBook.WRONG_FORMAT);
        }
    }



    public static void validateInputName(String name) {
        if (!phoneBook.getAllContacts().toString().contains(name)) {
            System.out.println("Такого имени в телефонной книге нет.\n" +
                    "Введите номер для абонента: " + name);
            String phone = inputPhoneNumberOrName();
            phoneBook.addContact(phone, name);

        } else if (phoneBook.getAllContacts().toString().contains(name)) {
            System.out.println(phoneBook.getPhonesByName(name));
        } else System.out.println(PhoneBook.WRONG_FORMAT);
    }


    public static void validateInputPhone(String phone) {
        if (!phoneBook.getAllContacts().toString().contains(phone)) {
            System.out.println("Такого номера в телефонной книге нет.\n" +
                    "Введите имя для номера: " + phone);
            String name = inputPhoneNumberOrName();
            phoneBook.addContact(phone, name);

        } else if (phoneBook.getAllContacts().toString().contains(phone)) {
            System.out.println(phoneBook.getNameByPhone(phone));
        } else System.out.println(PhoneBook.WRONG_FORMAT);
    }


    public static String inputPhoneNumberOrName() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

}


