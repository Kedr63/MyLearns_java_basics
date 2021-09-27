import figures.Circle;
import figures.Figure;
import figures.Square;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {
        /* Интерфейс Comparable */

        // Один из часто встречающихся интерфейсов в Java - это интерфейс Comparable. Этот интерфейс говорит о том,
        // что объекты которые его имплементируют - они СРАВНИМЫ. (Comparable - сравнимый)
        // Например, строки сравнимы
        String first = "a";
        String second = "b";
        System.out.println(first.compareTo(second)); // этот метод /compareTo()/ как раз метод интерфейса
        // Comparable, т.е. он вызывается у объекта и ему на вход подается объект такого же типа,
        // этот метод возвращает число.
        // Число < 0, если первый объект < второго, равно = 0, если они равны, и число > 0,
        // если первый объект > больше чем второй.
        // Соответственно, в строках этот метод реализован таким образом что сравнение происходит в
        // соответствии с алфавитом

        // Допустим, нам нужно сравнить наши квадраты, нужно сравнить их к примеру по ширине, или к примеру
        // по площади.
        // Соответственно, мы можем сделать так: прописать в /class Square/ интерфейс /implements Comparable/,
        // и он попросит
        // реализовать метод /compareTo()/, и мы должны в этом методе прописать каким образом этот наш
        // объект /Square/ сравнивается с другим объектом /Square/, и нужно в <> прописать
        // /implements Comparable<Square>/, поскольку он должен сравниваться не с объектом /Object o/, а с
        // объектом /Square square/, и в методе прописываем как они будут сравниваться (в данном случае
        // написали сравнение по ширине), и мы теперь можем, допустим создать коллекцию, где будут лежать
        // эти квадраты и добавить разных квадратов разной ширины->
        TreeSet<Square> squares = new TreeSet<>();
        squares.add(new Square(40));
        squares.add(new Square(20));
        squares.add(new Square(40));
        squares.add(new Square(40));
        squares.add(new Square(50));
        squares.add(new Square(10));
        // Распечатаем эту коллекцию по порядку, /TreeSet/ обеспечит упорядоченное множество
        for (Square square: squares){
            System.out.println(square.getWidth());
        }
        /*консоль:
        10.0
        20.0
        40.0
        50.0*/ // Они упорядочаться так, как мы прописали в методе /compareTo(Square square)/, если в
        // методе /compareTo(Square square)/ мы пропишем наоборот (-1 и 1), то
        /*консоль:
        50.0
        40.0
        20.0
        10.0*/
        System.out.println();
        // Т.о. мы можем управлять как сравниваются объекты и тем самым управлять как они будут сортироваться
        // в различных коллекциях
        ArrayList<Square> squares1 = new ArrayList<>();
        squares1.add(new Square(40));
        squares1.add(new Square(20));
        squares1.add(new Square(40));
        squares1.add(new Square(40));
        squares1.add(new Square(50));
        squares1.add(new Square(10));
        Collections.sort(squares1);
        for (Square square: squares1){
            System.out.println(square.getWidth());
        }
        /*консоль:
        10.0
        20.0
        40.0
        40.0
        40.0
        50.0*/ // Получим тоже самое
        // Вот так работает интерфейс Comparable, т.е. он нас обязывает реализовать метод /compareTo()/,
        // кстати говоря, если у нас класс не реализует этот интерфейс, просто есть метод /compareTo()/,
        // но интерфейса там нету, то будет возникать ошибка (ошибка: переданная коллекция не является коллекцией
        // объектов реализующих интерфейс Comparable) и отсортировать такую коллекцию мы не сможем,
        // компилятор не будет знать как ее отсортировать (даже при /TreeSet<Square> squares/ будет ошибка, что
        // элементы в этой коллекции не сравнимы, он не знает как их сравнивать)
        // Поэтому важно реализовывать этот интерфейс, тогда все внутренние проверки в Java будут проходить
        // корректно  и мы будем уметь сравнивать эти объекты, т.е. мало задать здесь метод /compareTo()/,
        // нужно еще реализовать интерфейс, чтобы классы которые используют ваш класс знали что он реализует
        // интерфейс Comparable и все эти проверки не выдавали ошибки.


    }

}
