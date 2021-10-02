import java.io.File;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {

    public static final String STAFF_TXT = "data/staff.txt";

    public static void main(String[] args) {
        List<Employee> staff = Employee.loadStaffFromFile(STAFF_TXT);
        sortBySalaryAndAlphabet(staff);
        System.out.println(staff);

    }

    public static void sortBySalaryAndAlphabet(List<Employee> staff) {
        //TODO Метод должен отсортировать сотрудников по заработной плате и алфавиту.
        Collections.sort(staff, (o1, o2) -> {
            if (o1.getSalary()>o2.getSalary()){
                return 1;
            } else if (o1.getSalary() < o2.getSalary()) {
                return -1;
            }
            return o1.getName().compareTo(o2.getName());
        });

    }
}