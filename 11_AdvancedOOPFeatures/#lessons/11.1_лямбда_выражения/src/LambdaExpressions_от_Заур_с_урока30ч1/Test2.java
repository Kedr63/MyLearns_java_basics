package LambdaExpressions_от_Заур_с_урока30ч1;

import java.util.ArrayList;

public class Test2 {
    // Следующий вариант: мы можем создать один интерфейс с методом /test/, например, и отразить
    // его в методе класса /StudentInfo/, что имеется в виду? Имеется ввиду что все эти методы которые были
    // в классе /StudentInfo/ мы удалим, они нам не нужны и
    // Создадим интерфейс /StudentChecks/ с одним методом
    // И дальше создадим ниже классы которые будут имплементировать этот интерфейс

    // Чем плох данный способ? Тем что здесь /return s.avgGrade > 8.5;/ мы должны конкретно указывать
    // больше какой оценки должен иметь студент чтобы информация о нем вывелась на экран
    // Ради практики создадим еще классы которые имплементируют данный интерфейс,

}

class Student1 {
    String name;
    char sex;
    int age;
    int course;
    double avgGrade;

    Student1(String name, char sex, int age, int course, double avgGrade) {
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.course = course;
        this.avgGrade = avgGrade;
    }
}

interface StudentChecks {
    boolean test(Student1 s);
}

class StudentInfo1 {
    void printStudent(Student1 st) {
        System.out.println("Imya studenta: " + st.name + ", pol: " + st.sex + ", vozrast: " + st.age + "," +
                " kurs: " + st.course + ", srednyaa ocenka: " + st.avgGrade);
    }

    void testStudents(ArrayList<Student1> aL, StudentChecks sc) { // вместо 2го параметра можем вставить
        // объект любого класса который импл-ет интерфейс /StudentChecks/, например
        // объект класса /FindStudentOverGrade/
        for (Student1 s : aL) {       // проверяем каждого студента из ArrayList и проверяем:
            if (sc.test(s)) {  // если объект Типа /StudentChecks/ вызывая метод /test/,
                // который он оверайдит, если он true- выводит на экран студента
                printStudent(s);
            }
        }
    }

    // И теперь поработаем
    public static void main(String[] args) {
        ArrayList<Student1> list1 = new ArrayList<>();
        Student1 st1 = new Student1("Ivan", 'm', 22, 3, 8.3);
        Student1 st2 = new Student1("Nikolay", 'm', 28, 2, 6.4);
        Student1 st3 = new Student1("Elena", 'f', 19, 1, 8.9);
        Student1 st4 = new Student1("Petr", 'm', 35, 4, 7);
        Student1 st5 = new Student1("Maria", 'f', 23, 3, 9.1);
        list1.add(st1);
        list1.add(st2);
        list1.add(st3);
        list1.add(st4);
        list1.add(st5);
        StudentInfo1 sI = new StudentInfo1();

        // создадим классы которые имплементируют данный интерфейс и
        // создадим объекты этих классов
        FindStudentsOverGrade fsog = new FindStudentsOverGrade();
        FindStudentsUnderGrade fsug = new FindStudentsUnderGrade();
        FindStudentsOverAge fsoa = new FindStudentsOverAge();
        FindStudentsUnderAge fsua = new FindStudentsUnderAge();
        FindStudentsBySex fsbs = new FindStudentsBySex();
        FindStudentsMixCondition fsmc = new FindStudentsMixCondition();

        sI.testStudents(list1, fsog);
        System.out.println("-----------------------------------");
        sI.testStudents(list1, fsug);
        System.out.println("-----------------------------------");
        sI.testStudents(list1, fsoa);
        System.out.println("-----------------------------------");
        sI.testStudents(list1, fsua);
        System.out.println("-----------------------------------");
        sI.testStudents(list1, fsbs);
        System.out.println("-----------------------------------");
        sI.testStudents(list1, fsmc);
        System.out.println("-----------------------------------");

        // Все выводы на консоль такие же как в Test1
        // В первом случае мы в внутри класса /StudentInfo/ писали методы,
        // здесь мы создали интерфейс --> потом создали классы которые имплементируют данный интерфейс
        // и оверайдили метод /test(Student1 s)/ каждый раз по своему. Минус этого способа
        // решения задач - должны конкретно указывать какие проверки мы осуществляем, но зато
        // если что-то надо изменить мы уже класс /StudentInfo/ не трогаем,
        // метод /testStudents(ArrayList<Student1> aL, StudentChecks sc)/ будет подходить под все.
        // Если что-то надо будет изменять, то будем уже изменять вне этого класса /StudentInfo/,
        // Но если надо что-то будет добавить -> каждый раз придется создавать новые классы,
        // которые имплементируют этот интерфейс и создавать потом объекты данного класса,
        // чтоб потом использовать их как параметр нашего метода
        // Этот способ в какой-то мере лучше первого, в каком-то хуже, здесь кода также много.

        // Сейчас рассмотрим третий - самый простой короткий и удобный вариант - это использование
        // лямбда-выражений. Смотри класс /Test3/

    }
}


class FindStudentsOverGrade implements StudentChecks {
    @Override
    public boolean test(Student1 s) {
        return s.avgGrade > 8.5;
    }
}

class FindStudentsUnderGrade implements StudentChecks {
    @Override
    public boolean test(Student1 s) {
        return s.avgGrade < 9;
    }
}

class FindStudentsOverAge implements StudentChecks {
    @Override
    public boolean test(Student1 s) {
        return s.age > 25;
    }
}

class FindStudentsUnderAge implements StudentChecks {
    @Override
    public boolean test(Student1 s) {
        return s.age < 27;
    }
}

class FindStudentsBySex implements StudentChecks {
    @Override
    public boolean test(Student1 s) {
        return s.sex == 'm';
    }
}

class FindStudentsMixCondition implements StudentChecks {
    @Override
    public boolean test(Student1 s) {
        return (s.avgGrade > 7.2 && s.age < 23 && s.sex == 'f');
    }
}
