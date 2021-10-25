import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Main {
    public static final String STAFF_TXT = "data/staff.txt";

    // ❤️ Stream API
    //____________________

    // Stream - это объект интерфейса Stream, который можно получить разными способами
    // и с помощью которого можно работать с множеством элементов наших коллекций, массивов в
    // функциональном стиле не создавая циклы.

    public static void main(String[] args) {
        List<Employee> staff = Employee.loadStaffFromFile(STAFF_TXT);

        // Один из способов получения /Stream/ ->  вызвать метод /stream/ у коллекции
        // и, например, мы хотим напечатать не всех наших сотрудников, а только тех у кого
        // з/п > чем 100_000 руб ⬇

        Stream<Employee> stream = staff.stream();
        stream.filter(employee -> employee.getSalary() >= 100000).forEach(System.out::println);

        // здесь /filter/ возвращает /stream/, который содержит только те элементы у которых
        // з/п больше 100_000,   и дальше  ➔ мы вызываем /forEach/ и печатаем только этих сотрудников
        /*  консоль:
        Алексей Ивлиев - 125000 - 11.11.2018
        Анна Сетяева - 140000 - 10.05.2012
        Дмитрий Кочергин - 140000 - 31.01.2017
        Степан Богданов - 140000 - 17.04.2016
        Савелий Фёдоров - 115000 - 07.05.2019
        Евгений Алфёров - 105000 - 12.08.2016  */

        // ⬆ это просто пример как работают stream-ы
        // и можно было эту конструкцию (см. выше) записать короче:
        staff.stream().filter(e -> e.getSalary() >= 100_000).forEach(System.out::println);

        // Теперь давайте чуть подробно о том, как получить переменную интерфейса /Stream/.
        // Можно взять и получить Stream прямо из множества элементов, например, мы хотим получить
        // стрим из чисел (вызываем статический метод /of/ и передаем сюда числа)
        Stream<Integer> numbers = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // так же как раньше делали массивы через [], также можно и получать числа,
        // и, например, можем взять и распечатать только четные числа:
        numbers.filter(number -> number % 2 == 0).forEach(System.out::println);

        // Ещё один способ получения stream-а, это получение его из массива
        Integer[] numbers1 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        // мы можем получить этот стрим у статического метода /stream/ класса Array ⬇
        Arrays.stream(numbers1).filter(integer -> integer < 5).forEach(System.out::println);

        // Также всю логику метода /loadStaffFromFile/ класса /Employee/ по фильтрации
        // правильных строк можно написать с помощью Stream API, потому что у класса /Files/
        // есть метод /lines/, который возвращает Stream<String>.

        // Это были так называемые конечные stream-ы, а бывают еще бесконечные stream-ы
        Stream.iterate(1, n -> n + 1).forEach(System.out::println);
        // вот такой метод /iterate/ создает бессконечность. В консоль будут выводится (генерироваться)
        // увеличивающиеся числа до бессконечности.

        // Есть еще один метод, чтоб создать бесконечный стрим - метод /generate/, в нем
        // мы задаем лямбду, которая возвращает значение из которого должен состоять этот стрим
        Stream.generate(() -> "aaa").forEach(System.out::println);

        // Еще можно создать стрим из стрим-char(ов)
        "fdgfhfjgkg".chars();

        // ❤️ Вебинар по стрим с К. Шибковым  https://www.youtube.com/watch?v=aLIAQiVZsTU

        // ❤️ Полное руководство по Java 8 Stream API в картинках и примерах (есть примеры)
        // https://annimon.com/article/2778

        // ❗️ Шпаргалка по стрим  https://habr.com/ru/company/luxoft/blog/270383/

        // 📌 Рецепты приготовления Stream API вызовов:
        // Java Stream API. Копилка рецептов
        // https://skillbox.ru/media/base/java-stream-api-kopilka-retseptov/

        // groupingBy() - группируем данные в StreamAPI❗️
        // https://habr.com/ru/post/348536/

        // collect() - получение коллекций из Stream❗️
        // https://metanit.com/java/tutorial/10.6.php


    }
}