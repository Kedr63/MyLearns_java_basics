package figures;

public class Circle extends Figure2D {
    private double radius;
    public Circle(double radius){
        this.radius = radius;
    }

    @Override
    public double getSquare() {
        return Math.PI * Math.pow(radius, 2); // здесь надо написать правильную реализацию
    } // этот метод теперь у нас определяет (имплементирует) АМ getSquare();

    @Override
    public double getVisibleHeight() {
        return 0;
    }

    @Override
    public double getVisibleWidth() {
        return 0;
    }
}
