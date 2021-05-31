public class Main {
    public static void main(String[] args) {
        String abc = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz " +
                "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯабвгдеёжзийклмнопрстуфхцчшщъыьэюя";
        for (int i = 0; i < abc.length(); i++) {
            char ch = abc.charAt(i);
            System.out.println(ch + " - в кодировке: " + (int) ch);
        }
    }
}
