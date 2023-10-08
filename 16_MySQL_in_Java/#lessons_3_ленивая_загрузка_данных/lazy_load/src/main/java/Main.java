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
    /* 📍 Ленивая загрузка данных */

    public static void main(String[] args) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();


        Session session = sessionFactory.openSession();



        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Course> query = builder.createQuery(Course.class);
        Root<Course> root = query.from(Course.class);
        query.select(root);
        List<Course> courseList = session.createQuery(query).getResultList();

//        for (Course course : courseList){
//            System.out.println(course.getName());
//        }
        /* Кроме курсов Hibernate также будет запрашивать всех учителей: первый запрос получает все курсы, а потом для
         * каждого из курсов получает учителя. Почему так происходит? Потому что у курса есть свойство /private Teacher teacher;/
         * Но учитель нам здесь допустим не нужен, здесь мы хотим распечатать название курсов, и так может потребляться много ресурсов,
         * поскольку в память из БД загружается много информации.
         * Поэтому есть два механизма загрузки связанных данных из базы:
         * 📍 Eager (действует по умолчанию. Получаем все данные сразу)
         * 📍 Lazy
         * И теперь у свойства /private Teacher teacher;/ добавим параметр /fetch = FetchType.LAZY/. И теперь запроса учителей не будет
         * */

        /* Но можем и вместе с курсами распечатать учителей (здесь запрос совершается по мере необходимости)*/
       try {
           int i = 1;
           for (Course course : courseList){
               System.out.println(i + ".  " + course.getName() + " - " + course.getTeacher().getName());
               i++;
           }
       } catch (Exception e){
           System.out.println(e.getMessage());
       }



        sessionFactory.close(); // обязательно закрыть в конце сессию


    }
}
