import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class Main {
    /* Система Hibernate нужна для того чтобы легко преобразовывать записи из БД в объекты Java  */
    // 📍 Создадим класс Course (из БД skillbox), и посмотрев структуру таблицы Course, запишем в класс поля из этой таблицы
    /* 📍 В файле /pom.xml/ создадим зависимости mysql и hibernate
    *  📍 Создадим файл конфигурации /hibernate.cfg.xml/ и обязательно в нем отредактировать свои данные (user, pass, БД)
    *  Чтобы класс Courses подцепился, внутри класса нужно написать аннотации  */

    // Теперь подключимся к БД и получим оттуда какой нибудь курс
    public static void main(String[] args) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();

        /* Рекомендуется эту процедуру делать один раз в коде, где-то статически инициализировать один раз
        * и потом этот /sessionFactory/ просто получать (лучше создать отдельный класс) */

        // Теперь нужно получить session (сессию так называемую)
        Session session = sessionFactory.openSession();

        Course course = session.get(Course.class, 1); // получим курс с id = 1
        System.out.println(course.getName());
        System.out.println(course.getPrice() + " руб");

        sessionFactory.close(); // обязательно закрыть в конце сессию


    }
}
