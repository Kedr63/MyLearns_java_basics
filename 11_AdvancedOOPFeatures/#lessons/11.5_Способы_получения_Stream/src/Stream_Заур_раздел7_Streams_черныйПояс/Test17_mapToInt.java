package Stream_Заур_раздел7_Streams_черныйПояс;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Test17_mapToInt {
    public static void main(String[] args) {
        // 📌 Метод /mapToInt/ (i)    ______________________________________________________

        // Данный метод возвращает не просто стрим после себя, а int-стрим, т/е стрим который содержит
        // значения int

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

        // Используем mapToInt: что у нас int у студентов? ➔ например, номер курса ➔ /getCourse/.
        // Посмотрите ➔ mapToInt возвращает intStream. И /collect(Collectors.toList())/
        // Мы хотим сохранять номера курсов в отдельный лист /List<Integer> courses/, но смотрите нам
        // не удается это сделать, хотя mapToInt возвращает после себя не поток студентов, а поток их
        // курсов (т/е поток int-а), мы не можем назначить листу Integer-ов результат работы такого
        // стрима. Почему? -> потому что когда мы хотим значение из стрима записать в лист Integer, мы
        // мы обязаны использовать метод /boxed/.
        // Метод /boxed/ просто конвентирует значение int в значение Integer ➔ у нас курсы это int,
        // а нам нужно конвентировать их в Integer. Этим и занимается метод /boxed/. Теперь можем
        // без проблем вывести на экран лист который содержит курсы

        List<Integer> courses = students.stream()
                .mapToInt(el -> el.getCourse())
                .boxed()
                .collect(Collectors.toList());
        System.out.println(courses);  // [3, 2, 1, 4, 3]

        // А если хотим вывести среднюю оценку (Double)
        List<Double> avgGrades = students.stream()
                .mapToDouble(el -> el.getAvgGrade())
                .boxed()
                .collect(Collectors.toList());
        System.out.println(avgGrades);   // [8.3, 6.4, 8.9, 7.0, 9.1]

        // У intStream-а есть несколько методов которые работают с числами: sum, average, min, max

        int sum = students.stream().mapToInt(el-> el.getCourse()).sum();  // он просуммирует все курсы
        // наших студентов, возращает int
        System.out.println(sum);  // 13

        double average = students.stream().mapToInt(el-> el.getCourse()).average().getAsDouble();
        // average уже возвращает optionalDouble, поэтому мы не можем optionalDouble присвоить
        // переменной типа int.
        // Мы должны из optionalDouble с помощью уже не /get/, а /getAsDouble/ получить это значение
        System.out.println(average);  // 2.6
        // Получили ср. ариф. значение курсов

        int min = students.stream().mapToInt(el-> el.getCourse()).min().getAsInt();
        // возвращает optional но уже int,
        // мы можем вызывать его значение с помощью /getAsInt/, т/е у него нет метода get, зато есть getAsInt,
        // потому что это не просто optional, а optional int, который оборачивает не любое какое-то значение,
        // а конкретно значение Integer.
        // Выведем на экран минимальный курс
        System.out.println(min);  // 1

        int max = students.stream().mapToInt(el-> el.getCourse()).max().getAsInt();
        System.out.println(max);  // 4







    }
}
