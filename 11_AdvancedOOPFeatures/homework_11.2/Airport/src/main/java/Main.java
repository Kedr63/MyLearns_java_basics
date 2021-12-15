import com.skillbox.airport.Airport;
import com.skillbox.airport.Flight;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        System.out.println(findPlanesLeavingInTheNextTwoHours(Airport.getInstance()));

    }

    public static List<Flight> findPlanesLeavingInTheNextTwoHours(Airport airport) {
        //TODO Метод должден вернуть список рейсов вылетающих в ближайшие два часа.

        return airport.getTerminals()
                .stream()
                .flatMap(f -> f.getFlights()
                        .stream()
                        .filter(d -> d.getType()
                                .equals(Flight.Type.DEPARTURE))
                        .filter(z -> z.getDate()
                                .getTime() / 1000 > LocalDateTime.now()
                                .toEpochSecond(ZoneOffset.ofHours(3)) && z.getDate()
                                .getTime() / 1000 < LocalDateTime.now()
                                .plusHours(2)
                                .toEpochSecond(ZoneOffset.ofHours(3))))
                .collect(Collectors.toList());
    }
}
