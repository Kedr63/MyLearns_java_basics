import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;

public class Main {
    public static void main(String[] args) {

        // BufferedReader хорош тем, что читает файл сразу построчно
        StringBuilder builder = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader("/home/kedr/Рабочий стол/ForWork_JAVA/Info_for_lesson.txt"));
            for (;;){
                String line = br.readLine();
                if (line == null){ // значит файл закончился
                    break;
                }
                builder.append(line + "\n");
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        System.out.println(builder.toString());
    }
}
