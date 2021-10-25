package Stream_Заур_раздел7_Streams_черныйПояс;

import java.util.ArrayList;
import java.util.List;

public class Test14_min_max {
    public static void main(String[] args) {

        // 📌 Методы /min и max/ (t)  ________________________________________________________

        Student st1 = new Student("Ivan", 'm', 22, 3, 8.3);
        Student st2 = new Student("Nikolay", 'm', 28, 2, 6.4);
        Student st3 = new Student("Elena", 'f', 19, 1, 8.9);
        Student st4 = new Student("Petr", 'm', 35, 4, 7);
        Student st5 = new Student("Maria", 'f', 23, 3, 9.1);
        List<Student> students = new ArrayList<>();
        students.add(st1);
        students.add(st2);
        students.add(st3);
        students.add(st4);
        students.add(st5);

        // Найдем минимального студента. /min/ возвращает /optional/, поэтому мы можем чтобы придать
        // это значение (результат всего этого) переменной /Student min/, ➔ использовать /get/.
        // Но посмотрите, в данном случае, Java не знает какой студент должен считаться минимальным,
        // мы должны ему (методу /min/) объяснить и метод /min/ ждет от нас что мы опишем
        // здесь /Comparator/ (будем считать что минимальный студент тот
        // кто младше всех /x.getAge()-y.getAge()/)

        Student min = students.stream().min((x, y)-> x.getAge()-y.getAge()).get();
        System.out.println(min);
        /*  консоль:
        Student{name='Elena', sex=f, age=19, course=1, avgGrade=8.9}  */

        // и так же найдем максимального студента. Процедура та же самая: мы должны с помощью компаратора
        // объяснить Java-е кого мы считаем из студентов максимальным (и опять по возврасту)
        Student max = students.stream().max((x, y)-> x.getAge()-y.getAge()).get();
        System.out.println(max);
        /*   консоль:
        Student{name='Petr', sex=m, age=35, course=4, avgGrade=7.0}   */






    }
}
