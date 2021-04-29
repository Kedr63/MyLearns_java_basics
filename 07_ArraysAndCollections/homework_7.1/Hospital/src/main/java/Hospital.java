import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;

public class Hospital {
    public static final int MIN_TEMPERATURE = 32;
    public static final int MAX_TEMPERATURE = 42;
    public static final float LOW_NORM_TEMPERATURE = 36.2F;
    public static final float UP_NORM_TEMPERATURE = 36.9F;

    public static float[] generatePatientsTemperatures(int patientsCount) {
        //TODO: напишите метод генерации массива температур пациентов
        float[] floats = new float[patientsCount];
        for (int i = 0; i < floats.length; i++) {
            float generateTemperature = (float) (MIN_TEMPERATURE + Math.random() * (MAX_TEMPERATURE - MIN_TEMPERATURE));

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
            if (temperatureDatum >= LOW_NORM_TEMPERATURE && temperatureDatum <= UP_NORM_TEMPERATURE) {
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
