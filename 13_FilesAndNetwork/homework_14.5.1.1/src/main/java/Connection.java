import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Connection {
    private Map<Station, String> stationsOfConnection;


    public Connection(Map<Station, String> stationMap) {
        this.stationsOfConnection = new LinkedHashMap<>(stationMap);
    }

    public Map<Station, String> getStationsOfConnection() {
        return stationsOfConnection;
    }

    public void setStationsOfConnection(Map<Station, String> stationsOfConnection) {
        this.stationsOfConnection = stationsOfConnection;
    }
}
