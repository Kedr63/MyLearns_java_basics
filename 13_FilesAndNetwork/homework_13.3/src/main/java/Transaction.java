public class Transaction { // Класс для представления каждой операции транзакции как объект
    private String typeOperation;
    private double amountIncome;
    private double amountExpense;

    public Transaction(String typeOperation, double amountIncome, double amountExpense) {
        this.typeOperation = typeOperation;
        this.amountIncome = amountIncome;
        this.amountExpense = amountExpense;
    }

    public String getTypeOperation() {
        return typeOperation;
    }

    public void setTypeOperation(String typeOperation) {
        this.typeOperation = typeOperation;
    }

    public double getAmountIncome() {
        return amountIncome;
    }

    public void setAmountIncome(double amountIncome) {
        this.amountIncome = amountIncome;
    }

    public double getAmountExpense() {
        return amountExpense;
    }

    public void setAmountExpense(double amountExpense) {
        this.amountExpense = amountExpense;
    }

    @Override
    public String toString() {
        return "Transaction {" + "тип операции = '" + typeOperation + '\'' + ", amountIncome=" + amountIncome + ", amountExpense=" + amountExpense + '}';
    }
}

