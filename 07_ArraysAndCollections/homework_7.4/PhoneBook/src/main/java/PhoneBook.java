import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class PhoneBook {
    // Создадим коллекцию Map, где ключ К -(имя), V значение - набор Set номеров
    private final Map<String, Set<String>> map = new TreeMap<>();
    public static final String WRONG_FORMAT = "Неверный формат ввода";
    public static final String CONTACT_SAVED = "Контакт сохранен!";


    public void addContact(String phone, String name) {
        // TODO: проверьте корректность формата имени и телефона
        // TODO: если такой номер уже есть в списке, то перезаписать имя абонента

        if (!map.containsKey(name) && !map.toString().contains(phone)) {
            checkSaveContact(name, phone);

        } else if (!map.containsKey(name) && map.toString().contains(phone)) {
            deletePhoneFromContact(phone);
            checkSaveContact(name, phone);

        } else if (map.containsKey(name) && !map.toString().contains(phone)) {
            checkSaveContactWithExistName(name, phone);
        }
    }

    public String getNameByPhone(String phone) {
        // TODO: формат одного контакта "Имя - Телефон"
        // TODO: если контакт не найдены - вернуть пустую строку
        String searchNamePhone = "";
        for (Map.Entry<String, Set<String>> entry : map.entrySet()) {
            if (entry.getValue().contains(phone)) {
                searchNamePhone = entry.getKey() + " - " + entry.getValue().toString().
                        replaceAll("[^0-9,\\s+]", "");
            }
        }
        return searchNamePhone;
    }

    public Set<String> getPhonesByName(String name) {
        // TODO: формат одного контакта "Имя - Телефон"
        // TODO: если контакт не найден - вернуть пустой TreeSet
        Set<String> phonesByName = new TreeSet<>();
        Set<String> set = map.get(name);
        String s = set.toString().replaceAll("[^0-9,\\s+]", "");
        phonesByName.add(name + " - " + s);
        return phonesByName;
    }

    public Set<String> getAllContacts() {
        // TODO: формат одного контакта "Имя - Телефон"
        // TODO: если контактов нет в телефонной книге - вернуть пустой TreeSet
        Set<String> allContacts = new TreeSet<>();

        for (String name : map.keySet()) {
            allContacts.add(name + " - " + map.get(name).toString().replaceAll("[^0-9,\\s+]", ""));
        }
        return allContacts;
    }


    public void checkSaveContact(String name, String phone) {
        if (checkFormatNamePhone(name, phone)) {
            Set<String> temp = new TreeSet<>();
            temp.add(phone);
            map.put(name, temp);
            System.out.println(CONTACT_SAVED);
        } else System.out.println(WRONG_FORMAT);
    }

    public void checkSaveContactWithExistName(String name, String phone) {
        if (checkFormatNamePhone(name, phone)) {
            Set<String> temp = map.get(name);
            temp.add(phone);
            map.put(name, temp);
            System.out.println(CONTACT_SAVED);
        } else System.out.println(WRONG_FORMAT);
    }

    public boolean checkFormatNamePhone(String name, String phone) {
        String formatPhone = "[7|8]\\d{10}";
        String formatName = "[a-zA-Zа-яА-Я]+";
        return phone.matches(formatPhone) && name.matches(formatName);
    }

    public void deletePhoneFromContact(String phone) {
        for (Map.Entry<String, Set<String>> entry : map.entrySet()) {
            if (entry.toString().contains(phone)) {
                Set<String> setPhone = entry.getValue();
                setPhone.remove(phone);          // удалим phone из set V-значений номеров
                if (setPhone.isEmpty()) {        // если номер был у имени-К ключа один,
                    map.remove(entry.getKey());  // то удалится имя -ключ К
                }
            }
        }
    }
}