public class BankAccount {

    double amount;      // переменная для хранения денежных средств на счете

    public double getAmount() {
        return amount;
    }

    public void put(double amountToPut) {
        if (amountToPut <= 0) {
            System.out.println("Сумма должна быть больше нуля");
        } else amount = amount + amountToPut;
    }

    public void take(double amountToTake) {
        if (amountToTake > amount || amountToTake <= 0) {
            System.out.println("Сумма не соответствует требуемому значению");
        } else amount = amount - amountToTake;
    }

    // метод для перевода денег м/у счетами
    boolean send(BankAccount receiver, double amount) {
        double beforeTakeAmount = getAmount();         // в этой перемен. сохраним сумму до снятия со счета отправителя
        double beforePutAmount = receiver.getAmount(); // в этой перемен. сохраним сумму до пополнения счета получателя
        take(amount);                                  // спишем деньги со счета отправителя
        if (getAmount() < beforeTakeAmount) {          // если со счета списались деньги (сумма уменьшилась)
            receiver.put(amount);                      // то пополняем счет получатель
        }
        return receiver.getAmount() > beforePutAmount; // и если счет получателя увеличился, то return true
    }
}


