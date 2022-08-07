import java.time.LocalDateTime;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;


public class Main {
    /* Создадим таблицу в БД: Создадим класс LinkedPurchaseList и класс KeyLinkedPurchaseList. Пропишем в файле конфигурации
    * эти два класса
    * <mapping class="LinkedPurchaseList"/>
      <mapping class="KeyLinkedPurchaseList"/>
      *
        * и изменим параметр с validate на update:
        * <property name="hbm2ddl.auto">update</property>
        *
        * И после запуска main в БД появится таблица LinkedPurchaseList*/

  public static void main(String[] args) {
    StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure(
        "hibernate.cfg.xml").build();
    Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
    SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();

    Session session = sessionFactory.openSession();

    Transaction transaction = session.beginTransaction();

    // Созданная Hibernate-ом таблица LinkedPurchaseList имеет поля в следующем порядке (course_id, student_id) - нас
    // такой порядок не устраивает, по заданию нужно наоборот (student_id, course_id),
    // поэтому пересоздадим таблицу LinkedPurchaseList
    String dropTable = "DROP Table " + LinkedPurchaseList.class.getSimpleName();
    session.createNativeQuery(dropTable, LinkedPurchaseList.class).executeUpdate();

    String createTable = "Create Table " + LinkedPurchaseList.class.getSimpleName()
        + " (`student_id` INT NOT NULL, `course_id` INT NOT NULL, PRIMARY KEY (`student_id`, `course_id`), UNIQUE(`student_id`, `course_id`))";
    session.createNativeQuery(createTable, LinkedPurchaseList.class).executeUpdate();


    // 📍 очистим таблицу PurchaseList чтоб далее заполнить ее актуальными данными (если изменяли вдруг другие таблицы)
    String hqlDeleteDatePurchaseList = "DELETE FROM " + PurchaseList.class.getSimpleName();
    session.createNativeQuery(hqlDeleteDatePurchaseList, PurchaseList.class).executeUpdate();

    // 📍 и также очистим таблицу LinkedPurchaseList чтоб далее заполнить ее актуальными данными из таблицы PurchaseList
    String hqlDeleteDateLinkedPurchaseList =
        "DELETE FROM " + LinkedPurchaseList.class.getSimpleName();
    session.createNativeQuery(hqlDeleteDateLinkedPurchaseList, LinkedPurchaseList.class)
        .executeUpdate();


    // 📍 заполним таблицу PurchaseList с помощью таблиц Subscriptions и Students
    String sql = "From " + Student.class.getSimpleName();
    List<Student> studentList = session.createQuery(sql, Student.class).list();

    for (Student st : studentList) {
      String nameStudent = st.getName();
      int studentId = st.getId(); // для таблицы LinkedPurchaseList
      List<Subscription> subscriptions = st.getSubscriptions();

      for (Subscription subscription : subscriptions) {
        String nameCourse = subscription.getCourse().getName();
        LocalDateTime dateSubscription = subscription.getSubscriptionDate();
        int priceCourse = subscription.getCourse().getPrice();
        PurchaseList purchaseList = new PurchaseList(nameStudent, nameCourse, priceCourse,
            dateSubscription);
        session.persist(purchaseList);
        // 📍 и заполним таблицу linkedPurchaseList
        int courseId = subscription.getCourse().getId();
        LinkedPurchaseList linkedPurchaseList = new LinkedPurchaseList(studentId, courseId);
        session.persist(linkedPurchaseList);
      }
    }

    // распечатаем таблицу LinkedPurchaseList
    String hqlGetLinkedPurchaseList = "FROM " + LinkedPurchaseList.class.getSimpleName();
    List<LinkedPurchaseList> linkedPurchaseLists = session.createQuery(hqlGetLinkedPurchaseList,
        LinkedPurchaseList.class).list();
    System.out.printf("%12s %12s %n", "student_id", "course_id");
    for (LinkedPurchaseList lpl : linkedPurchaseLists) {
      System.out.printf("%7d %14d %n", lpl.getStudentId(), lpl.getCourseId());
    }

    transaction.commit();
    sessionFactory.close();

    System.out.println("Ready");
  }
}

