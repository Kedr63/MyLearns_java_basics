package Stream_Заур_раздел7_Streams_черныйПояс;

import java.util.stream.Stream;

public class Test10_peek {
    public static void main(String[] args) {

        // 📌 Метод /peek/ (i)    (взглянуть, посмотреть)_______________________________________________

        // Данный метод как и метод forEach принимает в параметр consumer-а, но в отличии от forEach-а
        // данный метод возвращает не void, а стрим, поэтому он является /Intermediate methods/-ом.
        // Обычно этот метод бывает нужен чтобы посмотреть поэтапно как проходит наш метод /chaining/

        Stream<Integer> stream5 = Stream.of(1, 2, 3, 4, 5, 1, 2, 3);
        System.out.println(stream5.distinct().peek(System.out::println).count());
        /*   консоль:
        1
        2
        3
        4
        5
        5    */
        // Здесь после /distinct/-а оставили только уникальные элементы потока стрим5 и с помощью /peek/
        // смогли просмотреть какие элементы у нас остались (на экране: 1 2 3 4 5 ) и потом метод /count/
        // вернул количество оставшихся элементов - /5/
        // Вот так метод /peek/ очень похожий на forEach, но выступающий в роли /Intermediate methods/-а,
        // помогает нам посмотреть что происходит на разных этапах метод /chaining/-а.
    }
}
