public class LegalPerson extends Client {

    // объявим константу - снятие 1% с суммы к снятию
    protected static final double DEBIT_ONE_PERCENT_OF_AMOUNT_TO_TAKE = 1.01;

    @Override
    protected void put(double amountToPut) {
        super.put(amountToPut);
    }

    @Override
    protected void take(double amountToTake) {
        super.take(amountToTake* DEBIT_ONE_PERCENT_OF_AMOUNT_TO_TAKE);
    }

    @Override
    protected void printInformation() {
        System.out.println("условие пополнения: без комиссии");
        // с помощью (const * 100 - 100 ) приведем константу к виду в %
        System.out.println("условие снятия: " + (DEBIT_ONE_PERCENT_OF_AMOUNT_TO_TAKE * 100 - 100) + " %");
        System.out.println("Сумма на счету: " + getAmount());
    }
}
