public class PhysicalPerson extends Client {


    @Override
    protected void put(double amountToPut) {
        double amount = getAmount();
        if (amountToPut <= 0) {
            System.out.println("Сумма должна быть больше нуля");
        } else amount += amountToPut;
        setAmount(amount);
    }

    @Override
    protected void take(double amountToTake) {
        double amount = getAmount();
        if (amountToTake > amount || amountToTake <= 0) {
            System.out.println("Сумма не соответствует требуемому значению");
        } else amount -= amountToTake;
        setAmount(amount);
    }

    @Override
    protected void printInformation() {
        System.out.println("условие пополнения: без комиссии");
        System.out.println("условие снятия: без комиссии");
        System.out.println("Сумма на счету: " + getAmount());

    }
}
