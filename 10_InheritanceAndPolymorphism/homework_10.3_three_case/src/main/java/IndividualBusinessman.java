public class IndividualBusinessman extends Client {

    // объявим константу - сумма когда процент меняется
    protected static final double AMOUNT_WHEN_PERCENT_CHANGE = 1000;

    // объявим константу - снятие 0,5% с суммы к пополнению
    protected static final double DEBIT_HALF_PERCENT_OF_AMOUNT_TO_PUT = 0.995;

    // объявим константу - снятие 1% с суммы к пополнению
    protected static final double DEBIT_ONE_PERCENT_OF_AMOUNT_TO_PUT = 0.99;

    // объявим константу - снятие 1% с суммы к снятию
    protected static final double DEBIT_ONE_PERCENT_OF_AMOUNT_TO_TAKE = 1.01;


    @Override
    protected void printInformation() {
        System.out.println("условие пополнения до " + AMOUNT_WHEN_PERCENT_CHANGE +
                ": " + getDepositCommission(100) + " %");
        System.out.println("условие пополнения больше " + AMOUNT_WHEN_PERCENT_CHANGE +
                ": " + getDepositCommission(AMOUNT_WHEN_PERCENT_CHANGE) /10+ " %");
        System.out.println("условие снятия: " + getWithdrawalCommission(100) + " %");
        System.out.println("Сумма на счету: " + getAmount());

    }

    @Override
    protected double getWithdrawalCommission(double amount) {
        return (amount * DEBIT_ONE_PERCENT_OF_AMOUNT_TO_TAKE) - amount;
    }

    @Override
    protected double getDepositCommission(double amount) {
        double amountCommission = 0;
        if (amount > 0 && amount < AMOUNT_WHEN_PERCENT_CHANGE) {
            amountCommission = amount - (amount * DEBIT_ONE_PERCENT_OF_AMOUNT_TO_PUT);
        }
        if (amount >= AMOUNT_WHEN_PERCENT_CHANGE) {
            amountCommission = amount - (amount * DEBIT_HALF_PERCENT_OF_AMOUNT_TO_PUT);
        }
        return amountCommission;
    }
}
