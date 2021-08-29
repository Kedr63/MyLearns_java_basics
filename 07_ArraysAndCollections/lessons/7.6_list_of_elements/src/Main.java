import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        //Массивы нужны тогда, когда заранее известно количество элементов и оно не меняется
        //Список нужен для того, чтобы могли менять количество элементов, добавлять и удалять их оттуда, и
        //список в этом смысле динамический
        // (Э) добавляются в конец списка (если не по индексу)
        //Создадим, например, список дел:
        //ArrayList - самый распространеный класс для хранения списков, есть и другие классы

        ArrayList<String> toDoList = new ArrayList<>();    // в <> пишется тип ~ которая будет храниться
        toDoList.add("Первое наше дело");                  // так добаляем в список
        toDoList.add(0, "Второе наше дело");   //можно добалять по индексу, "Второе" станет первым,
        // а "Первое" вторым
        toDoList.add("Следующее наше дело");
        for (int i = 0; i < toDoList.size(); i++) {
            System.out.println(toDoList.get(i));
        }

        //консоль:
        //Второе наше дело
        //Первое наше дело
        //Следующее наше дело

        //Можно и другим циклом:
        for (String item : toDoList) {
            System.out.println(item);
        }


        //Есть и короткая запись добавления элементов (быстрая инициализация)

        ArrayList<String> toDoList1 = new ArrayList<>(){{
            add("Первое наше дело");
            add("Второе наше дело");
        }};
        toDoList1.add("Третье наше дело");  //и также добавлять в этот список
        toDoList1.remove(0);           //и удалять со списка
        for (String item : toDoList1){
            System.out.println(item);
        }
        //консоль:
        //Второе наше дело
        //Третье наше дело
    }
}
