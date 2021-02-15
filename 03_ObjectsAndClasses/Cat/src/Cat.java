
public class Cat
{
    private double originWeight;
    private double weight;

    private double minWeight;
    private double maxWeight;

    private boolean isAlive;

    public void setAlive(boolean alive) {
        if (weight < minWeight || weight > maxWeight){
            isAlive = false;
        } else {
            isAlive = alive;
        }
    }

    public boolean isAlive() {
        return isAlive;
    }

    public static double foodWeight; // объявим переменную - вес еды для кошки
    public static int count;         // счетчик кошек

    public static final int EYES_COUNT = 2;         //создадим константы
    public static final double MAX_WEIGHT = 9000.0;
    public static final double MIN_WEIGHT = 1000.0;

    //создадим переменную окраса кошки и сеттер и геттер для нее
    private Color color;
    public void setColor(Color color) {
        this.color = color;
    }
    public Color getColor() {
        return color;
    }

    //создадим переменную имени кошки для урока 7 (копирование объекта)
    private String name;
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

    public void setWeight(double weight)
    {
        minWeight = 1000.0;
        maxWeight = 9000.0;
        if (weight < minWeight || weight > maxWeight){
            System.out.println("вес не может быть меньше: " + minWeight + " и больше: " + maxWeight + ". Установите корректный вес.");
        }
        else {
            this.weight = weight;
        }
    }
    public Double getWeight() {
        return weight;
    }

    //урок 7 создадим конструктор (для копирования параметров кошек при создании новых кошек)
    public Cat(String name, double weight, Color color){
        if (name.isEmpty()){
            System.out.println("имя не может быть пустым. Дайте имя кошке");
        }
        else {
            minWeight = 1000.0;
            maxWeight = 9000.0;
            if (weight < minWeight || weight > maxWeight){
                System.out.println("вес не может быть меньше: " + minWeight + " и больше: " + maxWeight + ". Установите корректный вес.");
            }
            else {
                this.name = name;
                this.weight = weight;
                this.color = color;
                isAlive = true;
                count++;                // и опять добавим счетчик для контроля поголовья котов
            }

        }
    }


    public Cat()
    {
        weight = 1500.0 + 3000.0 * Math.random();
        originWeight = weight;
        minWeight = 1000.0;
        maxWeight = 9000.0;
        isAlive = true;
        count++;            //при создании кошки кол-во > на 1 кошку

    }

    //урок 5: создадим конструктор для установки веса кошки при ее создании
    public Cat(double weight){
        minWeight = 1000.0;
        maxWeight = 9000.0;
        if (weight < minWeight || weight > maxWeight){
            System.out.println("вес не может быть меньше: " + minWeight + " и больше: " + maxWeight + ". Установите корректный вес.");
        }
        else {
            this.weight = weight;
            isAlive = true;
            count++;
        }
    }

    public void meow()
    {
        if (weight < minWeight || weight > maxWeight){
            setAlive(false);
            System.out.println("Кошка не может мяукать, она - " + getStatus());
        }
        else {
            System.out.println("Meow");
            weight = weight - 1;
            if (weight < minWeight){
                setAlive(false);
                count--;
            }
        }


    }

    public void feed(Double amount)
    {
        if (weight < minWeight || weight > maxWeight) {
            setAlive(false);
            System.out.println("Кошка не может есть, она - " + getStatus());
        }
        else {
            weight = weight + amount;
            foodWeight = foodWeight + amount;  //добавим в метод счетчик суммирования съеденной еды
            if (weight > maxWeight){        //если после последнего приема еды вес уйдет за жизненные параметры, то кошка представится и отминусуется
                setAlive(false);
                count--;
            }
        }

    }

    public void drink(Double amount)
    {
        if (weight < minWeight || weight > maxWeight) {
            setAlive(false);
            System.out.println("Кошка не может пить, она - " + getStatus());
        }
        else{
            weight = weight + amount;
            if (weight > maxWeight){
                setAlive(false);
                count--;
            }
        }
    }


    public void setMinWeight(double minWeight) {
        this.minWeight = minWeight;
    }

    public double getMinWeight()  //создадим геттер для minWeight
    {
        return minWeight;
    }

    public void setMaxWeight(double maxWeight) {
        this.maxWeight = maxWeight;
    }

    public double getMaxWeight()     //создадим геттер для maxWeight
    {
        return maxWeight;
    }

    public String getStatus()
    {
        if(weight < minWeight) {
            return "Dead";
        }
        else if(weight > maxWeight) {
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
        if (weight < minWeight || weight > maxWeight){
            setAlive(false);
            System.out.println("Кошка не сможет сходить в туалет, она - " + getStatus());
        }
        else {
            System.out.println("Cat pee");
            weight = weight - weight / 100;
            if (weight < minWeight){
                setAlive(false);
                count--;
            }

        }

    }

    //создадим метод (геттер) возврата живых кошек
    public static void getCountCat()
    {
        System.out.println("Количество кошек: " + count);
    }

    //создадим геттер получения раскраса кошки
    public Color getColor(Color color){
        return color;
    }


}