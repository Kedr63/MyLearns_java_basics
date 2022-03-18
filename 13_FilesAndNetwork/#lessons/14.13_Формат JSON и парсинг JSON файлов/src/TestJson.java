import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class TestJson {
    private static final String PATH_FOR_DOWNLOADS = "13_FilesAndNetwork/#lessons/14.13_Формат JSON и парсинг JSON файлов/src/";
    private static final String FILE_JSON = "map.json";

    public static void main(String[] args) throws IOException, ParseException {
        // convert Java to json
        JSONObject root = new JSONObject();
        root.put("root1", "ROOT1");

        JSONObject place = new JSONObject();
        place.put("place1", "PLACE1");

        root.put("root2", place);
        root.put("root3", "ROOT3");

        Map<String, String> map = new LinkedHashMap<>();
        map.put("keyMap1", "valueMap1");
        map.put("keyMap2", "valueMap2");

        root.putAll(map);


        JSONArray jsonArray = new JSONArray();
        jsonArray.add(0, "array1_value");
        jsonArray.add(1, "array2_value");


        JSONObject inArray = new JSONObject();
        inArray.put("line", 2);
        inArray.put("station", "Невский проспект");
        JSONObject inArray1 = new JSONObject();
        inArray1.put("line", 3);
        inArray1.put("station", "Гостиный двор");


        jsonArray.add(2, inArray);
        jsonArray.add(3, inArray1);

        root.put("массив", jsonArray);


        Files.write(Paths.get(PATH_FOR_DOWNLOADS + FILE_JSON), root.toJSONString().getBytes());

        // Из JSON to Java
        JSONParser parser = new JSONParser();
        JSONObject jsonData = (JSONObject) parser.parse(getJsonFile());
        JSONArray result = (JSONArray) jsonData.get("массив");

        List<String> stringList = new ArrayList<>();

        String s = (String) result.get(0);
        String s1 = (String) result.get(1);
        stringList.add(s);
        stringList.add(s1);

        System.out.println(stringList);

    }

    private static String getJsonFile() {
        StringBuilder builder = new StringBuilder();
        try {
            List<String> lines = Files.readAllLines(Paths.get(PATH_FOR_DOWNLOADS + FILE_JSON));
            lines.forEach(line -> builder.append(line));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return builder.toString();
    }
}
