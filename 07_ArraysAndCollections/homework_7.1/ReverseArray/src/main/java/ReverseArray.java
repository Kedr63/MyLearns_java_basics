public class ReverseArray {

    //TODO: Напишите код, который меняет порядок расположения элементов внутри массива на обратный.
    public static String[] reverse(String[] strings) {
        String temp;
        for (int i = 0; i < (strings.length - 1) / 2; i++) { //перебираем половину значений [], т.к. во второй половине значения начнут переназначать пройденные
            temp = strings[i];
            strings[i] = strings[(strings.length - 1) - i];
            strings[(strings.length - 1) - i] = temp;
        }
        return strings;
    }
}