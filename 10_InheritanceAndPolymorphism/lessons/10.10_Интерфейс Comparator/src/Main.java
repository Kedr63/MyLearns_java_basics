import figures.Square;

import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {

        /* Интерфейс Comparator */

        // Можем не реализовывать интерфейс Comparable в своих объектах и классах, но чтобы не возникала
        // ошибка нужно передать на вход /TreeSet<>()/ какой-то класс который реализует интерфейс /Comparator/,
        // создадим класс /SquareComparator/ и /implements Comparator<Square>/ и в <> укажем что интерфейс
        // относится к объектам класса /Square/, и имплементируем единственный здесь
        // метод /compare(Square o1, Square o2)/, принимает два объекта, которые мы сравниваем. В нем
        // пропишем логику их сравнения
        TreeSet<Square> squares = new TreeSet<>(new SquareComparator());
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
