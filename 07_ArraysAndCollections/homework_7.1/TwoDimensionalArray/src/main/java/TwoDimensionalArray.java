public class TwoDimensionalArray {
    public static char symbol = 'X';

    public static char[][] getTwoDimensionalArray(int size) {

        //TODO: Написать метод, который создаст двумерный массив char заданного размера.
        // массив должен содержать символ symbol по диагоналям, пример для size = 3
        // [X,  , X]
        // [ , X,  ]
        // [X,  , X]
        char[][] arrayArray = new char[size][size];
        int x = 0;
        char space = ' ';
        for (int i = 0; i < arrayArray.length; i++) {
            for (int j = 0; j < arrayArray[i].length; j++) {
                if (j == x) {
                    arrayArray[i][j] = symbol;
                } else if (j == ((arrayArray[i].length - 1) - x)) {
                    arrayArray[i][j] = symbol;
                } else arrayArray[i][j] = space;
            }
            x++;
        }
        return arrayArray;
    }
}
