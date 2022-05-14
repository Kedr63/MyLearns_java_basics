import java.sql.*;

public class MainWork {
    private static final String URL = "jdbc:mysql://localhost:3306/skillbox";
    private static final String USER = "root";
    private static final String PASS = "";
    private static final String QUERY_AVG_COUNT_PURCHASE = "SELECT course_name, " +
            "COUNT(*) / (MONTH(MAX(subscription_date)) - MONTH(MIN(subscription_date)) + 1) " +
            "AS avg_count_purchase_per_month FROM PurchaseList " +
            "WHERE subscription_date BETWEEN '2018-01-01 00:00:00' AND '2019-01-01 00:00:00' GROUP BY course_name";
    // пояснение к формуле в запросе:
    /* С / ((L - F) + 1) = среднее количество покупок в месяц для каждого курса за 2018 год (с учетом диапазона месяцев),
    где
    С - кол-во купленных подписок за период в условии
    L - месяц последней подписки в текущем году
    F - месяц первой подписки
    так как необходим диапазон месяцев включая L и F, то прибавим +1      */

    public static void main(String[] args) {

        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASS);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(QUERY_AVG_COUNT_PURCHASE);

            while (resultSet.next()){
                String name = resultSet.getString("course_name");
                Double avg = Double.parseDouble(resultSet.getString("avg_count_purchase_per_month"));
                System.out.printf("%-34s %5.2f\n", name, avg);
            }

            resultSet.close();
            statement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
