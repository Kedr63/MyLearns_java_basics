import java.util.Random;

public class Manager extends Staffer implements Employee {

    // объявим переменые класса для работы при расчете зарплаты

    // мин сумма, заработанная для компании менеджером
    private final static int minAmountOfMoneyEarnedForCompany = 115000;

    // макс сумма, заработанная для компании менеджером
    private final static int maxAmountOfMoneyEarnedForCompany = 140000;

    // бонус 5% от суммы, заработ-х // для компании менеджером
    private final static double bonusOfAmountMoneyEarnedForCompany = 0.05;


    @Override
    public String toString() {
        return "Менеджер " + "з/п: " + getMonthSalary() + ", компания: "+ getCompany()+"\n";
    }

    // оверайдим метод с интерфейса: получим значение зарплаты Net (зарплата к выплате за месяц)
    @Override
    public int getMonthSalary() {
        int salary = getFixSalary() + (int) ((double) getGenerateAmountOfMoneyEarned()
                * bonusOfAmountMoneyEarnedForCompany);
        setSalary(salary); // (фиксОплата + заработСуммаДляКомпании * 5%) = з/п net
        return getSalary();
    }

    @Override
    public void setCompanyForEmployee(Company company) {
        setCompany(company);
    }

    @Override
    public void setFixSalaryEmployee(int amountFixSalary) {
        setFixSalary(amountFixSalary);
    }

    // получим сгенерируемую сумму заработанную для компании менеджером
    private static int getGenerateAmountOfMoneyEarned() {
        Random random = new Random();
        int diff = maxAmountOfMoneyEarnedForCompany - minAmountOfMoneyEarnedForCompany;
        return random.nextInt(diff + 1) + minAmountOfMoneyEarnedForCompany;
        // random number получаем в диапазоне [0 ÷ int x)
        // поэтому чтоб получить [0 ÷ int x]  -->
        // нужно сделать [0 ÷ (int x + 1)] -->(это для int)
        // И чтоб найти в дипазоне [min ÷ max]  -->
        // random number = random.nextInt((max - min) + 1) + min
    }
}
