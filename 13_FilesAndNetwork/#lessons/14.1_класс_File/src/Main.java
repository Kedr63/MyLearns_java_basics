import java.io.File;

public class Main {
    public static void main(String[] args) {
        // Свойства файлов
        // Создадим объект класса File
        // И создадим перед этим на рабочем столе файл с каким-то текстом
        // Будет записан абсолютный путь ⬇︎ (начинается от корня файловой системы)
        File file = new File("/Users/aleksandrshabalin/Desktop/Info_for_lesson.txt");
        // здесь путь записан в стиле linux, если у вас ubuntu то тоже будет начинаться со слэша "/", в linux
        // все слэши прямые

        // 📍 а если Windows ⬇︎ (путь начинается относительно имени диска, он тоже считается абсолютным)
        String path = "C:\\Users\\aleksandrshabalin\\Desktop\\file_lessons.txt";
        // но можно заменить на прямые и они в java будут работать
        String path1 = "C:/Users/aleksandrshabalin/Desktop/file_lessons.txt";

        // Можем запросить свойство /file/: длину
        System.out.println(file.length());  // 753 (это байт)

        // Если работаем с файлом к которому хотим обращаться относительно нашего проекта, то нужно писать
        // относительный путь.
        // Если положим файл в папку /data/ нашего проекта - в 13-й модуль , то
        File file1 = new File("13_FilesAndNetwork/#lessons/14.1_класс_File/data/Info_for_lesson.txt");
        System.out.println(file1.length());  // 753 (это байт)

        // Последняя дата и время изменения в миллисек
        System.out.println(file.lastModified()); // 1638000950000
        // Можно удалить файл
        /*System.out.println(file.delete());*/

        // Можем проверить, это файл или папка
        System.out.println(file.isDirectory()); // false
        File folder = new File("/Users/aleksandrshabalin/Desktop/");
        System.out.println(folder.isDirectory());  // true
        // Можем получить список файлов в папке (возвращает массив файлов)
        File[] files = folder.listFiles();
        for (File file4 : files) {
            System.out.println(file4.getAbsolutePath());
        }
        // получим список файлов с абс путями

        // Можно внутри папки создать еще одну папку
        File folder1 = new File("/Users/aleksandrshabalin/Desktop/folder1");
        folder1.mkdir();
        // и удалить потом
        folder1.delete();

    }
}