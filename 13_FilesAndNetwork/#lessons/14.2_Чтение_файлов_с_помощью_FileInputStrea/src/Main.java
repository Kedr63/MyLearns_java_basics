import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        // В java много способов чтения файлов
        // Начнем с самого фундаментального, который был в java всегда, это чтение с помощью стримов
        // FileInputStream - входной поток данных

        StringBuilder builder = new StringBuilder();
        try {
            FileInputStream is = new FileInputStream("/Users/aleksandrshabalin/Desktop/Info_for_lesson.txt");
            // есть метод /read/, он читает очередной символ в файле и возвращает код этого символа /int/
            for (;;){
                int code = is.read();
                if (code < 0){ // если code == -1 (означает что файл закончился), то выходим из цикла,
                    break;      // т/к он бесконечный
                }
                char ch = (char) code;
                // можно добавить StringBuilder
                builder.append(ch);

            }
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        System.out.println(builder.toString());
    }
}
