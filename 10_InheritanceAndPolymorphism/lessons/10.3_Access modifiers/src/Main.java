import figures.Square;

public class Main {
    public static void main(String[] args) {
        // Модификаторы доступа
         // Public // Protected // Private // или ничего не пишется

        Square square = new Square(40);

        // Допустим кто-то где-то в коде напишет
        /* square.width=55;
           square.height=11;   */
        // и мы запускаем код и не понимаем что происходит, и видим что у нас здесь не квадрат, а неизвестно что
        // Соответственно мы должны каким-то образом ограничить доступ к этим переменным, чтобы их нельзя было менять
        // Сначала ограничим с помощью ключевого слова protected

        // protected int width;
        // protected int height;

        // это приведет к тому что эти переменные не будут доступны на прямую, будут доступны только методы.
        // protected ограничивает доступ к этим переменным  из вне за исключением текущего класса, в котором
        // эти переменные объявлены, классов наследников и текущего пакета

        // Но этой защиты может быть не достаточно и нам может быть нужна более сильная защита, это -
        /*   private   */ //- защищает переменные и методы таким образом, что к ним нельзя получить доступ
        // ниоткуда, кроме того же класса где они объявлены
        // После того как объявим
       // private int width;
       // private int height;
        // в классе наследнике Square возникнут проблемы:
        /*   this.width=width;
             height=width;   */ // это перестанет работать
        // эти проблемы легко решаются через методы:
        /*  setWidth(width);
            setHeight(width);   */
        // Таким образом мы скрываем переменные "height"  "width" и они становятся не доступны ниоткуда
        // Вообще, ключевое слово private полезно, оно позволяет скрывать реализацию и скрывать методы и переменные,
        // которые нужны только самому классу (служебные методы и переменные)
        // Например, мы хотим чтоб при создании любой фигуры у нас запоминалась дата и время создания:
        // создадим такую переменную - "private Date creationTime;"
        // и создадим метод  /private void setCreationTime()/ и вызовем его в конструкторе "Rectangle(int width, int height)"

        System.out.println(square.getSquare());
        System.out.println(square.getCreationTime()); // получим время создания квадрата, на эту величину мы никак
        // влиять не можем, и не можем влиять на нее из классов наследников

        //Есть еще
        /*  package  */
        // бывают ситуации когда нам какие-то методы нужны только в пределах \package\

        // От Наиля:
        // Поля класса нужно делать \private\, поля можно делать \public\ только в том случае если декларируем константы
        // Напимер: public static final int AMOUNT_MONTH = 1;    и делаем эту константу статической для того чтоб эта
        // константа не была своя для каждого объекта этого класса, чтобы к этой константе -единственной, мы могли
        // получить доступ по имени класса. Константа должна быть одна и если бы она была не /static/, то
        // этих переменных AMOUNT_MONTH -полей, каждая копия была бы для каждого класса, т.е. в памяти было бы
        // несколько экземпляров этого поля, в зависимости от количества объектов

    }
}
