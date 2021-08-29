public class PhysicalPerson extends Client {


    @Override
    protected void put(double amountToPut) {
        super.put(amountToPut);
    }

    @Override
    protected void take(double amountToTake) {
        super.take(amountToTake);
    }

    @Override
    protected void printInformation() {
        System.out.println("условие пополнения: без комиссии");
        System.out.println("условие снятия: без комиссии");
        System.out.println("Сумма на счету: " + getAmount());

    }
}
