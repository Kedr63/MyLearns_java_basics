package main;

import java.util.Calendar;

public class Main {
    public static void main(String[] args) {
        BankAccount bankAccount = new BankAccount();
        bankAccount.put(10000);

        CardAccount cardAccount = new CardAccount();
        cardAccount.put(15000);

        DepositAccount depositAccount = new DepositAccount();
        depositAccount.put(20000, 2021, 6, 23);
        depositAccount.take(5500);
        depositAccount.take(700);
        depositAccount.take(400);

        // если \Calendar lastIncome\ будет не private, то можем с любого места пакета переустановить
        // дату открытия депозита:
        /* depositAccount.lastIncome.set(2021, Calendar.JULY, 15); */ // и это сработает
        // а нужно чтоб только класс DepositAccount имел возможность устанавливать
        // дату откр. депозита - поэтому \private Calendar lastIncome\


        System.out.println(depositAccount.send(cardAccount, 3000));
        System.out.println("на банк счете " + bankAccount.getAmount());
        System.out.println("на карт счете " + cardAccount.getAmount());
        System.out.println("на депозит счете " + depositAccount.getAmount());


    }
}
