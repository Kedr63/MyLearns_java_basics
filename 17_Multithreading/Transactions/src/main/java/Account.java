import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Account implements Comparable<Account>{

  private long money;
  private String accNumber;

  public Account(String accNumber, long money) {
    this.accNumber = accNumber;
    this.money = money;
  }

  public long getMoney()  {
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

  @Override
  public int compareTo(Account o) {
    return this.getAccNumber().compareTo(o.getAccNumber());
  }
}
