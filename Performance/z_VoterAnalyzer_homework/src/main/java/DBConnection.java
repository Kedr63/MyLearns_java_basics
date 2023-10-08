import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnection {

  private static Connection connection;

  private static String dbName = "learn";
  private static String dbUser = "root";
  private static String dbPass = "2805";

  private static final int DELTA = 100; // для уменьшения емкости билдера

  private static final int STRING_BUILDER_CAPACITY = 15_000_000; // в ходе проб оказалась оптимальная емкость

  private static StringBuilder insertQuery = new StringBuilder(STRING_BUILDER_CAPACITY); // Сразу зададим емкость, чтоб избежать следующего
  // сценария, где производительность пострадает -> " Каждый раз, когда наращиваемая строка становится слишком большой для ее хранения в массиве,
  // StringBuilder должен выделить новый, больший массив, скопировать данные из предыдущего массива в новый и удалить предыдущий массив"

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
                + "PRIMARY KEY(id), UNIQUE KEY name_date(name(50), birthDate))"); //
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    return connection;
  }

  public static void addVoterToDB(String name, String birthDay) throws SQLException {
    // если длина StringBuilder накопится больше /STRING_BUILDER_CAPACITY - DELTA/ то выполним множественный запрос
    if (insertQuery.length() >= STRING_BUILDER_CAPACITY - DELTA) {   // будем уменьшать емкость на DELTA, чтоб длина запроса не перескачила
      // за края емкости (за один append добавляется несколько десятков char и отсюда length() в условии может перепрыгнуть емкость и
      // затем емкость увеличится в 2 раза (выделение нового большего массива, копирование данных из предыдущего массива в новый и
      // удаление предыдущего массива)).

      executeMultiInsert();

      /** и сбросим накопленный билдер*/
      insertQuery.setLength(0);     // эта строка быстрей сбрасывает билдер
      // insertQuery.delete(0, insertQuery.length());   //  альтернатива строке 51
      // insertQuery = new StringBuilder(STRING_BUILDER_CAPACITY); //  альтернатива строке 51
    }
    birthDay = birthDay.replace('.', '-');
    insertQuery.append(
        (insertQuery.length() == 0 ? "" : ",") + "('" + name + "', '" + birthDay + "', 1)");

  }


  // и создадим метод для выполнения этого мультизапроса
  public static void executeMultiInsert() throws SQLException {
    String sql =
        "INSERT INTO voter_count (name, birthDate, `count`) VALUES" + insertQuery.toString()
            + "ON DUPLICATE KEY UPDATE `count`=`count` + 1";     // если наш /UNIQUE KEY/ дублируется то обновим его /count/
    DBConnection.getConnection().createStatement().execute(sql);
    // при выполнении возникала ошибка (Packet for query is too large (92489011 > 67108864).
    // You can change this value on the server by setting the 'max_allowed_packet' variable.), что слишком большой размер INSERT-а (который
    // увеличивал StringBuilder), решил проблему
    // см. скрин "max_allowed_packet"  ссылка: https://stackoverflow.com/questions/8062496/how-to-change-max-allowed-packet-size
    // см. скрин переустановка значения max_allowed_packet
  }

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
  }

  protected static void closeConnection(){
    try {
      connection.close();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
}
