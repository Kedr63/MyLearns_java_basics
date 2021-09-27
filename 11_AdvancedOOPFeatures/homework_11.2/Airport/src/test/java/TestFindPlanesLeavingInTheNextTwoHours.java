import static org.junit.jupiter.api.Assertions.assertIterableEquals;

import com.skillbox.airport.Aircraft;
import com.skillbox.airport.Airport;
import com.skillbox.airport.Flight;
import com.skillbox.airport.Flight.Type;
import com.skillbox.airport.Terminal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Поиск рейсов")
public class TestFindPlanesLeavingInTheNextTwoHours {

    private static final List<Flight> expectedFlights = new ArrayList<>();
    private static final int HOUR = 3_600_000;
    private static final int THREE_HOURS = 10_800_000;
    private static final int HOUR_AND_HALF = 5_400_000;
    private static final String[] companyCodes = new String[]{"SU", "AA", "AR", "AF", "B2", "FV"};

    private static String stringify(List<Flight> flights) {
        return "[" + String.join("\n",
                flights.stream()
                        .map(f->String.format("<%s %s %s %s>", f.getType(), f.getCode(), f.getDate(), f.getAircraft()))
                        .toArray(String[]::new)) + "]";
    }

    private static List<Terminal> generateRandomTerminals() {
        List<Terminal> terminals = new ArrayList<>();
        IntStream.range(0, 5).mapToObj(i -> new Terminal(UUID.randomUUID().toString()))
                .forEach(terminal -> {
                    Flight expectedFlight1 = generateDepartureFlight(generateDate(HOUR));
                    Flight expectedFlight2 = generateDepartureFlight(generateDate(HOUR_AND_HALF));
                    terminal.addFlight(expectedFlight1);
                    terminal.addFlight(expectedFlight2);
                    terminal.addFlight(generateDepartureFlight(generateDate(THREE_HOURS)));
                    terminal.addFlight(generateDepartureFlight(generateDate(THREE_HOURS)));
                    terminal.addFlight(generateArrivalFlight(generateDate(HOUR)));
                    terminal.addFlight(generateArrivalFlight(generateDate(HOUR_AND_HALF)));
                    terminal.addFlight(generateArrivalFlight(generateDate(THREE_HOURS)));
                    terminal.addFlight(generateArrivalFlight(generateDate(-THREE_HOURS)));
                    terminal.addFlight(generateDepartureFlight(generateDate(-THREE_HOURS)));

                    expectedFlights.add(expectedFlight1);
                    expectedFlights.add(expectedFlight2);
                    terminals.add(terminal);
                });
        return terminals;
    }

    private static Date generateDate(int hours) {
        return new Date(System.currentTimeMillis() + hours);
    }

    private static Aircraft generateAircraft() {
        return new Aircraft(getRandomString());
    }

    private static Flight generateArrivalFlight(Date date) {
        return new Flight(getRandomString(), Type.ARRIVAL, date, generateAircraft());
    }

    private static Flight generateDepartureFlight(Date date) {
        return new Flight(getRandomString(), Type.DEPARTURE, date, generateAircraft());
    }

    private static String getRandomString() {
        return companyCodes[(int) (Math.random() * companyCodes.length)] + "-" + (int) (Math.random() * 1000 + 1000);
    }

    @Test
    @DisplayName("Поиск рейсов вылетающих в ближайшие два часа")
    void searchFlight() {
        Airport airport = Airport.getInstance();
        List<Terminal> terminals = generateRandomTerminals();
        airport.getTerminals().clear();
        airport.getTerminals().addAll(terminals);

        List<Flight> actualFlight = Main.findPlanesLeavingInTheNextTwoHours(airport);

        assertIterableEquals(expectedFlights, actualFlight,
                String.join(", ", String.format("%nExpected:%s%n%nActual:%s%n%n",
                        stringify(expectedFlights),
                        stringify(actualFlight))
                ));

    }

}
