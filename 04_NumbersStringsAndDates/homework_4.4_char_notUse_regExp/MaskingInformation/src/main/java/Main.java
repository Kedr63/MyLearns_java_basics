public class Main {

    public static void main(String[] args) {
        String text = "Номер кредитной карты <4008 1234 5678> 8912";
        String placeholder = "***";
        System.out.println(searchAndReplaceDiamonds(text, placeholder));
    }

    public static String searchAndReplaceDiamonds(String text, String placeholder) {
        // TODO: реализовать метод, если в строке нет <> - вернуть строку без изменений

        if (text.indexOf('<') != -1 && text.indexOf('>') != -1) {
            for (int i = 0; i < text.length(); i++) {
                char ch = text.charAt(i);
                if (ch == '<') {
                    String search = text.substring(i, text.indexOf('>', i) + 1);
                    text = text.substring(0, i) + placeholder + text.substring(i + search.length());
                }
            }
            return text;
        }
        return text;
    }
}

