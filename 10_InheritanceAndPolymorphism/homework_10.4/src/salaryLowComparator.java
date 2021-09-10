import java.util.Comparator;

// создадим класс реализующий интерфейс Comparator для сортировки зарплат
public class salaryLowComparator implements Comparator<Employee> {
    @Override
    public int compare(Employee emp1, Employee emp2) {
        return emp1.getMonthSalary() - emp2.getMonthSalary(); // порядок будет от меньшего к большему
    }
}
