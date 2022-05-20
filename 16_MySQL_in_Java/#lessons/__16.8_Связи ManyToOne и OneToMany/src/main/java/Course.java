import javax.persistence.*;


@Entity // подключаем пакет
@Table(name = "Courses")
public class Course {
    @Id  // id обязательно должен иметь такую аннотацию
    @GeneratedValue(strategy = GenerationType.IDENTITY) // пишет такую аннотацию,т.к. id с auto_increment
    private int id;

    private String name;

    private int duration;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "enum")
    private CourseType type; // это ENUM

    private String description;

    /* Свойство (cascade) определяет тип взаимодействия с другими таблицами, если мы отсюда что нибудь удаляем или
    * наоборот добавляем */
    @ManyToOne(cascade = CascadeType.ALL)
    private Teacher teacher; // здесь поменяли с (int teacherId) на (Teacher teacher), и соответ-но геттер и сеттер

    @Column(name = "students_count")
    private int studentsCount;

    private int price;

    @Column(name = "price_per_hour")
    private float pricePerHour;

    // И создадим геттеры и сеттеры

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getStudentsCount() {
        return studentsCount;
    }

    public void setStudentsCount(int studentsCount) {
        this.studentsCount = studentsCount;
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
}
