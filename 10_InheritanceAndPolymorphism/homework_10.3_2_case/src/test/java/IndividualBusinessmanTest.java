import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IndividualBusinessmanTest {

    private static final double DELTA = 0.01;
    private static final String notExpectedSumMessage = "Сумма на счете не соответствует ожидаемой";
    
    private IndividualBusinessman individualBusinessman;

    @BeforeEach
    public void setUp() {
        individualBusinessman = new IndividualBusinessman();
    }

    @Test
    @DisplayName("Метод put, попытка положить сумму менее 1000")
    void putLess1000() {
        individualBusinessman.put(500.0);
        assertEquals(495.0, individualBusinessman.getAmount(), DELTA, notExpectedSumMessage);
    }

    @Test
    @DisplayName("Метод put, попытка положить 1000")
    void put1000() {
        individualBusinessman.put(1000.0);
        assertEquals(995.0, individualBusinessman.getAmount(), DELTA, notExpectedSumMessage);
    }

    @Test
    @DisplayName("Метод put, попытка положить сумму более 1000")
    void putMore1000() {
        individualBusinessman.put(2000.0);
        assertEquals(1990.0, individualBusinessman.getAmount(), DELTA, notExpectedSumMessage);
    }

    @Test
    @DisplayName("Метод put, попытка вызвать метод с отрицательной суммой (баланс не должен измениться)")
    void putNegativeAmount() {
        individualBusinessman.put(-1.0);
        assertEquals(0.0, individualBusinessman.getAmount(), DELTA, notExpectedSumMessage);
    }

    @Test
    @DisplayName("Метод take")
    void take() {
        individualBusinessman.put(500.0); //положилось 495
        individualBusinessman.take(1.0);
        assertEquals(494.0, individualBusinessman.getAmount(), DELTA, notExpectedSumMessage);
    }

    @Test
    @DisplayName("Метод take, попытка снять со счета денег больше, чем на счете имеется")
    void takeTooMuchMoney() {
        individualBusinessman.put(500.0); //положилось 495
        individualBusinessman.take(496.0);
        assertEquals(495.0, individualBusinessman.getAmount(), DELTA, notExpectedSumMessage);
    }
}