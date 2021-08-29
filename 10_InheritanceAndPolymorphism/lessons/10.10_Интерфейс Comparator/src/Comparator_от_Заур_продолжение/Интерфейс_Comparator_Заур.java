package Comparator_от_Заур_продолжение;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Интерфейс_Comparator_Заур {

    // Теперь совместим Comparator и Comparable, уберем /class idComparator/, у нас есть /class nameComparator/
    // и /class salaryComparator/, и теперь /class Employee implements Comparable<Employee>/. Сделали так как
    // обычно все делают, как считается правильно, т.е. наиболее эффективно, /class Employee/ имплементирует
    // Comparable по /id/, если это сравнение нужно чаще всего, т.е. мы считаем что естественная сортировка
    // должна быть по  /id/ и создали пару классов которые имплементят Comparator по имени и по зарплате,
    // т.е. если у нас есть имплементация по Comparable мы в /sort()/ второй параметр можем не использовать,
    // будет идти сортировка по /id/, но если в какой-то момент нам понадобилось отсортировать по зарплате,
    // (мы же не можем постоянно менять метод /compareTo/ в классе /Работник/, а в некоторых случаях мы
    // вообще не можем это делать), то мы просто в /sort()/ вставляем второй параметр /new salaryComparator()/,
    // см. /class Test3/ и правила сортировки /new salaryComparator()/-а перезаписывают (перечеркивают)
    // правила сортировки по /id/ и запустив /class Test3/ увидим что сортируется по зарплате, но как только
    // уберем второй параметр - все, снова сортировка будет по /id/ (как в Test1).
    // Когда нам не обойтись без Comparator-а?
    // У нас есть такой замечательный класс как String, он имплементирует интерфейс Comparable,
    // в нем написано как нужно сортировать String-и, т.е. начинается String на /a/--> он считается
    // меньше чем String, который начинается на /z/ и так далее и тому подобное, если вдруг захотим
    // отсортировать String наоборот (мы же не можем у String перезаписать метод /compareTo/), тогда
    // у нас нет иного выхода кроме как написать свой Comparator и при сортинге допустим Arraylist-а
    // использовать этот Comparator как второй параметр. Здесь уже вариантов много: или хотим String
    // отсортировать к примеру по количеству букв, по количеству слов в самом String-е  - тогда
    // должны использовать Comparator
    // Можно отметить, что если хотим использовать Comparator один раз, то можно не создавать класс
    // отдельный, можно использовать анонимный класс или использовать лямда-выражения.
    //     Итог: Интерфейс Comparator используется для сравнения объектов, используя НЕ естесственный
    // порядок, который (естесственный) обычно прописываем в методе /compareTo/. И метод
    // /int compare(Employee o1, Employee o2)/, который нужно оверайдить если мы имплементируем Comparator.
    //   НУЖНО ЗАПОМНИТЬ ТАКОЕ ПРАВИЛО: множество классов для сравнения могут иметь естесственный порядок,
    // т.е. какой-то у них элемент, по которому, если мы хотим сравнивать, например /id/, как в нашем
    // случае, если мы думаем что по /id/ нужно сравнивать наш объект, используйте Comparable и при
    // имплементации Comparable в методе /compareTo/ используйте логику с /id/: -->
    /*  public int compareTo(Employee anotherEmp) {
        if (this.id == anotherEmp.id) { // классический способ написания метода
            return 0;
        } else if (this.id < anotherEmp.id) {
            return -1;
        } else {
            return 1;
        }
    } */
    // по какому-то другому принципу хотите отсортировать - используйте Comparator, но если у нас
    // класс какой-то написан и мы пока не видим никакой логики по которой можно сортировать, тогда не
    // имплементируйте Comparable, когда нужно будет сортировать нам и мы уже будет знать по
    // какой логике нужно сортировать, тогда создадим Comparator-ы и объект Comparator-а будем
    // использовать в качестве второго параметра у метода /sort/
}

class nameComparator implements Comparator<Employee>{

    @Override
    public int compare(Employee emp1, Employee emp2) {
        return emp1.name.compareTo(emp2.name); // здесь /compareTo/ - это метод String-a
    }
}

class salaryComparator implements Comparator<Employee>{

    @Override
    public int compare(Employee emp1, Employee emp2) {
        return emp1.salary - emp2.salary;
    }
}

// Создадим класс Работник
class Employee implements Comparable<Employee>{
    int id;
    String name;
    String surname;
    int salary;

    public Employee(int id, String name, String surname, int salary) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" + "id=" + id + ", name='" + name + '\'' + ", surname='" + surname + '\'' + ", salary=" + salary + '}';
    }

   @Override
    public int compareTo(Employee anotherEmp) {
        if (this.id == anotherEmp.id) { // классический способ написания метода
            return 0;
        } else if (this.id < anotherEmp.id) {
            return -1;
        } else {
            return 1;
        }
    }
}

class Test1 { // Для класса Работник (здесь сравниваем по /id/)
    public static void main(String[] args) {
        List<Employee> list = new ArrayList<>();
        Employee emp1 = new Employee(100, "Zaur", "Tregulov", 12345);
        Employee emp2 = new Employee(15, "Ivan", "Petrov", 6543);
        Employee emp3 = new Employee(123, "Ivan", "Sidorov", 8542);
        list.add(emp1);
        list.add(emp2);
        list.add(emp3);
        System.out.println("Pered sortirovkoy \n" + list);
        Collections.sort(list);
        System.out.println("After sorting \n" + list);
    }
}

class Test2 {       // (сортируем по имени используя объект Comparator)
    public static void main(String[] args) {
        List<Employee> list = new ArrayList<>();
        Employee emp1 = new Employee(100, "Zaur", "Tregulov", 12345);
        Employee emp2 = new Employee(15, "Ivan", "Petrov", 6543);
        Employee emp3 = new Employee(123, "Ivan", "Sidorov", 8542);
        list.add(emp1);
        list.add(emp2);
        list.add(emp3);
        System.out.println("Pered sortirovkoy \n" + list);
        Collections.sort(list, new nameComparator());
        System.out.println("After sorting \n" + list);
    }
}

class Test3 {       // (сортируем по зарплате используя объект Comparator)
    public static void main(String[] args) {
        List<Employee> list = new ArrayList<>();
        Employee emp1 = new Employee(100, "Zaur", "Tregulov", 12345);
        Employee emp2 = new Employee(15, "Ivan", "Petrov", 6543);
        Employee emp3 = new Employee(123, "Ivan", "Sidorov", 8542);
        list.add(emp1);
        list.add(emp2);
        list.add(emp3);
        System.out.println("Pered sortirovkoy \n" + list);
        Collections.sort(list, new salaryComparator());
        System.out.println("After sorting \n" + list);
    }
}

