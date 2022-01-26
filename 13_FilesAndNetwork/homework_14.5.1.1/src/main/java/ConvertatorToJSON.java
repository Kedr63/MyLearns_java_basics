import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ConvertatorToJSON {
    protected static final String STATIONS = "stations";
    protected static final String LINES = "lines";

    public static void writeToJson(UnderGround underGround, String file) throws IOException {
        JSONObject root = new JSONObject();

        JSONObject jsonNumberLine2Stations = getJSONObjectNumberLine2Stations(underGround);

        List<JSONObject> listJSONObjectLines = getCreatedListJSONObjectLines(underGround);

        JSONArray arrayLines = getJSONArrayJSONObjectLines(listJSONObjectLines);



        root.put(STATIONS, jsonNumberLine2Stations);
        root.put(LINES, arrayLines);

        Files.write(Paths.get(file), root.toJSONString().getBytes());
        System.out.println("Json create!");
    }

    private static JSONObject getJSONObjectNumberLine2Stations(UnderGround underGround) {
        JSONObject jsonNumberLine2Stations = new JSONObject();
        underGround.getLineList().stream().
                forEach(el -> jsonNumberLine2Stations.put(el.getNumber(), el.getStations()));
        return jsonNumberLine2Stations;
    }

    private static List<JSONObject> getCreatedListJSONObjectLines(UnderGround underGround) {
        List<JSONObject> listJSONObjectLines = new ArrayList<>();
        List<Line> lineList = underGround.getLineList();

        for (Line line : lineList) {
            JSONObject tmp = new JSONObject();
            tmp.put("number", line.getNumber());
            tmp.put("name", line.getName());
            listJSONObjectLines.add(tmp);
        }
        return listJSONObjectLines;
    }

    private static JSONArray getJSONArrayJSONObjectLines(List<JSONObject> lines) {
        JSONArray arrayLines = new JSONArray();
        for (int i = 0; i < lines.size(); i++) {
            arrayLines.add(i, lines.get(i));
        }
        return arrayLines;
    }
}
