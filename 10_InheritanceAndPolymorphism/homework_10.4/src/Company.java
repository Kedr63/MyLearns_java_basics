import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Company {
    private final int income; // доход компании

    private final List<Employee> employeeList; // список сотрудников

    private static final String wrongInputCountEmployee = "Введеное число не в диапазоне списка сотрудников";

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    // конструктор компании (инициализируется доход и список сотрудников)
    public Company(int income) {
        this.income = income;
        employeeList = new ArrayList<>();
    }

    public double getIncome() {
        return income;
    }

    // Здесь дальше ниже /Employee employee/ может взаимодействовать с классами работников только
    // через методы интерфейса, которые реализованы в этих классах

    // метод найма (одного сотрудника /employee/, назначенная фиксированная оплата)
    protected void hire(Employee employee, int fixSalary) { // при устройстве в компанию:
        employee.setCompanyForEmployee(Company.this);    // сотрудник получит значение в какой компании работает
        employee.setFixSalaryEmployee(fixSalary);     // сотрудник получит значение фиксированной оплаты
        employee.getMonthSalary();                    // сотрудник получит значение зарплаты к выдаче (net)
        employeeList.add(employee);      // сотрудник принят: добавим сотрудника в штат компании (в ее список)
    }

    // метод найма (список сотрудников, назначенная фиксированная оплата)
    protected void hireAll(List<Employee> list, int fixSalary) {
        for (Employee employee : list) {           // по одному сотруднику из списка пригласим в отдел кадров
            employee.setCompanyForEmployee(Company.this); // всем сотрудникам назначат значения: компания,
            employee.setFixSalaryEmployee(fixSalary);     // фикс. оплата
            employee.getMonthSalary();                    // и зарплата
        }
        employeeList.addAll(list); // сотрудники из списка приняты: добавим их в штат компании (в ее список)
    }

    // метод увольнения сотрудников (кол-во работников на увольнение)
    protected void fire(int count) {
        if (validateCountEmployee(count)) {
            System.out.println(wrongInputCountEmployee);
        }
        Random random = new Random();  // допустим, будем увольнять по случайному индексу
        for (int i = 0; i <= count; i++) {
            int indexRandom = random.nextInt(employeeList.size()); // случайный индекс от [0  до размера списка]
            employeeList.remove(indexRandom);
        }
    }

    // метод получения самых низких зарплат
    protected List<Employee> getLowestSalaryStaff(int count) {
        if (validateCountEmployee(count)) {
            System.out.println(wrongInputCountEmployee);
            return null;
        }
        employeeList.sort(new salaryLowComparator());  // для сортировки применим Comparator
        System.out.println("Самые низкие з/п: ");
        return employeeList.subList(0, count);
    }

    // метод получения самых высоких зарплат
    protected List<Employee> getTopSalaryStaff(int count) {
        if (validateCountEmployee(count)) {
            System.out.println(wrongInputCountEmployee);
            return null;
        }
        employeeList.sort(new salaryTopComparator());
        System.out.println("Самые высокие з/п: ");
        return employeeList.subList(0, count);
    }

    private boolean validateCountEmployee(int countEmployee) {
        return countEmployee > employeeList.size() || countEmployee <= 0;
        // если входящее /число работников/ > количества работников в компании или это число отриц.
        // то return true
    }
}
