import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;


public class Main {
    /* 📍 Язык HQL */
   /* Бывает что sql-запрос нужно сделать очень большой и страшный, тогда проще использовать язык HQL  */


    public static void main(String[] args) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();

        Session session = sessionFactory.openSession();

        /* Пример */
        String hql = "From " + Course.class.getSimpleName() + " Where price > 120000";
        List<Course> courseList = session.createQuery(hql).getResultList();
        System.out.println(courseList.size());
        for (Course course : courseList){
            System.out.println(course.getName() + " - " + course.getPrice());
        }

        sessionFactory.close(); // обязательно закрыть в конце сессию
    }
}
