import java.util.List;
import java.util.stream.Stream;

public class Main {
    public static final String STAFF_TXT = "data/staff.txt";

    // ❤️ Метод forEach ___________________________

    // В этом уроке изучим /stream API/, специальный инструмент для работы с коллекциями, который
    // позволяет что-то делать сразу со всеми элементами коллекции, без создания циклов
    // Зачем они нужны?   Они нужны для того же, для чего и ЛВ и указатели на методы, которые посмотрим
    // более подробно. Т.е. они нужны для того чтоб писать меньше кода, код становиться от этоого менее
    // вложенным и в ряде случаев гораздо более понятным.

    // Начнем с метода forEach, ⬇ который позволяет обрабатывать каждый отдельный элемент нашей коллекции

    public static void main(String[] args) {
        List<Employee> staff = Employee.loadStaffFromFile(STAFF_TXT);

        /*   for (Employee employee : staff){
               System.out.println(employee);
        }                                              */
        // Как можно переписать этот код с использование метода /forEach/ и ЛВ? ↙
        staff.forEach(employee -> System.out.println(employee));

        // мы можем все это очень сильно сократить до указателя на этот метод
        staff.forEach(System.out::println);

        // Ещё раз, что здесь происходит? 📍 ➔
        // ➔  метод /forEach/ получает каждый элемент нашего списка и
        // применяет к нему метод /System.out.println/, 📍 т.е. вызывает метод /System.out.println/ и
        // передает ему на вход -> каждый из этих объектов.

        // Вот так работают указатели на методы и метод /forEach/

        // 📍 Сейчас сделаем что-нибудь более сложное, возьмем, и например, прибавим к каждому
        // нашему сотруднику з/п, например, по 10_000 руб:
        // сначала распечатаем старый список сотрудников
        System.out.println("Old salaries: ");
        staff.forEach(System.out::println);

        // здесь код, который позволит увеличить зарплаты нашим сотрудникам (длинный вариант)
        staff.forEach(employee -> {
            int salary = employee.getSalary();
            employee.setSalary(salary + 10000);
        });

        // можем сократить ⬆  данное выражение (короткий вариант)
        int salaryIncrease = 10000;
        staff.forEach(e -> e.setSalary(e.getSalary() + salaryIncrease));

        // и новый список, чтоб убедиться что зарплаты увеличились
        System.out.println("\nNew salaries: ");
        staff.forEach(System.out::println);

        // Примечание: так как метод forEach применили два раза (длинный и короткий вариант),
        // то в консоле зарплата новая увеличится на 20000 руб

        Stream<>



    }
}