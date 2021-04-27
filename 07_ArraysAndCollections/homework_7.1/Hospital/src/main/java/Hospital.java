import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;

public class Hospital {

    public static float[] generatePatientsTemperatures(int patientsCount) {
        //TODO: напишите метод генерации массива температур пациентов
        float[] floats = new float[patientsCount];
        for (int i = 0; i < floats.length; i++) {
            float generateTemperature = (float) (32 + Math.random() * (40 - 32));

            //округлим до 1 знака после запятой
            BigDecimal result = new BigDecimal(generateTemperature);
            floats[i] = result.setScale(1, RoundingMode.HALF_UP).floatValue();
        }
        return floats;
    }

    public static String getReport(float[] temperatureData) {
        /*
        TODO: Напишите код, который выводит среднюю температуру по больнице,количество здоровых пациентов,
            а также температуры всех пациентов.
        */
        // объявим ~ для счетчика здоровых пациентов
        int countNormalPatients = 0;
        float sumTemperature = 0f;
        for (float temperatureDatum : temperatureData) {
            if (temperatureDatum >= 36.2F && temperatureDatum <= 36.9F) {
                countNormalPatients++;
            }
            sumTemperature = sumTemperature + temperatureDatum;
        }
         // объявим ~ для использования при округлении в методе Math.round (степень дает кол-во после запятой)
        double rounding = Math.pow(10, 2);
        float averageTemperature = (float) (Math.round((sumTemperature / temperatureData.length) * rounding) / rounding);

        //переведем [] в строку и уберем лишние символы
        String temperaturePatients = Arrays.toString(temperatureData).
                replaceAll("[^0-9\\s.]", "");

        String report =
                "Температуры пациентов: " + temperaturePatients +
                        "\nСредняя температура: " + averageTemperature +
                        "\nКоличество здоровых: " + countNormalPatients;

        return report;
    }
}
