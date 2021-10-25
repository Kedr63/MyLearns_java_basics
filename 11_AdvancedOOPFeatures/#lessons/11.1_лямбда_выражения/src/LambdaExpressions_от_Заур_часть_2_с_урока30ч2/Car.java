package LambdaExpressions_от_Заур_часть_2_с_урока30ч2;

// с импортируем нужные нам интерфейсы:
// поставщик /Supplier/ ⬇  (он поставляет объекты когда его метод вызывается). Внутри его метод /T get()/
// потребитель /Consumer/ ⬇ (внутри его метод /void accept(T t)/)

import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.function.Supplier;


public class Car {
    String model;
    String color;
    double engine;

    public Car(String model, String color, double engine) {
        this.model = model;
        this.color = color;
        this.engine = engine;
    }

    // оверайдим toString чтоб красиво выводилось
    public String toString() {
        return "Our car is " + model + " , color is " + color + " and engine = " + engine;
    }

    // создадим метод ⬇ , который будет создавать три машины, помещать их в ArrayList, и return этот
    // ArrayList нам. А какие машины он будет создавать? - мы уже будем прописывать в ЛВ, когда будем
    // вызывать метод из метода /main/
}

class Test10 {
    public static ArrayList<Car> createThreeCars(Supplier<Car> carSupplier) {
        ArrayList<Car> al = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            al.add(carSupplier.get()); // здесь вызываем единственный метод /get()/ интерфейса Supplier
        }
        return al; // и возвращаем данный ArrayList
    }
    // похожие вещи ⬆ мы делали с /Preticate<T>/ на предыдущем уроке о ЛВ.
    // В итоге в этом методе мы используем метод /T get()/ Supplier-а, т.е. единственный его абстр. метод,
    // а уже что будет делать этот метод - мы сейчас будем указывать когда будем вызывать этот метод




    public static void main(String[] args) {
        ArrayList<Car> ourCars = createThreeCars(() -> new Car("Nissan tilda", "black", 1.6));
        // в () метода мы пишем: принимает метод /T get()/ какой-то параметр? - T get() ничего
        // не принимает -> значит здесь оставляем пустые скобки ();
        // дальше символ /->/. Метод /T get()/ что должен вернуть? - объект типа <T>, <T> у нас что? -> <Car>,
        // значит мы должны должны вернуть машину /Car/ -> мы создаем /new Car(...)/
        //   Всё очень просто: метод /T get()/ не принимает параметров и возвращает тип <T> здесь <Car>

        System.out.println("ourCars: " + ourCars);
       /* консоль:
        ourCars: [Our car is Nissan tilda , color is black and engine = 1.6, Our car is Nissan tilda ,
        color is black and engine = 1.6, Our car is Nissan tilda , color is black and engine = 1.6]*/
        // ⬆  выведем в консоль: метод создаст три одинаковые машины и поместит в ArrayList
        // Так работает  /Supplier/

        // Теперь нам нужно поработать с /Consumer/ 📌 (это потребитель и он потребляет объект). Под потреблением
        // мы понимаем что мы хотим что-то сделать с этим объектом, как-то потребить его.
        // Внутри его один метод /void accept(T t)/, который ничего не возвращает, но принимает параметр
        // типа /Т t/, (у интерфейсов /Consumer & Supplier/ функциональность противоположная).

        // Давайте придумает задачку и совместим эти два интерфейса.
        // Допустим хочу создать метод, который будет менять мою машину - Как менять? - буду решать
        // при вызове этого метода. Напишем этот метод ⬇  /changeCar(Car car, Consumer<Car>consumer)/
        changeCar(ourCars.get(0), car -> {
            car.color = "red";
            car.engine = 2.4;
            System.out.println("Updated car: " + car);
        });
        // Т.е. я вызвал метод /changeCar/, и внутри метода сказал как должен выглядеть метод /consumer-а/
        System.out.println("ourCars: " + ourCars);
        /*консоль:
        Updated car: Our car is Nissan tilda , color is red and engine = 2.4

        ourCars: [Our car is Nissan tilda , color is red and engine = 2.4, Our car is Nissan tilda ,
         color is black and engine = 1.6, Our car is Nissan tilda , color is black and engine = 1.6]*/
        // теперь "Updated car: " обновились данные по машине, и в нашем ArrayList-е /ourCars/ на первом месте
        // теперь идет обновленная машина, на втором и третьем месте прежние старые машины.

        // Заметка: вот этот метод /changeCar/ и его вызов можно было написать совершенно по другому,
        // используя другую технику в работе с ЛВ
        Consumer<Car> consumer = car -> {
            car.color = "red";
            car.engine = 2.4;
            System.out.println("Updated car: " + car);
        };
        // и затем на этом объекте /consumer/ вызвать метод /accept(car)/
        consumer.accept(ourCars.get(0));
        // Все будет работать точно также
        // Смотрите что мы сделали: мы создали объект consumer-а и после /=/ показали как должен работать
        // его метод /accept/, а метод /accept(T t)/, понятное дело, будет принимать в себя параметры и
        // какой параметр - мы указываем здесь <Car>. И потом когда на consumer-е вызвали метод accept -
        // в параметры вставили тот объект, который мы хотим update-ть, т.е. менять. Этот объект - первый
        // элемент  ArrayList-а /ourCars.get(0)/
        // И так и так можно было делать не только с consumer-ом, и с Supplier-ом можно и с Predicate-ом
        // Но в основном пользуются первой техникой, где метод /changeCar(Car car, Consumer<Car> consumer)/

        // См. далее class Test2
    }

    public static void changeCar(Car car, Consumer<Car> consumer) {
        consumer.accept(car);
        // это самая используемая техника применения
    }
}
