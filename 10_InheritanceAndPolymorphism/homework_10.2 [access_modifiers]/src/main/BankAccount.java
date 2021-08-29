package main;

public class BankAccount {

    private double amount;      // сделаем private, т.к. поле принадлежит экземпляру класса

    public double getAmount() {
        return amount;
    }

    // защитим *protected*, чтобы метод был доступен в пакете и в наследниках
    protected void put(double amountToPut) {
        if (amountToPut <= 0) {
            System.out.println("Сумма должна быть больше нуля");
        } else amount += amountToPut;
    }

    // защитим *protected*, чтобы метод был доступен в пакете и в наследниках
    protected void take(double amountToTake) {
        if (amountToTake > amount || amountToTake <= 0) {
            System.out.println("Сумма не соответствует требуемому значению");
        } else amount -= amountToTake;
    }

    // защитим *protected*, чтобы метод был доступен в пакете и в наследниках
    protected boolean send(BankAccount receiver, double amount) {
        double beforeTakeAmount = getAmount();
        double beforePutAmount = receiver.getAmount();
        take(amount);
        if (getAmount() < beforeTakeAmount) {
            receiver.put(amount);
        }
        return receiver.getAmount() > beforePutAmount;
    }


}


