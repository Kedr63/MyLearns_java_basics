import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Set;
import org.redisson.Redisson;
import org.redisson.api.RKeys;
import org.redisson.api.RScoredSortedSet;
import org.redisson.api.RSetMultimap;
import org.redisson.api.RedissonClient;
import org.redisson.client.RedisConnectionException;
import org.redisson.config.Config;

public class RedisStorage {

  private RedissonClient redissonClient;

  private RKeys rKeys;

  private RScoredSortedSet<String> usersSortedSet;

  private final static String KEY = "USER_SITE_DATING";

  protected void unit(){
    Config config = new Config();
    config.useSingleServer().setAddress("redis://127.0.0.1:6379");
    try{
      redissonClient = Redisson.create(config);
    } catch (RedisConnectionException ex){
      System.out.println("Не удалось подключиться к Redis");
      System.out.println(ex.getMessage());
    }
    rKeys = redissonClient.getKeys();
    usersSortedSet = redissonClient.getScoredSortedSet(KEY);
  }

  protected RScoredSortedSet<String> addUsersToSortedSet(Set<User> userSet){
    for (User user : userSet){
      int id = user.getId();
      LocalDateTime timeReg = user.getTimeRegistration();
      Timestamp ts = Timestamp.valueOf(timeReg);
      double timeTs = (double) ts.getTime();
      usersSortedSet.add(timeTs, String.valueOf(id));
    }
    return usersSortedSet;
  }

  protected int countUser(){
    return usersSortedSet.count(Double.NEGATIVE_INFINITY, true, Double.POSITIVE_INFINITY, true);
  }

  protected void shutdown(){
    redissonClient.shutdown();
  }
}
