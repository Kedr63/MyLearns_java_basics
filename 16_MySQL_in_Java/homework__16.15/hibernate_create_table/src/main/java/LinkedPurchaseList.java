import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "LinkedPurchaseList")
public class LinkedPurchaseList {

    @EmbeddedId
    private KeyLinkedPurchaseList id;

    @Column(name = "student_id", insertable = false, updatable = false)
    private int studentId;

    @Column(name = "course_id", insertable = false, updatable = false)
    private int courseId;


    public LinkedPurchaseList() {
    }

    public LinkedPurchaseList(int studentId, int courseId) {
        this.id = new KeyLinkedPurchaseList(studentId, courseId);
        this.studentId = studentId;
        this.courseId = courseId;
    }

    public KeyLinkedPurchaseList getId() {
        return id;
    }

    public void setId(KeyLinkedPurchaseList id) {
        this.id = id;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }
}
