import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.time.LocalDateTime;
import java.util.List;


public class Main {

    public static void main(String[] args) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();

        Session session = sessionFactory.openSession();

        Transaction transaction = session.beginTransaction();

        Student student = session.get(Student.class, 2);
        List<Subscription> subscriptionList = student.getSubscriptions();
        for (Subscription subscription : subscriptionList) {
            System.out.println(subscription.getCourse().getId() + " " + subscription.getCourse().getName());
        }
        System.out.println("Done");

        Course course = session.get(Course.class, 48);
        course.setName("Вязание спицами");
        session.persist(course);

        Teacher teacher = session.get(Teacher.class, 10);
        List<Course> courseList = teacher.getCourses();
        System.out.println(teacher.getName() + " " + teacher.getId());
        for (Course course1 : courseList) {
            System.out.println(course1.getName());
        }
        System.out.println("Done");

        // DELETE 📍
        // get student and delete him
        /* Student student3 = session.get(Student.class, 102);
           session.remove(student3);       */


//        Subscription subscription1 = new Subscription(80, 48, LocalDateTime.of(2022, 02, 23, 00, 00, 00));
//        session.persist(subscription1);


        // очистим таблицу PurchaseList чтоб далее заполнить ее актуальными данными
        String sq21 = "DELETE FROM " + PurchaseList.class.getSimpleName();
        session.createQuery(sq21).executeUpdate();


        // заполним таблицу PurchaseList с помощью таблиц Subscriptions и Students
        String sql = "From " + Student.class.getSimpleName();
        List<Student> studentList = session.createQuery(sql, Student.class).list();


        for (Student st : studentList) {
            String nameStudent = st.getName();
            List<Subscription> subscriptions = student.getSubscriptions();

            for (Subscription subscription : subscriptions) {
                String nameCourse = subscription.getCourse().getName();
                LocalDateTime dateSubscription = subscription.getSubscriptionDate();
                int priceCourse = subscription.getCourse().getPrice();
                PurchaseList purchaseList = new PurchaseList(nameStudent, nameCourse, priceCourse, dateSubscription);
                session.persist(purchaseList);
            }
        }

        transaction.commit();
        sessionFactory.close();
    }
}

