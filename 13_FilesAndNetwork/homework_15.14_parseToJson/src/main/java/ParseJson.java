import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.List;

public class ParseJson {

    protected static List<Line> getParseJsonLine2Stations(String path) {
        List<Line> lineList = new ArrayList<>();
        try {
            JSONParser parser = new JSONParser();
            JSONObject jsonData = (JSONObject) parser.parse(ParseHtml.parseFile(path));

            JSONArray jsonArrayLines = (JSONArray) jsonData.get("lines");
            parseLines(jsonArrayLines, lineList);

            JSONObject jsonObjectStations = (JSONObject) jsonData.get("stations");

            parseStations(jsonObjectStations, lineList);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return lineList;
    }

    private static void parseLines(JSONArray jsonArray, List<Line> lineList) {
        jsonArray.forEach(lineObject -> {
            JSONObject jsonObject = (JSONObject) lineObject;
            lineList.add(new Line((String) jsonObject.get("name"), (String) jsonObject.get("number")));
        });
    }

    private static void parseStations(JSONObject jsonObject, List<Line> lineList) {
        jsonObject.keySet().forEach(lineNumberObject -> {
            String lineNumber = (String) lineNumberObject;
            JSONArray arrayStations = (JSONArray) jsonObject.get(lineNumber);
            List<Station> stationList = new ArrayList<>();

            arrayStations.forEach(stationObject -> {
                Station station = new Station((String) stationObject, lineNumber);
                stationList.add(station);
                lineList.forEach(line -> {
                    if (line.getNumber().equals(lineNumber))
                        line.setStations(stationList);
                });
            });
        });
    }
}

