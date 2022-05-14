import java.sql.*;

public class Test1 {
    public static void main(String[] args) {
        // выполним команду: show tables

        String url = "jdbc:mysql://localhost:3306/skillbox";
        String user = "root";
        String pass = "";

        try {
            Connection connection = DriverManager.getConnection(url, user, pass);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("show tables");


            while (resultSet.next()) {
                String table = resultSet.getString(1);
                System.out.printf(table + "\n");
            }

            resultSet.close();
            statement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
