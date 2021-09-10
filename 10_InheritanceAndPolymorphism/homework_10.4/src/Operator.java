public class Operator extends Staffer implements Employee { // Оператор расширяет род. класс штатный
    // сотрудник /Staffer/ выполняя (реализуя) роль сотрудника /Employee/

    // при создании оператора будет срабатывать конструктор по умолчанию и
    // его поля будут не инициализированы, условно говоря будет жить человек
    // обученный на оператора, но который еще не устроился в компанию.
    // Только после найма в компанию его поля будут инициализированы:
    // он будет работать в компании: знать значение поля /company/ и
    // будет знать значения фиксОплаты и своей зарплаты


    @Override
    public String toString() {
        return "Оператор " + "з/п: " + getMonthSalary() + ", компания: " + getCompany() + "\n";
    }

    // оверайдим метод с интерфейса: получим значение зарплаты Net (зарплата к выплате за месяц)
    @Override
    public int getMonthSalary() {  // с помощью метода интерфейса
        setSalary(getFixSalary());  // получаем доступ к ~ /salary/ в классе /Staffer/
        return getSalary();      // и здесь оператор с ссылочной ~ типа /Employee/
        // взаимодейтвует с классом /Company/ как Работник компании, а не как конкретный оператор
        // и Работнику оператору не будут доступны специфичные методы оператора (если бы
        // переопределил методы родителя /Staff/ или создал методы в этом классе,
        // они бы не были доступны /Employee employee/)
    }

    @Override
    public void setCompanyForEmployee(Company company) {
        setCompany(company);
    }

    @Override
    public void setFixSalaryEmployee(int amountFixSalary) {
        setFixSalary(amountFixSalary);
    }
}



