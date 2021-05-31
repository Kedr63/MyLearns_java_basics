import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Расчет суммы заработка всех друзей с помощью регулярных выражений")
public class TestEarningsCalculator {

  @Test
  @DisplayName("Проверка используются ли методы регулярных выражений")
  void checkRegularExpression() {
    final List<String> lines = new ArrayList<>();
    try {
      Path path = Paths.get("src", "main", "java", "Main.java");
      lines.addAll(Files.readAllLines(path));
    } catch (IOException e) {
      e.printStackTrace();
    }

    List<String> errLines = lines.stream()
        .filter(line -> line.matches(".*(split|matches|Pattern|Matcher|replace).*"))
        .collect(Collectors.toList());

    assertFalse(errLines.isEmpty(),
        "\nВы не использовали регулярные выражения\n");
  }

  @Test
  @DisplayName("Текст = Вася заработал 5000 рублей, Петя - 7563 рубля, а Маша - 30000 рублей")
  void calculationTotalIncomeOption1() {
    int expected = 42563;
    int actual = Main
        .calculateSalarySum("Вася заработал 5000 рублей, Петя - 7563 рубля, а Маша - 30000 рублей");
    assertEquals(expected, actual);
  }

  @Test
  @DisplayName("Текст = Коля заработал 50000 рублей, Федя - 45800 рубля, а Саша - 23000 рублей")
  void calculationTotalIncomeOption2() {
    int expected = 118800;
    int actual = Main.calculateSalarySum(
        "Коля заработал 50000 рублей, Федя - 45800 рубля, а Саша - 23000 рублей");
    assertEquals(expected, actual);
  }

  @Test
  @DisplayName("Текст = Толя заработал 35000 рублей, а Лера - 45000 рублей")
  void calculationTotalIncomeOption3() {
    int expected = 80000;
    int actual = Main.calculateSalarySum("Толя заработал 35000 рублей, а Лера - 45000 рублей");
    assertEquals(expected, actual);
  }

  @Test
  @DisplayName("Текст = Никто ничего не заработал")
  void calculationTotalIncomeOption4() {
    int expected = 0;
    int actual = Main.calculateSalarySum("Никто ничего не заработал");
    assertEquals(expected, actual);
  }

}