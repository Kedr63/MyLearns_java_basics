import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Операции с массивами - изменение порядка элементов")
class ReverseArrayTest {

    @Test
    @DisplayName("Метод возвращает перевернутый массив")
    void reverseNotEmptyArray() {
        String[] testArray = {"1", "2", "3", "4", "5", "6", "7", "aa", "bb", "ccc", "ddd", "eee", "fff", "ggg", "hhh"};
        String[] expected = {"hhh", "ggg", "fff", "eee", "ddd", "ccc", "bb", "aa", "7", "6", "5", "4", "3", "2", "1"};
        assertEquals(Arrays.toString(expected), Arrays.toString(ReverseArray.reverse(testArray)));
    }

    @Test
    @DisplayName("Проверка, вернул ли метод тот же самый массив")
    void isReturnSameArray() {
        String[] testArray = {"1", "2", "3"};
        assertTrue(testArray == ReverseArray.reverse(testArray),
                "Необходимо изменить порядок в переданном массиве, и вернуть его же");
    }

    @Test
    @DisplayName("Проверка создается ли новый массив")
    void checkDuplicateArrayCreation() {
        final List<String> lines = new ArrayList<>();
        try {
            Path path = Paths.get("src", "main", "java", "ReverseArray.java");
            lines.addAll(Files.readAllLines(path));
        } catch (IOException e) {
            System.err.println("Не найден файл ReverseArray.java");
            e.printStackTrace();
        }

        Map<Integer, String> errLines = lines.stream()
                .filter(line -> line.matches(".*(new String\\[).*"))
                .collect(Collectors.toMap(lines::indexOf, s -> s));

        assertTrue(errLines.isEmpty(),
                "\nДля изменения порядка, используйте существующий массив, не создавая дубликат.");
    }
}