import java.util.Comparator;
import java.util.List;

public class Main {
    public static final String STAFF_TXT = "data/staff.txt";

    // ❤️ Sorted, max, min    ___________________________

    // Поговорим о двух часто используемых методах стримов - это получение минимального, максимального
    // значения  и сортировки стрим:

    public static void main(String[] args) {
        List<Employee> staff = Employee.loadStaffFromFile(STAFF_TXT);

        // допустим хотим отсортирвать всех сотрудников по з/п
        staff.stream()
             .sorted(Comparator.comparing(Employee::getSalary))
             .forEach(System.out::println);

        // в консоли получим стрим отсортированный по з/п (от меньшей к большей)

        System.out.println("______________________________________");

        // аналогичным образом мы можем получить макс. и миним. значение
        /*  staff.stream().max(Comparator.comparing(Employee::getSalary));  */
        // вот получили макс. значение - что с ним можно сделать? ➔ вот этот метод /max/ возвращает
        // объект /optional/ - создадим его ниже чтоб было очевидно ⬇. Это специальный объект, который
        // позволяет возвращать или не возвращать значения. Вместо того чтоб возвращать или не возвращать null,
        // они возвращают /optional/,
        // у которого есть всякие методы, например, /isPresent/ - /существует ли данный объект/
        /*   Optional<Employee> optional =  staff.stream().max(Comparator.comparing(Employee::getSalary));  */
        /*   optional.isPresent();  */ // существует ли данный объект/
        /*   optional.isEmpty();  */  // является ли он пустым

        // Преобразовать этот объект в сотрудника /Employee/ легко, достаточно вызвать метод /get/ у него
        /*  Employee employee = optional.get();  */
        // но можно сделать это безопасно и распечатать нашего сотрудника безопасно
        staff.stream()
             .max(Comparator.comparing(Employee::getSalary))
             .ifPresent(System.out::println);  // если он существует ➔ мы его напечатаем

       /*   консоль:
        Анна Сетяева - 140000 - 10.05.2012   */


    }
}