import java.util.Map;
import java.util.stream.Collectors;

public class Main {

    public static final String PATH = "src/main/resources/movementList.csv";

    public static void main(String[] args) {

        Movements movements = new Movements(PATH);

        System.out.printf("%-20s \t %-10.2f  руб.\n", "Общая сумма расходов:", movements.getExpenseSum());
        System.out.printf("%-20s \t %-10.2f  руб.\n", "Общая сумма доходов:", movements.getIncomeSum());

        Map<String, Double> stringDoubleMap = movements.loadMovements(PATH)
                .stream()
                .collect(Collectors.groupingBy(Transaction::getTypeOperation,
                        Collectors.summingDouble(Transaction::getAmountExpense)));

        System.out.println("\n" + "Суммы расходов по организациям: ");
        for (Map.Entry<String, Double> entry : stringDoubleMap.entrySet()) {
            System.out.printf("Операция: %-30s \t %-8.2f\t руб.\n", entry.getKey(), entry.getValue());
        }
    }
}
