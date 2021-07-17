import java.util.*;

public class Main {
    /*
    TODO:
     - реализовать методы класса CoolNumbers
     - посчитать время поиска введимого номера в консоль в каждой из структуры данных
     - проанализоровать полученные данные
     */

    public static void main(String[] args) {

        System.out.println("Введите красивый автономер:");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        // поместим сгенерированный список номеров в список list ArrayList
        List<String> list = new ArrayList<>(CoolNumbers.generateCoolNumbers());

        // измерим время поиска при прямом переборе номеров в list Arraylist
        Long star = System.nanoTime();
        String result;
        if (CoolNumbers.bruteForceSearchInList(list, input)) {
            result = "номер найден";
        } else result = "номер не найден";
        Long end = System.nanoTime();
        System.out.println("Поиск перебором: " + result + ", поиск занял " + (end - star) + "нс");

        // измерим время поиска при бинарном поиске номеров в list Arraylist(перед этим отсортируем)
        Collections.sort(list);
        Long star1 = System.nanoTime();
        String result1;
        if (CoolNumbers.binarySearchInList(list, input)) {
            result1 = "номер найден";
        } else result1 = "номер не найден";
        Long end1 = System.nanoTime();
        System.out.println("Бинарный поиск: " + result1 + ", поиск занял " + (end1 - star1) + "нс");

        // поместим сгенерированный список номеров в набор с уникальными строками Hashset
        // и измерим время поиска номера в наборе Hashset
        HashSet<String> set = new HashSet<>(CoolNumbers.generateCoolNumbers());
        Long star2 = System.nanoTime();
        String result2;
        if (CoolNumbers.searchInHashSet(set, input)) {
            result2 = "номер найден";
        } else result2 = "номер не найден";
        Long end2 = System.nanoTime();
        System.out.println("Поиск в HashSet: " + result2 + ", поиск занял " + (end2 - star2) + "нс");

        // поместим сгенерированный список номеров в набор с уникальными строками TreeSet
        // и измерим время поиска номера в наборе TreeSet
        TreeSet<String> set2 = new TreeSet<>(CoolNumbers.generateCoolNumbers());
        Long star3 = System.nanoTime();
        String result3;
        if (CoolNumbers.searchInTreeSet(set2, input)) {
            result3 = "номер найден";
        } else result3 = "номер не найден";
        Long end3 = System.nanoTime();
        System.out.println("Поиск в TreeSet: " + result3 + ", поиск занял " + (end3 - star3) + "нс");
    }
}

// Консоль:
/*Введите красивый автономер:
        В333ВА77
        Поиск перебором: номер не найден, поиск занял 68583800нс
        Бинарный поиск: номер не найден, поиск занял 127800нс
        Поиск в HashSet: номер не найден, поиск занял 65600нс
        Поиск в TreeSet: номер не найден, поиск занял 126400нс  */
// Поиск перебором: используется цикл for, и мы при каждой итерации сравниваваем искомый элемент с каждым элементом списка,
// и в худшем случае мы пройдем все элементы списка, получается сколько элементов в списке - столько и операций сравнения,
// эдесь сложность алгоритма линейная и принадлежит О(N)

// Бинарный поиск: так как при каждой итерации обрабатывается половина элементов списка , то сложность алгоритма
// логарифмическая и принадлежит О(log N)

// Поиск в HashSet: так при поиске используется хэш и поиск не зависит от количества элементов в наборе,
// то сложность алгоритма константная и принадлежит О(1)

// Поиск в TreeSet: поиск идет в красно-черном дереве и оно сбалансированно (этот поиск аналогичен бинарному поиску),
// здесь сложность алгоритма логарифмическая и принадлежит О(log N)

//     В итоге худшая временная сложность будет у поиска перебором - O(N),
//     Примерно одинаковая временная сложность у бинарного поиска и поиска в TreeSet - O(log N),
//     И лучшая временная сложность у поиска в HashSet - O(1)