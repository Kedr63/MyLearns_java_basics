import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class GenerationData {
    protected static Set<Account> generateAccountsSet(int countClients) {
        Set<Account> accountSet = new HashSet<>();
        Random random = new Random();
        for (int i = 0; i < countClients; i++) {
            String accNum = String.valueOf(i);
            int accMoney = random.nextInt(11) * 10_000;  // псевдослучайный диапазон (0 - 100_000) руб для депозитов
            accountSet.add(new Account(accNum, accMoney));
        }
        return accountSet;
    }
}
