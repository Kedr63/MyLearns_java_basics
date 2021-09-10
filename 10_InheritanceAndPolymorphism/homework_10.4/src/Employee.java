public interface Employee { // Способный быть работником (выполнять роль работника)

    int getMonthSalary();
    void setCompanyForEmployee(Company company);
    void setFixSalaryEmployee(int amountFixSalary);

    // Через эти методы различные сотрудники (операторы, менеджеры и тд) с типом /Employee/
    // будут взаимодействовать с классом /Company/.

    // Класс /Company/ сможет, в одном методе, принимая на вход различных сотрудников
    // (операторы, менеджеры и тд) с типом /Employee/ взаимодействовать с ними.

    // Менеджер /new Manager()/ выполняет роль Работника /Employee employee/,
    // т.е. Employee employee = new Manager()


}
