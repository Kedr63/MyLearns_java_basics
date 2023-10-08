import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnection {

  private static Connection connection;

  private static String dbName = "learn";
  private static String dbUser = "root";
  private static String dbPass = "2805";

  private static StringBuilder insertQuery = new StringBuilder();  // применим это поле

  public static Connection getConnection() {
    if (connection == null) {
      try {
        connection = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/" + dbName + "?user=" + dbUser + "&password=" + dbPass);
                /*connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/" + dbName, dbUser, dbPass);*/
        connection.createStatement().execute("DROP TABLE IF EXISTS voter_count");
        connection.createStatement().execute(
            "CREATE TABLE voter_count(" + "id INT NOT NULL AUTO_INCREMENT, "
                + "name TINYTEXT NOT NULL, " + "birthDate DATE NOT NULL, "
                + "`count` INT NOT NULL, " + "PRIMARY KEY(id), "
                + "UNIQUE KEY name_date(name(50), birthDate))"); // создадим уникальный ключ для оптимизированного метода
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    return connection;
  }

/*  public static void countVoter(String name, String birthDay) throws SQLException {
    birthDay = birthDay.replace('.', '-');
    String sql =
        "SELECT id FROM voter_count WHERE birthDate='" + birthDay + "' AND name='" + name + "'";
    ResultSet rs = DBConnection.getConnection().createStatement().executeQuery(sql);
    // если в БД нет такой записи, то вставим такую запись в БД
    // (первый вызов метода next делает первую строку текущей строкой; второй вызов делает вторую строку текущей строкой и так далее.)
    if (!rs.next()) {
      DBConnection.getConnection().createStatement().execute(
          "INSERT INTO voter_count(name, birthDate, `count`) VALUES('" + name + "', '" + birthDay
              + "', 1)");
      // иначе если есть уже в БД такая запись, то обновим у voter-а count
    } else {
      Integer id = rs.getInt("id");
      DBConnection.getConnection().createStatement()
          .execute("UPDATE voter_count SET `count`=`count`+1 WHERE id=" + id);
    }
    rs.close();
  }*/

  /**
   * оптимизируем метод выше, применив уже StringBuilder, и запрос выполнится однократно
   */
  public static void countVoterOptimize(String name, String birthDay) throws SQLException {
    birthDay = birthDay.replace('.', '-');

    insertQuery.append((insertQuery.length() == 0 ? "" : ",") + "('" + name + "', '" + birthDay + "', 1)");

  }

  // и создадим метод для выполнения этого мультизапроса
  public static void executeMultiInsert() throws SQLException {
    String sql =
        "INSERT INTO voter_count (name, birthDate, `count`) VALUES" + insertQuery.toString() + "ON DUPLICATE KEY UPDATE `count`=`count` + 1";
    DBConnection.getConnection().createStatement().execute(sql);
  }

  public static void printVoterCounts() throws SQLException {
    String sql = "SELECT name, birthDate, `count` FROM voter_count WHERE `count` > 1";
    ResultSet rs = DBConnection.getConnection().createStatement().executeQuery(sql);
    while (rs.next()) {
      System.out.println(
          "\t" + rs.getString("name") + " (" + rs.getString("birthDate") + ") - " + rs.getInt(
              "count"));
    }
    rs.close();
  }
}
