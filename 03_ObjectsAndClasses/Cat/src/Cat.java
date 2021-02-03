
public class Cat
{
    private double originWeight;
    private double weight;

    private double minWeight;
    private double maxWeight;

    public static double foodWeight; // объявим переменную - вес еды для кошки
    public static int count;         // счетчик кошек

    public static final int EYES_COUNT = 2;         //создадим константы
    public static final double MAX_WEIGHT = 9000.0;
    public static final double MIN_WEIGHT = 1000.0;

    public Cat()
    {
        weight = 1500.0 + 3000.0 * Math.random();
        originWeight = weight;
        minWeight = 1000.0;
        maxWeight = 9000.0;
        count++;            //при создании кошки кол-во > на 1 кошку

    }

    public void meow()
    {
        weight = weight - 1;
        System.out.println("Meow");
    }

    public void feed(Double amount)
    {
        if (weight < minWeight) {
            System.out.println("Кошка не может есть, она - " + getStatus());
        }
        else if (weight > maxWeight){
            System.out.println("Кошка не может есть, она - " + getStatus());
        }
        else {
            weight = weight + amount;
            foodWeight = foodWeight + amount;  //добавим в метод счетчик суммирования съеденной еды
        }

    }

    public void drink(Double amount)
    {
        if (weight < minWeight) {
            System.out.println("Кошка не может пить, она - " + getStatus());
        }
        else if (weight > maxWeight){
            System.out.println("Кошка не может пить, она - " + getStatus());
        }
        else{
            weight = weight + amount;
        }
    }

    public Double getWeight()
    {
        return weight;
    }


    public double getMinWeight()  //создадим геттер для minWeight
    {
        if (weight < minWeight){      //если условие будет true, то мы лишимся еще одной кошки
            count--;
        }
        return minWeight;
    }


    public double getMaxWeight()     //создадим геттер для maxWeight
    {
        if (weight > maxWeight){      //если условие будет true, то мы лишимся еще одной кошки
            count--;
        }
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
        if (weight < minWeight){
            System.out.println("Кошка не сможет сходить в туалет, она - " + getStatus());
        }
        else if (weight > maxWeight){
            System.out.println("Кошка не сможет сходить в туалет, она - " + getStatus());
        }
        else {
            weight = weight - weight / 100;
            System.out.println("Cat pee");
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