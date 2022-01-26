import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class ReaderJson {
    private static final String PATH_TO_FILE_JSON = "13_FilesAndNetwork/homework_14.5.1.1/src/main/resources/map.json";

    public static void main(String[] args) throws ParseException {
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject)parser.parse(getJsonFile(PATH_TO_FILE_JSON));
       //  JSONObject jsonLines = (Map)jsonObject.get(ConvertatorToJSON.STATIONS);
        Map<String, String[]> map = (Map)jsonObject.get(ConvertatorToJSON.STATIONS);
        for (Map.Entry<String, String[]> entry : map.entrySet()){
            System.out.println("Номер линии: " + entry.getKey() + " - количество станций: " + entry.getValue().length);
        }

        /* for (Object entry: jsonLines.entrySet()){
             System.out.println(entry.toString());
         }*/


        System.out.println("ok");



    }

    private static String getJsonFile(String path){
        StringBuilder builder = new StringBuilder();

        try {
            List<String> lines = Files.readAllLines(Paths.get(path));
           lines.forEach(line -> builder.append(line));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }
}
