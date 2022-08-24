public class Monitor_synchronized_blocks_by_Zaur_продолжение {

  // Здесь когда мы в описании метода используем synchronized и этот метод static, то используется монитор всего
  // класса SynchronizedBlock2

}
//________________________________________________
// Пример:
class SynchronizedBlock2 {

  static int counter = 0;

  // если используем synchronized-блок:
   public static void increment() {
    synchronized (SynchronizedBlock2.class){
      counter++;
    }
  }

  public static void main(String[] args) throws InterruptedException {
    Thread thread1 = new Thread(new R());
    Thread thread2 = new Thread(new R());
    thread1.start();
    thread2.start();
    thread1.join();
    thread2.join();
    System.out.println(counter);
  }
}

class R implements Runnable {
  @Override
  public void run() {
    for (int i = 0; i < 1000; i++) {
      SynchronizedBlock2.increment();
    }
  }
}

// Пример_______________________________________________
class Ex3 {

  static int counter = 0;

  // если используем synchronized-метод:
  public static synchronized void increment() {
    counter++;
  }

  public static void main(String[] args) throws InterruptedException {
    Thread thread1 = new Thread(new R1());
    Thread thread2 = new Thread(new R1());
    thread1.start();
    thread2.start();
    thread1.join();
    thread2.join();
    System.out.println(counter);
  }
}

class R1 implements Runnable {

  @Override
  public void run() {
    for (int i = 0; i < 1000; i++) {
      Ex3.increment();
    }
  }
}

// ______________________________________________________________________________
// Рассмотрим еще один пример. 📍
// Мы можем синхр-ть работу не только одного метода, но мы может синхр-ть работу нескольких методов.
// Придумаем такую задачку: нам могут звонить на моб. телефон используя моб. связь, на скайп могут
// звонить исп-я моб. телефон и по вотсАпп, у нас будет три разных потока ответственные за звонки
// с разных каналов, и придумаем правило что если мы уже разговариваем, используя любой из этих каналов
// и нам приходит звонок с другого канала, то этот звонок будет ждать пока не закончится текущий

class Ex1 {

  static final Car car = new Car();

  void mobileCall() {
    // System.out.println(this);
    synchronized (car) {
      System.out.println("Mobile call starts");
      try {
        Thread.sleep(3000);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
      System.out.println("Mobile call ends");
    }
  }

  void skypeCall() {
    //  System.out.println(this);
    synchronized (car) {
      System.out.println("Skype call starts");
      try {
        Thread.sleep(5000);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
      System.out.println("Skype call ends");
    }
  }

  void whatsAppCall() {
    // System.out.println(this);
    synchronized (car) {
      System.out.println("WhatsApp call starts");
      try {
        Thread.sleep(7000);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
      System.out.println("WhatsApp call ends");
    }
  }

  public static void main(String[] args) {
    Thread thread1 = new Thread(new RunnableImplMobile());
    Thread thread2 = new Thread(new RunnableImplSkype());
    Thread thread3 = new Thread(new RunnableImplWhatsApp());
    thread1.start();
    thread2.start();
    thread3.start();
    // без синхр-ции на консоль:
    // Mobile call starts
    // WhatsApp call starts
    // Skype call starts
    // Mobile call ends
    // Skype call ends
    // WhatsApp call ends
    // _______ Это не то что нас устраивает, теперь добавим synchronized к методам и опять ничего не работает
    // в консоли будет также, опять три звонка начались одновременно и заканчиваются в зависимости от их
    // длительности. Почему это так работает? Когда мы испол-ем synchronized в сигнатуре метода, а метод у нас
    // не статичный, тогда по умолчанию используется синхр-ция по объекту this. Выведем на экран /this/ для
    // каждого метода - добавив System.out.println(this).
    // _______Теперь консоль:
    // Ex1@7e81c0c7  📍
    // Mobile call starts
    // Ex1@396c174  📍
    // WhatsApp call starts
    // Ex1@294e986e  📍
    // Skype call starts
    // Mobile call ends
    // Skype call ends
    // WhatsApp call ends       Посмотрите, синхронизация идет по трем разным объектам (отм.📍).
    //  _______ Когда mobileCall() запускается, синхр-ция идет по этому объекту /Ex1@7e81c0c7/, монитор
    // этого объекта занят, и так же с остальными методами. Это абсолютно не то что нам нужно!
    // Если мы хотим сделать как хотели, нам нужно сделать синхронизацию по одному объекту, а у нас
    // происходит по трем. Почему так происходит? Потому что у нас в скобках стоит this поэтому в
    // данном примере нужно использовать synchronized-блоки и синхр-цию делать по одному объекту.
    // Мы можем использовать абсолютно любой объект, например мы можем создать класс Car и исп-ть объект
    // этого класса /static final Car car = new Car();/ для синхр-ции всех методов. Теперь изменим методы:
    // уберем с сигнатуры методов synchronized и сделаем синх-цию по car. Теперь эти три метода
    // синхронизируем по одному и тому же объекту, мы испол-ем монитор для синхр-ции одного и того же объекта.
    // Теперь консоль: ____________
    // Mobile call starts
    // Mobile call ends
    // WhatsApp call starts
    // WhatsApp call ends
    // Skype call starts
    // Skype call ends
    // _________________Теперь все последовательно. Порядок вида связи конечно может меняться.
    // Конечно не стоит применять выдуманный класс, обычно пишут:
    //_______ 📍 static final Object lock = new Object(); 📍
    // и синхронизируются по нему. И по названию переменной и самого объекта всем становится ясно что он был
    // создан для испол-я исключительно как /lock/
    // 📌 Шаблон synchronized-блока:
    // static final Object lock = new Object();
    // public void abc() { тело метода
    // synchronized(lock) {block body} тело метода }
    // ___________
    // P.S.: не возможно синхр-ть конструктор, JVM гарантирует что конструктор может обрабатываться в
    // одно и тоже время только одним потоком

  }
}

// И создадим три класса которые имплементируют Runnable
class RunnableImplMobile implements Runnable {

  @Override
  public void run() {
    new Ex1().mobileCall();
  }
}

class RunnableImplSkype implements Runnable {

  @Override
  public void run() {
    new Ex1().skypeCall();
  }
}

class RunnableImplWhatsApp implements Runnable {

  @Override
  public void run() {
    new Ex1().whatsAppCall();
  }
}

class Car {
}