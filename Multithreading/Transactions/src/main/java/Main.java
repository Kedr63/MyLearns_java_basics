import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
  protected static Logger logger;

  protected static final int AMOUNT_CLIENTS = 100;
  protected static final int AMOUNT_TRANSACTIONS_FOR_ONE_THREAD = 400;

  public static void main(String[] args) throws InterruptedException {
    logger = LogManager.getLogger();

    Bank bank = new Bank();

    Set<Account> accountSet = Account.generateAccountsSet(
        AMOUNT_CLIENTS); //сгенерируем базу клиентов

    // заполним банк новыми аккаунтами со значением "действующий"
    for (Account account : accountSet) {
      bank.getAccounts().put(account, Bank.STATUS_ACCOUNT_NORM);
    }
    System.out.println("банк наполнили клиентами");
    System.out.println("банк объект: " + bank);

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
    thread.start();
    thread1.start();
    thread2.start();
    thread3.start();
    thread4.start();
    thread.join();
    thread1.join();
    thread2.join();
    thread3.join();
    thread4.join();

    long amountAfterTransactions = bank.getSumAllAccounts();

    System.out.println("\nСумма всех счетов: " + amountAfterTransactions);
    System.out.println("Изменение баланса банка после всех транзакций: " + (amountBeforeTransactions
        - amountAfterTransactions));

    System.out.println("Время выполнения всех операций: " + (System.currentTimeMillis() - start));

    System.out.println("Заблокированных счетов: " + bank.getAccounts().entrySet().stream()
        .filter(e -> e.getValue().equals(Bank.STATUS_ACCOUNT_LOCK)).count());


  }
}
