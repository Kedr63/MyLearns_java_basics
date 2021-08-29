import figures.Square;

import java.util.Comparator;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {

        /* Краткая реализация интерфесов */

        // До этого мы создавали целый класс /SquareComparator/ для сравнения наших объектов, но
        // если он больше нигде не используется, то можно его создать прямо
        // вот здесь /new TreeSet<>(new SquareComparator())/, пишем в скобках () /new Comparator<Square>()/
        // и он автоматически предложит реализовать метод и теперь среда предлагает заменить
        // /new Comparator<Square>()/ на лямбда выражение (сделаем замену): и теперь
        // здесь /(o1, o2)/ мы передаем те параметры которые приходят в метод и их используем, сразу
        // пишем тело метода

        TreeSet<Square> squares = new TreeSet<>((o1, o2) -> {
            if (o1.getWidth() > o2.getWidth()){
                return 1;
            }
            if (o1.getWidth() < o2.getWidth()){
                return -1;
            }
            return 0;
        });
        squares.add(new Square(40));
        squares.add(new Square(20));
        squares.add(new Square(40));
        squares.add(new Square(40));
        squares.add(new Square(50));
        squares.add(new Square(10));
        // И теперь эти квадраты отсортируются в соответствии с их шириной
        for (Square square : squares) {
            System.out.println(square.getWidth());
        }
        /*консоль:
        10.0
        20.0
        40.0
        50.0*/
        // И соответственно если в методе /compare/ поменяем местами /-1/ и /1/, то измениться порядок
        // сортировки ->
        /*консоль:
        50.0
        40.0
        20.0
        10.0*/
        // Т.е. упорядочить объекты можно двумя способами: либо делать их Comparable, реализуя метод /compareTo/,
        // либо создавая отдельный Comparator для них, с помощью которого мы можем обозначить как они должны
        // сравниваться друг с другом

    }

}
