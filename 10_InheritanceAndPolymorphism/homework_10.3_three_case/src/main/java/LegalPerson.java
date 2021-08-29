public class LegalPerson extends Client {

    // объявим константу - снятие 1% с суммы к снятию
    protected static final double DEBIT_ONE_PERCENT_OF_AMOUNT_TO_TAKE = 1.01;



    @Override
    protected void printInformation() {
        System.out.println("условие пополнения: " + getDepositCommission(100) + " %");
        System.out.println("условие снятия: " + getWithdrawalCommission(100) + " %");
        System.out.println("Сумма на счету: " + getAmount());
    }

    @Override
    protected double getWithdrawalCommission(double amount) {
        return (amount * DEBIT_ONE_PERCENT_OF_AMOUNT_TO_TAKE) - amount;
    }

    @Override
    protected double getDepositCommission(double amount) {
        return 0;
    }
}
