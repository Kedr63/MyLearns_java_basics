package LambdaExpressions_от_Заур_часть_2_с_урока30ч2;

// ❤️ Некоторые методы ЛВ

// Дженерики сделали Java- коллекции -> TypeSafe (Типобезопасными), в свою очередь ЛВ вместе с
// функциональными интерфейсами придали коллекциям суперсилу. Теперь множество классов и интерфейсов
// коллекций содержат в себе методы, которые используют функциональные интерфейсы и теперь
// программисты могут вызывать эти методы с помощью ЛВ и множество процессов стало гораздо проще.

// Рассмотрим три метода

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Test2 {
    public static void main(String[] args) {

        // 📌 Метод /forEach/

        // для начала создадим ArrayList или просто list
        List<String> list = List.of("privet", "poka", "kak dela?", "vce normalno!");
        // Если нам необходимо вывести на экран по одному все элементы этого List-а, чтобы мы делали?
        // мы бы использовали for loop
        /*   for (String s : list){
                System.out.println(s);
        }*/
        // Но мы можем сделать по другому - исп-ем ЛВ, в () прописываем что нужно сделать для каждого элемента,
        // каждый элемент данного листа это -String, пусть это будет ~ /s/ -> что мы должны сделать с этой
        // переменной? -> мы должны эту переменную вывести на экран.
        list.forEach(s -> System.out.println(s));    // или ↙ аналог
        list.forEach(System.out::println);
        // результат такой же как и при /for loop/. Вот этот в () параметр метода forEach в виде ЛВ, мы
        // будем проделывать для каждого элемента  list-а

        // 📌 Метод /remove/

        ArrayList<Integer> al = new ArrayList<>();
        al.add(1);
        al.add(2);
        al.add(3);
        al.add(4);
        al.add(5);
        al.add(6);
        al.add(7);
        al.add(8);
        // теперь удалим из этого ArrayList-а элементы с помощью /removeIf/, которые нацело делятся на 3
        al.removeIf(element -> element % 3 == 0);
        // не забывайте что в predicate метод test должен возвращать булево значение
        // выведем ArrayList и посмотрим как удалились элементы
        System.out.println(al);
        /*   консоль: [1, 2, 4, 5, 7, 8]  */

        // ⬆  это выражение можно было и по другому написать: ⬇
        Predicate<Integer> p = element -> element % 3 == 0;
        al.removeIf(p);    // и если вывести, то также будет
        /*   консоль: [1, 2, 4, 5, 7, 8]  */


        // 📌 Метод /sort/

        // Мы будем сортировать ArrayList
        ArrayList<Integer> list1 = new ArrayList<>();
        list1.add(2);
        list1.add(7);
        list1.add(4);
        list1.add(1);
        list1.add(3);
        list1.add(6);
        list1.add(0);
        System.out.println("до сортировки: " + list1);

        list1.sort((x, y) -> x.compareTo(y)); // sort ждет в параметрах Comparator, Comparator - сравнивает,
        // и мы будем использовать метод compareTo, чтобы сравнить два элемента.
        // Мы пишем sort, и потом используем ЛВ:
        // два элемента x и y -> какого типа они будут? - так как мы работаем с ArrayList-ом list1 они
        // будут <Integer>
        // и выведем это на экран:
        System.out.println("после сортировки: " + list1);
        /*    консоль:
        до сортировки: [2, 7, 4, 1, 3, 6, 0]
        после сортировки: [0, 1, 2, 3, 4, 6, 7]
         */

        // т.е. этот метод compareTo сравнивает два аргумента и возвращает отрицательное значение, ноль или
        // положительное значение. Отрицательное - когда первый аргумент < второго,
        // Ноль - когда они равны, положительное - когда первый аргумент > второго.
        // Таким образом метод /sort/ может отсортировать нам коллекцию.
        // Если хотим отсортировать в обратном порядке, то надо добавить знак "минус" перед 1-м аргументом
        list1.sort((x, y) -> -x.compareTo(y));
        System.out.println(list1);
        /*     консоль: [7, 6, 4, 3, 2, 1, 0]    */


        // 📌 Scope of variable (область видимости переменнных)

        // Речь о переменных объявленных в ЛВ.
        // Переменные которые мы определяем в секции (x, y) объявления переменных, их область видимости
        // это все ЛВ, дальше они перестают быть видны


    }
}
