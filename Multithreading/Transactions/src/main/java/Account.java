import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Account {

  private long money;
  private String accNumber;

  public Account(String accNumber, long money) {
    this.accNumber = accNumber;
    this.money = money;
  }

  public long getMoney() {
    return money;
  }

  public void setMoney(long money) {
    this.money = money;
  }

  public String getAccNumber() {
    return accNumber;
  }

  public void setAccNumber(String accNumber) {
    this.accNumber = accNumber;
  }

  protected static Set<Account> generateAccountsSet(int countClients) {
    Set<Account> accountSet = new HashSet<>();
    Random random = new Random();
    for (int i = 0; i < countClients; i++) {
      String accNum = String.valueOf(i);
      int accMoney = random.nextInt(11) * 10_000;
      accountSet.add(new Account(accNum, accMoney));
    }
    return accountSet;
  }
}
