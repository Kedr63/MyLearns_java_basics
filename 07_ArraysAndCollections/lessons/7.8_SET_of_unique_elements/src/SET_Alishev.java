import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

public class SET_Alishev {
    public static void main(String[] args) {
        // Set - множества.
        // Set - это коллекция, которая хранит в себе только уникальные элементы (Э).

        // Посмотрим классы реализующие интерфейс Set:
        Set<String> hashSet = new HashSet<>();
        Set<String> linkedHashSet = new LinkedHashSet<>();
        Set<String> treeSet = new TreeSet<>();

        //hashSet - наиболее исп-мый Set, потому что самый быстрый и часто в множествах не нужно знать порядка

        hashSet.add("Bob");  // кладем (Э) в hashSet
        hashSet.add("Mike");
        hashSet.add("Tim");
        hashSet.add("Tom");
        hashSet.add("Mig");
        hashSet.add("Tom"); // проигнорирует, не будет выводить т.к. дубликат


        // получим (Э) обратно:
        for (String name : hashSet) {     //заводим врем.~ String name в hashSet, т.е. мы итерируем по нашему hashSet_у
            System.out.println(name);    // и каждое значение, которое мы получаем -это строка name
        }            //выведется в случайном порядке, т.к. hashSet
        // если будет linkedHashSet -выведется в том порядке в котором вводили
        // treeSet - выведется в лексико-графическом порядке

        //можно еще вывести так: т.к. все классы реализующие интерфейс Set реализуют метод .toString
        System.out.println(hashSet);  // [Mike, Tom, Bob, Tim, Mig]


        // Посмотрим какие есть методы у объектов реализующих интерфейс Set:

        System.out.println(hashSet.contains("Tim"));     //true , если в hashSet есть "Tim"
        System.out.println(hashSet.contains("Katya"));   //false
        // PS: .contains в Set-ах работает очень быстро, т.к. исп-ся хэширование

        System.out.println(hashSet.isEmpty()); //false, если hashSet не пустой


        //Еще одно применение Set: как взамодействие математических множеств.

        // Пересечение: a=123456789 пересекает b=567891011
        // 123456789
        //     567891011   -> 56789 -это то, что есть в обоих множествах

        // Объединение: a=12345  b=456789
        // 12345
        //    456789       -> 123456789

        // Разность. Зависит на каком множестве вызываем операцию
        // a=123456  b=456789
        // если вызываем операцию diff на множестве (a) -> a-b -> из (a) убираем все числа, которые есть в (b)
        // 123456
        //    456789     -> 123

        // Примеры
        Set<Integer> set1 = new HashSet<>();
        set1.add(0);
        set1.add(1);
        set1.add(2);
        set1.add(3);
        set1.add(4);
        set1.add(5);

        Set<Integer> set2 = new HashSet<>();
        set2.add(2);
        set2.add(3);
        set2.add(4);
        set2.add(5);
        set2.add(6);
        set2.add(7);

        // union - объединение множеств
        Set<Integer> union = new HashSet<>(set1);  // в конструктор передадим set1, будет новое множество как set1
        union.addAll(set2);  // .addAll в union добавляет все (Э) из переданного в качестве аргумента set2

        System.out.println(union);  // [0, 1, 2, 3, 4, 5, 6, 7]

        // intersection - пересечение множеств
        Set<Integer> intersection = new HashSet<>(set1);
        intersection.retainAll(set2); // .retain оставляет в set1 все (Э), которые есть в set2

        System.out.println(intersection);  // [2, 3, 4, 5]

        // difference - разность множеств
        Set<Integer> difference = new HashSet<>(set1);
        difference.removeAll(set2); // этот метод полная противоположность .retain()
        // из первого set-а удаляются все (Э), которые присутствуют в set-e, который передается в качестве аргумента

        System.out.println(difference);  // [0, 1]




    }
}
