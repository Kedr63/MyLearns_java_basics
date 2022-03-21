package work_with_file;

import java.io.*;

public class _3_BufferedReader_BufferWriter_CopyEx {
    // BufferedReader_BufferWriter ❤🔥️

    /**
     * Буферизация это процесс загрузки части файла, происходящая во время потоковой передачи, например музыки
     * или видео до их воспроизведения. Эта форма буферизации позволяет смотреть или слушать медиа файлы почти
     * мгновенно, загружая только часть файла, затем воспроизводя его, пока оставшаяся часть продолжает загружаться.
     * <p>
     * Использование буферизации в стримах позволяет достичь большей эффективности при чтении файла или записи
     * в него.
     * <p>
     * 📍 BufferWriter writer = new BufferWriter (new FileWriter("file1.txt"));
     * 📍 BufferReader reader = new BufferReader (new FileReader("file1.txt"));
     * <p>
     * Т/е /BufferedReader и BufferWriter/ - это обвертки (wraps). ❗️Они оборачивают FileWriter и FileReader
     * и добавляют функциональность буферизации. ❗️
     */

    public static void main(String[] args) {

        try (BufferedReader reader = new BufferedReader(new FileReader("file2.txt")); // read file
                BufferedWriter writer = new BufferedWriter(new FileWriter("file3.txt"))) // write file,
        // этот файл /file3.txt/ создастся при запуске
        {
            int character;
            while ((character = reader.read()) != -1) {
                writer.write(character);   // сразу будем записывать в другой файл
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // получается здесь ⬆ мы скопировали информацию с /file2.txt/ в /file3.txt/, а как еще могли это сделать
        // посмотрим на следующем примере ⬇ 🖍

        try (BufferedReader reader = new BufferedReader(new FileReader("file2.txt"));
                BufferedWriter writer = new BufferedWriter(new FileWriter("file4.txt"))) // этот файл
        // создастся при запуске
        {
            String line;
            while ((line = reader.readLine())!=null){
                writer.write(line);
                writer.write('\n');
            }
            System.out.println("Done!");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    // Про конструкцию "try-with-resources" ❤️
    /** Очень часто программисты забывают закрывать ресурсы которые они используют
      (особенно когда используется несколько ресурсов). И чтобы не забывать это делать и чтобы вообще
     не закрывать ресурсы был придуман механизм - "try-with-resources".
     В скобках после try() пишутся ресурсы, которые после срабатывания кода, автоматически закроются
     */
    /*  try (  BufferedReader reader = new BufferedReader(new FileReader("file2.txt"));
        BufferedWriter writer = new BufferedWriter(new FileWriter("file3.txt"))  )
        {
               //some code
        }     */

    // Ресурс, который используется в "try-with-resources" должен имплементировать интерфейс /AutoCloseable/

}
