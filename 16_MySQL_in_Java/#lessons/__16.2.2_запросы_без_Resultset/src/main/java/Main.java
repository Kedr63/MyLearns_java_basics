import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        // Запросы без result:
        /*  Как делать запросы которые не возвращают /result/. Их можно делать с помощью команды /execute()/, которая
        * возвращает boolean     */

        String url = "jdbc:mysql://localhost:3306/skillbox";
        String user = "kedr63";
        String pass = "ampilere1976";

        try {
            Connection connection = DriverManager.getConnection(url, user, pass);
            Statement statement = connection.createStatement();

            // применим команду
            statement.execute("UPDATE Courses SET name = 'Веб-разработчик с нуля до PRO' WHERE id = 1");
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Courses");

            while (resultSet.next()){
                String courseName = resultSet.getString("name");
                System.out.println(courseName);
            }
            resultSet.close();
            statement.close();
            connection.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
// Обновится первая профессия
/*  Веб-разработчик с нуля до PRO
    Мобильный разработчик с нуля
    Java-разработчик
    PHP-разработчик с 0 до PRO
    Python-разработчик с нуля
    и тд
* */
