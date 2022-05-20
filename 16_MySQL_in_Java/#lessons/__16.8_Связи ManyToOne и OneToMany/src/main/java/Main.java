import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class Main {
    /* (БД skillbox) В случае с учителями и курсами у нас связь многие к одному, т.е. у одного учителя может быть много курсов, но
    * у каждого курса только один учитель  */
    public static void main(String[] args) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();


        Session session = sessionFactory.openSession();

        Transaction transaction = session.beginTransaction();


        Course course = session.get(Course.class, 1);
        /* Например, хотим получить из нашего курса сразу учителя (имя), а не его id. Для этого нужно создать pojo-класс (Teacher), и
        * внести изменения по teacher в класс Course. Также добавить в конфигурационный файл (hibernate.cfg.xml) строку по классу
        * Teacher (<mapping class="Teacher"/>) */
        System.out.println(course.getTeacher().getName()); // консоль: Ягешев Сидор

        transaction.commit();
        sessionFactory.close();
    }
}
