public class PhysicalPerson extends Client {



    @Override
    protected void printInformation() {
        System.out.println("условие пополнения: " + getDepositCommission(100) + " %");
        System.out.println("условие снятия: " + getWithdrawalCommission(100) + " %");
        System.out.println("Сумма на счету: " + getAmount());

    }

    @Override
    protected double getWithdrawalCommission(double amount) {
        return 0;
    }

    @Override
    protected double getDepositCommission(double amount) {
        return 0;
    }
}
