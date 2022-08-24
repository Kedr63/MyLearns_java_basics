public class Friend implements Comparable<Friend> {

    private final String name;

    public Friend(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    /*public synchronized void throwBallTo(Friend catcher) {
        System.out.format("%s: %s кинул мне мяч!%n", catcher.getName(), this.name);
        catcher.throwBallTo(this);  // здесь метод вызывает сам себя только в обратную сторону и печатает
    } */ // консоль:
    // Витя: Вася кинул мне мяч!
    //Вася: Витя кинул мне мяч!
    // и программа здесь по сути останавливается, потоки ждут, но программа продолжает работать - зависает
    // Как это выглядит: 1-й поток вошел в метод throwBallTo(Friend catcher) - выполнил его,
    // напечатал строчку \Витя: Вася кинул мне мяч!\ и хочет выполнить \catcher.throwBallTo(this)\
    // 2-й поток вошел в метод throwBallTo(Friend catcher) - напечатал строчку \Вася: Витя кинул мне мяч!\
    // но выполнить этот же метод \catcher.throwBallTo(this)\ потому что этот метод пытается выполнить 1-й поток,
    // и они начинают ждать друг друга.
    // Как это предотвращать?  Самый расп-ный способ это установка очередности блокировки, создадим
    // synchronized-блок чтоб установить очередность и сделать эту очередность с помощью
    // сравнения объектов по алфавиту или метода compareTo() и определять который из них меньше и
    // блокировать например только по тому объекту который меньше и все начнет работать и взаимных блокировок
    // больше не возникает

    public void throwBallTo(Friend catcher) {
        System.out.format("%s: %s кинул мне мяч!%n", catcher.getName(), this.name);
        synchronized (compareTo(catcher) > 0 ? catcher : this) {
            catcher.throwBallTo(this);
        }
    }


    @Override
    public int compareTo(Friend o) {
        return this.getName().compareTo(o.getName());
    }
}
