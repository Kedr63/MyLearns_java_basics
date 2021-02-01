
public class Cat
{
    private double originWeight;
    private double weight;

    private double minWeight;
    private double maxWeight;

    public static double foodWeight; // объявим переменную - вес еды для кошки

    public Cat()
    {
        weight = 1500.0 + 3000.0 * Math.random();
        originWeight = weight;
        minWeight = 1000.0;
        maxWeight = 9000.0;

    }

    public void meow()
    {
        weight = weight - 1;
        System.out.println("Meow");
    }

    public void feed(Double amount)
    {
        weight = weight + amount;
        foodWeight = foodWeight + amount;  //добавим в метод счетчик суммирования съеденной еды
    }

    public void drink(Double amount)
    {
        weight = weight + amount;
    }

    public Double getWeight()
    {
        return weight;
    }


    public double getMinWeight()  //создадим геттер для minWeight
    {
        return minWeight;
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
    public double getFoodWeight() {    //создадим метод (геттер) возврата веса всей съеденной еды
        return foodWeight;
    }  //создадим метод (геттер) суммы съеденной еды кошкой

    public void pee()   {                 //создадим метод: кошка пописала
        weight = weight - weight / 100;
        System.out.println("Cat pee");
    }


}