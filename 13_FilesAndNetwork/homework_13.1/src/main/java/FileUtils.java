import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class FileUtils {
    private static final Character[] sizeBytes = new Character[]{'b', 'K', 'M', 'G'};
    private static final Map<Character, Integer> char2Size = getByteToSizeMapping(); // создадим map где,
    // К - символ размера, а V - количество байт в этом символе размера
    private static final long KILOBYTE = 1024; // шаг масштабирования размерности байтов

    public static long calculateFolderSize(String path) {
        File file = new File(path);
        if (!file.exists()) {
            throw new IllegalArgumentException("Такая директория или файл не существует");
        }
        if (file.isFile()) {
            return file.length();
        }
        long sum = 0;
        File[] files = file.listFiles();
        for (File fileOfList : files) {
            sum += calculateFolderSize(fileOfList.toString()); // применим рекурсию для обхода всех папок и файлов
        }
        return sum;
    }

    public static String getHumanReadableSize(long size) {
        for (int i = 0; i < char2Size.size(); i++) {
            long scaleByteSize = char2Size.get(sizeBytes[i]);  // будет получать кол-во байт в масштабе размера /b->Kb->Mb->Gb/
            if (scaleByteSize > size / KILOBYTE) {
                return Math.round(((double) size / (double) scaleByteSize) * 100) / 100.
                        + "" + sizeBytes[i] + (i > 0 ? "b" : "");
            }
        }
        return "очень большая папка";
    }

    // метод получения таблицы: символ размера <-> количество байт в размере
    public static Map<Character, Integer> getByteToSizeMapping() {
        Map<Character, Integer> char2size = new HashMap<>();
        for (int i = 0; i < sizeBytes.length; i++) {
            char2size.put(sizeBytes[i], (int) Math.pow(KILOBYTE, i));
        }
        return char2size;
    }
}

