import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Test1 {
    private static final String PATH_FOR_DOWNLOADS = "/Users/aleksandrshabalin/Skillbox/MyLearns_java_basics/13_FilesAndNetwork/homework_14.5.1.1/src/main/resources/";
    private static final String FILE_JSON = "map.json";

    public static void main(String[] args) throws IOException, ParseException {
        // convert Java to json
        JSONObject root = new JSONObject();
        root.put("message", "Hi");
        JSONObject place = new JSONObject();
        place.put("name", "World!");
        root.put("place", place);
        root.put("1", "proba");

        Map<String, String> map = new LinkedHashMap<>();
        map.put("20", "два ноль");
        map.put("21", "два один");

        root.putAll(map);

        //String[] arrayStr = {"q", "f", "i"};
        JSONArray jsonArray = new JSONArray();
        jsonArray.add(0, "0");
        jsonArray.add(1, "1");



        JSONObject inArray = new JSONObject();
        inArray.put("line", 2);
        inArray.put("station", "Невский проспект");
        JSONObject inArray1 = new JSONObject();
        inArray1.put("line", 3);
        inArray1.put("station", "Гостиный двор");


        jsonArray.add(2, inArray);
        jsonArray.add(3, inArray1);

        root.put("массив", jsonArray);





       /* String json = root.toString();
        System.out.println(json);*/
       Files.write(Paths.get(PATH_FOR_DOWNLOADS + FILE_JSON), root.toJSONString().getBytes());

        JSONParser parser = new JSONParser();
        JSONObject jsonData = (JSONObject)parser.parse(getJsonFile());
        String result = (String) jsonData.get("массив");
        System.out.println(result);


       /* System.out.println();
        // convert json to Java
        JSONObject jsonObject = new JSONObject(json);
        String message = jsonObject.getString("message");
        String name = jsonObject.getJSONObject("place").getString("name");
        System.out.println(message + " " + name);*/
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
