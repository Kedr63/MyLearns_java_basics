import figures.Square;

public class Main {
    public static void main(String[] args) {
        // Наследование классов

        // Мы знаем, что классы состоят из методов и переменных, соответственно если мы создаем какой-то класс,
        // который наследуется от какого-то другого класса, он перенимает на себя все методы и переменные
        // родительского класса (но на самом деле не все)

        // Создадим здесь в папке SRC новый package и назовем его "figures", будем изучать наследование классов
        // на примере различных фигур геометрических, как будто мы создаем графический редактор или ПО для работы
        // с геометрическими фигурами

        Square square = new Square(40);
        System.out.println(square.getSquare());// распечатаем его площадь

        /*консоль:
        1600*/

        // проверю работу методов
        System.out.println(square.getHeight());

        square.setHeight(30);
        System.out.println(square.getHeight() + " " + square.getSquare());
        /*консоль:
        1600
        40
        30 900
        */



    }
}
