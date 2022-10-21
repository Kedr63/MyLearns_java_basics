import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.Map.Entry;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BankTest {

  Bank bank = new Bank();

  //protected static Logger logger;

  @Before
  public void setUp() throws Exception {
    MyLogger.logger = LogManager.getLogger();

    Account account1 = new Account("1", 100_000);
    Account account2 = new Account("2", 200_000);
    Account account3 = new Account("3", 300_000);
    Account account4 = new Account("4", 400_000);
    Account account5 = new Account("5", 500_000);
    Account account6 = new Account("6", 600_000);
    Account account7 = new Account("7", 700_000);

    bank.getAccounts().put(account1, Bank.STATUS_ACCOUNT_NORM);
    bank.getAccounts().put(account2, Bank.STATUS_ACCOUNT_NORM);
    bank.getAccounts().put(account3, Bank.STATUS_ACCOUNT_NORM);
    bank.getAccounts().put(account4, Bank.STATUS_ACCOUNT_NORM);
    bank.getAccounts().put(account5, Bank.STATUS_ACCOUNT_NORM);
    bank.getAccounts().put(account6, Bank.STATUS_ACCOUNT_NORM);
    bank.getAccounts().put(account7, Bank.STATUS_ACCOUNT_NORM);
  }

  @Test
  @DisplayName("Проверка перевода между существующими счетами")
  public void testTransfer() throws InterruptedException {

    bank.transfer("2", "1", 10_000);

    long actualAmountFrom = bank.getAccounts().entrySet().stream()
        .filter(e -> e.getKey().getAccNumber().equals("2")).mapToLong(e -> e.getKey().getMoney())
        .findFirst().getAsLong();
    long expectedAmountFrom = 190_000;

    assertEquals(expectedAmountFrom, actualAmountFrom);

    long actualAmountTo = bank.getAccounts().entrySet().stream()
        .filter(e -> e.getKey().getAccNumber().equals("1")).mapToLong(e -> e.getKey().getMoney())
        .findFirst().getAsLong();
    long expectedAmountTo = 110_000;

    assertEquals(expectedAmountTo, actualAmountTo);


  }

  @Test
  @DisplayName("Проверка получения баланса счета")
  public void testGetBalance() {
    assertEquals(700_000, bank.getBalance("7"));
    assertEquals(200_000, bank.getBalance("2"));
    assertEquals(0, bank.getBalance("10"));  // не существующий счет
  }

  @Test
  @DisplayName("Проверка общего баланса всех счетов банка")
  public void testGetSumAllAccounts() {
    assertEquals(2_800_000, bank.getSumAllAccounts());
  }

  @Test
  @DisplayName("Проверка общего баланса всех счетов банка после проведения транзакций")
  public void testVerifyTotalBalanceAfterTransactions() throws Exception {
    long beforeTransactions = bank.getSumAllAccounts();
    bank.transfer("1", "2", 50_000);
    bank.transfer("1", "2", 500_000); // перевод суммы больше чем есть на счете
    bank.transfer("3", "4", 100_000); // возможно транзакция отменится СБ
    bank.transfer("20", "1", 5000); // перевод с не существующего счета
    bank.transfer("5", "20", 1000); // перевод на не существующий счет
    long afterTransactions = bank.getSumAllAccounts();
    assertEquals(beforeTransactions, afterTransactions);
  }

  @Test
  @DisplayName("Проверка изменения баланса заблокированного счета при проведения транзакции")
  public void testVerifyLockAccount() throws InterruptedException {
    // сделаем счет №1 заблокированным
    Entry<Account, String> entryAccount = bank.getAccounts().entrySet().stream()
        .filter(e -> e.getKey().getAccNumber().equals("1")).findFirst().get();
    bank.getAccounts().put(entryAccount.getKey(), entryAccount.setValue(Bank.STATUS_ACCOUNT_LOCK));

    long amountFrom = bank.getBalance("2");

    bank.transfer("2", "1", 20000);

    assertEquals(200_000, amountFrom); // баланс счета №2 должен остаться прежним
  }
}
