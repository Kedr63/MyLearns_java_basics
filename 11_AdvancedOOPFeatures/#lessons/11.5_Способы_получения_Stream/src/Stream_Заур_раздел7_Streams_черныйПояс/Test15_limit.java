package Stream_Заур_раздел7_Streams_черныйПояс;

import java.util.ArrayList;
import java.util.List;

public class Test15_limit {
    public static void main(String[] args) {
        // 📌 Метод /limit/ (i)    ______________________________________________________

        // Он возвращает стрим. limit ограничивает количество элементов в нашем стриме

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

        // Хочим лимитировать до 2-х студентов, т/е вот здесь ->/stream()/ наш стрим содержит 5 элементов,
        // здесь ->/filter/ - четыре, потому что 1 студент не прошел фильтрацию, а после /limit(2)/ стрим
        // будет содержать 2-х студентов

        students.stream()
                .filter(e -> e.getAge() > 20)
                .limit(2)
                .forEach(System.out::println);

        /*   консоль:
        Student{name='Ivan', sex=m, age=22, course=3, avgGrade=8.3}
        Student{name='Nikolay', sex=m, age=28, course=2, avgGrade=6.4}*/

    }
}
