import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;

@DisplayName("Сортировка по заработной плате и алфавиту")
public class TestSortBySalaryAndAlphabet {

    @Test
    @DisplayName("Входные данные - staff.txt")
    void sort() {
        List<Employee> actualStaff = Employee.loadStaffFromFile("data/staff.txt");
        List<Employee> expectedStaff = Employee.loadStaffFromFile("data/sortedStaff.txt");
        Main.sortBySalaryAndAlphabet(actualStaff);
        assertIterableEquals(expectedStaff, actualStaff, "сортировка выполнена не по условию");
    }

}
