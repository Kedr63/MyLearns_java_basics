import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {

    // ❤️ Указатели (ссылки) на методы

    public static final String STAFF_TXT = "data/staff.txt";

    public static void main(String[] args) {
        List<Employee> staff = Employee.loadStaffFromFile(STAFF_TXT);

        Collections.sort(staff, Comparator.comparing(Employee::getSalary));
        // когда на входе написали вторым параметром ЛВ вместо /comparator/,
        // среда предложила заменить на /Comparator.comparing(Employee::getSalary)/
        // Что мы делаем?
        // Мы вызываем метод /comparing/ у /Comparator/ и на вход передаем указатель на
        // метод /getSalary/ класса /Employee/, указатель (ссылка) передается через /::/

        System.out.println(staff);

    }
    /* В Java ссылки на методы - это сокращенные лямбда-выражения:

    Всего существует 3 конструкции ссылок на методы:

    object::instanceMethod - ссылается на объектный метод предложенного объекта
    Class::staticMethod - ссылается на статический метод класса
    Class::instanceMethod - ссылается на объектный метод предложенного объекта. Действует, так же как
     в п.1, только с именем класса
    Примеры реализации этих конструкций и их лямбда-аналоги ниже:

    System.out::println   равно    x -> System.out.println(x)
    Math::max             равно    (x,y) -> Math.max(x,y)
    String::length        равно    x -> x.length()      */
}