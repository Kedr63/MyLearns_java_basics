import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


public class GenerationUser {
  protected static Set<User> generateUsersSite(int countUser){
    Set<User> usersSite = new HashSet<>();
    for (int i = 1; i <= countUser; i++){
      int id = i;
      LocalDateTime timeRegistration = LocalDateTime.of(2000 + i, 3, 20, 12, 15);
      usersSite.add(new User(id, timeRegistration));
    }
    return usersSite;
  }
}
