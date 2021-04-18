public class Main {
    public static void main(String[] args) {

        float[] temperatureData = Hospital.generatePatientsTemperatures(0);
        System.out.println(Hospital.getReport(temperatureData));

        //Пример вывода в консоль:
        //Температуры пациентов: 36.7 38.9 34.7
        //Средняя температура: 36.76
        //Количество здоровых: 1

        //Округлите среднюю температуру с помощью Math.round до 2 знаков после запятой,
        //а температуры каждого пациента до 1 знака после запятой
    }
}
