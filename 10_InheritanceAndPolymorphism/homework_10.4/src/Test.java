import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        Company company2 = new Company(9_000_000);
        System.out.println(company2.getIncome());

        // Создадим список операторов (еще не трудоустроеных)
        List<Employee> listOperator = new ArrayList<>();
        for (int i = 0; i < 180; i++) {
            Employee operator1 = new Operator();
            listOperator.add(operator1);
        }


        // Создадим список менеджеров (еще не трудоустроеных)
        List<Employee> listManager = new ArrayList<>();
        for (int i = 0; i < 80; i++) {
            Employee manager1 = new Manager();
            listManager.add(manager1);
        }


        // Создадим список топМенеджеров (еще не трудоустроеных)
        List<Employee> listTopManager = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Employee topManager = new TopManager();
            listTopManager.add(topManager);
        }


        // наймем в компанию по одному сотруднику
        Employee operator = new Operator();
        company2.hire(operator, 42_000);

        Employee operator1 = new Operator();
        company2.hire(operator1, 40_000);

        Employee manager = new Manager();
        company2.hire(manager, 72_000);

        Employee topManager = new TopManager();
        company2.hire(topManager, 79_000);

        // наймем в эту же компанию 180 операторов, 80 менеджеров, 10 топменеджеров
        company2.hireAll(listOperator, 50_000);
        company2.hireAll(listManager, 70_000);
        company2.hireAll(listTopManager, 80_000);
        System.out.println("list Employee: \n" + company2.getEmployeeList());

        System.out.println(company2.getTopSalaryStaff(15));
        System.out.println(company2.getLowestSalaryStaff(30));

        System.out.println();
        System.out.println("Уволим часть сотрудников");
        company2.fire(142);
        System.out.println(company2.getTopSalaryStaff(15));
        System.out.println(company2.getLowestSalaryStaff(30));

        // создадим компанию
        Company company3 = new Company(15_000_000);
        System.out.println(company3.getIncome());

        List<Employee> listOperator1 = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            Employee operator2 = new Operator();
            listOperator1.add(operator2);
        }


        List<Employee> listManager1 = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Employee manager2 = new Manager();
            listManager1.add(manager2);
        }

        company3.hireAll(listOperator1, 30_000);
        company3.hireAll(listManager1, 40_000);

        System.out.println(company3.getTopSalaryStaff(20));
        System.out.println(company3.getLowestSalaryStaff(20));
        System.out.println("Уволим часть сотрудников");
        company3.fire(8);
        System.out.println(company3.getTopSalaryStaff(10));
        System.out.println(company3.getLowestSalaryStaff(10));


        System.out.println("Данные о компаниях");
        System.out.println(company2+ " доход: "+ company2.getIncome() +", "+ company2.getEmployeeList().size() + " сотрудников");
        System.out.println(company3+ " доход: "+ company3.getIncome() +", "+ company3.getEmployeeList().size() + " сотрудников");

    }
}

