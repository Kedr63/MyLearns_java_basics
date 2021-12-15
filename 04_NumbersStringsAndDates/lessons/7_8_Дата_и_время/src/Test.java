import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Locale;

public class Test {
    // Дата и время

    public static void main(String[] args) {
        // из первого модуля уже знакомы с классом /Date/
        // и можем создать текущую дату
        Date now = new Date();
        System.out.println(now);   // Tue Oct 26 14:33:43 GMT+03:00 2021

        // Но существуют более удобные и современные классы которые появились в 8 Java:
        //❤️ LocalDate   - предоставляет работать с датой
        //❤️ LocalDateTime  - для работы с датой и временем

        // Есть несколько способов создания объектов этих классов содержащих определенную нужную нам дату
        // или дату и время.
        // Например, если нужно создать сегодняшний день
        LocalDate today = LocalDate.now();
        System.out.println(today);    // 2021-10-26

        // Если нужно получить текущее время
        LocalDateTime now1 = LocalDateTime.now();
        System.out.println(now1);    // 2021-10-26T14:45:28.896108

        // Можем создать свою дату
        LocalDate birthday = LocalDate.of(1976, 12, 31);
        System.out.println(birthday);   // 1976-12-31

        // Если хотим получить дату 18-го дня рождения ⬆ этого человека
        System.out.println(birthday.plusYears(18));  // 1994-12-31

        // Также эти классы позволяют работать с временными зонами, т/е с часовыми поясами
        // Допустим можем получить текущее время в Нью-Йорке
        LocalDateTime nowNY = LocalDateTime.now(ZoneId.of("America/New_York"));
        System.out.println(nowNY);  // 2021-10-26T07:56:04.871427

        // Если у нас дата и время содержатся в строке в определенном формате, то эти классы
        // предоставляют возможность преобразовать такую строку в объекты этих классов.
        // Такое преобразование, как и любое преобразование любого текста в некий объект
        // называется ПАРСИНГОМ
        // Например, есть строка содержащая дату и мы хотим ее превратить в объект
        String date = "23/01/2021";
        // для этого нужно создать объект класса /DateTimeFormatter/ и передать сюда формат этой даты
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        // и воспользоваться этим /formatter/-ом
        LocalDate localDate = LocalDate.parse(date, formatter);
        System.out.println(localDate);  // 2021-01-23

        // Методы парсинга, классов LocalDate и LocalDateTime, это очень удобный инструмент,
        // поскольку они при преобразовании строки в дату проверяют все тонкости связанные с
        // календарем, например существование определенной даты, например 29 февраля в определенный
        // год, если в строку записать 29/02/2021 (а такой даты нет в 2021 году), то эта дата
        // автоматически преобразуется в 28/02/2021.

        // Аналогично тому как мы можем пропарсить строку с датами в определенном формате в объект,
        // мы можем сделать и обратную операцию, т/е преобразовать объект в строку с датой и
        // временем в определенном формате. Это можно сделать с помощью того же форматора
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime now2 = LocalDateTime.now();
        System.out.println(formatter1.format(now2));  // 26/10/2021 15:27:08


        // Формат даты и времени принято выносить в отдельную константу. Чтобы упростить жизнь
        // разработчикам, придумали несколько предустановленных констант,которые можно передать
        // с помощью метода /ofLocalizedDate/ или /ofLocalizedDateTime/
        DateTimeFormatter formatter2 = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT);
        DateTimeFormatter formatter3 = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
        LocalDateTime now3 = LocalDateTime.now();
        System.out.println(formatter2.format(now3)); // 26.10.2021, 17:02
        System.out.println(formatter3.format(now3)); // 26 окт. 2021 г., 17:03:46

        // Если хотим изменить на американский формат:
        DateTimeFormatter formatter4 = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
                .localizedBy(new Locale("us"));
        System.out.println(formatter4.format(now3));  // 2021 Oct 26 17:06:31


        // 📌 Сравнение дат__________________________________________

        LocalDateTime time1 = LocalDateTime.now();
        LocalDateTime time2 = LocalDateTime.now()
                .minusDays(2);

        // Сравнить их можно с помощью 3-х методов /isBefore/  /isAfter/  /equals/:
        if (time1.isAfter(time2)) {
            System.out.println("time1 is after time2");   // time1 is after time2
        }

        // также можно использовать метод /compareTo/, если даты равны то ➔ /0/, если первая дата > чем
        // вторая дата ➔ /число положительное/, если наоборот ➔ /число отрицательное/
        System.out.println(time1.compareTo(time2));  // 2


        // 📌 Вычисление разницы м/у датами или датой и временем__________________________________________

        LocalDateTime time3 = LocalDateTime.now();
        LocalDateTime time4 = time3.minusDays(2);
        System.out.println(time3.until(time4, ChronoUnit.HOURS));  // разница в часах считаем
        // консоль: -48   (минус так как второй день раньше на два дня)


        // Из Date в LocalDateTime (от преподавателя)
        // https://stackoverflow.com/questions/9474121/i-want-to-get-year-month-day-etc-from-java-date-to-compare-with-gregorian-cal
    }
}
