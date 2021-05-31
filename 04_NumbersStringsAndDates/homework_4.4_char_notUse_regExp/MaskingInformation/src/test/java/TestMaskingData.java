import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Маскирование конфиденциальной информации")
public class TestMaskingData {

  public static final String EMPTY_STRING = "";
  public static final String PLACEHOLDER = "***";

  @Test
  @DisplayName("Текст = Номер кредитной карты <4008 1234 5678> 8912")
  void maskingCreditCard() {
    String expected = "Номер кредитной карты *** 8912";
    String actual = Main
        .searchAndReplaceDiamonds("Номер кредитной карты <4008 1234 5678> 8912", PLACEHOLDER);
    assertEquals(expected, actual);
  }

  @Test
  @DisplayName("Текст = Пин код <6160>")
  void maskingPinCode() {
    String expected = "Пин код +++";
    String actual = Main.searchAndReplaceDiamonds("Пин код <6160>", "+++");
    assertEquals(expected, actual);
  }

  @Test
  @DisplayName("Текст = Номер паспорта <45 08 361>513")
  void maskingPassportNumber() {
    String expected = "Номер паспорта ***513";
    String actual = Main.searchAndReplaceDiamonds("Номер паспорта <45 08 361>513", PLACEHOLDER);
    assertEquals(expected, actual);
  }

  @Test
  @DisplayName("Текст = Номер паспорта <<45 08 36513")
  void noMatchingBracket() {
    String expected = "Номер паспорта <<45 08 36513";
    String actual = Main.searchAndReplaceDiamonds(expected, PLACEHOLDER);
    assertEquals(expected, actual);
  }

  @Test
  @DisplayName("Текст = Номер паспорта 45 08 36513")
  void noBracketInput() {
    String expected = "Номер паспорта 45 08 36513";
    String actual = Main.searchAndReplaceDiamonds("Номер паспорта 45 08 36513", PLACEHOLDER);
    assertEquals(expected, actual);
  }

  @Test
  @DisplayName("Текст = \"\"")
  void emptyString() {
    String actual = Main.searchAndReplaceDiamonds(EMPTY_STRING, PLACEHOLDER);
    assertEquals(EMPTY_STRING, actual);
  }

  @Test
  @DisplayName("Задание со * = Номер кредитной карты <4008> 1234 <5678> 8912")
  void doubleBracket() {
    String expected = "Номер кредитной карты *** 1234 *** 8912";
    String actual = Main
        .searchAndReplaceDiamonds("Номер кредитной карты <4008> 1234 <5678> 8912", PLACEHOLDER);
    assertEquals(expected, actual);
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

}