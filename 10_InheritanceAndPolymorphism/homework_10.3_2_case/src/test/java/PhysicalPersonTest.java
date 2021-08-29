import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PhysicalPersonTest {

    private static final double DELTA = 0.01;
    private static final String notExpectedSumMessage = "Сумма на счете не соответствует ожидаемой";
    
    private PhysicalPerson physicalPerson;

    @BeforeEach
    public void setUp() {
        physicalPerson = new PhysicalPerson();
    }

    @Test
    @DisplayName("Метод put")
    void put() {
        physicalPerson.put(1.0);
        assertEquals(1.0, physicalPerson.getAmount(), DELTA, notExpectedSumMessage);
    }

    @Test
    @DisplayName("Метод put, попытка вызвать метод с отрицательной суммой (баланс не должен измениться)")
    void putNegativeAmount() {
        physicalPerson.put(-1.0);
        assertEquals(0.0, physicalPerson.getAmount(), DELTA, notExpectedSumMessage);
    }


    @Test
    @DisplayName("Метод take")
    void take() {
        physicalPerson.put(2.0);
        physicalPerson.take(1.0);
        assertEquals(1.0, physicalPerson.getAmount(), DELTA, notExpectedSumMessage);
    }

    @Test
    @DisplayName("Метод take, попытка снять со счета денег больше, чем на счете имеется")
    void takeTooMuchMoney() {
        physicalPerson.put(2.0);
        physicalPerson.take(3.0);
        assertEquals(2.0, physicalPerson.getAmount(), DELTA, notExpectedSumMessage);
    }
}