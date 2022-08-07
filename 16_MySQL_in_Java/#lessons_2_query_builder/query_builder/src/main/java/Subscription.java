
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Subscriptions")
public class Subscription {

    @EmbeddedId
    private KeySubscription id;

    /* @Column(name = "student_id", insertable = false, updatable = false)
    private int studentId;*/
    // Для создания связи - поле выше поменяем на поле ниже:
    // Много подписок к одному студенту
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH
            , CascadeType.REFRESH, CascadeType.MERGE}) // при таком каскаде удаляя подписку студент не удалится
    @JoinColumn(name = "student_id", insertable = false, updatable = false) // здесь FK
    private Student student;

    /* @Column(name = "course_id", insertable = false, updatable = false)
    private int courseId;*/
    // Для создания связи - поле выше поменяем на поле ниже: (при таком каскаде удалив подписку курс не удалится)
    @ManyToOne (cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH, CascadeType.MERGE})
    @JoinColumn(name = "course_id", insertable = false, updatable = false)
    private Course course;

    @Column(name = "subscription_date")
    private LocalDateTime subscriptionDate;


    public Subscription() {
    }

    public Subscription(int studentId, int courseId, LocalDateTime subscriptionDate) {
        this.id = new KeySubscription(studentId, courseId);
        this.subscriptionDate = subscriptionDate;
    }

    public KeySubscription getId() {
        return id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public LocalDateTime getSubscriptionDate() {
        return subscriptionDate;
    }

    public void setSubscriptionDate(LocalDateTime subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
    }

    @Override
    public String toString() {
        return "Subscription{" + "id=" + id + ", student=" + student + ", course=" + course +
                ", subscriptionDate=" + subscriptionDate + '}';
    }
}
