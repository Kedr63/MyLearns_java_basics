package figures;

public class Square extends Rectangle {

    // Так же и здесь при создании квадрата двумя видами конструкторов будут сохранены дата и время создания
    public Square(int width, int height) {
        super(width, height);
        if (width!=height){
            System.out.println("The width doesn't equals height");
        }
    }

    public Square(int width){
        super(width, width);
    }


    public void setWidth(int width){
        setWidth(width);
        setHeight(width);
    }
    public void setHeight(int height){
        setHeight(height);
        setWidth(height);
    }
}
