import java.sql.*;

public class Test2 {
    public static void main(String[] args) {
        // выполним команду: describe Courses

        String url = "jdbc:mysql://localhost:3306/skillbox";
        String user = "kedr63";
        String pass = "ampilere1976";

        try {
            Connection connection = DriverManager.getConnection(url, user, pass);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("describe Courses");


            while (resultSet.next()) {
                String nameField = resultSet.getString(1); // первый столбец
                String typeValue = resultSet.getString(2);  // второй столбец
                String type = resultSet.getString(3);
                System.out.printf("%-20s \t %-65s \t %-10s \t\n", nameField, typeValue, type);
                // System.out.printf(nameField + "\n");
            }
            System.out.println("____________________________________________\n");
            /*while (resultSet.next()){
                String id = resultSet.getString("id");
                String name = resultSet.getString("name");
                String duration = resultSet.getString("duration");
                System.out.printf("%-20d \t %-65s \t %-10f \t\n", id, name, duration);

            }*/

            resultSet.close();
            statement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
