public class Main {
    public static void main(String[] args) {
        //массивы можно перебирать в обратном порядке
        //например распечать слова в обратном порядке
        String text = "Каждый охотник желает знать, где сидят фазаны";
        String[] colors = text.split(",?\\s+");
        for (int i = colors.length - 1; i >= 0; i--) {   //здесь в [] 7 элементов, и начинать нужно с 6го, поэтому colors.length - 1
            System.out.println(colors[i]);
        }

        System.out.println();
        //Например, бывают задачи сравнить предыдущий элемент и последующий элемент
        String text1 = "где желает желает знать Каждый Каждый охотник сидят фазаны";
        String[] colors1 = text1.split(",?\\s+");
        for (int i = 0; i < colors1.length; i++) {
            if (i > 0 && colors1[i].equals(colors1[i - 1])) {     //начинаем с 1 элемента [] && текущий элемент [] равен предыдущему
                continue;                                      //пропускаем элемент, если условие вып
            }
            System.out.println(colors1[i]);      //и печатаем элементы [] без повторов слов
        }
        //Бывают более сложные варианты использования индексов [], но их по возможности нужно избегать
        //Частая ошибка: много вложеных конструкций \for\if\. Надо стараться избегать такое
        //Особенно если элементы будут называться \i\a\b\c\x  и тд (когда исп-м два индекса, то еще допустимо),
        //А если уже большой код, то нужно использовать названия \colorIndex например\ чтобы в коде было понятно что такое \i\
        //Длинные вложеные циклы лучше раскрывать, выносить в отдельные методы
        //И делать код проще, короче и понятнее

    }
}
