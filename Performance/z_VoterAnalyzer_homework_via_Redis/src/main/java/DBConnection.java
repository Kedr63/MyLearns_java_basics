import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class DBConnection {

  private static Connection connection;

  private static String dbName = "learn";
  private static String dbUser = "root";
  private static String dbPass = "2805";

  private static int counterInvokeMulti = 0;
  public static int getCounterInvokeMulti() {
    return counterInvokeMulti;
  }

  private static int multiInvoke = 0;

  public static int getMultiInvoke() {
    return multiInvoke;
  }

 // private static final int STRING_BUILDER_CAPACITY = 1_000_000; //    5mil - 345
 private static final int STRING_BUILDER_CAPACITY = 3_000_000; //    5mil - 345
  // private static final int STRING_BUILDER_CAPACITY = 5_000_000; //    5mil - 340
  //  private static final int STRING_BUILDER_CAPACITY = 10_000_000; //    5mil - 353с
  // private static final int STRING_BUILDER_CAPACITY = 30_000_000;  // 5mil - 344c
  //   private static final int STRING_BUILDER_CAPACITY = 16_777_216; // 16   -79c    5mil - 343
  // private static final int STRING_BUILDER_CAPACITY = 33_554_432; // 32  -82c         5mil - 351
  //  private static final int STRING_BUILDER_CAPACITY = 67_108_864; // 64Mb capacity -83   5mil - 347c
  //  private static final int STRING_BUILDER_CAPACITY = 134_217_728; // 128  -98             5mil - 379
  private static StringBuilder insertQuery = new StringBuilder(STRING_BUILDER_CAPACITY);

  public static Connection getConnection() {
    if (connection == null) {
      try {
        connection = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/" + dbName + "?user=" + dbUser + "&password=" + dbPass);
        connection.createStatement().execute(
            "DROP TABLE IF EXISTS voter_count"); // таблица каждый раз пересоздается чтобы не было кеширования
        connection.createStatement().execute(
            "CREATE TABLE voter_count(" + "id INT NOT NULL AUTO_INCREMENT, "
                + "name TINYTEXT NOT NULL, " + "birthDate DATE NOT NULL, "
                + "`count` INT NOT NULL, "
                + "PRIMARY KEY(id), UNIQUE KEY name_date(name(50), birthDate))");
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    return connection;
  }

  public static void countVoter(String name, String birthDay) throws SQLException {
    if (insertQuery.length() >= STRING_BUILDER_CAPACITY -100) {
      executeMultiInsert();
      System.out.println("StringBuilder = " + insertQuery.capacity() + " length = " + insertQuery.length());

      insertQuery = new StringBuilder(STRING_BUILDER_CAPACITY);
      counterInvokeMulti++;
    }
    birthDay = birthDay.replace('.', '-');
    insertQuery.append(
        (insertQuery.length() == 0 ? "" : ",") + "('" + name + "', '" + birthDay + "', 1)");

  }


  // и создадим метод для выполнения этого мультизапроса
  public static void executeMultiInsert() throws SQLException {
    String sql =
        "INSERT INTO voter_count (name, birthDate, `count`) VALUES" + insertQuery.toString()
            + "ON DUPLICATE KEY UPDATE `count`=`count` + 1";
    DBConnection.getConnection().createStatement().execute(sql);
multiInvoke++;
    // при выполнении возникала ошибка (Packet for query is too large (92489011 > 67108864).
    // You can change this value on the server by setting the 'max_allowed_packet' variable.), что слишком большой размер INSERT-а (который
    // увеличивал StringBuilder), решил проблему
    // см. скрин "max_allowed_packet"  ссылка: https://stackoverflow.com/questions/8062496/how-to-change-max-allowed-packet-size
    // см. скрин переустановка значения max_allowed_packet
  }

  public static StringBuilder getInsertQuery() {
    return insertQuery;
  }

  /*  public static void countVoter(String name, String birthDay) throws SQLException {
        birthDay = birthDay.replace('.', '-');
        String sql =
            "SELECT id FROM voter_count WHERE birthDate='" + birthDay + "' AND name='" + name + "'";
        ResultSet rs = DBConnection.getConnection().createStatement().executeQuery(sql);
        if (!rs.next()) {
            DBConnection.getConnection().createStatement()
                .execute("INSERT INTO voter_count(name, birthDate, `count`) VALUES('" +
                    name + "', '" + birthDay + "', 1)");
        } else {
            Integer id = rs.getInt("id");
            DBConnection.getConnection().createStatement()
                .execute("UPDATE voter_count SET `count`=`count`+1 WHERE id=" + id);
        }
        rs.close();
    }*/

  public static void printVoterCounts() throws SQLException {
    String sql = "SELECT name, birthDate, `count` FROM voter_count WHERE `count` > 1";
    // ResultSet rs = DBConnection.getConnection().createStatement().executeQuery(sql);
    ResultSet rs = connection.createStatement().executeQuery(sql);
    while (rs.next()) {
      System.out.println(
          "\t" + rs.getString("name") + " (" + rs.getString("birthDate") + ") - " + rs.getInt(
              "count"));
    }
    rs.close();
    //   connection.close();
  }
}
