import java.util.Set;
import java.util.TreeSet;

public class EmailList {
    private final Set<String> stringSet = new TreeSet<>();     //создадим набор (множество) для хранения email
    public static final String WRONG_EMAIL_ANSWER = "Неверный формат email";


    public void add(String email) {
        // TODO: валидный формат email добавляется
        if (validateFormatEmail(email)) {             // если формат email валидный, то добавляем в набор,
            stringSet.add(email.toLowerCase());       // переводя символы в нижний регистр, чтоб не дублировать email одинаковые по символам
        } else System.out.println(WRONG_EMAIL_ANSWER);
    }

    public Set<String> getSortedEmails() {
        // TODO: возвращается список электронных адресов в алфавитном порядке
        return stringSet;
    }

    // создадим метод для проверки формата email
    public boolean validateFormatEmail(String string) {
        return string.matches("[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+\\.[a-z]+"); //формат email должен соответствовать этому regular
    }
}
