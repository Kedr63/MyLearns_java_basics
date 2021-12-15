import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class Test {
    // Метка времени

    public static void main(String[] args) {
        // Часто вместо даты и времени используются так называемые МЕТКИ ВРЕМЕНИ (timestamp).
        // Это числа которые удобно хранить и часто с ними удобно работать

        // МЕТКА ВРЕМЕНИ (timestamp) - количество секунд, прошедших с 00:00:00  01.01.1970 года
        // Любую дату/время можно выразить с помощью этого числа.
        // И в большинстве объектов которые работают с датой/временем - время и дата как раз и хранятся
        // в виде таких чисел.
        // В Java принято использовать метку времени в милисекундах.
        // Получить текущую метку времени:
        long now = System.currentTimeMillis();

        // Эту метку удобно использовать, например, для измерения длительности выполнения программного кода
        // Например, у нас есть строка, к которой мы будем прибавлять 100_000 раз случайное число,
        // и измерим длительность этой операции:
        long start = System.currentTimeMillis();
        String line = "";
        for (int i = 0; i < 100000; i++) {
            line += Math.random();
        }
        System.out.println(System.currentTimeMillis() - start);
       /* консоль:  81674      (это 84 секунды)  */


        // Преобразование МЕТКИ ВРЕМЕНИ в дату. В ряде систем используется хранение времени
        // в формате /timestamp/:
        LocalDateTime now1 = LocalDateTime.ofEpochSecond(System.currentTimeMillis()/1000,
                0, ZoneOffset.ofHours(3));
        // ofEpochSecond(в секундах, в наносекундах, часовой пояс по Москве +3 часа)
        System.out.println(now1);  // 2021-10-28T21:44:57

        // Получение МЕТКИ ВРЕМЕНИ из даты:
        LocalDateTime now3 = LocalDateTime.now();
        System.out.println(now3.toEpochSecond(ZoneOffset.ofHours(3)));  // 1635447372

    }
}
