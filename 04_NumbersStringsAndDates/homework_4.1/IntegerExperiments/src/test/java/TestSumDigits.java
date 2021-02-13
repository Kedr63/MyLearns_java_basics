
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Test for homework M4 Sum of Digits
 */

@DisplayName("М4 - получение суммы цифр числа")
public class TestSumDigits {


  @Test
  @DisplayName("Передано положительноe число 12345")
  void testPositiveNotZeroNumber() {
    assertEquals(15, Main.sumDigits(12345));
  }

  @Test
  @DisplayName("Передано положительноe число 20000")
  void testPositiveWithZeroNumber() {
    assertEquals(2, Main.sumDigits(20000));
  }

  @Test
  @DisplayName("Передан 0")
  void testZeroNumber() {
    assertEquals(0, Main.sumDigits(0));
  }

  @Test
  @DisplayName("Передан null")
  void testNullNumber() {
    assertEquals(-1, Main.sumDigits(null));
  }
}
