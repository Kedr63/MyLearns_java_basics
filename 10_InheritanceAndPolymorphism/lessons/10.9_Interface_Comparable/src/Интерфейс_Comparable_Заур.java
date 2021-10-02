import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Интерфейс_Comparable_Заур {
    /* ♦️ Comparable и Comparator*/
    // Эти интерфейсы нужны не просто для сравнения двух объектов, что они равны или нет, а эти интерфейсы
    // помогают нам узнавать какой объект больше, какой меньше, чтобы мы могли сортировать эти объекты,
    // например, в какой-то коллекции или в каком-то массиве. Сейчас разберем /Comparable/

    /*  Comparable  */
    public static void main(String[] args) {
        // Создадим ArrayList поместим туда несколько имен и посмотрим как происходит сортировка
        List<String> list = new ArrayList<>();
        list.add("Zaur");
        list.add("Ivan");
        list.add("Maria");
        System.out.println("Pered sortirovkoy");
        System.out.println(list);
        // Попробуем сначала так, сортировка произойдет по естесственному порядку, для String это
        // лексиграфический порядок, если быстро отвечать - сравнение которое работает в словарях:
        // сначала идут слова на /а/- это самые маленькие слова считаются с лексиграфической точки зрения,
        // слова на /z/ - самые большие, они идут после,
        // т.е. начиная от меньшего к большему произойдет сортировка
        Collections.sort(list);
        System.out.println("Posle sortirovki");
        System.out.println(list);

        /*  консоль:
        Pered sortirovkoy
        [Zaur, Ivan, Maria]
        Posle sortirovki
        [Ivan, Maria, Zaur]     */
        // Здесь никаких сюрпризов нет, так как это /String/ 📍 и здесь естесственный порядок очевиден,
        // также очевиден
        // естесственный порядок при работе допустим с /Integer/
    }
}
//____________________________________________________________________
// Создадим класс Работник (здесь сравниваем по /id/)
class Employee implements Comparable<Employee> {
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
        return "Employee{" + "id=" + id + ", name='" + name + '\'' + "," +
                " surname='" + surname + '\'' + ", salary=" + salary + '}';
    }

    // реализуем метод /compareTo/, после того как добавили implements Comparable<Employee>
    @Override
    public int compareTo(Employee anotherEmp) {
        if (this.id == anotherEmp.id) { // классический способ написания метода
            return 0;
        } else if (this.id < anotherEmp.id) {
            return -1;
        } else {
            return 1;
        }
    } // При написании метода надо придерживать естественного порядка
    // Еще один способ написания метода (в основном все так пишут 📌):
    /* public int compareTo(Employee anotherEmp) {
        return this.id - anotherEmp.id;
    } */
    // Если надо сравнить по имени (Здесь пользуемся функциональностью метода /compareTo/ класса String):
    /* public int compareTo(Employee anotherEmp) {
        return this.name.compareTo(anotherEmp.name);
    } */
}

class Test { // Для класса Работник /class Employee/
    public static void main(String[] args) {
        List<Employee> list = new ArrayList<>();
        Employee emp1 = new Employee(100, "Zaur", "Tregulov", 12345);
        Employee emp2 = new Employee(15, "Ivan", "Petrov", 6543);
        Employee emp3 = new Employee(123, "Ivan", "Sidorov", 8542);
        list.add(emp1);
        list.add(emp2);
        list.add(emp3);
        System.out.println("Pered sortirovkoy \n" + list);
        /*Collections.sort(list);*/ // Здесь компилятор ругается: Java не понимает каким образом делать
        // сортировку у объектов /Employee/. Со String и Integer Java понимает что делать, а с Employee нет.
        // По каким критериям сравнивать emp1 и emp2 не понятно, эти критерии мы сами должны задать,
        // эти критерии могут быть такими, какими мы захотим. Попробуем сравнивать объекты по /id/, у кого оно
        // больше, тот и больше, и кто меньше идет вперед, кто больше назад.
        // Для того чтобы применить этот метод сравнения этих объектов мы можем использовать
        // интерфейс Comparable ❤️
        // (переводится как - возможный для сравнения, способный быть сравнимым)
        // 📍 Имплементируем в классе /Employee/: implements Comparable<Employee>, и реализуем
        // метод /compareTo/, этот метод return int:
        // если текущий объект > объекта в параметре - мы должны вернуть положительное число, если меньше -
        // отрицательное число, если равны -> о.
        // Теперь объекты /Employee/ могут быть сравнены между собой по /id/
        // Теперь мы можем сортировать наш /list/
        Collections.sort(list);
        System.out.println("After sorting \n" + list);
        // Теперь сортируется отлично по возрастанию /id/


    }

}

//----------------------------------------------------------------------------------------
// Создадим класс Работник1 (здесь сравниваем по имени и если имена равны, то сравниваем фамилии)
class Employee1 implements Comparable<Employee1> {
    int id;
    String name;
    String surname;
    int salary;

    public Employee1(int id, String name, String surname, int salary) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" + "id=" + id + ", name='" + name + '\'' + ", surname='" + surname + '\'' + ", salary=" + salary + '}';
    }

    // реализуем метод /compareTo/, после того как добавили implements Comparable<Employee>
    @Override
    public int compareTo(Employee1 anotherEmp) {
        int res = this.name.compareTo(anotherEmp.name); // сравниваем по имени
        if (res == 0) {                                 // если равно, то
            res = this.surname.compareTo(anotherEmp.surname); // сравниваем по фамилии
        }
        return res;
    }
}

class Test2 { // для class Employee1
    public static void main(String[] args) {
        List<Employee1> list = new ArrayList<>();
        Employee1 emp1 = new Employee1(100, "Zaur", "Tregulov", 12345);
        Employee1 emp2 = new Employee1(15, "Ivan", "Petrov", 6543);
        Employee1 emp3 = new Employee1(123, "Ivan", "Sidorov", 8542);
        list.add(emp1);
        list.add(emp2);
        list.add(emp3);
        System.out.println("Pered sortirovkoy \n" + list);
        Collections.sort(list);
        System.out.println("After sorting \n" + list);
    }
}
// Интерфейс Comparable используется для сравнения объектов, используя естесственный порядок, чтобы его
// имплементировать нужно перезаписать метод /int compareTo(Element e)/