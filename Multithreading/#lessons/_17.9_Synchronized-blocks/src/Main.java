import java.util.ArrayList;

public class Main {

  // 📍 17.9 Synchronized-блоки

  // Допустим мы хотим защититься только от ошибок, можем synchronized только тот фрагмент кода
  // который выдавал ошибку

  private static ArrayList<Double> numbers = new ArrayList<>();

  public static void main(String[] args) {
    ArrayList<Thread> threads = new ArrayList<>();
    for (int i = 0; i < 10; i++){
      threads.add(new Thread(Main::someHeavyMethods));
    }
    threads.forEach(t->t.start());
  }

  private static void someHeavyMethods() { // будет заполнять ArrayList случайными числами
    for (int i = 0; i < 1000000; i++) {
      synchronized (numbers){
        double value = Math.random() / Math.random();
        numbers.add(value);
      } // в () тот объект по которому мы синхронизируемся, если он где еще используется то заключить его в synchronized будет очень логично,
    }    // и это приведет к тому что эти операции будут потокобезопасными
    System.out.println(numbers.size());
    numbers.clear();
  }
}
//  synchronized (Main.class) - можно и так, тогда синхронизируемся по всем экземплярам класса

//       synchronized (numbers){
//        numbers.add(Math.random() / Math.random());   здесь выполняется несколько операций, в synchronized
//      }                                              блоках так делать не надо, надо эти операции выносить из блока,
//                                тяжелые операции от туда должны быть убраны