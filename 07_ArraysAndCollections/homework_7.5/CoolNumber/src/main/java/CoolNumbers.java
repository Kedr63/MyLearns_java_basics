import java.util.*;

public class CoolNumbers {

    public static List<String> generateCoolNumbers() {
        List<String> listCoolNumbers = new ArrayList<>();
        String allowChars = "АВЕКМНОРСТУХ";        //допустимые символы в номере
        for (int i = 0; i < 2_000_000; i++) {
            Random random = new Random();

            // выберем случайное число из диапазона длины строки allowChars
            int index = random.nextInt(allowChars.length());
            // и из allowChars выберем символ с помощью этого числа
            String randomChar1 = allowChars.substring(index, index + 1);

            int index1 = random.nextInt(allowChars.length());
            String randomChar2 = allowChars.substring(index1, index1 + 1);

            int index2 = random.nextInt(allowChars.length());
            String randomChar3 = allowChars.substring(index2, index2 + 1);

            // сгенерируем случайное число (число сделается trim с двух сторон до индекса 2)
            String number = Double.toString(Math.random()).substring(2, 3);

             // установим псевдослучайный диапазон [1-199]
            int numberReg = 1 + (int) (Math.random() * 199);
            String region = String.valueOf(numberReg);
            if (numberReg < 10) {
                region = "0" + region;
            }

            String numberGenerate = String.format("%s%s%s%s%s%s%s", randomChar1, number, number, number,
                    randomChar2, randomChar3, region);
            listCoolNumbers.add(numberGenerate);
        }
        return listCoolNumbers;
    }

    public static boolean bruteForceSearchInList(List<String> list, String number) {
        for (String s : list) {
            if (s.equals(number)) {
                return true;
            }
        }
        return false;
    }

    public static boolean binarySearchInList(List<String> sortedList, String number) {
        int index = Collections.binarySearch(sortedList, number);
        return index >= 0;  // если true - то найден номер в коллекции
    }

    public static boolean searchInHashSet(HashSet<String> hashSet, String number) {
        return hashSet.contains(number);  // если true - то найден номер в коллекции
    }

    public static boolean searchInTreeSet(TreeSet<String> treeSet, String number) {
        return treeSet.contains(number);   // если true - то найден номер в коллекции
    }
}
