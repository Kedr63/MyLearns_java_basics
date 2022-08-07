import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(name = "Courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private int duration;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "enum")
    private CourseType type;

    private String description;

    /* @Column(name = "teacher_id")*/
    // Для создания связи - поле выше поменяем на поле ниже: (при таком каскаде удалив курс учитель не удалится)
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH, CascadeType.MERGE}, fetch = FetchType.LAZY)
    private Teacher teacher;

    @Column(name = "students_count", nullable = true)
    private Integer studentsCount;

    private int price;

    @Column(name = "price_per_hour")
    private float pricePerHour;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH, CascadeType.MERGE})
    @JoinTable(name = "Subscriptions",   // пропишем какую таблицу будем исп-ть для связи этих двух сущностей (курсов и студентов)
            joinColumns = {@JoinColumn(name = "course_id")}, // перечислим поля из Subscriptions, которые соединяем
            inverseJoinColumns = {@JoinColumn(name = "student_id")})
    private List<Student> studentList;  // создали коллекцию студентов и зададим ей связь ManyToMany


    public Course() {
    }

    public Course(String name, int duration, CourseType type, Teacher teacher, int price) {
        this.name = name;
        this.duration = duration;
        this.type = type;
        this.price = price;
        this.teacher = teacher;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public CourseType getType() {
        return type;
    }

    public void setType(CourseType type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }


    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public float getPricePerHour() {
        return pricePerHour;
    }

    public void setPricePerHour(float pricePerHour) {
        this.pricePerHour = pricePerHour;
    }


    public List<Student> getStudentList() {
        return studentList;
    }

    public int getStudentsCount() {
        return studentsCount;
    }

    public void setStudentsCount(int studentsCount) {
        this.studentsCount = studentsCount;
    }

    @Override
    public String toString() {
        return "Course{" + "id=" + id + ", name='" + name + '\'' + ", duration=" + duration + ", type=" +
                type + ", description='" + description + '\'' + ", teacher=" + teacher + ", studentsCount=" +
                studentsCount + ", price=" + price + ", pricePerHour=" + pricePerHour + ", studentList=" + studentList + '}';
    }
}
