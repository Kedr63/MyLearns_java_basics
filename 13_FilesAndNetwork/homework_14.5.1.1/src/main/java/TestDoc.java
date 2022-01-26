import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TestDoc {
    private static final String ELEMENT_CONTAINS_CONNECTIONS_ON_STATIONS = "p:contains(переход) > a:first-child";
    private static final String ELEMENT_CONTAINS_CONNECTIONS_ON_STATIONS2 = "p:contains(переход) > a:last-child";

    public static void main(String[] args) throws IOException {

        List<Connection> elementsList = new ArrayList<>();
       String htmlFile = parseFile("13_FilesAndNetwork/homework_14.5.1.1/src/main/resources/bulwar.html");
       String htmlFile2 = parseFile("13_FilesAndNetwork/homework_14.5.1.1/src/main/resources/bibliot.html");
       Document doc = Jsoup.parse(htmlFile2);

        List<String> stringStationsOfConnection = new ArrayList<>();
        String searchString = doc.select("meta[content^=Станция]").attr("content");
        String resultString = searchString.replaceAll("(Станция метро )([А-Яа-я\\s]+)( на карте .+)", "$2");
        stringStationsOfConnection.add(resultString);


        Elements elementsStation = doc.select(ELEMENT_CONTAINS_CONNECTIONS_ON_STATIONS);
        elementsStation.eachText().forEach(el ->stringStationsOfConnection.add(el));



       // List<Station> stationsOfConnection = stringStationsOfConnection.stream().map(st -> new Station(st, )).collect(Collectors.toList());
        //elementsList.add(new Connection(stationsOfConnection));

    }
    public static String parseFile(String path){
        StringBuilder builder = new StringBuilder();
        try {
            List<String> lines = Files.readAllLines(Paths.get(path));
            lines.forEach(line -> builder.append(line + "\n"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return builder.toString();
    }
}
