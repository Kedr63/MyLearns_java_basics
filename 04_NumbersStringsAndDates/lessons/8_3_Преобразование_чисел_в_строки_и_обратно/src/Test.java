public class Test {
    // ❤️ Преобразование_чисел_в_строки_и_обратно

    // Это часто бывает необходимо в реальных задачах, например, если читаете файл и получаете данные
    // ввиде строк, которые содержат числа, с которыми потом нужно будет проводить какие-то вычисления,
    // в этом случае вам придется преобразовывать строки в числа. Или наоборот, у вас есть числа и вам
    // нужно преобразовать их в строки для той же записи в файл или для отправки какого-то отсчета по
    // электр. почте, в этом случае вам придется числа преобразовывать в строки

    public static void main(String[] args) {
        int age = 37;
        String name = "Анна";
        // Самый простой способ преобразования числа в строку это автоматическое, по сути неявное
        // преобразование при конкатенации ⬇ , здесь /age/ будет автомат-ски преобр-но в строку
        String personInfo = name + " - " + age + " лет";

        // Но что делать если необходимо явное преобразование?
        /*  String value = age;   */  // <-- так не получится
        // Можно воспользоваться статическим методом /toString/ соответствующего класса (в данном случае
        // у нас будет соответствовать числу типа /int/ класс Integer
        String value = Integer.toString(age); // -> /age/ будет преобр-но в строку

        // Также если ваше число было объектом соответствующего класса, а не примитивом:
        Integer number = 29;
        // то у этого объекта тоже можно вызвать метод /toString/
        String value1 = number.toString();
        // Метод /toString/ есть у всех классов чисел.

        // Для преобразования чисел в строки можно также использовать
        // статический метод /valueOf/ класса String. 📌
        // Он существует для всех примитивов и даже для объектов
        String value2 = String.valueOf(number);

        // Таким образом есть 4 способа преобразования чисел ➔ в строку:
        // 📍 Неявное при конкатенации
        // 📍 Double.toString();
        // 📍 Double value = 2.56;
        //    value.toString();
        // 📍 String.valueOf(number);

// _________________________________________________________
        /*     ❤️ Преобразование строки в число    */

        String value3 = "3454324";
        // Основной способ преобразования -это методы парсинга строк у соответствующих классов чисел
        int count = Integer.parseInt(value3);
        System.out.println(count);  // 3454324

        // Если число которое мы парсим будет превышать допустимые пределы,
        /* String value4 = "345437689595968686824";
           int count1 = Integer.parseInt(value4);     */
        // то произойдет исключение /Exception ....NumberFormatException/

        // У классов чисел с плавающей точкой тоже есть такие методы парсинга
        String value5 = "3.454324";
        double weight = Double.parseDouble(value5);
        System.out.println(weight);   // 3.454324
        // если в этом случае число маленькое или большое,
        // то /parseDouble/ выдаст в консоль  /Infinity/ или /- Infinity/ если число отрицательное

        // Таким образом, преобразовывать строки в числа можно с использованием статических методов
        // соответствующих классов, эти методы есть у всех классов чисел.
        // Важно помнить, что числа в строках, которые мы парсим, должны соответствовать классам
        // которые мы используем для такого парсинга, иначе может произойти исключение



    }
}
