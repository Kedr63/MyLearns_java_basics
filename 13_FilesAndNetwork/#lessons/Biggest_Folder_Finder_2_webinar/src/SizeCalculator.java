import java.io.File;
import java.util.HashMap;

public class SizeCalculator {
    public static final char[] sizeUnitOfMeasurements =
            {'B', 'K', 'M', 'G', 'T'};
    private final static HashMap<Character, Integer>
            char2size = getSizeUnitOfMeasurements();

    // Задание из вебинара: создать два метода
    public static String getHumanReadableSize(long size) {
        for (int i = 0; i < sizeUnitOfMeasurements.length; i++) {
            double value = ((double) size) / Math.pow(1024, i);
            if (value < 1024) {
                return Math.round(value * 100) / 100. + "" + sizeUnitOfMeasurements[i] + (i > 0 ? "b" : "");
                // ⬆ Складывая объекты класса String с объектами других классов, мы приводим последние
                // к строковому виду. Преобразование объектов других классов к строковому представлению
                // выполняется через неявный вызов метода toString у объекта
                // (https://javarush.ru/groups/posts/2347-klass-string-v-java)
                // Поэтому здесь применили /""/, и их можно было поставить и вначале, главное применить
                // строковый символ (здесь пустой) чтоб привести объекты других классов к строковому виду
                // это такой хитрый способ
                // Округление: значение умн на 100 и делим на 100 чтоб было 2 знака после запятой
            }
        }
        return "Very big";
    }

    public static long getSizeFromHumanReadable(String size) {
        char sizeFactor = size.replaceAll("[0-9\\.\\s+]+", "")
                .charAt(0);
        int multiplier = char2size.get(sizeFactor);
        return multiplier * Long.parseLong(size.replaceAll("[^0-9]", ""));
    }

    private static HashMap<Character, Integer> getSizeUnitOfMeasurements() {
        HashMap<Character, Integer> char2size = new HashMap<>();
        for (int i = 0; i < sizeUnitOfMeasurements.length; i++) {
            char2size.put(sizeUnitOfMeasurements[i], (int) Math.pow(1024, i));
        }
        return char2size;
    }
}
