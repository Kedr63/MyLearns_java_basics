package Stream_Заур_раздел7_Streams_черныйПояс;

import java.util.stream.Stream;

public class Test8_distinct {
    public static void main(String[] args) {
        // 📌 Метод /distinct/ (i)    ______________________________________________________

        // Метод /distinct/ возвращает стрим уникальных элементов, а проверяет их он с помощью метода /equals/

        Stream<Integer> stream5 = Stream.of(1, 2, 3, 4, 5, 1, 2, 3);
        stream5.distinct().forEach(System.out::println);
        // консоль: 1 2 3 4 5  (вывелись только уникальные элементы)

    }
}
