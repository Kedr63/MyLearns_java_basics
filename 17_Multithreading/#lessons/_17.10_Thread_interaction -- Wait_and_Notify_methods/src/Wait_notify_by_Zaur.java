public class Wait_notify_by_Zaur {
  // 📌 Методы wait notify от Заур
  // Иногда при взаимодействии потоков встает вопрос об извещении одних потоков о действии других,
  // например действия одного потока могут зависить от результата действия другого потока и надо
  // как-то известить один поток что второй поток произвел какую-то работу. И для подобных ситуаций
  // у класса Object определено ряд методов:
  // 📍 wait - освобождает монитор и переводит вызывающий поток в состояние ожидания до тех пор,
  // пока другой поток не вызовет метод notify();
  // 📍 notify - НЕ освобождает монитор и будит поток, у которого ранее был вызван метод wait;
  // 📍 notifyAll - НЕ освобождает монитор и будит все потоки, у которого ранее был вызван метод wait;

  // Рассмотрим пример: пусть у нас будет магазин, в котором продают хлеб, на витрине одновременно
  // может находиться не более пяти хлебов. Таким образом если на витрине пять хлебов, то продавец
  // не может доложить хлебов и должен ждать когда покупатель купит хотя бы один хлеб, после чего хлеб
  // можно докладывать. Что касается покупателя, то понятное дело если на витрине нет хлеба, он должен
  // ждать пока хлеб не появится. И давайте допустим что в день печется всего 10 хлебов.
  // Производителя хлеба будем называть Producer, а любителя поесть хлеб - Consumer (потребитель).
  // Каждый из них будет представлять отдельный поток, и они будут общаться между собой с помощью
  // методов wait и notify, так если хлеба нет на витрине, поток Consumer-а будет ожидать - вызывая
  // метод wait(). При добавлении хлеба производителем, когда производитель добавит хотя бы один хлеб,
  // он будет вызывать метод notify(), давая понять потребителю что хлеб появился и твой поток может
  // просыпаться, т.е. хлеб появился -> можешь покупать его. Но вызов метода notify() Producer-ом
  // не заставляет его поток уснуть, он может дальше производить свой хлеб. Метод notify() просто
  // будит поток Consumer-а, но сам не засыпает. А когда должет засыпать поток производителя?
  // Если хлеба стало 5 на прилавке, то производителю делать нечего, он не может больше добавить на
  // прилавок хлеб, и может поспать вызвав метод wait(). Поток потребителя, в свою очередь,
  // разбудит поток производителя тогда когда хотя бы один хлеб будет куплен. Если потребитель возьмет
  // один хлеб и хлеба станет 4, тогда он может вызвать метод notify() и сказать потоку
  // Producer-а - просыпайся, ты можешь класть хлеб на витрину. Вот такой будет пример.
}

class WaitNotify {

  public static void main(String[] args) {
    Market market = new Market();
    Producer1 producer1 = new Producer1(market);
    Consumer1 consumer1 = new Consumer1(market);
    Thread thread1 = new Thread(producer1);
    Thread thread2 = new Thread(consumer1);
    thread1.start();
    thread2.start();

  }
}

class Market {

  private int breadCount = 0;

  // метод симулирования взятия хлеба потребителем
  public synchronized void getBread() {
    while (breadCount < 1) {
      try {
        wait();
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }
    breadCount--;
    System.out.println("Потребитель купил 1 хлеб");
    System.out.println("Количество хлеба в магазине = " + breadCount);
    notify();  // сообщаем тем самым производителю, что кол-во хлеба уменьшилось на 1 -> ты можешь
    // добавлять хлеб
  }

  // метод добавления хлеба производителем
  public synchronized void putBread() {
    while (breadCount >= 5) {
      try {
        wait();
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }
    breadCount++; // если хлеба не 5, то добавляем хлеб
    System.out.println("Производитель добавил на витрину 1 хлеб");
    System.out.println("Количество хлеба в магазине = " + breadCount);
    notify(); // и должны известить потребителя что хлеб добавился
  }
}

// Теперь создадим потоки производителя и потребителя
class Producer1 implements Runnable {

  Market market;

  public Producer1(Market market) {
    this.market = market;
  }

  @Override
  public void run() { // производитель будет делать 10 хлебов в день
    for (int i = 0; i < 10; i++) {
      market.putBread();
    }
  }
}

class Consumer1 implements Runnable {

  Market market;

  public Consumer1(Market market) {
    this.market = market;
  }

  @Override
  public void run() { // потребитель сможет покупать 10 хлебов в день
    for (int i = 0; i < 10; i++) {
      market.getBread();
    }
  }
}

// В некоторых случаях наши вызовы notify(), будь то из getBread() или из putBread(), бывают холостыми
// т.е. другой поток, поток не из которого вызывается notify(), не ждет (он не находится в состоянии
// ожидания, он тоже активный, просто монитор занят не им) и тогда этот notify() бывает холостым.
// 📌 Очень важно понять, что все эти методы wait() notify() вызываются только из синхронизированного
// контекста, т.е из synchronized блока или метода. Данные методы вызываются на объекте, который
// используется для создания lock-а в наших synchronized методах или блоках, т.е. когда мы здесь в
// методах пишем просто wait() или просто notify(), то как и любой метод они срабатывают на /this/,
// это тоже самое что /this.notify();/ , и у нас синхронизация тоже идет по объекту /this/.
// Если же мы использовали допустим synchronized-блок и в нем указали что мы ставим lock на мониторе
// какого-то другого объекта (допустим создали объект lock класса Object) и синхронизироваться будем
// на нем в одном методе и тогда мы естесственно wait() и notify() должны вызывать используя этот объект
// на котором идет синхронизация /lock.wait();/  /lock.notify();/
// Сделаем тот же код только через lock  и output будет таким же  (код ниже)

class WaitNotify2 {

  public static void main(String[] args) {
    Market2 market2 = new Market2();
    Producer2 producer2 = new Producer2(market2);
    Consumer2 consumer2 = new Consumer2(market2);
    Thread thread1 = new Thread(producer2);
    Thread thread2 = new Thread(consumer2);
    thread1.start();
    thread2.start();

  }
}

class Market2 {

  private int breadCount = 0;
  private final Object lock = new Object();

  public void getBread() {
    synchronized (lock) {
      while (breadCount < 1) {
        try {
          lock.wait();                      // 📍
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
      }
      breadCount--;
      System.out.println("Потребитель купил 1 хлеб");
      System.out.println("Количество хлеба в магазине = " + breadCount);
      lock.notify();                         // 📍
    }
  }

  public void putBread() {
    synchronized (lock) {
      while (breadCount >= 5) {
        try {
          lock.wait();                        // 📍
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
      }
      breadCount++;
      System.out.println("Производитель добавил на витрину 1 хлеб");
      System.out.println("Количество хлеба в магазине = " + breadCount);
      lock.notify();                           // 📍
    }
  }
}

class Producer2 implements Runnable {

  Market2 market2;

  public Producer2(Market2 market) {
    this.market2 = market;
  }

  @Override
  public void run() {
    for (int i = 0; i < 10; i++) {
      market2.putBread();
    }
  }
}

class Consumer2 implements Runnable {

  Market2 market2;

  public Consumer2(Market2 market) {
    this.market2 = market;
  }

  @Override
  public void run() {
    for (int i = 0; i < 10; i++) {
      market2.getBread();
    }
  }
}

// 📍 Просто учтите тот момент на мониторе какого объекта вы делаете синхронизацию, на том же
// самом объекте вы должны вызывать wait() и notify()
// 📌 И очень важное ЗАМЕЧАНИЕ: wait() - освобождает монитор, notify() - не освобождает монитор.

// По методу wait(): может принимать милисекунды. Если напишем /wait(1000)/ - это будет означать
// что столько этот поток который ждет, будет ждать максимум по времени, т.е. если в течении 1сек
// другой поток какой-то не вызовет notify(), и этот поток не станет активным, то через 1сек он
// перестанет ждать и станет активным, т.е. какой из этих двух событий быстрее сработает, пройдет 1с или
// вызовется метод notify(), то событие и переведет наш thread вызвавший /wait(1000)/ в активное состояние

// И почему помещаем наш /wait()/ в while loop, а не в if loop. Это ремомендация из java-doc, говорится
// что поток оказывается в очень редких случаях может проснуться без notify() и тогда мы должны быть уверены что
// условие while перепроверится еще раз, если это просыпание потока было случайным, то опять сработает
// wait() и поток продолжит ждать, поэтому используем while, а не if. Если бы использовали if, то условие
// снова бы не проверялось и поток начал бы бордствовать тогда когда ему самое время ждать