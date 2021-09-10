import java.util.Random;

// Создадим общий класс для всех сотрудников, которые унаследуют его состояние

public class Staffer { // Штатный сотрудник. Расширим этот класс сотрудниками компании

    private int fixSalary; // фиксированная часть зарплаты
    private int salary;    // зарплата Net (зарплата к выплате)
    private Company company; // компания в которой работает сотрудник, после инициализации /company/,
    // эта ссылочная переменная свяжет наслед-ов этого класса с классом Company через
    // интерфейс Employee


    // Сеттеры и геттеры для полей класса
    public int getFixSalary() {
        return fixSalary;
    }

    public void setFixSalary(int fixSalary) {
        this.fixSalary = fixSalary;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
