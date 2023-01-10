import org.apache.logging.log4j.LogManager;
import org.junit.Before;
import org.junit.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BankMultiThreadTest {

    private static final int AMOUNT_CLIENTS = 100;

    Bank bank = new Bank();

    @Before
    public void setUp() throws Exception {

        MyLogger.logger = LogManager.getLogger();

        Set<Account> accountSets = GenerationData.generateAccountsSet(AMOUNT_CLIENTS);
        for (Account account : accountSets) {
            bank.getAccounts().put(account, Bank.STATUS_ACCOUNT_NORM);
        }
    }

    @Test
    public void testDiffBalanceAfterTransactions() throws InterruptedException {

        TransactionsRun transactionsRun = new TransactionsRun(bank, 1000);
        TransactionsRun transactionsRun1 = new TransactionsRun(bank, 2000);
        TransactionsRun transactionsRun2 = new TransactionsRun(bank, 3000);

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
        System.out.println("Дисбаланс после всех транзакций: " + (before - after));

    }
}