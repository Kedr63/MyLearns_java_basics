
public class Cat
{
    private double originWeight;
    private double weight;

    private boolean isAlive;

    private double foodWeight; // объявим переменную - вес еды для кошки
    public static int count;         // счетчик кошек

    public static final int EYES_COUNT = 2;         //создадим константы
    public static final double MAX_WEIGHT = 9000.0;
    public static final double MIN_WEIGHT = 1000.0;

    //создадим переменную окраса кошки
    private Color color;

    //создадим переменную имени кошки для урока 7 (копирование объекта)
    private String name;



    //урок 7 создадим конструктор (для копирования параметров кошек при создании новых кошек)
    public Cat(String name, double weight, Color color){
        if (name.isEmpty()){
            System.out.println("имя не может быть пустым. Дайте имя кошке");
        }
        else {
            if (weight < MIN_WEIGHT || weight > MAX_WEIGHT){
                System.out.println("вес не может быть меньше: " + MIN_WEIGHT + " и больше: " + MAX_WEIGHT + ". Установите корректный вес.");
            }
            else {
                this.name = name;
                this.weight = weight;
                this.color = color;
                isAlive = true;
                count++;                //добавим счетчик для контроля поголовья котов
            }

        }
    }


    public Cat()
    {
        weight = 1500.0 + 3000.0 * Math.random();
        originWeight = weight;
        isAlive = true;
        count++;            //при создании кошки кол-во > на 1 кошку

    }

    //урок 5: создадим конструктор для установки веса кошки при ее создании
    public Cat(double weight){
        if (weight < MIN_WEIGHT || weight > MAX_WEIGHT){
            System.out.println("вес не может быть меньше: " + MIN_WEIGHT + " и больше: " + MAX_WEIGHT + ". Установите корректный вес.");
        }
        else {
            this.weight = weight;
            isAlive = true;
            count++;
        }
    }
    

    public boolean isAlive() {
        return isAlive;
    }

    public void setName(String name)
    {
        if (name.isEmpty()){
            System.out.println("имя не может быть пустым. Дайте имя кошке");
        }
        else {
            this.name = name;
        }
    }
    public String getName(){
        return name;
    }

    public Double getWeight() {
        return weight;
    }

    public void meow()
    {
        if (weight < MIN_WEIGHT || weight > MAX_WEIGHT){
            System.out.println("Кошка не может мяукать, она - " + getStatus());
        }
        else {
            System.out.println("Meow");
            weight = weight - 1;
            if (weight < MIN_WEIGHT){
                isAlive = false;
                count--;
            }
        }


    }

    public void feed(Double amount)
    {
        if (weight < MIN_WEIGHT || weight > MAX_WEIGHT) {
            System.out.println("Кошка не может есть, она - " + getStatus());
        }
        else {
            weight = weight + amount;
            foodWeight = foodWeight + amount;  //добавим в метод счетчик суммирования съеденной еды
            if (weight > MAX_WEIGHT){        //если после последнего приема еды вес уйдет за жизненные параметры, то кошка представится и отминусуется
                isAlive = false;
                count--;
            }
        }

    }

    public void drink(Double amount)
    {
        if (weight < MIN_WEIGHT || weight > MAX_WEIGHT) {
            System.out.println("Кошка не может пить, она - " + getStatus());
        }
        else{
            weight = weight + amount;
            if (weight > MAX_WEIGHT){
                isAlive = false;
                count--;
            }
        }
    }



    public String getStatus()
    {
        if(weight < MIN_WEIGHT) {
            return "Dead";
        }
        else if(weight > MAX_WEIGHT) {
            return "Exploded";
        }
        else if(weight > originWeight) {
            return "Sleeping";
        }
        else {
            return "Playing";
        }
    }

    //создадим метод (геттер) возврата веса всей съеденной еды
    public double getFoodWeight()
    {
        return foodWeight;
    }


    //создадим метод: кошка пописала
    public void pee()   {
        if (weight < MIN_WEIGHT || weight > MAX_WEIGHT){
            System.out.println("Кошка не сможет сходить в туалет, она - " + getStatus());
        }
        else {
            System.out.println("Cat pee");
            weight = weight - weight / 100;
            if (weight < MIN_WEIGHT){
                isAlive = false;
                count--;
            }

        }

    }

    //создадим метод (геттер) возврата живых кошек (если придется арифметически где-то применять это число)
    public static int getCountCat(){
        return count;
    }

    //создадим метод распечатывания количества живых кошек (для получения информации)
    public static void printCountCat()
    {
        System.out.println("количество живых кошек: " + count);
    }



    public void setColor(Color color) {
        this.color = color;
    }
    public Color getColor() {
        return color;
    }


}