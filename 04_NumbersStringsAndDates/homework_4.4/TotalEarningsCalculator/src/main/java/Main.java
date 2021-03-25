public class Main {

    public static void main(String[] args) {

        String text = "Вася заработал 5000 рублей, Петя - 7563 рубля, а Маша - 30000 рублей";
        //TODO: напишите ваш код, результат вывести в консоль

        String search1 = "5000 ";
        String search2 = " 7563";
        String search3 = "30000";
        String vasyaMoney = text.substring(text.indexOf(search1), text.indexOf(search1) + search1.length()).trim();
        int money1 = Integer.parseInt(vasyaMoney);

        String petyaMoney = text.substring(text.indexOf(search2), text.indexOf(search2) + search2.length()).trim();
        int money2 = Integer.parseInt(petyaMoney);

        String mashaMoney = text.substring(text.indexOf(search3), text.indexOf(search3) + search3.length()).trim();
        int money3 = Integer.parseInt(mashaMoney);

        System.out.println(money1 + money2 + money3);
    }

}