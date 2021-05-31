public class Main {
    public static void main(String[] args) {
        boolean[] array = new boolean[5];  //
        System.out.println(array[0]);    //у массива примитива boolean значение по умолчанию /false/
        String[] array1 = new String[5];
        System.out.println(array1[0]);   //у массива объектов String значение по умолчанию /null/

        //Можно создавать массивы массивов
        //Создадим массив чисел, допустим есть школа, в ней классы и ученики, и каждого есть оценки
        //Длина этого массива будет 4
        int[][] studentMarks = {
                {4, 5, 2, 1, 3},        // в этом массиве класс с оценками учеников
                {5, 5, 4, 5, 4, 4, 3},
                {2, 4, 5, 1, 5, 5, 2, 3},
                {5, 3, 2, 1}
        };
        for (int i = 0; i < studentMarks.length; i++) {           //здесь ходим по классам (4шт.)
            System.out.println("Class " + i + " (" +
                    studentMarks[i].length + " students): ");
            for (int j = 0; j < studentMarks[i].length; j++) {     //здесь ходим по оценкам
                System.out.println("\t" + studentMarks[i][j]);
            }
        }
        /*Class 0 (5 students):
        4
        5
        2
        1
        3
        Class 1 (7 students):
        5
        5
        4
        5
        4
        4
        3
        Class 2 (8 students):
        2
        4
        5
        1
        5
        5
        2
        3
        Class 3 (4 students):
        5
        3
        2
        1 */

    }
}
