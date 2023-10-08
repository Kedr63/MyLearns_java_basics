import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        // Урок 7.12  _Поиск и сортировка

        /*   Бинарный поиск  */
        // Как пример алфавитный словарь - отрываем его посередине и знаем что искомое слово или справа или слева,
        // затем отрываем пополам следующую половину словаря и снова так постепенно сужается поиск.
        /* Алгоритм бинарного поиска (для упрощения поиска по отсортированным данным)*/ //- разбиваем множество пополам и
        // смотрим в какой половине находится нужный нам элемент, видим допустим что он находится в правой половине -
        // и правую половину мы тоже разбиваем пополам, если у нас нечетное количество значений,
        // то у нас серединка лежит не между элементами, а на каком-то конкретном элементе,
        // и мы с ним сравниваем то что мы ищем. Если элемент вдруг совпал -значит мы нашли.
        // Если мы видим что элемент меньше, чем то слово которое в середине, то мы смотрим слева (в левую часть),
        // если больше, то в правую часть. И когда мы берем левую часть массива,
        // она у нас вообще остается из одного элемента, мы ее сравниваем с искомым - и получаем то что мы искали

        /*   Поиск по хэшу  */
        // Как пример книга, допустим мы знаем номер страницы, где находится искомое слово. Допустим ищем "лошадь" и
        // программа сразу открывает 4ю страницу где "лошадь", и перебрав несколько элементов которые находятся
        // на этой странице находит "лошадь",
        // так работают HashMap и HashSet


        // Для бинарного поиска есть класс Collections (для листов и коллекций)
        ArrayList<String> items = new ArrayList<>();
        items.add("as");
        items.add("ba");
        items.add("ca");
        items.add("xa");
        items.add("fe");
        items.add("red");
        Collections.sort(items);       //меняет ArrayList в порядке возврастания (ничего не возвращает),
        // и делает его отсортированным
        // и дальше по этому листу можно искать бинарным поиском

        int index = Collections.binarySearch(items, "лошадка");
        int index1 = Collections.binarySearch(items, "sa");
        int index2 = Collections.binarySearch(items, "as");
        // этот поиск return целое число, т.е. индекс элемента и на вход он получает ту коллекцию,
        // в которой мы ищем искомую строку
        // Метод возвращает int. Если возвращаемое значение больше или равно нулю —   элемент найден.
        // Если возвращаемое значение int меньше нуля — элемент в коллекции не найден.

        System.out.println(index + " " + index1 + " " + index2); // -7 -6  0


        /*   Пример 1: прямая сортировка ArrayList   */

        // Здесь  мы сортируем ArrayList типа String.
        // Делать это можно, просто используя метод Collections.sort (arraylist).
        // Список вывода будет отсортирован по алфавиту.

        ArrayList<String> listOfCountries = new ArrayList<>();

        listOfCountries.add("India");
        listOfCountries.add("US");
        listOfCountries.add("China");
        listOfCountries.add("Denmark");

        /*Unsorted List*/
        System.out.println("До:");
        for (String counter : listOfCountries) {
            System.out.println(counter);
        }

        /* Sort statement*/
        Collections.sort(listOfCountries);

        /* Sorted List*/
        System.out.println("После:");
        for (String counter : listOfCountries) {
            System.out.println(counter);
        }

        /*Выход:
        До:  `India  US  China  Denmark`
        После:  `China  Denmark  India  US`    */

        //  Пример 2  //
        // Тот же метод Collections.sort() можно использовать и для сортировки целочисленного массива Java.

        ArrayList<Integer> arraylist = new ArrayList<>();
        arraylist.add(11);
        arraylist.add(2);
        arraylist.add(7);
        arraylist.add(3);

        /* ArrayList before the sorting*/
        System.out.println("Before Sorting:");
        for (int counter : arraylist) {
            System.out.println(counter);
        }

        /* Sorting of arraylist using Collections.sort*/
        Collections.sort(arraylist);

        /* ArrayList after sorting*/
        System.out.println("After Sorting:");
        for (int counter : arraylist) {
            System.out.println(counter);
        }

       /* Выход:
        Before Sorting:  `11  2  7  3`
        After Sorting:  `2  3  7  11`   */
//////////////////////////////////////

        /*  Сортировка по убыванию  */
        // Используем метод Collections.reverseOrder() вместе с Collections.sort()
        // для сортировки списка в порядке убывания. В приведенном ниже примере мы использовали
        // инструкцию для сортировки в обратном порядке:
        // Collections.sort(list, Collections.reverseOrder()).

        // список будет сначала отсортирован в порядке возрастания,
        // а затем будет перевернут методом Collections.reverse()

        List<String> list = new ArrayList<>();
        list.add("AA");
        list.add("ZZ");
        list.add("CC");
        list.add("FF");

        /*Unsorted List: ArrayList content before sorting*/
        System.out.println("Before Sorting:");
        for (String str : list) {
            System.out.println(str);
        }

        /* Sorting in decreasing order*/
        Collections.sort(list, Collections.reverseOrder());

        /* Sorted List in reverse order*/
        System.out.println("ArrayList in descending order:");
        for (String str : list) {
            System.out.println(str);
        }

      /*  Вывод:

        Before Sorting:
        AA
        ZZ
        CC
        FF

        ArrayList in descending order:
        ZZ
        FF
        CC
        AA    */

       // Класс Collections
        // https://www.examclouds.com/ru/java/java-core-russian/algoritm-collections
    }
}
