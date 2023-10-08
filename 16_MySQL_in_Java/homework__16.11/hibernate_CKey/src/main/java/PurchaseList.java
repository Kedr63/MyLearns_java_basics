import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "PurchaseList", uniqueConstraints = @UniqueConstraint(columnNames = {"student_name", "course_name"}))
public class PurchaseList {
    // Эта таблица будет заполняться данными с Subscriptions и Course в main

    @EmbeddedId
    private KeyPurchaseList id;

    @Column(name = "student_name", insertable = false, updatable = false)
    private String studentName;

    @Column(name = "course_name", insertable = false, updatable = false)
    private String courseName;

    private int price;

    @Column(name = "subscription_date")
    private LocalDateTime subscriptionDate;

    public PurchaseList() {
    }

    public PurchaseList(String studentName, String courseName, int price, LocalDateTime subscriptionDate) {
        this.id = new KeyPurchaseList(studentName, courseName);
        this.studentName = studentName;
        this.courseName = courseName;
        this.price = price;
        this.subscriptionDate = subscriptionDate;
    }

    public KeyPurchaseList getId() {
        return id;
    }

    public void setId(KeyPurchaseList id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public LocalDateTime getSubscriptionDate() {
        return subscriptionDate;
    }

    public void setSubscriptionDate(LocalDateTime subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
    }
}
