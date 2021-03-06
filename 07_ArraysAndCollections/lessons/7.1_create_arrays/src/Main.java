public class Main {
    // Массивы нужны для хранения множества элементов: чисел, строк, объектов, массивов
    // Массив может содержать заранее известное число одинаковых элементов
    // Начинается массив с 0 индекса

    public static void main(String[] args) {
        // например в массиве будет храниться количество комнат на каждом этаже задания
        int[] roomCounts = new int[6]; // в [] - количество элеметнов которые есть в массиве
        // например в здании 6 этажей, теперь можем его заполнять:
        roomCounts[0] = 5; // на нулевом этаже 5 комнат
        roomCounts[1] = 45; // на 1 этаже 45 комнат
        roomCounts[2] = 34;
        roomCounts[3] = 28;
        for (int i = 0; i < roomCounts.length; i++) {  //length это не метод, а свойство
            System.out.println(roomCounts[i]);
        }
        /*Консоль (не заполненые элементы имеют значение 0)
        5
        45
        34
        28
        0
        0
        */
        // Есть другой более удобный способ заполнения массива
        int[] roomCounts1 = {5, 45, 34, 28, 0, 0};

        // Теперь создадим массив объектов, допустим массив строк
        String[] colors = {"Красный", "Зеленый", "Синий"};
        // Или можем массивы получать из методов
        String text = "Каждый охотник желает знать, где сидят фазаны";
        String[] colors1 = text.split(",?\\s+"); // это будет массив (этот метод .split() возвращает массив)

    }

}
