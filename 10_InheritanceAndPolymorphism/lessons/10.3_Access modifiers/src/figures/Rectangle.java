package figures;

import java.util.Date;

public class Rectangle { // прямоугольник
    private int width;
    private int height;
    private Date creationTime;

    public Rectangle(int width, int height) {
        this.width = width;
        this.height = height;
        setCreationTime();   // зафиксируется время создания объекта
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getSquare() {   // площадь прямоугольника
        return width * height;
    }

    // все фигуры должны обладать таким методом
    private void setCreationTime() {
        creationTime = new Date();  // зафиксируется время создания объекта
    }

    public Date getCreationTime() {
        return creationTime;
    }
}
