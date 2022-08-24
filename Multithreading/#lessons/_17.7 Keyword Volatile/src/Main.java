import java.util.Scanner;

public class Main {

  // 📍 17.7 Ключевое слово Volatile

  // Это ключевое слово задется когда пишем какую либо переменную
  // например
  // private volatile int xsa = 0;
  // и оно обозначает что эта переменная может изменяться из разных потоков
  // оно используется крайне редко, но обратите внимание что могут кэшироваться переменные в отдельных потоках,
  // если переменная пишется одним потоком, изменяется , а читается другим, то такая ситуация высоко вероятна

  public static void main(String[] args) {
    Task task = new Task();
    new Thread(task).start();

    Scanner scanner = new Scanner(System.in);
    scanner.nextLine(); // на этой строчке главный поток остановится, пока не нажмем enter в консоли
    task.stop();
    System.out.println("Main: " + task.getCounterValue());
  }
}

// 📍 от Zaur
// Ключевое слово Volatile используется для пометки переменной, как хранящейся только в основной
// памяти "main memory"
// Для синхронизации значения переменной между потоками ключевое слово Volatile используется тогда,
// когда только один поток может изменять значение этой переменной, а остальные потоки могут только
// его читать
