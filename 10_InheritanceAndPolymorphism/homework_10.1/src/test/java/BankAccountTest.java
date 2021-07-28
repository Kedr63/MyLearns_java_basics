import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("М6 — тест класса BankAccount")
public class BankAccountTest {

    public static final double DELTA = 0.01;

    private static final String notExpectedSumMessage = "Сумма на счете не соответствует ожидаемой";
    private BankAccount bankAccount;

    @BeforeEach
    public void setUp() {
        bankAccount = new BankAccount();
    }

    @Test
    @DisplayName("Метод put")
    void put() {
        bankAccount.put(1.0);
        assertEquals(1.0, bankAccount.getAmount(), DELTA, notExpectedSumMessage);
    }

    @Test
    @DisplayName("Метод put, попытка вызвать метод с отрицательной суммой (баланс не должен измениться)")
    void putNegativeAmount() {
        bankAccount.put(-1.0);
        assertEquals(0.0, bankAccount.getAmount(), DELTA, notExpectedSumMessage);
    }

    @Test
    @DisplayName("Метод take")
    void take() {
        bankAccount.put(2.0);
        bankAccount.take(1.0);
        assertEquals(1.0, bankAccount.getAmount(), DELTA, notExpectedSumMessage);
    }

    @Test
    @DisplayName("Метод take, попытка снять со счета денег больше, чем на счете имеется")
    void takeTooMuchMoney() {
        bankAccount.put(2.0);
        bankAccount.take(3.0);
        assertEquals(2.0, bankAccount.getAmount(), DELTA, notExpectedSumMessage);
    }
}