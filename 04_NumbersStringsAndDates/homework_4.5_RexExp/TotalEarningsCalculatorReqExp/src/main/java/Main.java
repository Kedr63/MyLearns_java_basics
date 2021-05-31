public class Main {

    public static void main(String[] args) {
        String text = "Вася заработал 5000 рублей, Петя - 7563 рубля, а Маша - 30000 рублей";
        System.out.println(calculateSalarySum(text));

    }

    public static int calculateSalarySum(String text) {
        //TODO: реализуйте метод
        String[] strings = text.split(" ");
        int totalAmount = 0;
        for (int i = 0; i < strings.length; i++) {
            if (strings[i].matches("\\d+")){
                totalAmount = totalAmount + Integer.parseInt(strings[i]);
            }
        }
        return totalAmount;
    }

}