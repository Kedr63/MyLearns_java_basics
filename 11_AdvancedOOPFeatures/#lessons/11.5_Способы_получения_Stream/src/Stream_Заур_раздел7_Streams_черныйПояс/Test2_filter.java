package Stream_Заур_раздел7_Streams_черныйПояс;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Test2_filter {
    public static void main(String[] args) {

        // 📌 Метод /filter/ (i)  ______________________________________________________

        Student st1 = new Student("Ivan", 'm', 22, 3, 8.3);
        Student st2 = new Student("Nikolay", 'm', 28, 2, 6.4);
        Student st3 = new Student("Elena", 'f', 19, 1, 8.9);
        Student st4 = new Student("Petr", 'm', 35, 4, 7);
        Student st5 = new Student("Maria", 'f', 23, 3, 9.1);
        List<Student> students = new ArrayList<>();
        students.add(st1);
        students.add(st2);
        students.add(st3);
        students.add(st4);
        students.add(st5);

        // Отфильтруем студентов и оставим только тех, возвраст которых больше 22 года и ср. оценка
        // меньше 7,2 ⬇
        // Опишем код ниже: из нашей коллекции мы получаем стрим (это будет неизменно) ➔ потом мы
        // вызываем метод /filter/, который в параметре принимает /Predicate/, т/е в вместо параметра
        // мы должны написать /Predicate/ → (т/е написать как будет выглядеть наш метод, а метод должен
        // возвращать true или false).
        // Вот так работает метод /filter/.
        // Смотрите как это работает: 📍 /filter/ возвращает тоже стрим: т/е мы получили стрим из 5-ти
        // студентов (на вход метода filter), и /filter/ вернет стрим с теми студентами, которые пройдут
        // этот фильтр.
        // И если мы хотим в итоге этот стрим преобразовать в коллекцию (допустим, в List), то мы
        // используем метод /collect/.
        /*   students.stream()
                   .filter(element -> element.getAge() > 22 && element.avgGrade < 7.2)
                   .collect(Collectors.toList());     */

        // И давайте присвоим нашему List-у students вот этот лист /toList())/. И этот лист будет
        // содержать только тех студентов, которые прошли через этот фильтр. И выведем этот лист
        // на экран
        students = students.stream()
                           .filter(element -> element.getAge() > 22 && element.avgGrade < 7.2)
                           .collect(Collectors.toList());

        System.out.println(students);
        /*   консоль:
        Student{name='Nikolay', sex=m, age=28, course=2, avgGrade=6.4},
        Student{name='Petr', sex=m, age=35, course=4, avgGrade=7.0}     */
    }
}

// Знакомый уже нам класс Студента (с урока про ЛВ)
class Student {
    String name;
    char sex;
    int age;
    int course;
    double avgGrade; // средняя оценка

    Student(String name, char sex, int age, int course, double avgGrade) {
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.course = course;
        this.avgGrade = avgGrade;
    }

    @Override
    public String toString() {
        return "Student{" + "name='" + name + '\'' + ", " + "sex=" + sex + ", age=" + age + ", " + "course=" + course + ", avgGrade=" + avgGrade + '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getCourse() {
        return course;
    }

    public void setCourse(int course) {
        this.course = course;
    }

    public double getAvgGrade() {
        return avgGrade;
    }

    public void setAvgGrade(double avgGrade) {
        this.avgGrade = avgGrade;
    }
}
