import figures.Square;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {

        /* Интерфейсы_Map_и_Set */

        // В Java очень много разных интерфейсов и можем всегда почитать документацию, посмотреть какие
        // бывают интерфейсы. Кратко рассмотрим интерфейсы Map и Set
        // HashMap<>() TreeMap<>() - это все наследники интерфейса Map
        // HashSet<>() TreeSet<>() - это все наследники интерфейса  Set
        // Чем они похожи? Они похожи тем, что у них есть одинаковые методы, т.е. у всех Map-ов есть
        // метод put(), который позволяет положить туда K и V, и другие методы стандартные для всех
        // Map-ов. И конечно у Set-а есть аналогичные методы стандартные для всех Set-ов

        Map<String, String> map = new HashMap<>();
        map.put("s", "a");
        Set<String> set = new TreeSet<>();




    }

}
