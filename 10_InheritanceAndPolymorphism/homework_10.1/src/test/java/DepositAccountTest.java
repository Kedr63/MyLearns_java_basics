import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.time.LocalDate;
import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("М6 — тест класса DepositAccount")
public class DepositAccountTest {

    public static final double DELTA = 0.001;

    private static final String notExpectedSumMessage = "Сумма на счете не соответствует ожидаемой";
    private BankAccount depositAccount;

    @BeforeEach
    public void setUp() {
        depositAccount = new DepositAccount();
    }

    @Test
    @DisplayName("Метод put")
    void put() {
        depositAccount.put(1.0);
        assertEquals(1.0, depositAccount.getAmount(), DELTA, notExpectedSumMessage);
    }

    @Test
    @DisplayName("Метод put, попытка вызвать метод с отрицательной суммой (баланс не должен измениться)")
    void putNegativeAmount() {
        depositAccount.put(-1.0);
        assertEquals(0.0, depositAccount.getAmount(), DELTA, notExpectedSumMessage);
    }


    @Test
    @DisplayName("Метод take, попытка снять деньги через месяц после пополнения")
    void takeInMonth() {
        depositAccount.put(2.0);
        rollBackTime(0, 1, 1);
        depositAccount.take(1.0);
        assertEquals(1.0, depositAccount.getAmount(), DELTA, notExpectedSumMessage);
    }

    @Test
    @DisplayName("Метод take, попытка снять деньги через год после пополнения")
    void takeInYear() {
        depositAccount.put(2.0);
        rollBackTime(1, 0, 0);
        depositAccount.take(1.0);
        assertEquals(1.0, depositAccount.getAmount(), DELTA, notExpectedSumMessage);
    }

    @Test
    @DisplayName("Метод take, попытка снять со счета денег больше, чем на счете имеется")
    void takeTooMuchMoney() {
        depositAccount.put(2.0);
        rollBackTime(0, 1, 1);
        depositAccount.take(3.0);
        assertEquals(2.0, depositAccount.getAmount(), DELTA, notExpectedSumMessage);
    }

    @Test
    @DisplayName("Метод take, попытка снять со счета деньги менее, чем через месяц после зачисления")
    void takeNow() {
        depositAccount.put(1.0);
        depositAccount.take(3.0);
        assertEquals(1.0, depositAccount.getAmount(), DELTA, notExpectedSumMessage);
    }

    private void rollBackTime(int year, int months, int days) {
        try {
            Field field = depositAccount.getClass().getDeclaredField("lastIncome");

            if ((field.getModifiers() & Modifier.FINAL) == Modifier.FINAL) {
                throw new IllegalAccessException();
            }

            field.setAccessible(true);

            if (field.getAnnotatedType().getType().getTypeName().equals("java.util.Calendar")) {
                Calendar future = Calendar.getInstance();
                future.add(Calendar.YEAR, -year);
                future.add(Calendar.MONTH, -months);
                future.add(Calendar.DAY_OF_YEAR, -days);
                field.set(depositAccount, future);
            } else if (field.getAnnotatedType().getType().getTypeName().equals("java.time.LocalDate")) {
                field.set(depositAccount, LocalDate.now().minusYears(year).minusMonths(months).minusDays(days));
            } else {
                Assert.fail("В классе отсутствует поле lastIncome типа LocalDate или Calendar");
            }

        } catch (NoSuchFieldException e) {
            Assert.fail("Поле lastIncome не найдено в классе DepositAccount.");
        } catch (IllegalAccessException e) {
            Assert.fail("Что-то не так с полем lastIncome. Оно final?");
        }
    }
}