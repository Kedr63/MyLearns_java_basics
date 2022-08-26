import core.Line;
import core.Station;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;
// в файле pom.xml нужно создать такую зависимость
/*<groupId>junit</groupId>
<artifactId>junit</artifactId>
<version>4.12</version>*/

// Создать тест:
// В папке /src/ создадим папку /test/ и  будет предлагаться создать ниже зеленую папку /test/java/ ->
// выбираем ее и создаем -> появится новая зеленая папка /java/
// Затем в классе, который будем тестить выделяем имя класса и ПКМ выбираем /Generate -> Test.../ ➔
// выбираем библиотеку, допустим Junit4 и снизу методы, которые будем тестить и в папке /java/
// появится класс для тестирования

public class RoutCalculatorTest extends TestCase { // обязательно расширяемся от этого класса
    // создадим маршрут
    List<Station> route;

    @Override
    protected void setUp() throws Exception { // позволяет инициализировать какие-то данные
        route = new ArrayList<>();
        // Создадим несколько линий
        Line line1 = new Line(1,"Первая"); // Название дали произвольное, можно брать и реальные примеры
        Line line2 = new Line(2, "Вторая");
        // И добавим сюда 4 станции
        route.add(new Station("Петровская", line1));
        route.add(new Station("Арбузная", line1));
        route.add(new Station("Морковная", line2));
        route.add(new Station("Яблочная", line2));
        // В итоге ⬆ получаем 2.5 + 3.5 + 2.5 = 8.5 минут (между станциями одной линии -2,5мин, переход на другую линию -3,5 мин)
        // Маршрут создали, теперь нужно написать метод который будет тестировать наш метод /calculateDuration/
    }
    public void testCalculateDuration(){ // должны начинаться со слова /test/
        // у нас есть значение ⬇ ︎ которое мы получим (мы здесь ожидаем получить 8.5 мин)
        double actual = RouteCalculator.calculateDuration(route);
        double expected = 8.5;
        // Теперь вызовем метод класса /TestCase/, который проверяет совпадение двух значений, и
        // который в случае необходимости выдает нужный Exception
        assertEquals(expected, actual);
    }



    // ⬇ методы которые позволяют удалять данные после проведения тестов
    // например в базе данных данные какие-то тестировали
    @Override
    protected void tearDown() throws Exception {

    }
}
