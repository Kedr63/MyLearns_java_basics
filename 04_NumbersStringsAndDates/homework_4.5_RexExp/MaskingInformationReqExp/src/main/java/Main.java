public class Main {

    public static void main(String[] args) {
        String text = "Владелец <Иванов Иван>. Номер кредитной карты <4008 1234 5678> 8912. Адрес: <Какой-то> владельца.";
        String placeholder = "***";
        System.out.println(searchAndReplaceDiamonds(text, placeholder));
    }

    public static String searchAndReplaceDiamonds(String text, String placeholder) {
        // TODO: реализовать метод, если в строке нет <> - вернуть строку без изменений

        return text.replaceAll("<.+?>", placeholder);
    }

}