package main;

public class CardAccount extends BankAccount {
    // *public* , т.к. константа статическая
    public static final double TAKE_PERCENTAGE = 1.01;

    // защитим *protected*, чтобы метод был доступен в пакете и в наследниках
    @Override
    protected void take(double amountToTake) {
        super.take(amountToTake * TAKE_PERCENTAGE);
    }
}
