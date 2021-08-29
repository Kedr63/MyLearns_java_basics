import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LegalPersonTest {

    private static final double DELTA = 0.001;
    private static final String notExpectedSumMessage = "Сумма на счете не соответствует ожидаемой";
    
    private LegalPerson legalPerson;

    @BeforeEach
    public void setUp() {
        legalPerson = new LegalPerson();
    }

    @Test
    @DisplayName("Метод put")
    void put() {
        legalPerson.put(legalPerson,1.0);
        assertEquals(1.0, legalPerson.getAmount(), DELTA, notExpectedSumMessage);
    }

    @Test
    @DisplayName("Метод put, попытка вызвать метод с отрицательной суммой (баланс не должен измениться)")
    void putNegativeAmount() {
        legalPerson.put(legalPerson,-1.0);
        assertEquals(0.0, legalPerson.getAmount(), DELTA, notExpectedSumMessage);
    }

    @Test
    @DisplayName("Метод take")
    void take() {
        legalPerson.put(legalPerson,2.0);
        legalPerson.take(legalPerson,1.0);
        assertEquals(0.99, legalPerson.getAmount(), DELTA, notExpectedSumMessage);
    }

    @Test
    @DisplayName("Метод take, попытка снять со счета денег больше, чем на счете имеется")
    void takeTooMuchMoney() {
        legalPerson.put(legalPerson,1.0);
        legalPerson.take(legalPerson, 3.0);
        assertEquals(1.0, legalPerson.getAmount(), DELTA, notExpectedSumMessage);
    }
}