import org.apache.logging.log4j.LogManager;

import java.util.Set;

public class Main {

    protected static final int AMOUNT_CLIENTS = 100;
    protected static final int AMOUNT_TRANSACTIONS_FOR_ONE_THREAD = 40_000;

    public static void main(String[] args) throws InterruptedException {

        MyLogger.logger = LogManager.getLogger();

        Bank bank = new Bank();

        Set<Account> accountSet = GenerationData.generateAccountsSet(AMOUNT_CLIENTS); //сгенерируем базу клиентов
        // и заполним банк новыми аккаунтами со значением "действующий"
        for (Account account : accountSet) {
            bank.getAccounts().put(account, Bank.STATUS_ACCOUNT_NORM);
        }
        System.out.println("банк наполнили клиентами");

        long start = System.currentTimeMillis();

        TransactionsRun transactionsRun = new TransactionsRun(bank, AMOUNT_TRANSACTIONS_FOR_ONE_THREAD);
        TransactionsRun transactionsRun1 = new TransactionsRun(bank, AMOUNT_TRANSACTIONS_FOR_ONE_THREAD);
        TransactionsRun transactionsRun2 = new TransactionsRun(bank, AMOUNT_TRANSACTIONS_FOR_ONE_THREAD);
        TransactionsRun transactionsRun3 = new TransactionsRun(bank, AMOUNT_TRANSACTIONS_FOR_ONE_THREAD);
        TransactionsRun transactionsRun4 = new TransactionsRun(bank, AMOUNT_TRANSACTIONS_FOR_ONE_THREAD);

        long amountBeforeTransactions = bank.getSumAllAccounts();
        System.out.println("Сумма всех счетов: " + amountBeforeTransactions + "\n");

        Thread thread = new Thread(transactionsRun);
        Thread thread1 = new Thread(transactionsRun1);
        Thread thread2 = new Thread(transactionsRun2);
        Thread thread3 = new Thread(transactionsRun3);
        Thread thread4 = new Thread(transactionsRun4);
        Thread thread5 = new Thread(transactionsRun4);
        thread.start();
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
        thread.join();
        thread1.join();
        thread2.join();
        thread3.join();
        thread4.join();
        thread5.join(); // пока потоки не завершатся код ниже не выполним

        long amountAfterTransactions = bank.getSumAllAccounts();

        System.out.println("\nСумма всех счетов: " + amountAfterTransactions);
        System.out.println("Изменение баланса банка после всех транзакций: " + (amountBeforeTransactions - amountAfterTransactions));

        System.out.println("Время выполнения всех операций: " + (System.currentTimeMillis() - start));

        System.out.println("Заблокированных счетов: " + bank.getAccounts().entrySet().stream().filter(e -> e.getValue().equals(Bank.STATUS_ACCOUNT_LOCK)).count());
    }
}
