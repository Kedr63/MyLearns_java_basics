import javax.net.ssl.HandshakeCompletedListener;
import java.util.ArrayList;
import java.util.List;

public class Interface_ot_Zayr {
    // ❤️ Интерфейс от Заур

// Допустим, пришло распоряжение, что теперь и водитель и учитель должны уметь оказывать первую медпомощь
// и тушить пожар, а водитель еще дополнительно уметь плавать.
// Как можно это сделать?
// Если добавить в класс Employee - оказывать помощь, тушить пожары и плавать, то учитель тоже будет уметь
// плавать (все наследники будут уметь делать эти три навыка), можно попробовать создать эти методы в
// другом классе и сделать от него extends - нет, нельзя так, потому что Teacher и Driver могут делать extends
// только один класс (здесь Employee). Java не поддерживает множественное наследование.
// Можно написать вручную в Teacher, что он может тушить пожар и оказывать помощь, в Driver тоже написать
// что может тушить, оказывать помощь и плюс плавать - но это нехороший вариант.
// Вот как лучше всего это сделать (в таких моментах очень хорошо помогает интерфейс).
// создадим интерфейсы --> (см. под методом main)



    public static void main(String[] args) {
        Help_able h = new Driver();
        Swim_able s = new Driver();
        Employee e = new Driver(); // с помощью переменной /е/ можем обращаться только к тем элементам
        // класса Driver, которые есть и в классе Employee тоже (к перезаписанным методам из класса
        // Employee в классе Driver). Тоже можно сказать и про интерфейсы
        System.out.println(h.a); // можем с помощью /h/ вызвать /а/, которая прописана в интерфейсе.
        // System.out.println(h.salary); но не могу посмотреть допустим /salary/. Почему? Потому что
        // компилятор проверяет -> /h/ к какому типу относится? -> к /Help_able/, в /Help_able/ есть
        // константа /salary/? -> нет, тогда запрещаю
        // можем с помощью /h/ вызвать методы, которые /Driver/ оверайдит из интерфейса /Help_able/
        h.pomosh();
        h.tushitPojar("voda");
        // h.eat(); не можем вызвать этот метод, который /Driver/ наследует от /Employee/
        // h.vodit(); тоже не может, потому что этих методов компилятор не находит в переменной /h/
        // типа /Help_able/
        s.swim();
        // консоль:
        /*10
        Driver okazivaet pomosh
        Driver tushit Pojar c pomowyu: voda
        Driver mojet plavat   */

        // Т.о. на объект класса /new Driver()/ может ссылать переменная такого же типа что и сам
        // объект /Driver/, затем типа его супер-класса и типа интерфейса, который инмплементировал класс
        // созданного объекта.
        // Swim_able s = new Driver(); можем сказать, что водитель - это способный плавать.
        // Help_able h = new Driver(); водитель - это способный помогать, оказывать первую помощь
    }
}

    interface Help_able {     // способный помогать
        void pomosh();        // методы абстрактные у интерфейса и public
        void tushitPojar(String Predmet);   // компилятор вместо нас уже впереди поставит abstract
        int a = 10;    //константа
    }

    interface Swim_able {     // умеющий плавать
        void swim();
    }

// далее см. class Text ⬇ ниже

    class Employee {    // Родит. класс "Работник"
        double salary = 100;
        String name = "Bob";
        int age;
        int experience;

        void eat() {
            System.out.println("Kushat");
        }

        void sleep() {
            System.out.println("Spat");
        }
    }

    class Teacher extends Employee implements Help_able {
        int kolichestvoUchenikov;

        void uchit() {
            System.out.println("Uchit");
        }

        @Override
        public void pomosh() {
            System.out.println("Uchitel okazivaet pomosh");
        }

        @Override
        public void tushitPojar(String s) {
            System.out.println("Driver tushit Pojar c pomowyu: " + s);
        }
    }

    class Driver extends Employee implements Help_able, Swim_able {
        String naznachenieMashini;

        void vodit() {
            System.out.println("Vodit");
        }

        @Override
        public void pomosh() {
            System.out.println("Driver okazivaet pomosh");
        }

        @Override
        public void tushitPojar(String s) {
            System.out.println("Driver tushit Pojar c pomowyu: " + s);
        }

        @Override
        public void swim() {
            System.out.println("Driver mojet plavat");
        }
    }

    class Text {
// и теперь применим эти интерфейсы с помощью добавления implements (оно переводиться как - воплощать в жизнь,
// осуществлять, реализовывать) и теперь должны реализовать все методы интерфейса, как у АК - абстр. методы.
// И если добавим класс Доктор и не имплементируем интерфейсы, то он ни оказывать помощь ни тушить, ни плавать
// не сможет
// Интерфейс из переменных может содержать только константы, даже если внутри тела интерфейса написать
// int a = 10; то компилятор по умолчанию добавит сюда /public final static/
// Но мало кто использует константы в интерфейсе

// Интерфейс - это конструкция языка программирования, которую часто сравнивают с контрактом. В этом контракте
// указано, чтО класс может делать, т.е. какие методы в нем будут присутствовать, если он имплементирует
// данный интерфейс. Когда класс имплементирует какой либо интерфейс, он обязуется снабдить методы этого
// интерфейса телами (перезаписать абстрактные методы); в противном случае класс должен стать абстрактным.
// Т.о. если известно,
// что класс имплементировал какой либо интерфейс, то в этом классе гарантированно будут методы из интерфейса.

// Если не по Java, то пример из жизни: интерфейс автомобиля - наличие руля, рычага КПП, педалей. Когда мы
// используем авто, мы уверены что сможем пользоваться им, необходимым нам образом, это все происходит благодаря
// тому что мы знакомы с интерфейсом авто.

// Зачем нужен интерфейс?
// Причин несколько. Самые основные это следующие:
// Интерфейс - это контракт или протокол, в котором говорится чтО класс сможет делать. Классы, которые
// имплементируют этот интерфейс соглашаются на контракт и соглашаются что они будут перезаписывать эти методы.
// Если мы знаем что класс имплементировал интерфейс, значит мы знаем что класс будет содержать эти методы
// и мы гарантированно можем вызывать эти методы из класса.
// Во вторых, Java не поддерживает множественное наследование. Для этого и нужен интерфейс, он позволяет нам
// работать с множественным наследованием (имплементировать хоть сколько интерфейсов)
        // см. метод main ⬆


        // Еще пример
        List<String> l = new ArrayList<>();
        // Здесь List - интерфейс, в нем были такие методы как /add()/ например, и /ArrayList/ имплементировал
        // этот интерфейс /List/ и перезаписывал его методы. Одним словом, имплементация интерфейса это очень
        // удобный момент для использования полиморфизма
        // И обратите внимание, вот как мы писали:
        // Parent p = new Child(); переменная p типа род. класса ссылается на объект дочернего класса
        // Написание с интерфейсом тоже такое возможно, мы можем создавать объект, например /ArrayList/ и
        // ссылаться на него с помощью переменной /l/ типа его интерфейса, который он имплементировал

        // Вернемся к нашему примеру, допустим нам понадобилось в метод /void tushitPojar();/ добавить какой-то
        // String, который будет указывать чем мы тушим пожар (ведро воды или огнетушитель),
        // добавим /void tushitPojar(String Predmet);/.
        // Изменив в интерфейсе сигнатуру метода, нам пришлось менять сигнатуру методов в классах, которые
        // имплементируют данный интерфейс
    }



