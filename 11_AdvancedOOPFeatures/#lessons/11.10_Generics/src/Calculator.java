import java.util.List;

public class Calculator {
    // создадим метод который будет суммировать любые списки любых чисел
    public static Double sum(List<? extends Number> numbers) {
        return numbers.stream()
                .map(n -> ((Number) n).doubleValue())
                .reduce((n1, n2) -> n1 + n2)
                .get();

    }
    // мы фактически здесь параметризовали этот список любым классом который
    // наследуется от интерфейса /Number/
    // Т/е этот список получает у нас все что угодно, что отнаследовано от /Number/-а,
    // преобразует в /Double/  -> суммирует -> и возвращает.

    // помимо /extends/ можно писать /super/ -> /List<? super Number> numbers/ - это любой класс
    // от которого наследуется /Number/

}
