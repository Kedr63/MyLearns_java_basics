import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex_Skillbox {

  /**
   * Замена одних фрагментов строк на другие
   */
  /*  замена в строках одних фрагментов на другие. Например, у вас есть номера телефонов в разных форматах:  */
  static String phone1 = "+7 903 712-37-54";
  static String phone2 = "7 (903) 968-60-45";
  static String phone3 = "7999-666-66-66";
  static String phone4 = "7(903)9616245";

  static String regex = "[^0-9]";

  /* Вам нужно написать метод, который будет приводить номера телефонов к единому формату: 79037123754 */
  public static String formatPhoneNumber(String phone) {
    return phone.replaceAll(regex, "");

  }

  public static void main(String[] args) {

    System.out.println(formatPhoneNumber(phone1));
    System.out.println(formatPhoneNumber(phone2));
    System.out.println(formatPhoneNumber(phone3));
    System.out.println(formatPhoneNumber(phone4));
  }


  public static class Test1 {

    /**
     * Проверка соответствия строк шаблону Представьте, что вы разрабатываете веб-приложение, в
     * котором есть форма с полем ввода номера автомобиля, и значение этого поля попадает в
     * переменную number:
     */
    /* В номерах могут быть только те буквы кириллицы, которые имеют латинские аналоги. Напишем эти буквы:
       String regex = "АВЕКМНОРСТУХ"; */
    public static void main(String[] args) {
      String number = "А674МР197";
      String lettersRange = "[АВЕКМНОРСТУХ]";
      String regex = lettersRange + "[0-9]{2}[1-9]" + lettersRange + "{2}[0-9]{2,3}";
      System.out.println(number.matches(regex));
    }
  }


  public static class Test2 {

    /**
     * Поиск фрагментов строк, соответствующих шаблону
     */
    /*
     * поиск фрагментов в строке, соответствующих определённому фрагменту. Обычно эта задача
     *  возникает, когда нужно найти в тексте тот или иной фрагмент. Например, извлечь из текста
     *  ссылки на веб-страницы.*/

    static String text =
        "Алексей, добрый день!\nМой гитхаб — https://github.com/, а также ссылка на"
            + " мой персональный сайт — https://www.skillbox.ru/\nЕсли возникнут вопросы, пишите мне н"
            + "апрямую. Я всегда доступен";

    static String regex = "https:\\/\\/[^,\\s]+";

    static Pattern pattern = Pattern.compile(regex);

    public static void main(String[] args) {
      Matcher matcher = pattern.matcher(text);
      while (matcher.find()) {
        int start = matcher.start();
        int end = matcher.end();
        System.out.println(text.substring(start, end));
      }

      //  Фрагменты можно получать и более простым способом, используя метод group класса Matcher
      Matcher matcher1 = pattern.matcher(text);
      while (matcher1.find()) {
        System.out.println(matcher1.group());
      }
    }
  }


  public static class Test3 {

    /** Маски в регулярных выражениях */
    /* При использовании регулярных выражений иногда нужно извлечь фрагменты из части строки, которая
    соответствует заданному в регулярном выражении шаблону.
       Представьте, что вам необходимо извлечь из текста ниже все цитаты — фрагменты в кавычках:  */
    /** Дмитрий сообщил следующее: «Я вернусь в 12:40 и, будьте добры, подготовьте к этому времени все
     * документы!» На что Анна ему ответила: «А документы-то так и не привезли». Дмитрий удивлённо посмотрел
     * на неё и сказал: «Ну и ладно», — вздохнул, махнул рукой и удалился. */

    static String text = "Дмитрий сообщил следующее: «Я вернусь в 12:40 и, будьте добры, подготовьте к этому времени все\n"
        + "     * документы!» На что Анна ему ответила: «А документы-то так и не привезли». Дмитрий удивлённо посмотрел\n"
        + "     * на неё и сказал: «Ну и ладно», — вздохнул, махнул рукой и удалился.";

    /* Для того чтобы извлечь из текста все цитаты, можно написать такое регулярное выражение: */
    // String regex = "«[^»]+»";
    /* Оно будет соответствовать всем цитатам в данном тексте: в начале стоит открывающая кавычка, потом множество
       символов, кроме закрывающей кавычки, а потом — закрывающая кавычка. Как и в примере выше, можем воспользоваться
       классами Pattern и Matcher, но что делать, если хочется сразу получить цитаты без кавычек? Для этого можно
       использовать так называемые маски — фрагменты выражений, выделяемые круглыми скобками: */

   static String regex = "«([^»]+)»";

    /* Обратите внимание, что в круглые скобки помещено содержимое каждой цитаты, а кавычки находятся за скобками.
    Для работы с масками у класса Matcher есть метод group, в качестве параметра в который можно передавать порядковый
    номер маски, начиная с единицы. То есть:     matcher.group(1); вернёт содержимое первой маски, matcher.group(2); — второй,
    а  matcher.group(0); будет возвращать целиком весь соответствующий регулярному выражению текст. Теперь можем написать код: */

    public static void main(String[] args) {
      Pattern pattern = Pattern.compile(regex);
      Matcher matcher = pattern.matcher(text);
      while (matcher.find()) {
        String citation = matcher.group(1);
        System.out.println(citation);
      }
    }

  }

}
