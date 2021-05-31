import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class MainTreeMapLinkedHashMapAlishev {
    public static void main(String[] args) {
        // Если ходим упорядочить список то исп. LinkedHashMap и TreeMap
        // Класс LinkedHashMap сохраняет порядок добавления новых пар K-V в Map (какой добавили -такой и получили),
        // А класс TreeMap сортирует пары K-V по (К) (в естественном порядке)
        //PS: (К) в Map не могут повторятся, а V могут повторятся

        // Создадим 3 объекта разных классов (будем ссылать объекты наших классов на интерфейс, который эти классы реализует):

        Map<Integer, String> hashMap = new HashMap<>();      // внутри не гарантируется никакого порядка

        // в каком порядке пары К-V были добавлены в таком порядке они и вернутся:
        Map<Integer, String> linkedHashMap = new LinkedHashMap<>();

        Map<Integer, String> treeMap = new TreeMap<>(); // пары K-V сортируются по ключу (естественный порядок)

        testMap(hashMap);
        //console:
//        0 : Tim
//        39 : Bob
//        7 : Bob
//        12 : Mike
//        1500 : Lewis
//        78 : Tom
        System.out.println();

        testMap(linkedHashMap);
        //console:
//        39 : Bob
//        12 : Mike
//        78 : Tom
//        0 : Tim
//        1500 : Lewis
//        7 : Bob
        System.out.println();

        testMap(treeMap);
        //console:
//        0 : Tim
//        7 : Bob
//        12 : Mike
//        39 : Bob
//        78 : Tom
//        1500 : Lewis

    }

    // создадим метод для тестирования
    public static void testMap(Map<Integer, String> map) {  //на вход он будет принимать объекты, которые реализуют интерфейс Map
        // в этот map в качестве (К) поместим случайное число, в качестве значения - допустим, будет имя
        map.put(39, "Bob");
        map.put(12, "Mike");      // (12, "Mike") - это entry
        map.put(78, "Tom");
        map.put(0, "Tim");
        map.put(1500, "Lewis");
        map.put(7, "Bob");

        for (Map.Entry<Integer, String> entry : map.entrySet()) {   //.entrySet() -получить все пары K-V
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
    // Изучили 3 разных класса реализующих интерфейс Map.
    // Все они представляют из себя одну структуру данных,
    // т.е. это структура данных -отображение, отображение Ключа-Значение (K-V).
}
