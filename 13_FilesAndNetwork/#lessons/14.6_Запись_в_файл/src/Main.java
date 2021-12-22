import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // ❤️ Способы записи в файл ❤️

        // 📌 1-й способ
        /**  FileOutputStream os = new FileOutputStream("13_FilesAndNetwork/#lessons" +
         "/14.6_Запись_в_файл/data/file12.txt");
         os.write();      */
        // в него ⬆ можно писать либо байты, либо коды конкретных символов - что не очень удобно и мы этого делать
        // не будем. Мы будем использовать более продвинутый класс - это класс /PrintWriter/

        // 📌 2-й способ записи в файл
        try {
            PrintWriter writer = new PrintWriter("13_FilesAndNetwork/#lessons/14.6_Запись_в_файл/data/file12.txt");

            // Он удобен тем что мы можем сразу записывать строку
            // К примеру возьмем генератор чисел и запишем туда числа
            for (int i = 0; i < 1000; i++) {
                writer.write(i + "\n");
            }
            writer.flush();
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // Если запустим этот код (сначала без методов /flush/ /close/, то ничего не запишется,
        // потому что после того когда мы что-то записали в файл, нужно вызвать еще метод /flush/ и метод /close/
        // 📍метод /close/ - закрывает файл
        // 📍метод /flush/ - сбрасывает буфер, в разных райтерах есть так называемые буфферы, которые запоминают
        // какое-то количество символов, потом вы их туда сбрасываете и закрываете этот райтер

        // и теперь запустив с этими ⬆ методами и все запишется

        // ❤️ в папке /data/ появится файл /file12.txt/ и в него запишутся сгенерированные числа из цикла ❤️


        // 📌 3-й способ записи в файл

        try {
            ArrayList<String> strings = new ArrayList<>();
            for (int i = 0; i < 1000; i++){
                 strings.add(Integer.toString(i));
            }
            Files.write(Paths.get("13_FilesAndNetwork/#lessons/14.6_Запись_в_файл/data/file13.txt"), strings);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // ❤️ в папке /data/ появится файл /file13.txt/ и в него запишутся строки из ArrayList❤️


    }
}
