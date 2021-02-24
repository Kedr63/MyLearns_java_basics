public class Main {

    public static void main(String[] args) {
        Container container = new Container();
        container.count += 7843;

        int sum = sumDigits(0);

        System.out.println(sum);
    }

  /* Реализуйте метод sumDigits который возвращает сумму цифр числа, пример:
  передано 12345, метод должен вернуть 15
  если передано null, то должен вернуть -1

  Запустите тесты TestSumDigits для проверки корректности работы метода

  не меняйте название метода, его возвращаемое значение и модификаторы доступа (public).
  В противном случае тестовый метод не сможет проверить ваш код
   */

    public static int sumDigits(Integer number) {
        if (number == null) {
            return -1;
        } else {
            String string = number.toString();           //переводим число в строку
            int sum = 0;
            for (int i = 0; i < string.length(); i++) {

                /*  char ch = string.charAt(i);             //циклом каждое строчное значение переведем в символьное значение
                String string2 = String.valueOf(ch);    //теперь символьное значение переведем в строчное значение, для того
                int n = Integer.parseInt(string2);      //чтобы это строчное значение перевести в число и провести арифметику
                sum = sum + n;  */
                // заменим эти строки одной ниже (и закомментируем их)

                sum = sum + Integer.parseInt(String.valueOf(string.charAt(i)));
            }
            return sum;
        }

    }
}
