import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

public class BankMultiThreadTest {

  private static final int AMOUNT_CL = 100_000;

  Bank bank = new Bank();

  @Before
  public void setUp() throws Exception {

 /* Set<Account> accountSets = Account.generateAccountsSet(AMOUNT_CL);
  for (Account account : accountSets){
    bank.getAccounts().put(account, Bank.STATUS_ACCOUNT_NORM);
  }*/

    // TransactionsRun transactionsRun =new TransactionsRun(bank, 500);

    /*Account account1 = new Account("1", 100_000);
    Account account2 = new Account("2", 200_000);
    Account account3 = new Account("3", 300_000);
    Account account4 = new Account("4", 400_000);
    Account account5 = new Account("5", 500_000);
    Account account6 = new Account("6", 600_000);
    Account account7 = new Account("7", 700_000);
    Account account8 = new Account("8", 800_000);
    Account account9 = new Account("9", 900_000);

    bank.getAccounts().put(account1, Bank.STATUS_ACCOUNT_NORM);
    bank.getAccounts().put(account2, Bank.STATUS_ACCOUNT_NORM);
    bank.getAccounts().put(account3, Bank.STATUS_ACCOUNT_NORM);
    bank.getAccounts().put(account4, Bank.STATUS_ACCOUNT_NORM);
    bank.getAccounts().put(account5, Bank.STATUS_ACCOUNT_NORM);
    bank.getAccounts().put(account6, Bank.STATUS_ACCOUNT_NORM);
    bank.getAccounts().put(account7, Bank.STATUS_ACCOUNT_NORM);
    bank.getAccounts().put(account8, Bank.STATUS_ACCOUNT_NORM);
    bank.getAccounts().put(account9, Bank.STATUS_ACCOUNT_NORM);

    Integer[] operation = {1, 2, 10000};
    Integer[] operation1 = {2, 3, 60000};
    Integer[] operation2 = {3, 4, 40000};
    Integer[] operation3 = {4, 5, 60000};
    Integer[] operation4 = {6, 7, 10000};
    Integer[] operation5 = {9, 10, 10000};*/

    //  List<Integer[]> transactions = new ArrayList<>();
   /* transactions.add(operation);
    transactions.add(operation1);
    transactions.add(operation2);
    transactions.add(operation3);
    transactions.add(operation4);
    transactions.add(operation5);*/

   /* TransactionsRun transactionsRun = new TransactionsRun(bank);
    transactionsRun.setTransactions(transactions);

    TransactionsRun transactionsRun1 = new TransactionsRun(bank);
    transactionsRun1.setTransactions(transactions);

    long before = bank.getSumAllAccounts();
    Thread thread = new Thread(transactionsRun);
    Thread thread1 = new Thread(transactionsRun1);
    thread.start();
    thread1.start();
    thread.join();
    thread1.join();
    long after = bank.getSumAllAccounts();*/

  }

  @Test
  public void testDiffBalanceAfterTransactions() throws InterruptedException {

    Set<Account> accountSets = Account.generateAccountsSet(AMOUNT_CL);
    for (Account account : accountSets) {
      bank.getAccounts().put(account, Bank.STATUS_ACCOUNT_NORM);
    }

    TransactionsRun transactionsRun = new TransactionsRun(bank, 1000);
    TransactionsRun transactionsRun1 = new TransactionsRun(bank, 1000);
    TransactionsRun transactionsRun2 = new TransactionsRun(bank, 1000);
    TransactionsRun transactionsRun3 = new TransactionsRun(bank, 1000);
    TransactionsRun transactionsRun4 = new TransactionsRun(bank, 1000);
    TransactionsRun transactionsRun5 = new TransactionsRun(bank, 1000);

    long before = bank.getSumAllAccounts();
    Thread thread = new Thread(transactionsRun);
    Thread thread1 = new Thread(transactionsRun1);
    Thread thread2 = new Thread(transactionsRun2);
    Thread thread3 = new Thread(transactionsRun3);
    Thread thread4 = new Thread(transactionsRun4);
    Thread thread5 = new Thread(transactionsRun5);
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
    thread5.join();
    long after = bank.getSumAllAccounts();

    assertEquals(before, after);
    System.out.println("Дисбаланс после всех транзакций: " + (before - after));

  }

  @Test
  @DisplayName("Проверка в многопоточности переводов с разных счетов на один счет")
  public void testDiffBalanceAfterTransactionsTransferFromOneAccount() throws InterruptedException {
    Account account1 = new Account("1", 100_000);
    Account account2 = new Account("2", 200_000);
    Account account3 = new Account("3", 300_000);
    Account account4 = new Account("4", 400_000);
    Account account5 = new Account("5", 500_000);

    bank.getAccounts().put(account1, Bank.STATUS_ACCOUNT_NORM);
    bank.getAccounts().put(account2, Bank.STATUS_ACCOUNT_NORM);
    bank.getAccounts().put(account3, Bank.STATUS_ACCOUNT_NORM);
    bank.getAccounts().put(account4, Bank.STATUS_ACCOUNT_NORM);
    bank.getAccounts().put(account5, Bank.STATUS_ACCOUNT_NORM);

    List<Integer[]> transactions = List.of(new Integer[]{1, 5, 15000}, new Integer[]{2, 5, 25000},
        new Integer[]{3, 5, 60000}, new Integer[]{4, 5, 45000});

    List<Integer[]> transactions1 = List.of(new Integer[]{1, 5, 10000}, new Integer[]{2, 5, 60000},
        new Integer[]{3, 5, 30000}, new Integer[]{4, 5, 40000});

    List<Integer[]> transactions2 = List.of(new Integer[]{1, 5, 10500}, new Integer[]{2, 5, 20500},
        new Integer[]{3, 5, 30500}, new Integer[]{4, 5, 60000});

    TransactionsRun transactionsRun = new TransactionsRun(bank);
    transactionsRun.setTransactions(transactions);
    TransactionsRun transactionsRun1 = new TransactionsRun(bank);
    transactionsRun1.setTransactions(transactions1);
    TransactionsRun transactionsRun2 = new TransactionsRun(bank);
    transactionsRun2.setTransactions(transactions2);

    long before = bank.getSumAllAccounts();
    Thread thread = new Thread(transactionsRun);
    Thread thread1 = new Thread(transactionsRun1);
    Thread thread2 = new Thread(transactionsRun2);

    thread.start();
    thread1.start();
    thread2.start();

    thread.join();
    thread1.join();
    thread2.join();

    long after = bank.getSumAllAccounts();

    assertEquals(before, after);
    System.out.println("Дисбаланс после всех транзакций при переводе на один счет: " + (before - after));

  }
}