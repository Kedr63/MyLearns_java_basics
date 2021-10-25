package Stream_Заур_раздел7_Streams_черныйПояс;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Test12_collect {
    public static void main(String[] args) {

        // 📌 Метод /collect/ (t)  ________________________________________________________

        // Мы использовали его чтобы поток преобразовать в лист или set, или другую какую-то коллекцию
        // Когда мы использовали этот метод, мы использовали Collectors - класс, у которого есть два
        // интересных для нас метода:
        // 📍 1 - groupingBy
        // 📍 2 - partitioningBy

        //📍 1 - groupingBy (означает "группировка по")
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

        // Давайте изменим нашим студентам имена на заглавные буквы и отсортируем их по курсам,
        // т/е для каждого курса у нас будет свой лист
        /* students.stream()
                .map(el -> {
                    el.setName(el.getName()
                            .toUpperCase());
                    return el;
                }).collect(Collectors.groupingBy(el-> el.getCourse()));   */// У класса Collectors вызываем

        // метод /groupingBy/,
        // в котором указываем по какому атрибуту моего студента я буду производить группировку
        // (я буду производить группировку по курсу).
        // ⬆ Т/е я увеличил имена и заменил их на заглавные буквы и потом я хочу сгруппировать студентов
        // по курсу.
        // Т/е у нас будет отдельный лист, который содержит студентов с первого курса, отдельный лист который
        // содержит студентов со второго, трейтьего и четвертого курсов, т/е у нас получится четыре листа,
        // в каждом из которых будет по одному студенту, лишь в одном будет два студента.
        // И посмотрите что в данном случае вернет collect ➔ он возвращает /map/, который содержит
        // Integer - как ключ, и как значение - лист студентов, т/е это означает что в нашей коллекции /map/
        // будет 4-ре элемента, а элемент представляет собой пару: ключ - значение (ключ - это будет
        // номер курса, а значение - лист студентов).
        // Поэтому результату этого кода ⬆ можем присвоить /Map<Integer, List<Student>> map/ ⬇
        Map<Integer, List<Student>> map = students.stream()
                .map(el -> {
                    el.setName(el.getName()
                            .toUpperCase());
                    return el;
                })
                .collect(Collectors.groupingBy(el -> el.getCourse()));
        //Еще раз что происходит ⬆:
        // У нас будет 4 элемента ->
        // 📍 1-й элемент с ключом - 1 (первый курс), а значение- это лист студентов, но в данном листе будет
        // всего лишь только один студент "Elena".
        // 📍 2-й элемент /map/-а с ключом- 2 (второй курс), а значение тоже - это лист студентов, содержащих
        // всего один элемент.
        // Выведем это на экран
        for (Map.Entry<Integer, List<Student>> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue()
                    .toString());
        }
        /*консоль:
        1: [
        Student{name='ELENA', sex=f, age=19, course=1, avgGrade=8.9}]
        2: [
        Student{name='NIKOLAY', sex=m, age=28, course=2, avgGrade=6.4}]
        3: [
        Student{name='IVAN', sex=m, age=22, course=3, avgGrade=8.3},
        Student{name='MARIA', sex=f, age=23, course=3, avgGrade=9.1}]
        4: [
        Student{name='PETR', sex=m, age=35, course=4, avgGrade=7.0}]*/
        // 3-й элемент содержит ключ -3 и лист из двух студентов....
        // Вот так мы смогли сгруппировать студентов в листы по курсу, а сам курс является ключом нашего /map/

        System.out.println();

        //-------------------------------------------------------------------
        // 📍 2 - partitioningBy  (означает "разделение по")
        // Поделим студентов на две группы: те кто получает высокую оценку и те кто низкую (граница высокой и
        // низкой оценки будет 7).
        // Описание кода: ⬇ сразу на стриме вызываем метод collect, и здесь уже используем /partitioningBy/
        // и параметр он принимает Predicate, т/е тело ЛВ должно быть boolean. Из-за того что здесь ➔
        // ➔ /el.avgGrade > 7/ - boolean, для каких-то студентов это будет true или false, поэтому результат
        // всего этого будет map из двух элементов и в данном случае ключом будет - boolean, если ключ-true у
        // элемента /map/-а, то значение этого элемента /map/-а будет /List<Student>/, которые хорошо учатся
        // (>7 оценка). Если false, то у данного элемента /map/-а будет значение /List<Student>/, студентов
        // которые учатся плохо
        Map<Boolean, List<Student>> map1 = students.stream()
                .collect(Collectors.partitioningBy(el -> el.avgGrade > 7));

        for (Map.Entry<Boolean, List<Student>> entry : map1.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue()
                    .toString());
        }
        /*    консоль:
        false: [
        Student{name='NIKOLAY', sex=m, age=28, course=2, avgGrade=6.4},
        Student{name='PETR', sex=m, age=35, course=4, avgGrade=7.0}]
        true: [
        Student{name='IVAN', sex=m, age=22, course=3, avgGrade=8.3},
        Student{name='ELENA', sex=f, age=19, course=1, avgGrade=8.9},
        Student{name='MARIA', sex=f, age=23, course=3, avgGrade=9.1}]*/

        // ❤️Эти методы при использовании стримов очень популярны❗️

    }
}
