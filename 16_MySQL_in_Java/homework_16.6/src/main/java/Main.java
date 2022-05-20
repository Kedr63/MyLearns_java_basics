import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class Main {
    public static void main(String[] args) {
        SessionFactory sessionFactory = SessionUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        Student student = session.get(Student.class, 1);
        System.out.println(student.getName() + " " + student.getRegistrationDate() + "\n");

        int id = 1;
        while (session.contains(session.get(Course.class, id))) {
            Course course = session.get(Course.class, id);
            System.out.println("id:" + course.getId() + "  Имя курса: " + course.getName() + " количество студентов: " + course.getStudentsCount());
            id++;
        }

        sessionFactory.close();
    }
}
