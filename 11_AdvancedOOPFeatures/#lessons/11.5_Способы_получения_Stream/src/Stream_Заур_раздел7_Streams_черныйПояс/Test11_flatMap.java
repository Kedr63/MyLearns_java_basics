package Stream_Заур_раздел7_Streams_черныйПояс;

import java.util.ArrayList;
import java.util.List;

public class Test11_flatMap {
    public static void main(String[] args) {

        // 📌 Метод /flatMap/ (i)  ________________________________________________________

        // Этот метод похож на метод /map/, но работает немного по другому

        Student st1 = new Student("Ivan", 'm', 22, 3, 8.3);
        Student st2 = new Student("Nikolay", 'm', 28, 2, 6.4);
        Student st3 = new Student("Elena", 'f', 19, 1, 8.9);
        Student st4 = new Student("Petr", 'm', 35, 4, 7);
        Student st5 = new Student("Maria", 'f', 23, 3, 9.1);

        // создадим факультеты
        Faculty f1 = new Faculty("Economics");
        Faculty f2 = new Faculty("Applied mathematics");

        // добавим студентов в факультеты
        f1.addStudentToFaculty(st1);
        f1.addStudentToFaculty(st2);
        f1.addStudentToFaculty(st3);
        f2.addStudentToFaculty(st4);
        f2.addStudentToFaculty(st5);

        // Теперь создадим лист факультетов
        List<Faculty> facultyList = new ArrayList<>();
        facultyList.add(f1);
        facultyList.add(f2);
        // Т/е какую мы имеем ситуацию? У нас есть лист факультетов, который содержит факультеты ➔ а сами
        // факультеты содержат листы своих студентов.
        // Метод /flatMap/ мы используем тогда, когда нам надо поработать не с самими элементами нашей
        // коллекции, а с элементами элементов нашей коллекции.
        // Например, в данном случае мы хотим вывести имена всех студентов из всех факультетов:
        facultyList.stream()
                .flatMap(faculty -> faculty.getStudentsOnFaculty()
                        .stream())
                .forEach(e-> System.out.println(e.getName()));

        // Вызываем метод /flatMap/ ⬆ -> что он делает? -> передаем сюда элемент /faculty/ ➔
        // ➔ из /faculty/ получаем другой лист /faculty.getStudentsOnFaculty()/, и уже на этом листе -
        // - на листе студентов конкретного факультета вызываем метод стрим, т/е сначала мы имеем
        // факультет ➔ с помощью этого факультета мы получаем лист студентов на этом факультете ➔
        // ➔ и уже на этом листе мы вызываем стрим ➔ и уже потом с помощью метода forEach мы можем
        // вывести имена всех студентов
        /*консоль:
        Ivan Nikolay Elena Petr Maria   */

        // С помощью метода /flatMap/ и вызова метода стрим на каждом элементе листа факультетов, мы
        // для каждой нашей коллекции создаем отдельный стрим, и все эти стримы в итоге образуют один
        // большой стрим из всех элементов facultyList.
        // ❤️ Еще раз в кратце: мы взяли первый элемент facultyList-а -> это первый факультет /f1/, нашли у
        // первого факультета лист его сотрудников и с помощью этого листа мы получаем поток и из этого
        // потока мы выводим имена всех студентов ➔ на следующей итерации мы получаем второй факультет /f2/ ➔
        // ➔ находим всех студентов на этом факультете ➔ из этого листа мы получаем поток ➔ и опять выводим
        // на экран имена всех студентов на факультете номер 2 /f2/.
        // Т/е в таких случаях когда нам нужно поработать с элементами элементов нашего листа (нашей коллекции)
        // мы можем использовать такой метод /flatMap/.


    }
}

class Faculty {
    String name;
    List<Student> studentsOnFaculty;

    public Faculty(String name) {
        this.name = name;
        studentsOnFaculty = new ArrayList<>();
    }

    public List<Student> getStudentsOnFaculty() {
        return studentsOnFaculty;
    }

    public void addStudentToFaculty(Student st) {
        studentsOnFaculty.add(st);
    }
}