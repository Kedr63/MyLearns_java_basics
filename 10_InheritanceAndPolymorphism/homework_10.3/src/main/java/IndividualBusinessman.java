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
    protected void put(double amountToPut) {
        double amount = getAmount();
        if (amountToPut <= 0) {
            System.out.println("Сумма должна быть больше нуля");
        }
        if (amountToPut > 0 && amountToPut < AMOUNT_WHEN_PERCENT_CHANGE) {
            amount += amountToPut * DEBIT_ONE_PERCENT_OF_AMOUNT_TO_PUT;
            setAmount(amount);
        }
        if (amountToPut >= AMOUNT_WHEN_PERCENT_CHANGE) {
            amount += amountToPut * DEBIT_HALF_PERCENT_OF_AMOUNT_TO_PUT;
            setAmount(amount);
        }
    }

    @Override
    protected void take(double amountToTake) {
        double amount = getAmount();
        if (amountToTake > amount || amountToTake <= 0) {
            System.out.println("Сумма не соответствует требуемому значению");
        } else amount -= amountToTake * DEBIT_ONE_PERCENT_OF_AMOUNT_TO_TAKE;
        setAmount(amount);
    }

    @Override
    protected void printInformation() {
        // с помощью (const * 100 - 100 ) приведем константу к виду в %,
        // и с помощью Math.abs уберем минус у значения
        System.out.println("условие пополнения до " + AMOUNT_WHEN_PERCENT_CHANGE +
                ": " + Math.abs(DEBIT_ONE_PERCENT_OF_AMOUNT_TO_PUT * 100 - 100) + " %");
        System.out.println("условие пополнения больше " + AMOUNT_WHEN_PERCENT_CHANGE +
                ": " + Math.abs(DEBIT_HALF_PERCENT_OF_AMOUNT_TO_PUT * 100 - 100) + " %");
        System.out.println("условие снятия: " + (DEBIT_ONE_PERCENT_OF_AMOUNT_TO_TAKE * 100 - 100) + " %");
        System.out.println("Сумма на счету: " + getAmount());

    }
}
