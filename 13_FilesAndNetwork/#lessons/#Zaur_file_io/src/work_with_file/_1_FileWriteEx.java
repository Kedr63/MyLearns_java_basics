package work_with_file;

import java.io.FileWriter;
import java.io.IOException;

public class _1_FileWriteEx {
    public static void main(String[] args) throws IOException {

        // ♦️ Введение в стримы. FileWriter.
        // 📍 Character Streams and Byte Streams

        /** Стрим (поток) для работы с файлами - это упорядоченная последовательность данных.
         Когда мы захотим записать какую-то информацию в файл или прочитать ее из файла - нам
         в этом будут помогать стримы.
         В java разделяются стримы, которые работают с байтами и с какими-то символами (т/е с буквами, цифрами и тд)

         Файлы разделяются на:
         📍 читабельные человеком - text files;
         📍 нечитабельные человеком - binary files; (например картинку в бинарном режиме)

         При работе с текстовыми и бинарными файлами необходимо использовать разные типы стримов, например
         если попытаемся с помощью стрима который подходит для текстовых файлов прочитать бинарный файл, то ничего
         не получится.

         ♦️ Рассмотрим стримы которые подходят для текстовых файлов: 📍 FileReader & FileWriter 📍
         Пример ниже ⬇
         */
        String rubai = "Много лет размышлял я над жизнью земной.\n" + "Непонятного нет для меня под луной.\n"
                + "Мне известно, что мне ничего не известно, —\n" + "Вот последний секрет из постигнутых мной.\n";

        // Запишем этот /рубаи/ в файл, который сначала создадим на Desktop-e /file.txt/
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter("/Users/aleksandrshabalin/Desktop/file.txt"); //абс. путь
            for (int i = 0; i < rubai.length(); i++) {
                fileWriter.write(rubai.charAt(i)); // посимвольно передадим в файл с помощью /fileWriter-а/
            }
            System.out.println("Done!");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            fileWriter.close(); // Этот стрим ⬆ нужно закрывать с помощью метода close, и обычно
            // закрывают в finally блоке. Если не закрыть, то ничего не запишется в файл
        }


        // Второй вариант: можем не создавать файл, а указать в конструкторе файл /file2.txt/ без
        // абсолютного пути, и тогда файл создастся в корне проекта
        FileWriter fileWriter1 = null;
        try {
            fileWriter1 = new FileWriter("file2.txt"); // относительный путь (отн-но корня нашего проекта)
            for (int i = 0; i < rubai.length(); i++) {
                fileWriter1.write(rubai.charAt(i));
            }
            // fileWriter1.write(rubai); можно и так вместо цикла (но все равно будет идти посимвольная работа)
            System.out.println("Done!");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            fileWriter1.close();
        }
        /** Но все это не так эффективно на самом деле, позже рассмотрим более эффективные варианты */

        // Еще один момент
        String s = "privet";

        /*  FileWriter fileWriter2 = null;
        try {
            fileWriter2 = new FileWriter("file2.txt"); // относительный путь (отн-но корня нашего проекта)
            fileWriter2.write(s);
            System.out.println("Done!");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            fileWriter2.close();
        }    */
        // file2.txt перепишется и будет только слово "privet"
        // А если необходимо добавить в файл, не изменяя содержимое файла, то в конструкторе нужно добавить /true/

        FileWriter fileWriter2 = null;
        try {
            fileWriter2 = new FileWriter("file2.txt", true);
            fileWriter2.write(s);
            System.out.println("Done!");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            fileWriter2.close();
        }
        /** file2.txt
         * Много лет размышлял я над жизнью земной.
         * Непонятного нет для меня под луной.
         * Мне известно, что мне ничего не известно, —
         * Вот последний секрет из постигнутых мной.
         * privet
         */

        /** Стримы на самом деле можно не закрывать, но тогда нужно использовать другую технику.
         *
         Конструктор FileWriter-а может принимать не только String, но объект класса File

         */


    }
}
