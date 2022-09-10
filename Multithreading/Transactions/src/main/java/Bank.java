import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Random;

public class Bank {

  private int counter = 1;

  public int getCounter() {
    return counter;

  }

  private int counter1 = 1;

  public int getCounter1() {
    return counter;
  }

  public static final String STATUS_ACCOUNT_NORM = "действующий";
  public static final String STATUS_ACCOUNT_LOCK = "заблокирован !";
  public static final int MAX_AMOUNT_WITHOUT_SECURITY_CHECK = 50000;

  private final Object lock = new Object(); // на этом объекте будет синхронизироваться блок synchronized

  private Map<Account, String> accounts;
  private final Random random = new Random();
  private TransactionsRun transactionsRun;

  public Bank() {
    this.accounts = new HashMap<>();
  }

  public Map<Account, String> getAccounts() {
    return accounts;
  }

  public void setAccounts(Map<Account, String> accounts) {
    this.accounts = accounts;
  }

  public TransactionsRun getTransactionsRun() {
    return transactionsRun;
  }

  public void setTransactionsRun(TransactionsRun transactionsRun) {
    this.transactionsRun = transactionsRun;
  }

  public synchronized boolean isFraud(String fromAccountNum, String toAccountNum, long amount)
      throws InterruptedException
  {
    Thread.sleep(1000);
    return random.nextBoolean();
  }

  /**
   * TODO: реализовать метод. Метод переводит деньги между счетами. Если сумма транзакции > 50000,
   * то после совершения транзакции, она отправляется на проверку Службе Безопасности – вызывается
   * метод isFraud. Если возвращается true, то делается блокировка счетов (как – на ваше
   * усмотрение)
   */
  public void transfer(String fromAccountNum, String toAccountNum, long amount)
      throws InterruptedException, NullPointerException
  {
    try {
      synchronized (accounts.keySet().stream().filter(e -> e.getAccNumber().equals(fromAccountNum))
          .findFirst().get()) {
        synchronized (accounts.keySet().stream().filter(t -> t.getAccNumber().equals(toAccountNum)).findFirst().get()){

      /*  System.out.println(
            "\n" + accounts.keySet().stream().filter(e -> e.getAccNumber().equals(fromAccountNum))
                .findFirst().get() + " " + Thread.currentThread().getName() + " вход в метод № "
                + counter1++);*/
        Map.Entry<Account, String> entryFrom = null;
        Map.Entry<Account, String> entryTo = null;
        //  try {
        entryFrom = checkAccount(fromAccountNum);
        entryTo = checkAccount(toAccountNum);
        entryFrom.getKey();   // попросим ключ, и если не все в порядке с счетом, то получим сразу исключение, чтоб не продолжать код
        entryTo.getKey();     // попросим ключ, и если не все в порядке с счетом,то получим сразу исключение, чтоб не продолжать код

        // аккаунты прошли проверку перед транзакцией на сущ-ние в банке и то что они действующие (не заблокированы),
        // теперь проведем транза-ю
        if (amount > MAX_AMOUNT_WITHOUT_SECURITY_CHECK && isFraud(fromAccountNum, toAccountNum,
            amount))               // если сумма перевода > 50_000 руб и СБ проверив перевод что-то заподозрит, то
        {
          entryFrom.setValue(STATUS_ACCOUNT_LOCK);         // заблокируем оба счета
          entryTo.setValue(STATUS_ACCOUNT_LOCK);
          accounts.put(entryFrom.getKey(), entryFrom.getValue()); // и обновим данные
          accounts.put(entryTo.getKey(), entryTo.getValue());   //  этих аккаунтов в банке

          System.out.println(
              "Внимание! Мошенники! Счета " + entryFrom.getKey().getAccNumber() + ", "
                  + entryTo.getKey().getAccNumber() + " заблокированы");


        } else {  // если все норм то совершим перевод

          if ((entryFrom.getKey().getMoney() - amount) < 0) {
            System.out.println(
                "Недостаточно средств на счете from " + entryFrom.getKey().getAccNumber()
                    + ". Транзакция отменена");
          } else {
            long fromBefore = getBalance(fromAccountNum); // for logger
            long toBefore = getBalance(toAccountNum);     // for logger

            entryFrom.getKey().setMoney(entryFrom.getKey().getMoney() - amount);

            entryTo.getKey().setMoney(entryTo.getKey().getMoney() + amount);

            accounts.put(entryFrom.getKey(), entryFrom.setValue(entryFrom.getValue()));
            accounts.put(entryTo.getKey(), entryTo.setValue(entryTo.getValue()));

            Main.logger.info(" объект: " + accounts.keySet().stream()
                .filter(e -> e.getAccNumber().equals(fromAccountNum)).findFirst().get() + "  "
                + fromAccountNum + " -balance: " + fromBefore + " -> " + toAccountNum
                + " -balance: " + toBefore + " amount trans: " + amount + " __ After: "
                + getBalance(fromAccountNum) + ", " + getBalance(toAccountNum));
          }
        }
        }
      }
    } catch (NoSuchElementException ex) {
      System.out.println("Транзакция отменена: счет списания не существует");
    } catch (NullPointerException ex) {
      System.out.println("Транзакция отменена: счет не существует или заблокирован");
    }
  }

  /**
   * TODO: реализовать метод. Возвращает остаток на счёте.
   */
  public long getBalance(String accountNum) {
    Optional<Account> optionalAccount = accounts.keySet().stream()
        .filter(e -> e.getAccNumber().equals(accountNum)).findFirst();

    if (optionalAccount.isPresent()) {
      return optionalAccount.get().getMoney();
    } else {
      System.out.println("Такого счета " + accountNum + " не существует");
    }
    return 0;
  }

  public long getSumAllAccounts() {
    return accounts.keySet().stream().mapToLong(e -> e.getMoney()).sum();
  }

  // проверим существование счетов для перевода и если существуют то статус \действующий - заблокирован\
  private Map.Entry<Account, String> checkAccount(String accountNum) {
    Map.Entry<Account, String> entrySearch = null;
    for (Map.Entry<Account, String> entry : accounts.entrySet()) {
      if (entry.getKey().getAccNumber().equals(accountNum) && entry.getValue()
          .equals(STATUS_ACCOUNT_NORM))
      {
        entrySearch = entry;
        break;
      }
    }
    return entrySearch;
  }
}
