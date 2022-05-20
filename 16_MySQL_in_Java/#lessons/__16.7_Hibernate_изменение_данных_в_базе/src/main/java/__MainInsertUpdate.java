import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class __MainInsertUpdate {
    /* Команды (update insert delete) по изменению данных БД нужно делать в транзакциях. Транзакция - это некая такая сессия,
     * в рамкой которой можем сделать много запросов и можем ее отменить.
     * Транзакция хороша тем что делается несколько запросов, и если у одного из запросов будет ошибка, то
     * транзакция автоматически отменяется */

    public static void main(String[] args) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();


        Session session = sessionFactory.openSession();
        // создаем транзакцию:
        Transaction transaction = session.beginTransaction();

        /* Теперь в рамках этой транзакции можно проводить изменения в БД.
         * 📍INSERT📍 Сначала создадим какой-нибудь новый курс: */
        Course course = new Course();
        course.setName("Новый курс");
        course.setType(CourseType.BUSINESS);
        course.setTeacherId(1);
        /* Создастся в таблице новый курс и ему присвоится id=50 (потом присвоилось 51) (хотя предыдущий был 46, не знаю почему так) */
        /* Если будем добавлять только имя курса, то поймаем исключения, так как курс не может быть создан без других полей*/

        /* 📍UPDATE📍 Теперь получим этот созданный курс и установим ему новое имя */
        /*Course course = session.get(Course.class, 51);
        course.setName("Совсем новый курс");*/

        session.save(course); // сохраняем изменения (в новых версиях save нет, не знаю какой вместо него)

        transaction.commit();
        sessionFactory.close();
    }
    /* Работу кода можно проверить подкл-сь к БД skillbox через терминал */
}
