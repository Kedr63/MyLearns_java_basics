package work_with_file;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;

public class _5_FileEx {
    public static void main(String[] args) throws IOException {
        // Класс File ❤️(старый класс до Java 7)

        /**  Класс File позволяет управлять информацией о файлах и директориях. Класс File не работает на
         напрямую с потоками, его задачей является управление информацией о файлах и каталогах (еще их называют
         папками или директориями).
         На уровне операционной системы файлы и директории отличаются, но в Java они описываются одним классом
         File.
         **/

        // Самый частый конструктор ⬇

        File file = new File("file2.txt"); // относительный путь (относительно проекта, лежит в
        // корневой папке проекта)
        File folder = new File("/home/kedr/Рабочий стол/ForWork_JAVA/A");

        // Если создадим файл и папку, которые не существуют (пути неверные), исключение не будет выброшено
        File file1 = new File("/home/kedr/Рабочий стол/ForWork_JAVA/A/text20.txt");
        File folder1 = new File("/home/kedr/Рабочий стол/ForWork_JAVA/B");

        // создадим файл и папку, которые не существуют в компе (пути неверные), исключение не будет выброшено,
        // и с помощью методов ниже создадим файл и папку по этим путям
        File file2 = new File("/home/kedr/Рабочий стол/ForWork_JAVA/A/text40.txt");
        File folder2 = new File("/home/kedr/Рабочий стол/ForWork_JAVA/C");

        File folder3 = new File("/home/kedr/Рабочий стол/ForWork_JAVA/");



        System.out.println("file.getAbsolutePath() " + file.getAbsolutePath());
        System.out.println("folder.getAbsolutePath() " + folder.getAbsolutePath());
        System.out.println("----------------------------------------------");

        System.out.println("file.isAbsolute() " + file.isAbsolute());
        System.out.println("folder.isAbsolute() " + folder.isAbsolute());
        System.out.println("----------------------------------------------");

        System.out.println("file.isDirectory() " + file.isDirectory());
        System.out.println("folder.isDirectory() " + folder.isDirectory());
        System.out.println("----------------------------------------------");

        System.out.println("file.exists() " + file.exists());
        System.out.println("folder.exists() " + folder.exists());
        System.out.println("file1.exists() " + file1.exists()); // существует ли file1, которого нет? // false
        System.out.println("folder1.exists() " + folder1.exists()); // существует ли folder1, которого нет? // false
        System.out.println("----------------------------------------------");

        System.out.println("file2.createNewFile() " + file2.createNewFile()); // create new file (по указанному пути)
        System.out.println("folder2.mkdir() " + folder2.mkdir()); // create new directory (по указанному пути)
        System.out.println("----------------------------------------------");

        System.out.println("file2.isDirectory() " + file2.createNewFile()); // если попытаемся создать файл который
        // уже существует - то будет -> false
        System.out.println("----------------------------------------------");

        // узнать размер файла
        System.out.println("file1.length() " + file.length());
        System.out.println("----------------------------------------------");

        // попытаемся узнать размер папки
        System.out.println("folder.length() " + folder.length()); // размер не получим, класс File
        // не предоставляет такой функциональности, мы должны заходить в папку -> читать размеры файлов этой папки
        // и складывать эти размеры
        System.out.println("----------------------------------------------");

        System.out.println("file1.delete() " + file1.delete());
        System.out.println("folder1.delete() " + folder1.delete()); // сможем удалить папку, если только она пуста
        // сначала должны удалить все под папки и файлы, а потом только папку которую удаляем
        System.out.println("----------------------------------------------");
        //❤️ метод /delete/ не удалит папку если она не пуста ❗️

        // можем проверить содержимое папки
        File[] files = folder3.listFiles();
        System.out.println(Arrays.toString(files));
        System.out.println("----------------------------------------------");

        System.out.println("file1.isHidden() " + file1.isHidden());
        System.out.println("file1.canRead() " + file1.canRead());
        System.out.println("file1.canWrite() " + file1.canWrite());
        System.out.println("file1.canExecute() " + file1.canExecute());
        System.out.println("----------------------------------------------");



    }
}
