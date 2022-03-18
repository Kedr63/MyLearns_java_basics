import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Еще один из способов чтения файлов это класс Files, это уже новая Java
        // У него есть полезный метод /readAllLines/ который читает все строки в List<String>

        StringBuilder builder = new StringBuilder();
        try {
            List<String> lines = Files.readAllLines(Paths.get("/home/kedr/Рабочий стол/ForWork_JAVA/Info_for_lesson.txt"));
            lines.forEach(line -> builder.append(line + "\n"));
        } catch (Exception ex) {
            ex.printStackTrace();
        } System.out.println(builder.toString());

        // Здесь можно было сделать /Files.readAllBytes()/ - читать все байты нашего файла, для того чтобы
        // например этот символ "\n" тут не добавлять и работать с байтами
    }
}
