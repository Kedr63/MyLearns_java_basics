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
    /* 📍 Where и OrderBy */
   /* */


    public static void main(String[] args) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();

        Session session = sessionFactory.openSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Course> query = builder.createQuery(Course.class);
        Root<Course> root = query.from(Course.class);

        /* Получим 5 самых высокооплачиваемых курсов дороже 100_000 руб и упорядочим от самого дорого до самого дешевого */
        query.select(root).where(builder.greaterThan(root.get("price"), 100000)).orderBy(builder.desc(root.get("price")));
        /* И возьмем пять самых первых результатов  */
        List<Course> courseList = session.createQuery(query).setMaxResults(5).getResultList();


        for (Course course : courseList){
            System.out.println(course.getName() + " - " + course.getPrice());
        }

        sessionFactory.close(); // обязательно закрыть в конце сессию
    }
}
