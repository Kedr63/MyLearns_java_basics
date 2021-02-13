
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

        //урок 4
        System.out.println();
        System.out.println("Кошка имеет " + cat1.getColor(Color.GREY) + " раскрас");
        System.out.println("Cat have " + cat3.getColor(Color.WIGHT) + " color");

        //урок 5: 'objects creation and constructor'
        System.out.println();
        Cat.getCountCat(); //количество кошек перед рождением котят
        getKitten("Снежок");
        getKitten("Барсик");
        getKitten("Рыжик");
        getKitten("Муська");
        Cat.getCountCat();  //заодно проверим сколько стало кошек


        //урок 7 глубокая копия объекта (с помощью конструктора)
        System.out.println();
        System.out.println("Копирование объекта с помощью коструктора");
        Cat cat10 = new Cat("Барсик", 1500.0, Color.GREY);
        System.out.println(cat10.getName() + " " + cat10.getWeight() + " " +  cat10.getColor());
        Cat cat11 = new Cat(cat10.getName(), cat10.getWeight(), cat10.getColor());
        System.out.println(cat11.getName() + " " + cat11.getWeight() + " " +  cat11.getColor());
        Cat.getCountCat();

        System.out.println();
        System.out.println("Копирование объектов сеттерами и геттерами");
        Cat cat12 = new Cat();
        cat12.setName("Муська");
        cat12.setWeight(1650.0);
        cat12.setColor(Color.BLACK);
        System.out.println(cat12.getName() + " " + cat12.getWeight() + " " + cat12.getColor());
        Cat cat13 = new Cat();
        cat13.setName(cat12.getName());
        cat13.setWeight(cat12.getWeight());
        cat13.setColor(cat12.getColor());
        System.out.println(cat13.getName() + " " + cat13.getWeight() + " " + cat13.getColor());
        Cat.getCountCat();

        //потестируем работу счетчика кошек и выведем их состояние (жива- true, нежива- false)
        System.out.println();
        System.out.println("потестируем работу счетчика кошек и выведем их состояние (жива- true, нежива- false)");
        System.out.println("закормленная cat2: " + cat2.getStatus() + " " + cat2.getWeight() + " " + cat2.getMaxWeight());
        cat2.feed(150.0);
        Cat.getCountCat();
        cat2.feed(500.0);
        Cat.getCountCat();
        cat1.meow();
        System.out.println(cat1.getWeight()+" "+cat1.isAlive());
        Cat.getCountCat();
        System.out.println();
        cat1.drink(85.0);
        cat1.meow();
        cat1.feed(400.0);
        cat1.pee();
        cat3.pee();
        cat3.feed(250.0);
        cat3.meow();
        cat3.drink(312.0);
        System.out.println("cat1: "+cat1.isAlive());
        System.out.println("cat2: "+cat2.isAlive());
        System.out.println("cat3: "+cat3.isAlive());
        System.out.println("cat4: "+cat4.isAlive());
        System.out.println("cat5: "+cat5.isAlive());
        System.out.println("cat6: "+cat6.isAlive());
        System.out.println("cat7: "+cat7.isAlive());
        System.out.println("cat10: "+cat10.isAlive());
        System.out.println("cat11: "+cat11.isAlive());
        System.out.println("cat12: "+cat12.isAlive());
        System.out.println("cat13: "+cat13.isAlive());
        Cat.getCountCat();
        cat3.feed(8000.0);
        cat3.feed(500.0);
        System.out.println("cat3: "+cat3.isAlive());
        Cat.getCountCat();

        //протестируем setAlive и защиту других сеттеров и конструкторов
        System.out.println();
        System.out.println("протестируем setAlive и защиту других сеттеров и конструкторов");
        cat1.setAlive(true);
        System.out.println("cat1: "+cat1.isAlive());
        Cat.getCountCat();
        cat2.setName("");
        System.out.println("cat2 name: "+cat2.getName());
        Cat cat14 = new Cat(-600.0);
        cat14.setWeight(500.0);
        System.out.println("cat14 weight: " + cat14.getWeight());
        Cat.getCountCat();
        Cat cat15 = new Cat("Murka", 4500.0, Color.BEIGE);
        Cat cat16 = new Cat("", 3500,Color.BLACK);
        Cat cat17 = new Cat("Naff", -3200.0, Color.WIGHT);
        Cat.getCountCat();
        System.out.println(cat17.getName() + " " +cat17.getWeight() + " " + cat17.getColor() + " " + cat17.isAlive());


    }

    //урок 5: 'objects creation and constructor'
    // создадим метод генерации (рождения) котят
    private static Cat getKitten(String name){
        Cat cat = new Cat(1100.0);
        System.out.println("Born kitten: " + name + " ,weight: " + cat.getWeight());
        return cat;
    }
}