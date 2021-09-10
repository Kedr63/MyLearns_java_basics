import java.util.Comparator;

public class salaryTopComparator implements Comparator<Employee> {
    @Override
    public int compare(Employee emp1, Employee emp2) {
        return emp2.getMonthSalary() - emp1.getMonthSalary(); // порядок будет от большего к меньшему
    }
}
