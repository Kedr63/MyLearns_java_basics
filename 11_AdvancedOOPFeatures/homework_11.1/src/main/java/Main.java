
import java.util.List;

public class Main {

    public static final String STAFF_TXT = "data/staff.txt";

    public static void main(String[] args) {
        List<Employee> staff = Employee.loadStaffFromFile(STAFF_TXT);
        sortBySalaryAndAlphabet(staff);
        System.out.println(staff);
    }

    public static void sortBySalaryAndAlphabet(List<Employee> staff) {
        // отсортируем методом sort: на вход метода подадим /comparator/,
        // который будет представен лямбда выражением (входными параметрами которого
        // будут - сотрудник o1 и сотрудник o2)

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