public class Polimorphism_by_Zaur {
    public static void main(String[] args) {
        Employee emp1 = new Teacher();
        Employee emp2 = new Driver();
        Employee emp3 = new Doctor();
        emp1.work();
        emp2.work();
        emp3.work();
        // консоль:
        /*Teacher works
        Driver works
        Doctor works*/
        // В зависимости от типа объекта, который вызывает этот метод /work()/, метод /work()/ будет
        // работать по разному - это и есть полиморфизм
        Help_able h = new Teacher();
        h.help();
        /*  h.work();  */ // компилятор против, он говорит что у /h/ тип данных /Help_able/, а в /Help_able/
        // нет метода /work()/
        // А если сделаем /Employee implements Help_able/, то
        emp1.help(); // потому что /help()/ в классе /Employee/ уже есть

        // можем создавать массивы примитивных и ссылочных переменных типа конкретного класса, АК или интерфейса
        Driver [] array1 = {new Driver(), new Driver()};
        // а если тип будет /Employee/, то уже можем добавить больше разных объектов
        Employee [] array2 = {new Driver(), new Driver(), new Teacher(), new Doctor()};
        // и так можно
        Help_able [] array3 = {new Driver(), new Driver(), new Teacher(), new Doctor()};

        System.out.println();
        // Пример (классический пример когда объясняют полиморфизм):
        Employee [] array4 = {emp1, emp2, emp3};
        for (Employee emp: array4){
            emp.work();
        }
        // консоль:
        // Teacher works
        // Driver works
        // Doctor works
        //____Как работает этот foreach loop: /emp/ сначала принимает значение /emp1/, т.е. он бывает
        // учителем -> "Teacher works", затем /emp/ принимает значение /emp2/, т.е. ссылается на
        // объект /new Driver()/ -> "Driver works", затем /emp/ ссылается на объект /new Doctor()/ ->
        // "Doctor works"
        // В зависимости от того какой объект вызывает метод, срабатывает определенное тело метода

    }
}

abstract class Employee implements Help_able{
    void sleep() {
        System.out.println("Employee sleeps");
    }

    abstract void work();
}

class Teacher extends Employee implements Help_able{

    @Override
    void work() {
        System.out.println("Teacher works");
    }

    @Override
    public void help() {
        System.out.println("Teacher helps");
    }
}

class Driver extends Employee {

    @Override
    void work() {
        System.out.println("Driver works");
    }

    @Override
    public void help() {
        System.out.println("Driver helps");
    }
}

class Doctor extends Employee {

    @Override
    void work() {
        System.out.println("Doctor works");
    }

    @Override
    public void help() {
        System.out.println("Doctor helps");
    }
}

interface Help_able{
    void help();
}