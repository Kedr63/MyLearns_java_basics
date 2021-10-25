package Stream_Заур_раздел7_Streams_черныйПояс;

import java.util.ArrayList;
import java.util.List;

public class Test16_skip {
    public static void main(String[] args) {
        // 📌 Метод /skip/ (i)    ______________________________________________________

        // Ограничивает количество элементов в нашем стриме также как и лимит, но если лимит оставлял первые
        // N-элементов из нашего стрима, то метод /skip/ будет пропускать первые N-элементов нашего стрима

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

        // Здесь ⬇ просто выводим всех студентов старше 20
        students.stream()
                .filter(e -> e.getAge() > 20)
                .forEach(System.out::println);

        System.out.println("---------------------------");

        // Здесь ⬇ выводить первых 2-х студентов старше 20
        students.stream()
                .filter(e -> e.getAge() > 20)
                .limit(2)
                .forEach(System.out::println);

        System.out.println("---------------------------");

        // Здесь ⬇ буду пропускать первых 2-х студентов старше 20 и выводить оставшихся
        students.stream()
                .filter(e -> e.getAge() > 20)
                .skip(2)
                .forEach(System.out::println);

        /*консоль:
        Student{name='Ivan', sex=m, age=22, course=3, avgGrade=8.3}
        Student{name='Nikolay', sex=m, age=28, course=2, avgGrade=6.4}
        Student{name='Petr', sex=m, age=35, course=4, avgGrade=7.0}
        Student{name='Maria', sex=f, age=23, course=3, avgGrade=9.1}
        ---------------------------
        Student{name='Ivan', sex=m, age=22, course=3, avgGrade=8.3}
        Student{name='Nikolay', sex=m, age=28, course=2, avgGrade=6.4}
        ---------------------------
        Student{name='Petr', sex=m, age=35, course=4, avgGrade=7.0}
        Student{name='Maria', sex=f, age=23, course=3, avgGrade=9.1}*/





    }
}
