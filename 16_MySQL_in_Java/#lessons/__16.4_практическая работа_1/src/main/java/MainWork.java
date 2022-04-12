import java.sql.*;
import java.util.Date;

public class MainWork {
    private static final String URL = "jdbc:mysql://localhost:3306/skillbox";
    private static final String USER = "kedr63";
    private static final String PASS = "ampilere1976";
    private static final String QUERY_AVG_COUNT_PURCHASE = "SELECT course_name, " +
            "(COUNT(subscription_date) / (MAX(MONTH(subscription_date)) - MIN(MONTH(subscription_date)) + 1)) " +
            "AS avg_count_purchase_per_month FROM PurchaseList " +
            "WHERE subscription_date BETWEEN \"2018-01-01 00:00:00\" AND \"2019-01-01 00:00:00\" GROUP BY course_name";
    // пояснение к формуле в запросе:
    /* С / ((L - F) + 1) = среднее количество покупок в месяц для каждого курса за 2018 год (с учетом диапазона месяцев),
    где
    С - кол-во купленных подписок (по дате подписки)
    L - месяц последней подписки в текущем году
    F - месяц первой подписки
    так как необходим диапазон месяцев включая L и F, то прибавим +1      */

    public static void main(String[] args) {

        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASS);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(QUERY_AVG_COUNT_PURCHASE);

            System.out.println();
            while (resultSet.next()){
                String name = resultSet.getString("course_name");
                Double avg = Double.parseDouble(resultSet.getString("avg_count_purchase_per_month"));
                System.out.printf("%-35s %10.2f\n", name, avg);
            }

            resultSet.close();
            statement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
