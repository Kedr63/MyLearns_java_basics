public class Date_race_and_synchronized_methods_by_Zaur {

  // Разберем технику синхронизации потоков когда в нашем приложении несколько потоков хотят менять
  // значение нашей переменной, т.е потоки могут одновременно менять какие-то данные, это может
  // повлечь за собой некоторые нежелательные последствия
  // Расмотрим пример:
}

/*📍 2 */
class Ex1 {
  public static void main(String[] args) {
    MyRunnableImpl1 myRunnableImpl1 = new MyRunnableImpl1(); // и создадим 3 потока используя этот myRunnableImpl1
    Thread thread1 = new Thread(myRunnableImpl1);
    Thread thread2 = new Thread(myRunnableImpl1);
    Thread thread3 = new Thread(myRunnableImpl1);
    thread1.start();
    thread2.start();
    thread3.start();

    // консоль: 3 4 5 2 6 7 3 8 9  \\Как видим нет никакой последовательности, каждый новый запуск
    // дает новый output, даже если пропишем \volatile static int count = 0;\ это не поможет.
    // Здесь нет никакой синхронизации между потоками. А ожидали на консоль: 1 2 3 4 5 6 7 8 9
  }
}

/*📍 1 */
class Counter {
  static int count = 0;
}

class MyRunnableImpl1 implements Runnable {
  public void increment() {
    Counter.count++;
    System.out.print(Counter.count + " ");
  }

  @Override
  public void run() {
    for (int i = 0; i < 3; i++) {
      increment();
    }
  }
}
// 📍 Date race - гонка данных, т.е. потоки как будто бы участвуют в гонке, пытаются побыстрей проделать
// свои операции, у кого-то это получается быстрее у кого-то медленнее.
// Пока решение проблемы не предложил.

// Рассмотрим еще пример:
class Ex2 {
  static int counter = 0;
  public static void increment() {
    counter++;
  }

  public static void main(String[] args) throws InterruptedException {
    Thread thread1 = new Thread(new R());
    Thread thread2 = new Thread(new R());
    thread1.start();
    thread2.start();
    thread1.join();
    thread2.join();
    System.out.println(counter); // join() сначала дает потокам закончить и потом только выдедет
    // в консоль значение /counter/, если так не сделать то /counter/ выведется в консоль, а потоки
    // еще будут работать.
    // Чему будет равен /counter/? Первый поток инкрементирует на 1000 и второй также на 1000 и должно
    // получиться 2000. В итоге иногда он будет равняться 2000 иногда нет, происходит 📍Date race,
    // потому что работа наших потоков не синхронизированна, они друг от друга никак не зависят,
    // они работают парралельно делают свою работу и не обращают внимания что они могут помешать
    // друг другу
    // 📍Date race - это проблема, которая может возникнуть когда два и более потоков обращаются к
    // одной и той же переменной и как минимум один поток ее изменяет
    // даже если пропишем \volatile static int counter = 0;\ это не поможет.
  }
}

class R implements Runnable {
  @Override
  public void run() {
    for (int i = 0; i < 1000; i++) {
      Ex2.increment();
    }
  }
}

// Как решить эту проблему?
// Нам нужно быть увереным в том что в одно и тоже время с переменной \counter\ , т.е чтение и запись
// этой переменной в одно и тоже время будет производиться только одним потоком, т.е к этой переменной
// одновременно оба потока уже не обращались.
// Как этого добиться? Мы можем поставить /lock/ (переводится как замок) и добиться того что
// метод increment() в одно и тоже время работает только для одного потока, потому что нам важно чтобы
// counter увеличивался только одним потоком в один и тот же момент времени, это можно достичь с помощью
// установки замка. Ставится замок на определенный участок кода и теперь в одно и тоже время только
// один поток сможет выполнять наш метод (или участок кода), с помощью этого замка достигается
// синхронизация (это и есть принцип синхронизации)

// Эта синхронизация достигается с помощью ключего слова synchronized в описании нашего метода (см. пример ниже),
// и теперь эту операцию в один и тот же момент времени может делать или thread1 или thread2. Каждый из
// этих потоков будет забегать в метод increment() - 1000 раз. Какой поток будет шустрее, тот и будет
// заходить в метод первым - ставить замок, и только выходя из метода открывать этот замок, потом будет
// заходить другой поток - ставить замок и также выходя из метода открывать замок и так до конца выполнения программы.
// И теперь всегда будет выводиться на консоль: 2000
// Таким образом мы синхронизировали метод increment() и теперь с ним в одно и тоже время может работать только один поток
class Ex3 {
  static int counter = 0;
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


// И теперь синхронизируем первый пример в начале урока
class Ex4 {
  public static void main(String[] args) {
    MyRunnableImpl2 myRunnableImpl2 = new MyRunnableImpl2(); // и создадим 3 потока используя этот myRunnableImpl1
    Thread thread1 = new Thread(myRunnableImpl2);
    Thread thread2 = new Thread(myRunnableImpl2);
    Thread thread3 = new Thread(myRunnableImpl2);
    thread1.start();
    thread2.start();
    thread3.start();

    // консоль: 1 2 3 4 5 6 7 8 9  \\ Теперь то что мы ожидали
  }
}

class Counter1 {
  static int count = 0;
}

class MyRunnableImpl2 implements Runnable {

  public synchronized void increment() {
    Counter1.count++;
    System.out.print(Counter1.count + " ");
  }

  @Override
  public void run() {
    for (int i = 0; i < 3; i++) {
      increment();
    }
  }
}

// 📌 Пример метода:
// public synchronized void abc() {method body}

// Как изнутри работает этот пример локинга и анлокинга рассмотрим в следующем уроке





