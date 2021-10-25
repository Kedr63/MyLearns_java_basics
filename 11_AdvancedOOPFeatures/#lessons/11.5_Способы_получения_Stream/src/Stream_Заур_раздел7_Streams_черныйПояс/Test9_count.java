package Stream_Заур_раздел7_Streams_черныйПояс;

import java.util.stream.Stream;

public class Test9_count {
    public static void main(String[] args) {
        // 📌 Метод /count/ (t)  ______________________________________________________

        // Он считает количество элементов в стриме и возвращает не int, а long

        Stream<Integer> stream5 = Stream.of(1, 2, 3, 4, 5, 1, 2, 3);
        /*   System.out.println(stream5.count());     */
        /* консоль:  8*/

        // А теперь выведем сколько уникальных элементов содержит стрим5
        System.out.println(stream5.distinct()
                .count());

        // Должны увидеть /5/, но получили /Exception in thread "main" java.lang.IllegalStateException:
        // stream has already been operated upon or closed/  - Это означает что нам нужно запомнить
        // очень важное правило ❗: стрим после обработки нельзя переиспользовать, иначе будет
        // выброшен Exception, мы не можем переиспользовать стрим5 (последнюю строчку),
        // поэтому закоментируем предпоследнюю строку.

        // Теперь консоль: 5

        /*   консоль: 5   */
    }
}
