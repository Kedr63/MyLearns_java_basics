import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class Main {

    private static final String STAFF_TXT = "data/staff.txt";

    public static void main(String[] args) {
        List<Employee> staff = Employee.loadStaffFromFile(STAFF_TXT);
        Employee employeeMaxSalary = findEmployeeWithHighestSalary(staff, 2017);
        System.out.println(employeeMaxSalary);

        Employee emp = new Employee("Bob", 75000, new Date());
        System.out.println(emp);

    }

    public static Employee findEmployeeWithHighestSalary(List<Employee> staff, int year) {
        //TODO Метод должен вернуть сотрудника с максимальной зарплатой среди тех,
        // кто пришёл в году, указанном в переменной year

        // первый вариант (через String)
       /*
       String yearOfEmployment = Integer.toString(year);
       return staff.stream()
                .filter(e -> e.getWorkStart().toString().contains(yearOfEmployment))
                .max((Comparator.comparingInt(Employee::getSalary))).get();             */



        // второй вариант (с помощью меток времени)
        LocalDateTime yearOfEmployment = LocalDateTime.of(year, 1, 1, 0, 0);
        return staff.stream()
                .filter(e -> e.getWorkStart()
                        .getTime() / 1000 > yearOfEmployment.toEpochSecond(ZoneOffset.ofHours(3)) && e.getWorkStart()
                        .getTime() / 1000 < yearOfEmployment.plusYears(1)   // делю на 1000 чтобы получить секунды
                        .toEpochSecond(ZoneOffset.ofHours(3)))
                .max(Comparator.comparingInt(Employee::getSalary))
                .get();
    }
}