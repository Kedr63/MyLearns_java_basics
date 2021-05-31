import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Очистка номера и приведение к российскому формату")
class TestPhoneCleaner {

  /**
   * in/out streams to swap System in out
   */
  private ByteArrayOutputStream outContent;
  private ByteArrayInputStream inContent;
  /**
   * original in/out streams links
   */
  private static final PrintStream originalOut = System.out;
  private static final InputStream originalIn = System.in;
  /**
   * string when phone number not valid
   */
  private static final String NOT_VALID_PHONE_RESPONSE = "Неверный формат номера\n";
  /**
   * string at which the testing program will terminate
   */
  private static final String EXIT_CODE = "0";
  /**
   * os dependent line separator
   */
  private static final String LS = System.lineSeparator();
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
    System.setIn(originalIn);
  }

  @Test
  @DisplayName("Номер = 11 цифр, код страны 7 (разделитель тире)")
  void simpleCleanPhoneSeparateDash() {
    launchApplication(
        collectTestInput("+7 909 123-45-67"),
        collectTestOutput("79091234567"));
  }

  @Test
  @DisplayName("Номер = 11 цифр, код страны 7 (разделитель скобки)")
  void simpleCleanPhoneSeparateBracket() {
    launchApplication(
        collectTestInput("+7 (909) 1234567"),
        collectTestOutput("79091234567"));
  }

  @Test
  @DisplayName("Номер = 11 цифр, первый символ 8")
  void cleanPhoneInnerCountryCode() {
    launchApplication(
        collectTestInput("8-905-1234567"),
        collectTestOutput("79051234567"));
  }

  @Test
  @DisplayName("Номер = 11 цифр, код страны не 7 или 8")
  void countryCodeNotRu() {
    launchApplication(
        collectTestInput("9-453-1234567"),
        NOT_VALID_PHONE_RESPONSE);
  }

  @Test
  @DisplayName("Номер = 10 цифр, добавляется код 7")
  void phoneNumberWithoutCountryCode() {
    launchApplication(
        collectTestInput("905-1234567"),
        collectTestOutput("79051234567"), "905-1234567 -> 79051234567");
  }

  @Test
  @DisplayName("Номер < 10 цифр")
  void phoneNumberTooShort() {
    launchApplication(
        collectTestInput("8-905-123"),
        NOT_VALID_PHONE_RESPONSE);
  }

  @Test
  @DisplayName("Номер > 11 цифр")
  void phoneNumberTooLong() {
    launchApplication(
        collectTestInput("8-905-12345672342"),
        NOT_VALID_PHONE_RESPONSE);
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

    List<String> errLines = lines.stream()
        .filter(line -> line.matches(".*(split|matches|Pattern|Matcher|replace).*"))
        .collect(Collectors.toList());

    assertFalse(errLines.isEmpty(),
        "\nВы не использовали регулярные выражения\n");
  }

  /**
   * call user method
   *
   * @param userInputMock - string lines imitate real user input
   * @param expected      - expected console output
   * @param message       - message if test has failed
   */

  private void launchApplication(String userInputMock, String expected, String message) {
    provideInput(userInputMock);
    assertTimeoutPreemptively(
        Duration.ofSeconds(TEST_TIMEOUT_SECONDS),
        () -> Main.main(new String[0]));
    assertEquals(expected.strip(), outContent.toString().strip(), message);
  }

  /* overload    */

  private void launchApplication(String userInputMock, String expected) {
    launchApplication(userInputMock, expected, null);
  }

  /*
  Change default System.in and fill with strings to imitate user input
  @param data - string for application input
   */
  private void provideInput(String data) {
    inContent = new ByteArrayInputStream(data.getBytes());
    System.setIn(inContent);
  }

  /*
  Construct string with lines separators and EXIT_CODE which signal that application must stop
  @param cases - strings, emulated user inputs for application
   */
  private String collectTestInput(String... cases) {
    return collectTestOutput(cases)
        .concat(EXIT_CODE);
  }

    /*
  Construct string with lines separators, use for base string actual, expected
  @param cases - strings, input/output for each line
   */

  private String collectTestOutput(String... cases) {
    return String.join(LS, cases)
        .concat(LS);
  }

}
