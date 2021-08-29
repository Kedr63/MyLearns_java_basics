public abstract class Client {

    // задекларируем переменную - будет хранить значение суммы счета и защитим ее в границах класса
    private double amount;

    protected void setAmount(double amount){
        this.amount = amount;
    }

    protected double getAmount() {
        return amount;
    }

    protected abstract void put(double amountToPut);


    protected abstract void take(double amountToTake);

    // создадим абстр. метод вывода информации об условии счета и его состоянии
    protected abstract void printInformation();


}
