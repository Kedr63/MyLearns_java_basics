import com.skillbox.airport.Airport;

public class MainAir {
    public static void main(String[] args) {
        Airport airport = Airport.getInstance();
        int countAircraft = airport.getAllAircrafts().size();
        System.out.println("Список самолетов: " + airport.getAllAircrafts());
        System.out.println("Количество самолетов: " + countAircraft);
    }
}
