package figures;

public class Square extends Rectangle {


    public Square(double width, double height) {
        super(width, height);
        if (width!=height){
            System.out.println("The width doesn't equals height");
        }
    }

    public Square(double width){
        super(width, width);
    }


    public void setWidth(double width){
        setWidth(width);
        setHeight(width);
    }
    public void setHeight(double height){
        setHeight(height);
        setWidth(height);
    }
}
