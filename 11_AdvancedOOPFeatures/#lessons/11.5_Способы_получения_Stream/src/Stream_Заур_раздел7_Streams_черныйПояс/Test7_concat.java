package Stream_Заур_раздел7_Streams_черныйПояс;

import java.util.stream.Stream;

public class Test7_concat {
    public static void main(String[] args) {
        // 📌 Метод /concat/  ______________________________________________________
        Stream<Integer> stream1 = Stream.of(1, 2, 3, 4, 5, 1, 2, 3);
        Stream<Integer> stream2 = Stream.of(1, 2, 3, 4, 5);
        Stream<Integer> stream3 = Stream.of(6, 7, 8 ,9 ,10);
        Stream<Integer> stream4 = Stream.concat(stream2, stream3);
        stream4.forEach(System.out::println);
        // консоль: 1 2 3 4 5 6 7 8 9 10

        // Метод /concat/ статический и возвращает стрим, но не может быть использован в цепочке (chaining).
        // Нельзя сказать что он терминал, ведь он возвращает стрим, но и после него нельзя вызвать
        // любой /Intermediate methods/, поэтому не будем указывать к какому семейству методов он относится.
    }
}
