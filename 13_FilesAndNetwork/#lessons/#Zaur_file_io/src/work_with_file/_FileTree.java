package work_with_file;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class _FileTree {
    //// ❤️ Files.walkFileTree ❤️

    // Для прогулки по дереву файлов. Например, когда мы хотим удалить папку, которая содержит файлы или
    // другие папки, мы должны прогуляться по дереву - сначала удалить все внутренние файлы и папки, и
    // только после этого мы сможем удалить саму папку тоже. Или когда хотим копировать папку с каким-то содержимым
    // мы должны также прогуляться по дереву файлов и скопировать все ее содержимое вместе с ней.

    //📌 Метод /Files.walkFileTree(Path start, FileVisitor vizitor)/ используется для обхода дерева файлов.
    // Параметры:
    //📍 Path start - означает путь откуда начнется наша прогулка, обход по дереву файлов
    //📍 FileVisitor vizitor - это интерфейс, имплементируя который мы будем описывать всю логику обхода
    // дерева файлов. Т/е что нам нужно делать когда мы работаем с тем, или иным файлом, или директорией?
    // Логика обхода дерева файлов заключается в классе, имплементирующем интерфейс /FileVisitor/.
    // ❤️ Интерфейс /FileVisitor/ содержит 4 метода:
    /**
     * 🖍 preVisitDirectory - срабатывает перед обращением к элементам папки;
     * Т/е, когда мы только заходим в какую-то директорию, мы можем описать в этом методе
     * что мы будем делать в этом случае при входе в директорию.
     * 🖍 visitFile - срабатывает при обращении к файлу;
     * Например, мы можем сказать, что нужно копировать этот файл.
     * 🖍 postVisitDirectory - срабатывает после обращения ко всем элементам папки;
     * Т/е поработали со всеми элементами этой папки - что нам дальше делать? Это мы можем описать в этом методе.
     * 🖍 visitFileFailed - срабатывает когда файл по каким-либо причинам недоступен.
     * Допустим у нас просто нет прав для работы с этим файлом.
     */
    // Т/е, чтобы работать с /walkFileTree/ методом, мы должны имплементировать этот интерфейс FileVisitor.
    // Но что если мы не хотим переопределять все 4 метода? Допустим нам достаточно переопределить
    // метод visitFile, а три других нам не нужны. Что тогда делать? Разработчики Java позаботились об этом ->
    // есть класс /SimpleFileVisitor/, который имплементирует /FileVisitor/, так как в этом классе есть базовая
    // имплементация всех 4-х методов, то мы можем создать класс который будет extend-ить /SimpleFileVisitor/ и
    // переопределить только тот метод, который нам нужен, например visitFile

    public static void main(String[] args) throws IOException {
       // Теперь практика:
        // В первом примере просто выведем информацию на экран о всех файлах и директориях внутри папки и в этом
        // примере будем самостоятельно имплементировать /FileVisitor/ интерфейс, таким образом переопределим
        // все 4 метода.
        // (работать будем с папкой - /Users/aleksandrshabalin/Desktop/ForWorkFiles/X
        // Давайте выведем информацию о всех элементах папки "Х", и папки "Х" тоже, на экран
        // Сначала создадим класс /MyFileVisitor/ ⬇
        // После этого класса /MyFileVisitor/ создадим
        Path path = Paths.get("/Users/aleksandrshabalin/Desktop/ForWorkFiles/X");
        Files.walkFileTree(path, new MyFileVisitor());


    }

    static class MyFileVisitor implements FileVisitor<Path> {  // в видео класс не static

        @Override
        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
            System.out.println("Enter to Directory: " + dir);
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            // когда исследуется какой-то файл, мы будем выводить ⬇
            System.out.println("File name: " + file.getFileName());
            return FileVisitResult.CONTINUE; // и продолжим работу дальше
        }

        @Override
        public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
            // если будет какая-то ошибка при изучении файла, то выведем сообщение
            System.out.println("Error while visiting file: " + file.getFileName());
            // и если допустим нет доступа на файл, то давайте заканчивать гуляние по дереву и вернем,
            return FileVisitResult.TERMINATE;
        }

        @Override
        public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
            System.out.println("Exit from directory: " + dir);
            return FileVisitResult.CONTINUE;
        }
        // Все эти четыре метода возвращают FileVisitResult. Это ENUM, и он содержит четыре значения. Что это
        // означает? Когда мы зашли в первую директорию, перед тем как мы поработали со всеми ее файлами -
        // вызывается метод /preVisitDirectory/, после вызова этого метода и срабатывания кода в теле этого метода,
        // мы должны сказать нашей программе: что мы делаем дальше. И у нас есть четыре варианта, что мы будем
        // делать дальше
       /**❤️ enum FileVisitResult
        * Значения FileVisitResult:
        * 📍 CONTINUE - означает, что нужно продолжать обход по файлам;
        * 📍 TERMINATE - означает, что нужно немедленно прекратить обход по файлам;
        *    Например, мы ищем какой-то файл -> нашли искомый файл -> и дальше гулять по файловому дереву не будем,
        *    мы уже нашли наш файл, мы можем вызвать TERMINATE, и все обход по файлам можем прекратить
        * 📍 SKIP_SUBTREE - означает, что в данную директорию заходить не надо;
        * 📍 SKIP_SIBLINGS (SIBLING - родной брат или сестра) - означает, что в данной директории продолжать
        *    обход по файлам не нужно;
        *    Например, (наша папка "Х" - в ней папки Y1, Y2, Z) -> SIBLING для папки Y1 - это Y2 и Z.
        *    Еще пример, (наша папка "Y1" - в ней папка О и файлы "test1.txt", "test2.txt", "test3.txt")
        *    -> SIBLING для файла "test2.txt" - это папка О, "test1.txt" и "test3.txt".
        *    Когда мы можем использовать значение 📍SKIP_SIBLINGS ? Это допустим мы зашли внутрь директории
        *    Y1 -> изучили директорию "О" -> прогулялись по ней, и файл "test1.txt" -> после его изучения
        *    возвращаем SKIP_SIBLINGS, это означает что больше в директории "Y1" ничего исследовать не надо,
        *    т/е "test2.txt" и "test3.txt" можно пропускать и двигаться дальше, и начинаем уже работать с
        *    директорией "Y2".   */
    }
}
