import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DepositAccount extends BankAccount {
    public static final int AMOUNT_MONTH = 1;

    Calendar lastIncome = Calendar.getInstance();
    DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");

    // изменим род. метод -> add на вход дату пополнения депозита
    public void put(double amountToPut, int year, int month, int day) {
        super.put(amountToPut);
        lastIncome.set(year, month - 1, day); // set дату пополнения депозита (т.к. месяца начинаются с нуля , то -1)
        System.out.println("Дата открытия депозита " + dateFormat.format(lastIncome.getTime()));
    }

    public void take(double amountToTake) {
        Date dateNow = new Date();                  // создадим текущую дату
        lastIncome.add(Calendar.MONTH, AMOUNT_MONTH);   // увеличим дату открытия депозита на 1 месяц
        if (lastIncome.getTime().before(dateNow)) { // если (дата откр. + 1 мес.) перед текущей датой -> true, то
            super.take(amountToTake);               // вызовет род. метод снять средства
        } else
            System.out.println("Нельзя снять с депозита раньше " + AMOUNT_MONTH +
                    " месяца(ев) после последнего пополнения");
    }
}
