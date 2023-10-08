import core.Line;
import core.Station;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RouteCalculatorTest {
    StationIndex krvMetro;
    List<Station> routeWithTwoConnections;
    RouteCalculator routeCalculator;


    @Before
    public void setUp() throws Exception {
        // Создадим небольшое метро в городе N для теста
        krvMetro = new StationIndex();

        routeCalculator = new RouteCalculator(krvMetro);

        // Линии метро
        Map<Integer, String> lines = Map.of(1, "Красная", 2, "Синяя", 3, "Зеленая");

        // Станции
        Map<String, Integer> stations = new LinkedHashMap<>(); // нужен порядок станций как при добавлении
        stations.put("Верхняя", 1);
        stations.put("Промежуточная", 1);
        stations.put("Средняя", 1);
        stations.put("Полусредняя", 1);
        stations.put("Нижняя", 1);
        stations.put("Утренняя", 2);
        stations.put("Вечерняя", 2);
        stations.put("Ночная", 2);
        stations.put("Солнечная", 2);
        stations.put("Тенистая", 2);
        stations.put("Засушливая", 3);
        stations.put("Туманная", 3);
        stations.put("Ветренная", 3);
        stations.put("Прохладная", 3);
        stations.put("Дождливая", 3);

        // Добавим линии к метро
        for (Map.Entry<Integer, String> entryLine : lines.entrySet()) {
            krvMetro.addLine(new Line(entryLine.getKey(), entryLine.getValue()));
        }

        // Добавим станции к линиям и добавим станции к метро
        for (Map.Entry<String, Integer> entryStation : stations.entrySet()) {
            krvMetro.getLine(entryStation.getValue())
                    .addStation(new Station(entryStation.getKey(), krvMetro.getLine(entryStation.getValue())));
            krvMetro.addStation(new Station(entryStation.getKey(), krvMetro.getLine(entryStation.getValue())));
        }

        // Добавим переходы к метро
        krvMetro.addConnection(List.of(krvMetro.getStation("Средняя"), krvMetro.getStation("Солнечная")));
        krvMetro.addConnection(List.of(krvMetro.getStation("Ветренная"), krvMetro.getStation("Вечерняя")));
        // Метро для теста создано //

        //создадим маршрут с двумя пересадками
        routeWithTwoConnections = new ArrayList<>();
        routeWithTwoConnections.add(krvMetro.getStation("Промежуточная"));
        routeWithTwoConnections.add(krvMetro.getStation("Средняя"));
        routeWithTwoConnections.add(krvMetro.getStation("Солнечная"));
        routeWithTwoConnections.add(krvMetro.getStation("Ночная"));
        routeWithTwoConnections.add(krvMetro.getStation("Вечерняя"));
        routeWithTwoConnections.add(krvMetro.getStation("Ветренная"));
        routeWithTwoConnections.add(krvMetro.getStation("Прохладная"));
    }


    @Test
    @DisplayName("Получить маршрут на одной линии")
    public void testGetShortestRouteOnTheLine() {

        List<Station> actual = routeCalculator.getShortestRoute(krvMetro.getStation("Верхняя"), krvMetro.getStation("Средняя"));

        List<Station> expected = List.of(krvMetro.getStation("Верхняя"), krvMetro.getStation("Промежуточная"),
                krvMetro.getStation("Средняя"));

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Получить маршрут с одной пересадкой линии")
    public void testGetShortestRouteOneConnections() {

        List<Station> actual = routeCalculator.getShortestRoute(krvMetro.getStation("Промежуточная"),
                krvMetro.getStation("Ночная"));
        List<Station> expected = List.of(krvMetro.getStation("Промежуточная"), krvMetro.getStation("Средняя"),
                krvMetro.getStation("Солнечная"), krvMetro.getStation("Ночная"));
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Получить маршрут с двумя пересадками линии")
    public void testGetShortestRouteWithTwoConnections() {

        // маршрут уже создан в методе setUp()
        List<Station> actual = routeCalculator.getShortestRoute(krvMetro.getStation("Промежуточная"), krvMetro.getStation("Прохладная"));
        List<Station> expected = routeWithTwoConnections;
        assertEquals(expected, actual);
    }


    @Test
    @DisplayName("Проверить время маршрута")
    public void testCalculateDuration() {

        double actual = RouteCalculator.calculateDuration(routeWithTwoConnections);
        double expected = 17.0;
        assertEquals(expected, actual);
    }

}