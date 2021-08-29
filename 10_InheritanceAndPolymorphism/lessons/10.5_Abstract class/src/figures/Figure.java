package figures;

import java.awt.*;

public abstract class Figure {
    private Color color; // можно испол-ть класс Color java-ский

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public abstract double getSquare();
}
