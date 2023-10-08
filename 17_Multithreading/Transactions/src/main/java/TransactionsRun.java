import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TransactionsRun implements Runnable {

    private Bank bank;
    private List<Integer[]> transactions;
    private Random random = new Random();

    public TransactionsRun(Bank bank, int count) {
        this.bank = bank;
        this.transactions = new ArrayList<>();
        transactions = generateTransactions(count);
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public List<Integer[]> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Integer[]> transactions) {
        this.transactions = transactions;
    }

    protected List<Integer[]> generateTransactions(int count) {
        for (int i = 0; i < count; i++) {
            Integer[] arrayFrom2To = new Integer[3];
            // этот индекс - счет с которого происходит перевод
            arrayFrom2To[0] = (int) (random.nextInt(Main.AMOUNT_CLIENTS + 1) * 1.1); // диапазон ((0 - (кол-во клиентов)) + 10%), 10% не существующие счета
            // этот индекс - счет на который производится перевод
            arrayFrom2To[1] = (int) (random.nextInt(Main.AMOUNT_CLIENTS + 1) * 1.1); // диапазон (0 - (кол-во клиентов) + 10%)
            // этот индекс - сумма перевода
            arrayFrom2To[2] = 5_000 + random.nextInt(11) * 5_000;  // диапазон (5000 - 55_000)руб
            transactions.add(arrayFrom2To);
        }
        return transactions;
    }


    @Override
    public void run() {
        for (Integer[] array : transactions) {
            MyLogger.logger.info("внутри run(): from = " + array[0] + " to = " + array[1]);

            bank.transfer(String.valueOf(array[0]), String.valueOf(array[1]), (long) array[2]);
        }
    }
}