
import java.util.List;

public class Main {

    public static final String STAFF_TXT = "data/staff.txt";

    public static void main(String[] args) {
        List<Employee> staff = Employee.loadStaffFromFile(STAFF_TXT);
        LRUCache <Employee> cache = new LRUCache(5);
        // Добавим сотрудников в этот кэш
        for (Employee employee : staff){
            cache.addElement(employee);
        }
        cache.getAllElements().forEach(System.out::println); // распечатаются 5 последних сотрудников
        // из списка файла STAFF_TXT

        // можем сделать и так
        System.out.println(cache.getElement(4).getSalary());// можем получить з/п сотрудника под индексом 4

        // Вот можно использовать /generics/
        // Далее пример смотри в классе /Calculator/

    }

    public static void sortBySalaryAndAlphabet(List<Employee> staff) {

        staff.sort((o1, o2) -> {
            if (o1.getSalary() > o2.getSalary()) {
                return 1;
            } else if (o1.getSalary() < o2.getSalary()) {
                return -1;
            }
            // если з/п окажутся равны, то будут сравниваться имена /name/ по алфавиту (а<я)
            return o1.getName().compareTo(o2.getName());
            // при o1>o2 return 1; при o1<o2 return -1; при o1==o2 return 1;
        });
    }
}