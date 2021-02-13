import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Расчет суммы заработка всех друзей")
public class TestEarningsCalculator {

  private ByteArrayOutputStream outContent;
  /**
   * original out streams links
   */
  private static final PrintStream originalOut = System.out;
  /**
   * string when sum is wrong
   */
  private static final String WRONG_SUM = "Неверная сумма заработков\n";
  /**
   * max time to execute each test, on occasion infinity loop
   */
  private static final int TEST_TIMEOUT_SECONDS = 10;

  @BeforeEach
  public void setUpStreams() {
    outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));
  }

  @AfterEach
  void restoreStreams() {
    System.setOut(originalOut);
  }

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

    Map<Integer, String> errLines = lines.stream()
        .filter(line -> line.matches(".*(split|matches|Pattern|Matcher).*"))
        .collect(Collectors.toMap(lines::indexOf, s -> s));

    String message = errLines.entrySet().stream()
        .map(entry -> "Строка " + entry.getKey() + " - <" + entry.getValue() + ">")
        .collect(Collectors.joining("\n"));

    assertTrue(errLines.isEmpty(),
        "\nВы использовали методы для регулярных выражений в строках\n" + message);
  }

  @Test
  @DisplayName("Текст = Вася заработал 5000 рублей, Петя - 7563 рубля, а Маша - 30000 рублей")
  void calculationTotalIncome() {
    launchApplication("42563");
  }

  /**
   * call user method
   */

  private void launchApplication(String expected) {
    assertTimeoutPreemptively(
        Duration.ofSeconds(TEST_TIMEOUT_SECONDS),
        () -> Main.main(new String[0]));
    assertEquals(expected, outContent.toString().strip(), WRONG_SUM);
  }

}