public abstract class Client {

    // задекларируем переменную - будет хранить значение суммы счета и защитим ее в границах класса
    private double amount;

    protected double getAmount() {
        return amount;
    }

    // изменим метод, добавив параметр *Client person*, чтобы
    // классы наследники используя род. метод могли применять
    // свои данные при работе метода
    protected void put(Client person, double amountToPut) {
        if (amountToPut <= 0) {
            System.out.println("Сумма должна быть больше нуля");
        } else amount += amountToPut - person.getDepositCommission(amountToPut);
    }

    // изменим метод, добавив параметр *Client person*, чтобы
    // классы наследники используя род. метод могли применять
    // свои данные при работе метода
    protected void take(Client person, double amountToTake) {
        if (amountToTake > amount || amountToTake <= 0) {
            System.out.println("Сумма не соответствует требуемому значению");
        } else amount -= amountToTake + person.getWithdrawalCommission(amountToTake);
    }

    // создадим абстр. метод вывода информации об условии счета и его состоянии
    protected abstract void printInformation();

    // возвращает сумму комиссии при снятии
    protected abstract double getWithdrawalCommission(double amount);

    // возвращает сумму комиссии при пополнении
    protected abstract double getDepositCommission(double amount);


}
