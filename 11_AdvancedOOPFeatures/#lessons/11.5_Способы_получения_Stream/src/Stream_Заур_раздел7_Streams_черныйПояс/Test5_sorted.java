package Stream_Заур_раздел7_Streams_черныйПояс;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Test5_sorted {
    public static void main(String[] args) {
        // 📌 Метод /sorted/ (i)    ______________________________________________________

        int[] array = {3, 8, 1, 5, 9, 12, 4, 21, 81, 7, 18};

        // Как отсортируем его?
        // В sorted можем ничего не передавать, Java знает как сортировать числа
        array = Arrays.stream(array)
                .sorted()
                .toArray();
        System.out.println(Arrays.toString(array));
        /*        консоль: [1, 3, 4, 5, 7, 8, 9, 12, 18, 21, 81]  */

        // Теперь отсортируем наших студентов
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
        // Отсортируем ⬇ лист студентов и наш стрим переведем в лист и назначим этот лист листу /students/,
        // т/е был такой не отсортированный лист студентов, а теперь будет отсортирванный
        /*  students = students.stream()
                           .sorted()
                           .collect(Collectors.toList());  */
        // Но при выводе выбрасывается /Exception/ - Почему? Потому что Java не находит Comparable и не знает
        // как сортировать наших студентов. Поэтому в параметр метода sorted мы должны вставить
        // наш компаратор /(x, y) -> x.getName().compareTo(y.getName())/ (сортируем по имени)
        // Заметим: sorted возвращает тоже стрим, который мы в дальнейшем переводим в стрим.
        // В первом примере с int-ми Java знала как сортировать, а здесь мы сами должны сказать что ей
        // надо делать, по какому критерию сортировать наших студентов и написали
        // такое ЛВ ➔ /(x, y) -> x.getName().compareTo(y.getName())/  ➔
        //    ➔  два элемента: (x, y)  ➔ сравнивай по их имени: x.getName().compareTo(y.getName())
        students = students.stream()
                .sorted((x, y) -> x.getName()
                        .compareTo(y.getName()))
                .collect(Collectors.toList());
        System.out.println(students);
        /*консоль:                      имена выведутся в алфавитном порядке
        Student{name='Elena', sex=f, age=19, course=1, avgGrade=8.9},
        Student{name='Ivan', sex=m, age=22, course=3, avgGrade=8.3},
        Student{name='Maria', sex=f, age=23, course=3, avgGrade=9.1},
        Student{name='Nikolay', sex=m, age=28, course=2, avgGrade=6.4},
        Student{name='Petr', sex=m, age=35, course=4, avgGrade=7.0}]*/

    }
}
