import java.sql.*;

public class Test3 {
    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/skillbox";
        String user = "kedr63";
        String pass = "ampilere1976";

        try {
            Connection connection = DriverManager.getConnection(url, user, pass);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Students ORDER BY registration_date");


            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String name = resultSet.getString("name");
                String age = resultSet.getString("age");
                String regDate = resultSet.getString("registration_date");
                System.out.printf("%-3s \t %-20s\t %-3s\t %-15s\n", id, name, age, regDate);
            }

            resultSet.close();
            statement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
