import org.redisson.Redisson;
import org.redisson.api.RKeys;
import org.redisson.api.RMap;
import org.redisson.api.RMapCache;
import org.redisson.api.RedissonClient;
import org.redisson.client.RedisConnectionException;
import org.redisson.config.Config;

import java.util.Map;

public class RedisStorage {

  private RedissonClient redissonClient;

  private RKeys rKeys;

  // private static RMap<Voter, Integer> voterIntegerRMap;   // 562c  5mil
  private static RMapCache<Voter, Integer> voterIntegerRMap;   //
 // private static RLocalCachedMap<Voter, Integer> voterIntegerRMap;  // 948c   5mil

  public static RMap<Voter, Integer> getVoterIntegerRMap() {
    return voterIntegerRMap;
  }

  private final static String KEY = "Voter_visit";

  protected void unit() {
    Config config = new Config();
    config.useSingleServer().setAddress("redis://127.0.0.1:6379");
    try {
      redissonClient = Redisson.create(config);
    } catch (RedisConnectionException ex) {
      System.out.println("Не удалось подключиться к Redis");
      System.out.println(ex.getMessage());
    }
    rKeys = redissonClient.getKeys();
    voterIntegerRMap = redissonClient.getMapCache(KEY);
  }

  protected static void addVoterToRMap(Voter voter) {
    /*int count = voterIntegerRMap.getOrDefault(voter, 0);
    voterIntegerRMap.put(voter, count +1);*/

  /*  voterIntegerRMap.fastPutIfAbsent(voter, 1);
    voterIntegerRMap.fastReplace(voter, voterIntegerRMap.get(voter) + 1);*/
    if (!voterIntegerRMap.containsKey(voter)) {
      voterIntegerRMap.put(voter, 1);
    } else {
      voterIntegerRMap.get(voter);
      voterIntegerRMap.put(voter, voterIntegerRMap.get(voter) + 1);
    }
  }

/*  protected int countUser(){
    return usersSortedSet.count(Double.NEGATIVE_INFINITY, true, Double.POSITIVE_INFINITY, true);
  }*/

  protected void printVoterCounts(){
    for (Map.Entry<Voter, Integer> entry : voterIntegerRMap.entrySet()){
      if (entry.getValue() > 1){
        System.out.println(entry.getKey() + " " + entry.getValue());
      }
    }
   /* voterIntegerRMap.readAllEntrySet().forEach(v-> {if(v.getValue() > 1){
      System.out.println(v.getKey() + " " + v.getValue());
    }
    });*/
  }

  protected void shutdown() {
    redissonClient.shutdown();
  }

  protected static void clear(){
    voterIntegerRMap.destroy();
 //   voterIntegerRMap.destroy();
  }

}
