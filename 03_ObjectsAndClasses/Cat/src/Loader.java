
public class Loader
{
    public static void main(String[] args)
    {
        Cat cat1 = new Cat();
        Cat cat2 = new Cat();
        Cat cat3 = new Cat();
        Cat cat4 = new Cat();
        Cat cat5 = new Cat();
        Cat cat6 = new Cat();

        //выведем вес кошек
        System.out.println("Weight Cat1 - " + cat1.getWeight() + " kg");
        System.out.println("Weight Cat2 - " + cat2.getWeight() + " kg");
        System.out.println("Weight Cat3 - " + cat3.getWeight() + " kg");
        System.out.println("Weight Cat4 - " + cat4.getWeight() + " kg");
        System.out.println("Weight Cat5 - " + cat5.getWeight() + " kg");
        System.out.println("Weight Cat6 - " + cat6.getWeight() + " kg \n");

        //покормим кошек
        System.out.println("Weight Cat6 before eating: " + cat6.getWeight().intValue());
        cat6.feed(120.0);
        System.out.println("Weight Cat6 after eating: " + cat6.getWeight().intValue() + "\n");

        System.out.println("Weight Cat5 before eating: " + cat5.getWeight());
        cat5.feed(200.0);
        System.out.println("Weight Cat5 after eating: " + cat5.getWeight() + "\n");


        // замяукать cat1
        System.out.println("Cat1 - weight before meow: " + cat1.getWeight());
        while (cat1.getWeight() > cat1.getMinWeight()) {
            cat1.meow();
            System.out.println("Cat1 decreased by: "+ cat1.getWeight() + "  (Cat1 meow and " + cat1.getStatus() + ")");
        }
        System.out.println("Cat1 - after many meows: " + cat1.getStatus() + "\n");



        // закормить Cat2
        System.out.printf("Cat2 - weight before eating: %.2f \n", cat2.getWeight());  // округлим вес до 2 знаков после запятой и перенесем строку
        while (cat2.getWeight() < cat2.getMaxWeight()){
            cat2.feed(75.0);
            System.out.printf("Cat2 - weight after eating: %.2f ", cat2.getWeight());
            System.out.println("   (Cat2 ate and " + cat2.getStatus() + ")");
        }
        System.out.println("Cat2 - after overeating: " + cat2.getStatus() + "\n");


        //урок 2 'method, parameters and return'

        Cat cat7 = new Cat();
        Cat.foodWeight = 0;


        System.out.println("Cat weight begin: " + cat7.getWeight().intValue());
        cat7.feed(150.0);
        cat7.pee();
        System.out.println("After First feeding, cat weight : " + cat7.getWeight().intValue());
        cat7.feed(100.0);
        cat7.drink(45.0);
        cat7.pee();
        System.out.println("After Second feeding, cat weight : " + cat7.getWeight().intValue());
        System.out.println("Cat weight at the end: " + cat7.getWeight().intValue());
        System.out.println("Amount weight food: " + cat7.getFoodWeight() + "\n");


        //урок 3 'static methods and variables'

        Cat.getCountCat();  //получим количество живых кошек

        System.out.println();

        System.out.println(cat1.getWeight() + " Покормим кошку: ");  //покормим замяуканную кошку
        cat1.feed(150.0);
        System.out.println(cat1.getWeight());

        System.out.println(cat2.getWeight() + " Покормим кошку: ");   //покормим закормленную кошку
        cat2.feed(100.0);
        System.out.println(cat2.getWeight());

        System.out.println(cat3.getWeight() + " Покормим кошку: ");   //покормим живую кошку
        cat3.feed(100.0);
        System.out.println(cat3.getWeight());

        System.out.println();

        System.out.println(cat1.getWeight() + " Попоим кошку: ");    //попоим замяуканную кошку
        cat1.drink(75.0);
        System.out.println(cat1.getWeight());

        System.out.println(cat2.getWeight() + " Попоим кошку: ");    //попоим закормленную кошку
        cat2.drink(85.0);
        System.out.println(cat2.getWeight());

        System.out.println(cat4.getWeight() + " Попоим кошку: ");    //попоим живую кошку
        cat4.drink(55.0);
        System.out.println(cat4.getWeight());

        System.out.println();

        System.out.println(cat1.getWeight() + " Посадим кошку на горшок: ");   // замяуканную кошку посадим на горшок
        cat1.pee();
        System.out.println(cat1.getWeight());

        System.out.println(cat2.getWeight() + " Посадим кошку на горшок: ");   // закормленную кошку посадим на горшок
        cat2.pee();
        System.out.println(cat2.getWeight());

        System.out.println(cat5.getWeight() + " Попоим кошку: ");    //живую кошку посадим на горшок
        cat5.drink(55.0);
        System.out.println(cat5.getWeight());



    }
}