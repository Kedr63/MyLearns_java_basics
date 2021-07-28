public class Main {
    public static void main(String[] args) {
        BankAccount bankAccount = new BankAccount();
        bankAccount.put(10000);

        CardAccount cardAccount = new CardAccount();
        cardAccount.put(15000);

        DepositAccount depositAccount = new DepositAccount();
        depositAccount.put(20000, 2021, 6, 23);

        System.out.println(depositAccount.send(cardAccount, 3000));
        System.out.println("на банк счете " + bankAccount.getAmount());
        System.out.println("на карт счете " + cardAccount.getAmount());
        System.out.println("на депозит счете " + depositAccount.getAmount());








    }
}
