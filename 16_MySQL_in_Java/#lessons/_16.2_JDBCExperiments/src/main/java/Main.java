import java.sql.*;

public class Main {
    public static void main(String[] args) {
        // Для того чтобы подключится к MySQL серверу создадим соединение /connection/:

        String url = "jdbc:mysql://localhost:3306/skillbox";    // классический путь
        /* skillbox - имя БД к которой подключаемся, можно не указывать, а просто потом использовать команду /use/, но
        * обычно одно приложение использует одну БД, поэтому удобней указать ее сразу здесь при подключении */

        String user = "root";
        String pass = "";

        try {
            Connection connection = DriverManager.getConnection(url, user, pass);

            // Для того чтобы сделать SQL запрос:
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Courses");

            // /resultSet/ можно перебирать
            while (resultSet.next()){    // если у /resultSet/ есть следующая строчка то вернет -/true/
                String courseName = resultSet.getString("name");
                System.out.println(courseName);
            }
            resultSet.close();
            statement.close();
            connection.close(); // главное закрыть connection
            /* если программа будет выполнятся дальше, то это соединение зависнет в памяти и будет память занимать.
            * Количество соединений к БД ограничено, если их непрерывно создавать то будут проблемы с mysql */

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        /* Что такое /resultSet/?
        *   Любой SQL-запрос возвращает таблицу. Эта таблица состоит из столбцов и строк, т.е. из полей и их записей,
        * соответственно если мы получаем /resultSet/, мы его можем итерировать по записям, т.е. по строчкам. Вызываем
        * первый раз метод /.next()/ и /resultSet/ содержит в себе текущую строчку (текущую запись) из этой строчки
        * можно получать данные различными методами, в зависимости от типа данных (если это строка, то можно по имени
        * столбца получить это поле)   */

        // _______ Вот таким образом мы можем получать данные из базы в /resultSet/ _____________

    }
}
