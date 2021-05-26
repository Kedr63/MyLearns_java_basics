public class Main {
    public static void main(String[] args) {
        // Какие бывают способы перебора элементов массивов:
        // 1- С помощью цикла for()
        // 2 - Или, например, хотим сгенерировать номера лотерейных билетов

        int[] ticketNumbers = new int[1000];            //1 - представим что есть 1000 лот-х билетов от 0 до 999
        int[] winTickets = new int[10];                 //2 - теперь мы хотим чтоб каждый 100й билет был выигрышный
        for (int i = 0; i < ticketNumbers.length; i++) {  // заполним значение билетов от 1000000 до 2000000
            int value = 1000000 + (int) (Math.round(1000000 * Math.random())); //получим 1000 лот билетов с разными случайными номерами
            ticketNumbers[i] = value;           //если распечатать все значения билетов, то System.out.println(value);
            if (i % 100 == 0) {                 // каждый 100й будет true
                winTickets[i / 100] = value;     // в этот массив кладем наше значение, а какой здесь будет элемент массива? 0/100=0, 100/100=1 и тд
            }
        }                                        // и распечатаем массив winTickets[] с помощью цикла с новым систаксисом
        for (int winTicketsNumber : winTickets) {
            System.out.println(winTicketsNumber);
        }
        // int winTicketsNumber - новая ~ , winTickets - массив, который перебераем. Этот упрощенный систаксис
        // не позволяет узнать индекс элемента массива на котором сейчас находимся, но позволяет последовательно
        // от нулевого элемента и до конца получить все элементы в переменной /int winTicketsNumber/
        /*  for (тип_данных название_переменной : контейнер){
            // действия
           }
           int[] array = new int[] { 1, 2, 3, 4, 5 };
            for (int i : array){
            System.out.println(i);
            }
           */


        // Также можно было перебрать массив из прошлого урока
        String text = "Каждый охотник желает знать, где сидят фазаны";
        String[] colors = text.split(",?\\s+");           // это будет массив (этот метод .split() возвращает массив)
        for (String colorWorld : colors) {             // Эта запись применима тогда, когда не нужен номер элемента в массиве
            System.out.println(colorWorld);
        }

        // если номер элемента нужен, то используем обычный синтаксис цикла for
        String text1 = "Каждый охотник желает знать, где сидят фазаны";
        String[] colors1 = text.split(",?\\s+");
        for (int i = 0; i < colors1.length; i++) {
            System.out.println(colors1[i]);
        }

    }
}
