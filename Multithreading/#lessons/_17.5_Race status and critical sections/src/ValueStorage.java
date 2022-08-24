import java.util.concurrent.atomic.AtomicInteger;

public class ValueStorage {

  private static int value;

  public static void incrementValue(){
    value = value + 1;
  }

  public static int getValue(){
    return value;
  }
}
