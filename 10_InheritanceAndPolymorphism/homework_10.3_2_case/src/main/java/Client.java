public abstract class Client {

    // задекларируем переменную - будет хранить значение суммы счета и защитим ее в границах класса
    private double amount;


    protected double getAmount() {
        return amount;
    }

    protected void put(double amountToPut) {
        if (amountToPut <= 0) {
            System.out.println("Сумма должна быть больше нуля");
        } else amount += amountToPut;
    }


    protected void take(double amountToTake){
        if (amountToTake > amount || amountToTake <= 0) {
            System.out.println("Сумма не соответствует требуемому значению");
        } else amount -= amountToTake;
    }

    // создадим абстр. метод вывода информации об условии счета и его состоянии
    protected abstract void printInformation();


}
