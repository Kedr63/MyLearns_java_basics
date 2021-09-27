package LambdaExpressions_от_Заур;

import java.util.ArrayList;

public class Test1 {
}

// Доустим у нас есть студент
class Student {
    String name;
    char sex;
    int age;
    int course;
    double avgGrade; // средняя оценка

    Student(String name, char sex, int age, int course, double avgGrade) {
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.course = course;
        this.avgGrade = avgGrade;
    }
}

class StudentInfo {
    void printStudent(Student st) {
        System.out.println("Imya studenta: " + st.name + ", pol: " + st.sex + ", vozrast: "
                + st.age + ", kurs: " + st.course + ", srednyaa ocenka: " + st.avgGrade);
    }

    // И создадим ArrayList  в который поместим студентов (см. main), и нужно сделать так чтоб мы их могли
    // сортировать по различным признакам

      // создадим метод который будет выводить студентов выше определенной оценки
    void printStudentsOverGrade(ArrayList<Student> aL, double avgGrade) {
        for (Student s : aL) {
            if (s.avgGrade > avgGrade) {
                printStudent(s);
            }
        }
    }

    // создадим метод который будет выводить студентов ниже определенной оценки
    void printStudentsUnderGrade(ArrayList<Student> aL, double avgGrade) {
        for (Student s : aL) {
            if (s.avgGrade < avgGrade) {
                printStudent(s);
            }
        }
    }

    // какая еще понадобиться фильтрация? Допустим по возврасту
    void printStudentsOverAge(ArrayList<Student> aL, int age) {
        for (Student s : aL) {
            if (s.age > age) {
                printStudent(s);
            }
        }
    }

    void printStudentsUnderAge(ArrayList<Student> aL, int age) {
        for (Student s : aL) {
            if (s.age < age) {
                printStudent(s);
            }
        }
    }

    // фильтрация по полу
    void printStudentsBySex(ArrayList<Student> aL, char sex) {
        for (Student s : aL) {
            if (s.sex == sex) {
                printStudent(s);
            }
        }
    }

    // и фильтрация по смешанным условиям
    void printStudentsMixCondition(ArrayList<Student> aL, double avgGrade, int age, char sex) {
        for (Student s : aL) {
            if (s.avgGrade > avgGrade && s.age < age && s.sex == sex) {
                printStudent(s);
            }
        }
    }
    // И если нужны еще фильтрации, то будем использовать еще больше методов

    // И теперь поработаем
    // создадим ArrayList  в который поместим студентов (см. main), и нужно сделать так чтоб мы их могли
    // сортировать по различным признакам (см. метод /StudentInfo/ выше)
    public static void main(String[] args) {
        ArrayList<Student> list = new ArrayList<>();
        Student st1 = new Student("Ivan", 'm', 22, 3, 8.3);
        Student st2 = new Student("Nikolay", 'm', 28, 2, 6.4);
        Student st3 = new Student("Elena", 'f', 19, 1, 8.9);
        Student st4 = new Student("Petr", 'm', 35, 4, 7);
        Student st5 = new Student("Maria", 'f', 23, 3, 9.1);
        list.add(st1);
        list.add(st2);
        list.add(st3);
        list.add(st4);
        list.add(st5);
        StudentInfo si = new StudentInfo();  // создадим объект Типа StudentInfo, чтоб обращаться к методам
        si.printStudentsOverGrade(list, 8.5);  // класса /StudentInfo/

/*консоль
        Imya studenta: Elena, pol: f, vozrast: 19, kurs: 1, srednyaa ocenka: 8.9
        Imya studenta: Maria, pol: f, vozrast: 23, kurs: 3, srednyaa ocenka: 9.1*/

        System.out.println("--------------------------------------------------");
        si.printStudentsUnderGrade(list, 9); // отсортируются ниже 9.0
        System.out.println("--------------------------------------------------");
        si.printStudentsOverAge(list, 25);
        System.out.println("--------------------------------------------------");
        si.printStudentsUnderAge(list, 25);
        System.out.println("--------------------------------------------------");
        si.printStudentsBySex(list, 'm');
        System.out.println("--------------------------------------------------");
        si.printStudentsMixCondition(list, 7.3, 23, 'f');
        // Этими примерами показано как сложно писать такие приложения (и если понадобится какая-то
        // новая фильтрация, то придется снова вписывать новый метод в класс /StudentInfo/ и
        // использовать его. Таких методов может быть целая куча
        // Рассмотрим следующий вариант- как можем, своего рода, упростить. Создадим Test2
    }
}

