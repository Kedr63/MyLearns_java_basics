import com.mysql.cj.x.protobuf.MysqlxCrud;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import jakarta.persistence.EntityManager;
import org.hibernate.type.StandardBasicTypes;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


public class Main {
    /* Hibernate query builder и язык запросов HQL (hibernate query language) */
   /* На практике часто нужны сложные запросы*/


    public static void main(String[] args) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();


        Session session = sessionFactory.openSession();

         Course course1 = session.get(Course.class, 1); // это простой запрос
        /* На практике часто нужны сложные запросы. Здесь мы не можем взять и просто выполнить sql-запрос
        * Есть 2 способа делать сложные запросы через hibernate:
        * 📍 класс QueryBuilder
        * 📍 язык запросов hql   */

        /* Сначала посмотрим как работает класс QueryBuilder: */
        CriteriaBuilder builder = session.getCriteriaBuilder();
        /* Допустим хотим курсы получать */
        CriteriaQuery<Course> query = builder.createQuery(Course.class);
        Root<Course> root = query.from(Course.class);
        query.select(root);
        List<Course> courseList = session.createQuery(query).getResultList();

        /* И распечатаем название курсов: */
        for (Course course : courseList){
            System.out.println(course.getName());
        }
        /* Выскочит ошибка. Устраним ее изменив в классе Course /private int studentsCount/ на / private Integer studentsCount/,
        * потому что int не может быть равен null  */






        //___Мои экперименты с HQL_______________________________________________________
        // (https://docs.jboss.org/hibernate/orm/current/userguide/html_single/Hibernate_User_Guide.html#hql-conditional-expressions)

        List<Student> students = session.createQuery("from Student", Student.class).getResultList();
        for (Student student : students){
            System.out.println("student: " + student);
        }
        System.out.println("распечатали студентов");

        // можно так (аналогично выше)
        List<Student> students1 = session.createQuery("select s from Student s", Student.class).getResultList();
        for (Student student : students1){
            System.out.println("student: " + student);
        }
        System.out.println("распечатали студентов");

        // можно распечатать только имена
        List<String> studentNames = session.createQuery("from Student s select s.name", String.class).getResultList();
        for (String name : studentNames){
            System.out.println("student name: " + name);
        }
        System.out.println("распечатали студентов");

        // abs()
        //Величина числового значения
        List<Integer> abs = session.createQuery("select abs(s.age) " +
                                "from Student s ", Integer.class).getResultList();
        System.out.println("ABS age: " + abs);

        // predicate Операторы отношений ___________________________________________________________________________
        List<Student> studentList = session.createQuery("select s from Student s " +
                                "where s.age < 30 ", Student.class).getResultList();
        System.out.println("Student < 30: ");
        for (Student student : studentList){
            System.out.println(student);
        }

        // predicate Сопоставление шаблонов строк
        List<Student> studentList1 = session.createQuery("select s from Student s " +
                "where s.name like 'Ца%' ", Student.class).getResultList();
        System.out.println("Student with name begin 'Ца ': ");
        for (Student student : studentList1){
            System.out.println(student);
        }

        List<Object[]> studentList2 = session.createQuery("select distinct st, sb " +
                                "from Student st, Subscription sb " +
                                "where sb.student = st and sb is not null", Object[].class).getResultList();
        System.out.println("Object[]");
        for (Object [] objects : studentList2){
            System.out.println(Arrays.stream(objects).toList());
        }

        /* Выборка данных с помощью Criteria. Вы можете создавать запросы без написания SQL запросов (только с помощью объектов) */

        // Пример 543. Выбор корневой сущности
        // (https://docs.jboss.org/hibernate/orm/current/userguide/html_single/Hibernate_User_Guide.html#criteria)
        CriteriaBuilder builder1 = session.getCriteriaBuilder();
        CriteriaQuery<Student> query1 = builder1.createQuery(Student.class);
        Root<Student> root1 = query1.from(Student.class);
        // выберем студентов старше 36
        query1.select(root1).where(builder1.greaterThan(root1.get("age"), 36));
        // выберем студента с определенным именем
     //   criteria.where(builder1.equal(root1.get("name"), "Носов Макар")); // you must use the entity field names and not the database column names

        List<Student> studentList3 = session.createQuery(query1).getResultList();
        System.out.println("Criteria");
        for (Student student : studentList3){
            System.out.println(student);
        }


        // Собственные SQL-запросы
        System.out.println("Собственные SQL-запросы");
        List<Object[]> listStudents = session.createNativeQuery("SELECT * FROM Students").getResultList();
        for (Object [] obs : listStudents){
            System.out.println(Arrays.stream(obs).toList());
        }


        // Hibernate собственный запрос с пользовательским выбором столбца
        System.out.println("Hibernate собственный запрос с пользовательским выбором столбца");
        List<Object[]> studentsList1 = session.createNativeQuery(
                        "SELECT id, name FROM Students").list();

        for(Object[] student : studentsList1) {
            int id = (int) student[0];
            String name = (String) student[1];
            System.out.println(id + " " + name);
        }

        // Hibernate собственный запрос с явным выбором результирующего набора
        System.out.println("Hibernate собственный запрос с явным выбором результирующего набора");
        List<Object[]> studentsList = session.createNativeQuery(
                        "SELECT * FROM Students")
                .addScalar("id", StandardBasicTypes.INTEGER)
                .addScalar("name", StandardBasicTypes.STRING)
                .list();

        for(Object[] student : studentsList) {
            int id = (int) student[0];
            String name = (String) student[1];
            System.out.println(id + " " + name);
        }


        sessionFactory.close(); // обязательно закрыть в конце сессию
    }
}
