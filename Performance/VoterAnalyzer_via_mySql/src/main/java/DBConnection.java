import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnection {

    private static Connection connection;

    private static String dbName = "learn";
    private static String dbUser = "root";
   // private static String dbPass = "ya78yrc8n4w3984";
   private static String dbPass = "2805";

    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/" + dbName +
                        "?user=" + dbUser + "&password=" + dbPass);
                /*connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/" + dbName, dbUser, dbPass);*/
                connection.createStatement().execute("DROP TABLE IF EXISTS voter_count");
                connection.createStatement().execute("CREATE TABLE voter_count(" +
                    "id INT NOT NULL AUTO_INCREMENT, " +
                    "name TINYTEXT NOT NULL, " +
                    "birthDate DATE NOT NULL, " +
                    "`count` INT NOT NULL, " +
                    "PRIMARY KEY(id), " +
                    "UNIQUE KEY name_date(name(50), birthDate))"); // создадим уникальный ключ для оптимизированного метода
              // "UNIQUE KEY name_date(name(50), birthDate))"
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    public static void countVoter(String name, String birthDay) throws SQLException {
        birthDay = birthDay.replace('.', '-');
        String sql =
            "SELECT id FROM voter_count WHERE birthDate='" + birthDay + "' AND name='" + name + "'";
        ResultSet rs = DBConnection.getConnection().createStatement().executeQuery(sql);
      // если в БД нет такой записи, то вставим такую запись в БД
      // (первый вызов метода next делает первую строку текущей строкой; второй вызов делает вторую строку текущей строкой и так далее.)
        if (!rs.next()) {
            DBConnection.getConnection().createStatement()
                .execute("INSERT INTO voter_count(name, birthDate, `count`) VALUES('" +
                    name + "', '" + birthDay + "', 1)");
          // иначе если есть уже в БД такая запись, то обновим у voter-а count
        } else {
            Integer id = rs.getInt("id");
            DBConnection.getConnection().createStatement()
                .execute("UPDATE voter_count SET `count`=`count`+1 WHERE id=" + id);
        }
        rs.close();  // если закоментируем, то произойдет утечка памяти, ResultSet-ы начнут копиться в памяти не закрытые и память их очищать
                     // автоматически не будет и получим ошибку OutOfMemory. Очень важно! Когда чтото делаем с БД в конце нужно закрывать и
                     // ResultSet-ы и по хорошему Connection-ы, если создаем много Connection-ов (это уже не утечка памяти), лучше на одну программу
                     // держать только одно соединение
    }
  /** оптимизируем метод выше, сделаем все одним запросом */
  public static void countVoterOptimize(String name, String birthDay) throws SQLException {
    birthDay = birthDay.replace('.', '-');
    // в mySql (через индексы) есть способ реализации счетчика , -> сделаем два поля (name и birthDay) уникальным индексом, и при очередном
    // добавлении сюда значения которое уже есть в БД, по идее должна произойти ошибка, но есть специальный метод /DUPLICATE KEY UPDATE/ и
    // для него пишем что должны сделать когда сталкиваемся с такой ошибкой. Получается если такой ключ встречается в индексе, то добавляем
    // к полю count +1
    String sql = "INSERT INTO voter_count (name, birthDate, `count`) VALUES('" + name + "', '" + birthDay +"', 1) "
        + "ON DUPLICATE KEY UPDATE `count`=`count` + 1";
    DBConnection.getConnection().createStatement().execute(sql);

  }

    public static void printVoterCounts() throws SQLException {
        String sql = "SELECT name, birthDate, `count` FROM voter_count WHERE `count` > 1";
        ResultSet rs = DBConnection.getConnection().createStatement().executeQuery(sql);
        while (rs.next()) {
            System.out.println("\t" + rs.getString("name") + " (" +
                rs.getString("birthDate") + ") - " + rs.getInt("count"));
        }
        rs.close();
    }

  /** 📌 Для поиска например по запросу: SELECT * FROM voter_count WHERE name='Артур Пирожков', поиск происходим перебором всех записей в БД. И нам
   * нужно проиндексировать все элементы чтобы искать по ним быстро (проиндексировать поля). Добавим индекс на поле 'name' /KEY(name(50))/,
   * и добавим при создании таблицы ключ, также как и id
   *   connection.createStatement().execute("CREATE TABLE voter_count(" +
   *                     "id INT NOT NULL AUTO_INCREMENT, " +
   *                     "name TINYTEXT NOT NULL, " +
   *                     "birthDate DATE NOT NULL, " +
   *                     "`count` INT NOT NULL, " +
   *                     "PRIMARY KEY(id), KEY(name(50))");     //обязательно указать длину
   *
   *   Индексы можно делать разных видов, можно делать HASH-типа, можно BTREE (это аналогично будет индексам в коллекциях HashMap и TreeMap или
   *   HashSet и TreeSet, в одном случае это будет hash-функция в другом B-дерево), бывают уникальные индексы по двум полям сразу и когда используем
   *   поиск по нескольким полям, порядок полей тоже имеет значение: если у поля мало значений то лучше чтобы оно стояло на первом месте, например,
   *   если у нас здесь имя и дата, то лучше чтобы на первом месте стояла дата, потому что из 120 милионов человек даты у нас точно будут
   *   пересекаться не однократно, а имена здесь гораздо реже пересекаются. И если здесь дата будет на первом месте, то дерево поиска так
   *   называемое, будет меньше, по нему нужные элементы будут искать быстрее.
   *
   *     📌 Запросы бывают очень сложные, и если у нас есть какой-то очень сложный селект, то лучше его вызвать с оператором EXPLAIN, он позволяет
   *      понять как работает ваш запрос, какие индексы он использует и в какой последовательности он и использует
   *
   *      */
}
