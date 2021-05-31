import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {
        // Изучим еще одну разновидность коллекций - Map
        // В Map может храниться соответствие ключей неким значениям, при этом ключи должны быть уникальные, ключи хранятся в Set

        //HashMap<K,V>  K -ключ   V - значение

        // Например, есть список покупок (с консоли будем вводить товары):
           //String- имя товара; Integer- количество

        HashMap<String, Integer> good2count = new HashMap<>();

        // TreeMap<String, Integer> good2count = new TreeMap<>();  если сделаем так, то все товары выведутся в алфавитном порядке

        Scanner scanner = new Scanner(System.in);
        for (; ; ) {
            String goodName = scanner.nextLine();
            if (goodName.equals("LIST")) {
                mapPrint(good2count);
                continue;              // означает, что игнорируем код ниже и идем на следующую итерацию цикла
            }
            int count = 1;
            if (good2count.containsKey(goodName)) {
                count = good2count.get(goodName) + 1;     //.get возвращает V (здесь - количество)
            }
            good2count.put(goodName, count);
        }
    }

    private static void mapPrint(Map<String, Integer> map) {   //создадим метод для печати Map, чтоб не загромождать основной код
        for (String key : map.keySet()) {
            System.out.println(key + " => " + map.get(key));  // так перебераем ключи Map (чаще встречается)
        }

        /*Set<String> keys = map.keySet();
        for (String key : keys){
            System.out.println(key + " => " + map.get(key));  // так тоже перебераем ключи Map
        }*/


    }
    //консоль:
    /*сок
      молоко
      сок
      LIST
    молоко => 1
    сок => 2   */

    //Какие методы есть у Map:
    // good2count.put(String key, Integer value) - добавляет
    // good2count.get(Object key) - получает ключ и выдает значение (V) Integer
    // good2count.remove(Object key) - удалить определенный ключ и ссответствующее ему значение
}
