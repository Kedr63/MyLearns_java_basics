package figures;

// так как у Square (квадрата) площадь считается так же как у прямоугольника Rectangle, то
// отнаследуем его от класса Rectangle, для того чтоб он принял в себя все методы и переменные

public class Square extends Rectangle {

    //после extends, идея заругалась и предложила создать конструктор,
    // соответствующий конструктору родительского класса
    public Square(int width, int height) {
        super(width, height); // super - можно вызвать конструктор родительского класса в классе потомке
        // можно добавить для проверки
        if (width!=height){
            System.out.println("The width doesn't equals height");
        }
    }

    // так как у нас квадрат, то достаточно передать ширину
    public Square(int width){
        super(width, width);
    }
// переопределим сеттеры
    public void setWidth(int width){
         this.width=width;
         height=width;
    }
    public void setHeight(int height){
        this.height=height;
        width=height;
    }
}
