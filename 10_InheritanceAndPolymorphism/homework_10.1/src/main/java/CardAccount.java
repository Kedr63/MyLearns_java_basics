public class CardAccount extends BankAccount {
    public static final double TAKE_PERCENTAGE = 1.01;

    @Override
    public void take(double amountToTake) {
        super.take(amountToTake * TAKE_PERCENTAGE);
    }
}
