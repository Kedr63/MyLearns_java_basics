import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Movements {
    List<Transaction> transactionList;

    public Movements(String path) { // при создании объекта, объект получит
        loadMovements(path);    // список операций с приходом-расходом из файла
    }                        // и с этим объектом уже можно работать оперируя его данными

    public List<Transaction> loadMovements(String pathMovementsCsv) {
        transactionList = new ArrayList<>();
        int columnDescriptionOperation = 0; // сохраним сюда index столбца "Описание операции"
        int columnIncome = 0;     // столбец "Приход"
        int columnExpense = 0;    // столбец "Расход"
        try {
            List<String> lines = Files.readAllLines(Paths.get(pathMovementsCsv));
            for (String line : lines) {
                if (line.equals(lines.get(0))) {  // из первой строки найдем индексы нужных столбцов
                    String[] lineExtract = line.split(",");
                    for (int i = 0; i < lineExtract.length; i++) {
                        if (lineExtract[i].contains("Описание операции")) {
                            columnDescriptionOperation = i;
                        }
                        if (lineExtract[i].contains("Приход")) {
                            columnIncome = i;
                        }
                        if (lineExtract[i].contains("Расход")) {
                            columnExpense = i;
                        }
                    }
                    continue;
                }
                /** разделителем столбцов в файле является символ /,/ (запятая)
                 поэтому сначала обработаем строки где /,/ применяется в нецелых значениях  */
                if (line.contains("\"")) {  // найдем строки содержащие /"/ (т.к. числа с /,/ "123,56" спрятаны в них)
                    line = getLinePreparedForSplit(line); // method will return lines prepared for split ("123,56" -> 123.56)
                }
                String[] arrayLine = line.split(",");
                if (arrayLine.length != 8) {
                    System.out.println("Неверная длина строки");
                } else {
                    String descriptionOperation = arrayLine[columnDescriptionOperation];
                    String nameOperation = getExtractFromColumnDescriptionOperation(descriptionOperation);
                    double income = Double.parseDouble(arrayLine[columnIncome]);
                    double expense = Double.parseDouble(arrayLine[columnExpense]);
                    transactionList.add(new Transaction(nameOperation, income, expense));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return transactionList;
    }

    public double getExpenseSum() {
        double totalAmount = 0;
        for (Transaction operation : transactionList) {
            totalAmount += operation.getAmountExpense();
        }
        return totalAmount;
    }

    public double getIncomeSum() {
        double totalAmount = 0;
        for (Transaction operation : transactionList) {
            totalAmount += operation.getAmountIncome();
        }
        return totalAmount;
    }

    // метод получения строки подготовленной к разделению
    private static String getLinePreparedForSplit(String line) {
        String search = line.substring(line.indexOf('\"') + 1, line.lastIndexOf('\"'));
        String result = search.replace(",", ".");
        return line.replaceAll("\".+\"", result);
    }

    // метод извлечения из столбца "Описание операции" -> имени операции
    private static String getExtractFromColumnDescriptionOperation(String descriptionOperation) {
        Pattern pattern = Pattern.compile("([\\\\|/])([\\w>\\s]+)(\\s{3,})");
        Matcher matcher = pattern.matcher(descriptionOperation);
        return matcher.find() ? matcher.group(2).trim() : "notFoundOperation"; // return то что во 2-х скобках
    }
}
