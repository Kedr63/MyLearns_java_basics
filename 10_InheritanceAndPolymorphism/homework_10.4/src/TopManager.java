public class TopManager extends Staffer implements Employee {

    private final static double bonusForTopManager = 1.5; // 150% премия

    private final static int amountIncomeCompanyForBonusTopManager = 10_000_000;

    @Override
    public String toString() {
        return "TopManager " + "з/п: " + getMonthSalary() + ", компания: " + getCompany() + "\n";
    }

    @Override
    public int getMonthSalary() {
        if (getCompany().getIncome() > amountIncomeCompanyForBonusTopManager) {
            int salary = (int) ((double) getFixSalary() * bonusForTopManager);
            setSalary(salary);    // (фиксОплата + 150%) = з/п net, если доход компании > 10_000_000
            return getSalary();
        }
        setSalary(getFixSalary());
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
}
