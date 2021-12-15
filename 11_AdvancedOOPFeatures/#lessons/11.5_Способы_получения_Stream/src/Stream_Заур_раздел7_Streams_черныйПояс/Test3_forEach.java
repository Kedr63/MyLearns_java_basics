package Stream_Заур_раздел7_Streams_черныйПояс;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test3_forEach {
    public static void main(String[] args) {
        // 📌 Метод /forEach/ (t)  ______________________________________________________

        // Это совершенно не тот метод который мы использовали напрямую вызвав его из коллекции.
        // Этот forEach мы будем вызывать на стриме.
        // forEach возвращает void, т/е ничего не возвращает, таким образом после него нельзя создать,
        // например, лист с помощью /collect/. Мы лист с помощью collect создавали когда у нас был стрим
        // на руках, стрим превращали в лист, а здесь void в лист мы конечно превратить не можем.
        // Метод /forEach/ помогает проходиться по всем элементам, например, показывать выводить на экран
        // эти элементы.
        // Пример: ⬇

        int[] array = {5, 1, 9, 3, 1};
        /*   Arrays.stream(array)
              .forEach(el -> {
                  el *= 2;
                  System.out.println(el);
              });    */
        // консоль: 10 2 18 6 2

        //можно было и так
        Arrays.stream(array)
              .forEach(el -> System.out.println(el));
        // И посмотрим как можно сократить вот это /el -> System.out.println(el)/ написание ➔
        // Вообще forEach что говорит? -> с каждым элементом сделай то, что
        // здесь написано ➔ /el -> System.out.println(el)/ ➔
        // ➔ первый el  ➔  выведи на экран
        // ➔ второй el  ➔  выведи на экран
        // и т.д.
        // Мы можем вот это ЛВ /el -> System.out.println(el)/ еще упростить
        Arrays.stream(array)
              .forEach(System.out::println);       // Что на самом деле здесь происходит?
        // Вот это /System.out::println/ называется метод reference, т/е мы даем ссылку на
        // наш метод /println/   ➔ Какая ссылка? - мы говорим что этот метод находится
        // в /System.out/-е.  И JAVA достаточно умна, чтобы понять что в параметр к println() мы
        // должны вставить каждый элемент нашего стрима. Она ищет /println/ вот здесь → /System.out/
        // и в параметр к println() подставляет каждый элемент и выводит на экран.
        // Здесь до :: указываем класс, после :: метод.

        // Приведем более легкий пример: ⬇ создадим класс Utils
        // И теперь можем написать
        Arrays.stream(array)
              .forEach(Utils::myMethod);    // Что это означает?
        // Это означает: что я говорю → каждый элемент потока помести в метод /myMethod(в качестве
        // параметра)/, который находится в классе Utils. JAVA понимает: в классе Utils есть /myMethod/,
        // в качестве параметра он принимает (int a), а наш поток содержит int-ы, поэтому мы можем
        // подставить в параметр /myMethod/-а наши элементы.
        /*консоль:
        Element = 10
        Element = 6
        Element = 14
        Element = 8
        Element = 6    */
        //❤️ Примечание от Заура: я больше предпочитаю писать ЛВ, чем на столько сокращать код,
        // это для меня уже не настолько информативно. Все зависит от личных предпочтений ❤️

        // 📍Еще один момент хотел рассказать:  не обязательно создавать стрим из готовой коллекции
        // или готового массива. Мы можем создать его с нуля (повторим код с Test2)
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
        // Создам стрим с нуля: говорю что стрим содержит <Student> → назову его myStream, и
        // здесь -> /of()/ укажу что будет содержать мой стрим. И метод /filter/ применим к myStream
        Stream<Student> myStream = Stream.of(st1, st2, st3, st4, st5);
        myStream.filter(element -> element.getAge() > 22 && element.avgGrade < 7.2)
                .collect(Collectors.toList());
        // Такой вариант стрима тоже существует, и тоже часто используется
    }
}

class Utils {
    public static void myMethod(int a) {
        a = a + 5;
        System.out.println("Element = " + a);
    }
}


