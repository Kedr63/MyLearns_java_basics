package LambdaExpressions_от_Заур;

import java.util.ArrayList;
import java.util.function.Predicate;

public class Test6 {
}

// Интерфейс Preticate<T> видео 01:08:31

class Student6 {
    String name;
    char sex;
    int age;
    int course;
    double avgGrade;

    Student6(String name, char sex, int age, int course, double avgGrade) {
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.course = course;
        this.avgGrade = avgGrade;
    }
}

/*interface StudentChecks6 {
    boolean test(Student6 s);
}*/  // Уберем этот интерфейс, вместо него у нас будет интерфейс Preticate<T>
// Это интерфейс находится в java.util.function | импортируем его


class StudentInfo6 {
    void printStudent(Student6 st) {
        System.out.println("Imya studenta: " + st.name + ", pol: " + st.sex + ", " +
                "vozrast: " + st.age + ", kurs: " + st.course + ", srednyaa ocenka: " + st.avgGrade);
    }

// изменим метод исп-я интерфейс /Predicate/
    void testStudents(ArrayList<Student6> aL, Predicate <Student6> t) {
        for (Student6 s : aL) {
            if (t.test(s)) {
                printStudent(s);
            }
        }
    }
    // Этот пример отличается от предыдущего только тем что исп-ем интерфейс который есть уже в Java
    // а не создаю свой.
    // Этот интерфейс нужен только для того чтобы не создавать свой интерфейс

    // И теперь поработаем
    public static void main(String[] args) {
        ArrayList<Student6> list6 = new ArrayList<>();
        Student6 st1 = new Student6("Ivan", 'm', 22, 3, 8.3);
        Student6 st2 = new Student6("Nikolay", 'm', 28, 2, 6.4);
        Student6 st3 = new Student6("Elena", 'f', 19, 1, 8.9);
        Student6 st4 = new Student6("Petr", 'm', 35, 4, 7);
        Student6 st5 = new Student6("Maria", 'f', 23, 3, 9.1);
        list6.add(st1);
        list6.add(st2);
        list6.add(st3);
        list6.add(st4);
        list6.add(st5);
        StudentInfo6 studentInfo6 = new StudentInfo6();


        studentInfo6.testStudents(list6, (Student6 st) -> {return st.avgGrade > 8.5;});
        System.out.println("-----------------------------------");

        studentInfo6.testStudents(list6, (Student6 st) -> {return st.avgGrade < 9;});
        System.out.println("-----------------------------------");

        studentInfo6.testStudents(list6, st -> st.age > 25);  // короткий способ написания
        System.out.println("-----------------------------------");

        studentInfo6.testStudents(list6, st -> {return st.age < 27;}); // смешанный вариант
        System.out.println("-----------------------------------");

        studentInfo6.testStudents(list6, (Student6 st) -> st.sex == 'm'); // смешанный вариант
        System.out.println("-----------------------------------");

        studentInfo6.testStudents(list6, (Student6 st) -> {return st.avgGrade > 7.2 && st.age < 23 && st.sex =='f';});
        System.out.println("-----------------------------------");
    }
}




