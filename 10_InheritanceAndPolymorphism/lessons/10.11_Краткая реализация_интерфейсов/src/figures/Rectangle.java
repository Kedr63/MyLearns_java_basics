package figures;

import java.util.Date;

public class Rectangle extends Figure2D { // прямоугольник
    private double width; // поменяем с int на double, чтоб у всех фигур было одинаково
    private double height;
    private Date creationTime;

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
        setCreationTime();   // зафиксируется время создания объекта
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    // появится слева от метода значек, что имлементирует абстр. метод из АК Figure
    public double getSquare() {
        return width * height;
    }


    private void setCreationTime() {
        creationTime = new Date();
    }

    public Date getCreationTime() {
        return creationTime;
    }

    @Override
    public double getVisibleHeight() {
        return 0;
    }

    @Override
    public double getVisibleWidth() {
        return 0;
    }
}
