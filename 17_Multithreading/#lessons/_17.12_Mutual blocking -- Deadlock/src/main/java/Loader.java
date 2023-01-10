public class Loader {

    // 📍 Взаимная блокировка - Deadlock. Это ситуация при которой два потока начинают ждать друг друга.
    // И блокируют какой-то один ресурс и никто не может уступить друг другу.
    // Возникает не часто, но тяжело отлаживается. Нужно проектировать ПО таким образом чтобы такая ситуация
    // не возникала в принципе

    public static void main(String[] args) {
        final Friend vasya = new Friend("Вася");
        final Friend vitya = new Friend("Витя");

        new Thread(() -> vasya.throwBallTo(vitya)).start();
        new Thread(() -> vitya.throwBallTo(vasya)).start();
    }
}