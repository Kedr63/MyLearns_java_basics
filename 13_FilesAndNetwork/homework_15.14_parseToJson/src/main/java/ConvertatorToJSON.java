import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ConvertatorToJSON {
    protected static final String STATIONS = "stations";
    protected static final String LINES = "lines";
    protected static final String CONNECTIONS = "connections";

    public static void writeToJson(UnderGround underGround, String file) throws IOException {
        JSONObject root = new JSONObject();

        JSONObject jsonNumberLine2Stations = getJSONObjectNumberLine2Stations(underGround);

        List<JSONObject> listJSONObjectLines = getCreatedListJSONObjectLines(underGround);
        JSONArray arrayLines = getJSONArrayOfJsonObject(listJSONObjectLines);

        List<JSONArray> listJsonArrayNodes = getCreatedListJSONObjectNodes(underGround);
        JSONArray arrayNodes = getJSONArrayOfJsonArray(listJsonArrayNodes);


        root.put(STATIONS, jsonNumberLine2Stations);
        root.put(LINES, arrayLines);
        root.put(CONNECTIONS, arrayNodes);

        Files.write(Paths.get(file), root.toJSONString().getBytes());
        System.out.println("Json create!");
    }

    private static JSONObject getJSONObjectNumberLine2Stations(UnderGround underGround) {
        JSONObject jsonNumberLine2Stations = new JSONObject();
        underGround.getLineList()
                .forEach(line -> jsonNumberLine2Stations.put(line.getNumber(), line.getStations()));
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

    private static JSONArray getJSONArrayOfJsonObject(List<JSONObject> lines) {
        JSONArray arrayLines = new JSONArray();
        for (int i = 0; i < lines.size(); i++) {
            arrayLines.add(i, lines.get(i));
        }
        return arrayLines;
    }

    private static List<JSONArray> getCreatedListJSONObjectNodes(UnderGround underGround) {
        List<JSONArray> listJsonArrayNodes = new ArrayList<>();
        List<Node> nodeListList = underGround.getNodeList();

        for (Node node : nodeListList) {
            JSONArray jsonArray = new JSONArray();

            Set<Station> stationSet = node.getNodeConnections();
            int i = 0;
            for (Station station : stationSet){
                JSONObject tmp = new JSONObject();
                tmp.put("station", station.getName());
                tmp.put("line", station.getLocateOnLine());
                jsonArray.add(i, tmp);
                i++;
            }
            listJsonArrayNodes.add(jsonArray);
        }
        return listJsonArrayNodes;
    }

    private static JSONArray getJSONArrayOfJsonArray(List<JSONArray> nodesArray) {
        JSONArray arrayLines = new JSONArray();
        for (int i = 0; i < nodesArray.size(); i++) {
            arrayLines.add(i, nodesArray.get(i));
        }
        return arrayLines;
    }
}
