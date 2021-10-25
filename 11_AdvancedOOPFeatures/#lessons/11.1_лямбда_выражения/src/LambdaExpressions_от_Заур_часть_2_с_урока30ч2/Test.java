package LambdaExpressions_от_Заур_часть_2_с_урока30ч2;

/* Var
 * Local variable type inference (перев. подразумеваемые, а глагол infer - делать вывод)
 * */
// Идет постоянная тенденция упрощения кода, это не всегда хорошо (пропадает читабельность кода)
// var - это локальная ~ типа inference
// Java сама делает вывод (infer) что за тип у переменной и нам писать сам тип не нужно.
// Компилятор все это понимает и при компиляции уже сам вставляет название класса
// var - относится только к локальным переменным

import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
        // TestInferenceType tit = new TestInferenceType();
        // Чтобы так ⬆ не писать мы можем написать так ⬇
        var tit = new TestInferenceType();

        // Посмотрим как можно писать
        var i = 10; // компилятор понимает что это int
        var w = 1.5f; // компилятор понимает что это float
        var array = new String[]{"a", "b"}; // компилятор заменит var на String
        var result = abc(); // ⬇ компилятор понимает что метод возвращает double -> поэтому result
        // будет double

        Object obj1 = "privet";
        var obj2 = obj1; // ссылается на тот же объект что и obj1 (obj2 тоже будет тип Object)

        ArrayList<String> al = new ArrayList<>();
        for (var s : al) {     // компилятор понимает что /al/ содержит String-и, поэтому /s/ будет типа String
            System.out.println(s);
        }

        // Теперь рассмотрим как нельзя писать:
        // Не можем написать ВНЕ какого-то метода,
        // не можем использовать внутри параметра метода (),
        // не можем так - var h; var = null;  var array2 = {1, 2, 3}

        // И вообще var нужно использовать в разумных границах, надо не забывать что код должен
        // быть читабельным в первую очередь


    }

    // допустим есть какой-то метод
    static double abc() {
        return 3.14;
    } // см. ⬆
}

class TestInferenceType {
}

// 📌 Перейдем к ЛВ часть_2

// Functional interface //

interface I {    // это фунциональный интерфейс (так как один абстр. метод), но в нем может быть хоть
    void abc();          // сколько дефолтных и static методов
    default void def(){}          // ↵
    default void def2(){}
    static void def3(){}
}
  // 🔔 Еще рассмотрим два функционал. интерфейса : Consumer & Supplier (потребитель & поставщик)
// Создадим класс /Car/
