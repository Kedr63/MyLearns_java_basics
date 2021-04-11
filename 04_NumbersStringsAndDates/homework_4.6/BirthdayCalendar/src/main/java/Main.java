import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Main {

    public static void main(String[] args) {

        int day = 31;
        int month = 12;
        int year = 1990;

        System.out.println(collectBirthdays(year, month, day));

    }

    public static String collectBirthdays(int year, int month, int day) {

        //TODO реализуйте метод для построения строки в следующем виде
        //0 - 31.12.1990 - Mon
        //1 - 31.12.1991 - Tue
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, day);     //так как месяцы в Calendar начинаются с 0, то отнимем 1
        DateFormat dateFormat = new SimpleDateFormat(" - dd.MM.yyyy - EE", Locale.ENGLISH);
        Date dateNow = new Date();
        StringBuilder builder = new StringBuilder();
        String dataBirthday = "";
        for (int i = 0; !calendar.getTime().after(dateNow); i++) {
            builder.append(i).append(dateFormat.format(calendar.getTime())).append(System.lineSeparator());
            dataBirthday = builder.toString();
            calendar.add(Calendar.YEAR, 1);
        }
        return dataBirthday;
    }
}
