import java.util.Collections;
import java.util.Comparator;
import java.util.List;
// ЛВ нужны для более быстрого программирования и написания более понятного и легкого кода

// Синтаксис: /()/ -здесь пишутся параметры, но их может и не быть, далее /->/ и потом /{};/
// в фигурных скобках пишется сам код
// ()->{};
// Это на примере компаратора этот синтаксис
// Компаратор в свою очередь назвали функциональным интерфейсом, функциональным интерфейсом назвали
// все интерфейсы содержащие один абстрактный метод, который можно легко преобразовывать в ЛВ

/* Ссылки к уроку:
* Новшества Java 8 — https://www.youtube.com/watch?v=6bN1HcRhse4
* Лямбда-выражения в Java 8 — https://habr.com/ru/post/224593/
* Java Generics — http://www.quizful.net/post/java-generics-tutorial
* Apache Maven — основы — https://habr.com/ru/post/77382/
* Аннотации в Java: обзор синтаксиса и создание собственных — https://habr.com/ru/post/139736/
* Lombok — https://habr.com/ru/post/345520/   */


public class Main {

    public static final String STAFF_TXT = "data/staff.txt";

    public static void main(String[] args) {
        List<Employee> staff = Employee.loadStaffFromFile(STAFF_TXT);
        System.out.println(staff);

        // Например, у нас есть задача отсортировать работников по зарплате
        // Мы привыкли писать так
        Collections.sort(staff, new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                return o1.getSalary().compareTo(o2.getSalary());
            }
        });
        // и распечатаем сотрудников (отсортируются по возврастанию зарплаты)
        for (Employee employee : staff){
            System.out.println(employee);
        }

        // ЛВ позволяет очень сильно сократит эту конструкцию
        Collections.sort(staff, ((o1, o2) -> o1.getSalary().compareTo(o2.getSalary())));
        // круглые скобки с параметрами (o1, o2) далее -> и код, который эти параметры обрабатывает
        // ((o1, o2) -> o1.getSalary().compareTo(o2.getSalary())) - все это вместо:
        // new Comparator<Employee>() {
        //            @Override
        //            public int compare(Employee o1, Employee o2) {
        //                return o1.getSalary().compareTo(o2.getSalary());
        //            }
        //        }

    }

    public static void sortBySalaryAndAlphabet(List<Employee> staff) {
        //TODO Метод должен отсортировать сотрудников по заработной плате и алфавиту.
    }
}
