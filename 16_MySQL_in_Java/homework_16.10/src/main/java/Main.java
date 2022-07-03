import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;


public class Main {
    /* (БД skillbox) Множественная связь всегда делается через отдельную таблицу, потому что в одном поле несколько значений хранить
    не удобно. У нас здесь - это таблица Subscriptions (поля: student_id, course_id, subscription_date).
      1. Создадим сначала студента чтоб связать с ним курс (class Student).
      2. Создали class Student - теперь курсы связаны со студентами
      и можно задать связь только в одном из классов, сделаем это в классе Course: создадим в нем коллекцию со студентами.
       В файл hibernate.cfg.xml добавим (<mapping class="Student"/>)*/

    public static void main(String[] args) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();

        Session session = sessionFactory.openSession();

        Transaction transaction = session.beginTransaction();

        Course course = session.get(Course.class, 1);
        System.out.println(course.getStudents().size()); // консоль: 6
        // И распечатаем студентов, которые подписаны на этот курс
        List<Student> students = course.getStudents();
        for (Student student : students) {
            System.out.println(student.getName());
        }

        System.out.println("get subscr");
        Subscription subscription = session.get(Subscription.class, 1);
        System.out.println(subscription.getStudentId() + " " + subscription.getCourseId() + " " + subscription.getSubscriptionDate());

        //  Student student = session.get(Student.class, 50);

        Teacher teacher = session.get(Teacher.class, 10);
        List<Course> courseList = teacher.getCourses();
        System.out.println(teacher.getName() + " " + teacher.getId());
        for (Course course1 : courseList) {
            System.out.println(course1.getName());
        }

        Course course2 = session.get(Course.class, 5);
        System.out.println(course2.getDescription());

        transaction.commit();
        sessionFactory.close();
    }
}
