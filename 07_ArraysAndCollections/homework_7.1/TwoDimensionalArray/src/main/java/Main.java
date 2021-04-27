import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        //Распечатайте сгенерированный в классе TwoDimensionalArray.java двумерный массив
        String[] str = Arrays.deepToString(TwoDimensionalArray.getTwoDimensionalArray(9)).split("],\\s");
        for (String s : str) {
            System.out.println(s.replaceAll("[^X\\s]", ""));
        }
    }
}
