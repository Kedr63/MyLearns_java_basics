package main;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DepositAccount extends BankAccount {
    // *public* , т.к. константа статическая
    public static final int AMOUNT_MONTH = 1;

    // сделаем *private*, т.к. эти переменные должны устанавливаться только этим классом
    private Calendar lastIncome = Calendar.getInstance();
    private DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");

    // защитим *protected*, чтобы метод был доступен в пакете и в наследниках
    protected void put(double amountToPut, int year, int month, int day) {
        super.put(amountToPut);
        lastIncome.set(year,month - 1, day);
        System.out.println("Дата открытия депозита " + dateFormat.format(lastIncome.getTime()));
    }

    // защитим *protected*, чтобы метод был доступен в пакете и в наследниках
    protected void take(double amountToTake) {
        Date dateNow = new Date();
        lastIncome.add(Calendar.MONTH, AMOUNT_MONTH);
        if (lastIncome.getTime().before(dateNow)) {
            super.take(amountToTake);
        } else
        System.out.println("Нельзя снять с депозита раньше " + AMOUNT_MONTH +
                    " месяца(ев) после последнего пополнения");
        lastIncome.add(Calendar.MONTH, - AMOUNT_MONTH); // вернем дату открытия к изначальному состоянию
    }
}
