import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        String line = "Каждый охотник желает знать, где сидит фазан";
       String[] array = line.split(",?\\s+");
        for (String str : ReverseArray.reverse(array)) {
            System.out.println(str);
        }
    }
}
