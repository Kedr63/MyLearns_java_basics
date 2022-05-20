import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;


public class Main {
    /* (БД skillbox) Множественная связь всегда делается через отдельную таблицу, потому что в одном поле несколько значений хранить
    не удобно. У нас здесь это таблица Subscriptions (поля: student_id, course_id, subscription_date).
      Создадим сначала студента чтоб связать с ним курс (class Student). Создали class Student - теперь курсы связаны со студентами
      и можно задать связь только в одном из классов, сделаем это в классе Course: создадим в нем коллекцию со студентами.
       В файл hibernate.cfg.xml добавим (<mapping class="Student"/>)*/

    public static void main(String[] args) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();

        Session session = sessionFactory.openSession();

        Transaction transaction = session.beginTransaction();

        Course course = session.get(Course.class, 1);
        System.out.println(course.getStudents().size()); // консоль

        transaction.commit();
        sessionFactory.close();
    }
}
