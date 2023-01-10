import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Main {

  public static void main(String[] args) {
    Callable callable = () -> {
      double sum = 0;
      for (int i = 0; i < 1000; i++) {
        sum += Math.random();
      }
      if (sum/1000 < 0.6) {  /* разделит на 1000 чтоб получить исключение */
        throw new IllegalArgumentException("fsfdg");
      }
      return sum / 1000;
    };
    FutureTask<Double> futureTask = new FutureTask<>(callable);
    new Thread(futureTask).start();

    try {
      System.out.println(futureTask.get());
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    } catch (ExecutionException e) {
      throw new RuntimeException(e);
    } catch (IllegalArgumentException e){
      System.out.println("Exception: " + e.getMessage());
    }
  }

}
