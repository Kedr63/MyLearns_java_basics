package Comparator_от_Заур;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Интерфейс_Comparator_Заур {
    /*Comparable и Comparator*/
    // Эти интерфейсы нужны не просто для сравнения двух объектов, что они равны или нет, а эти интерфейсы
    // помогают нам узнавать какой объект больше, какой меньше, чтобы мы могли сортировать эти объекты,
    // например, в какой-то коллекции или в каком-то массиве. Сейчас разберем /Comparator/

    /*  Comparator  */
    // Мы можем создать класс /idComparator/, т.е. сравнитель по id так скажем, который имплементирует
    // /Comparator<Employee>/ - это тоже дженерикс, что он будет сравнивать? -> Он будет сравнивать наших
    // работников, поэтому -> <Employee>. Здесь нужно оверайдить один метод /compare(Employee o1, Employee o2)/.
    // Этот метод имеет ту же логику что и /compareTo/, просто /compareTo/ сравнивал атрибуты текущего объекта
    // с объектом в параметрах, а здесь текущего объекта не будет, здесь мы сравниваем двух работников emp1 и
    // emp2.
    // И теперь когда мы пытаемся отсортировать наш /list/ мы можем в метод /sort/ в качестве второго параметра
    // передать объект /idComparator/, т.е. мы говорим -> сортируй, используя этот /Comparator/ и теперь
    // сортировка будет проходить по /id/.
    // Создадим еще несколько классов, которые будут /Comparator/: /class nameComparator/ и см. class Test2,
    // в нем сортируем по имени, /class salaryComparator/ и см. class Test3, в нем сортируем по зарплате (от
    // наименьшей к большей)
    // Теперь совместим Comparator и Comparable (см. пакет "Comparator_от_Заур_продолжение")
}

class idComparator implements Comparator<Employee> {

    @Override
    public int compare(Employee emp1, Employee emp2) {
        if (emp1.id == emp2.id) {
            return 0;
        } else if (emp1.id < emp2.id) {
            return -1;
        } else {
            return 1;
        }
    }
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
        Collections.sort(list, new idComparator());
        System.out.println("After sorting \n" + list);
    }
}

// Создадим класс Работник
class Employee {
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
}

class Test2 {       // (сортируем по имени)
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

class Test3 {       // (сортируем по зарплате)
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

