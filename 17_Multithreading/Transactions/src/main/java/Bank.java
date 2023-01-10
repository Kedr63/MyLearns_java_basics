import java.util.*;


public class Bank {

    protected static final String STATUS_ACCOUNT_NORM = "действующий";
    protected static final String STATUS_ACCOUNT_LOCK = "заблокирован !";
    protected static final int MAX_AMOUNT_WITHOUT_SECURITY_CHECK = 50_000;

    private Map<Account, String> accounts;  // V (String) в map будет хранить значение счета \"действующий" - "заблокирован !"\
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

    public synchronized boolean isFraud(String fromAccountNum, String toAccountNum, long amount) throws InterruptedException
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


        public void transfer (String fromAccountNum, String toAccountNum, long amount)
        {
            try {
                Account fromAccount = getAccount(accounts, fromAccountNum);
                Account toAccount = getAccount(accounts, toAccountNum);
                if (fromAccount == null || toAccount == null) {  // если один из счетов заблокирован, то выкинем NullPointerException
                    throw new NullPointerException("Транзакция отменена. Один из счетов не существует");
                }
                MyLogger.logger.info("зашли в transfer() - before synchronized(): fromAccountNum - " + fromAccountNum + "  toAccountNum - " + toAccountNum);

                /* Простейший способ избежать взаимной блокировки – не допускать цикличного ожидания. Этого можно достичь, получая мониторы разделяемых ресурсов
                 в определённом порядке и освобождая их в обратном порядке. */
                synchronized (fromAccount.compareTo(toAccount) > 0 ? fromAccount : toAccount) {
                    synchronized (toAccount.compareTo(fromAccount) < 0 ? toAccount : fromAccount) {
                        MyLogger.logger.info("в transfer() - after synchronized(): fromAccountNum - " + fromAccountNum + "  toAccountNum - " + toAccountNum);

                        Map.Entry<Account, String> entryFrom = getAndCheckingAccount(fromAccountNum);
                        Map.Entry<Account, String> entryTo = getAndCheckingAccount(toAccountNum);
                        if (entryFrom == null || entryTo == null) {  // если один из счетов заблокирован, то выкинем NoSuchElementException
                            throw new NullPointerException("Транзакция отменена. Счет заблокирован");
                        }

                        MyLogger.logger.info("внутри метода transfer(): entryFrom - " + entryFrom.getKey().getAccNumber() + "  entryTo - " + entryTo.getKey().getAccNumber());

                        // аккаунты прошли проверку перед транзакцией на сущ-ние в банке и то что они действующие (не заблокированы),
                        // теперь проведем транза-ю
                        if (amount > MAX_AMOUNT_WITHOUT_SECURITY_CHECK && isFraud(fromAccountNum, toAccountNum, amount))  // если сумма перевода > 50_000 руб и СБ проверив перевод что-то заподозрит, то
                        {
                            entryFrom.setValue(STATUS_ACCOUNT_LOCK);         // заблокируем оба счета
                            entryTo.setValue(STATUS_ACCOUNT_LOCK);
                            accounts.put(entryFrom.getKey(), entryFrom.getValue()); // и обновим данные
                            accounts.put(entryTo.getKey(), entryTo.getValue());   //  этих аккаунтов в банке

                            MyLogger.logger.info("Внимание! Мошенники! Счета " + entryFrom.getKey().getAccNumber() + ", " + entryTo.getKey().getAccNumber() + " заблокированы");

                        } else {       // если все норм то совершим перевод
                            if ((entryFrom.getKey().getMoney() - amount) < 0) {
                                MyLogger.logger.info("Недостаточно средств на счете from " + entryFrom.getKey().getAccNumber() + ". Транзакция отменена");
                            } else {
                                long fromBefore = entryFrom.getKey().getMoney(); // for logger
                                long toBefore = entryTo.getKey().getMoney();     // for logger

                                entryFrom.getKey().setMoney(entryFrom.getKey().getMoney() - amount);
                                entryTo.getKey().setMoney(entryTo.getKey().getMoney() + amount);

                                accounts.put(entryFrom.getKey(), entryFrom.setValue(entryFrom.getValue()));
                                accounts.put(entryTo.getKey(), entryTo.setValue(entryTo.getValue()));

                                MyLogger.logger.info(fromAccountNum + " -balance: " + fromBefore + " -> " + toAccountNum + " -balance: " + toBefore + " | сумма перевода: " + amount + " После перевода: " + entryFrom.getKey().getMoney() + ", " + entryTo.getKey().getMoney() + " (diff balance before/after = " + ((fromBefore + toBefore) - (entryFrom.getKey().getMoney() + entryTo.getKey().getMoney())) + ")");
                            }
                        }
                    }
                }
            } catch (NullPointerException | InterruptedException ex) {
                MyLogger.logger.info(ex.getMessage());
                System.out.println(ex.getMessage());
            }
        }


    /**
     * TODO: реализовать метод. Возвращает остаток на счёте.
     */
    public long getBalance(String accountNum) {
        Optional<Account> optionalAccount = accounts.keySet().stream().filter(e -> e.getAccNumber().equals(accountNum)).findFirst(); // обернем в Optional, вдруг null получим

        if (optionalAccount.isPresent()) {         // и если аккаунт существует
            return optionalAccount.get().getMoney(); // то вернем сумму счета
        } else {
            System.out.println("Такого счета " + accountNum + " не существует");
        }
        return 0;
    }


    public long getSumAllAccounts() {
        return accounts.keySet().stream().mapToLong(e -> e.getMoney()).sum();
    }


    // получим Entry<Account, String> и проверим статус \действующий - заблокирован\
    private Map.Entry<Account, String> getAndCheckingAccount(String accountNum) {
        Map.Entry<Account, String> entrySearch = null;
        for (Map.Entry<Account, String> entry : accounts.entrySet()) {
            if (entry.getKey().getAccNumber().equals(accountNum) && entry.getValue().equals(STATUS_ACCOUNT_NORM)) {
                entrySearch = entry;
                break;
            }
        }
        return entrySearch; // если заблокирован счет, то return null
    }

    public static Account getAccount(Map<Account, String> accounts, String account) throws NoSuchElementException {
        Optional<Account> optionalAccount = accounts.keySet().stream()
                .filter(e -> e.getAccNumber().equals(account)).findFirst();
        if (optionalAccount.isPresent()){
            return optionalAccount.get();
        } else return null;
    }
}
