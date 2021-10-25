package Stream_Заур_раздел7_Streams_черныйПояс;

import java.util.ArrayList;
import java.util.List;

public class Test13_findFirst {
    public static void main(String[] args) {

        // 📌 Метод /findFirst/ (t)  ________________________________________________________

        // Этот метод будет возвращать нам первый элемент нашего стрима

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

        /*  students.stream()
                .map(element -> {
                    element.setName(element.getName()
                            .toUpperCase());
                    return element;
                })
                .filter(element -> element.getSex() == 'f')
                .sorted((x, y) -> x.getAge() - y.getAge())
                .forEach(element -> System.out.println(element));  */
        // В итоге мы получали двух студентов
        /*  консоль:
        Student{name='ELENA', sex=f, age=19, course=1, avgGrade=8.9}
        Student{name='MARIA', sex=f, age=23, course=3, avgGrade=9.1}  */

        // Если нам не нужен этот полный вывод, мы можем использовать метод /findFirst/
        Student first = students.stream()
                .map(element -> {
                    element.setName(element.getName()
                            .toUpperCase());
                    return element;
                })
                .filter(element -> element.getSex() == 'f')
                .sorted((x, y) -> x.getAge() - y.getAge())
                .findFirst().get();  // вот этого первого студента запишем в переменную /Student first/,
        // но мы не можем (подчеркивается ошибкой) присвоить результат /findFirst()/-а студенту -Почему?-
        // потому что /findFirst/ также возвращает /optional/. Мы можем здесь использовать метод /get/ и
        // не будем проверять /isPresent/, потому что мы уверенны что такой студент есть.
        // И теперь мы можем этого первого студента вывести на экран:
        System.out.println(first);
        /*  консоль:
        Student{name='ELENA', sex=f, age=19, course=1, avgGrade=8.9}   */
    }
}
