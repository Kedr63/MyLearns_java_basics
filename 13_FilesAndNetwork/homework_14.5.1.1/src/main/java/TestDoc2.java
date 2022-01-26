import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class TestDoc2 {
    private static final String ELEMENT_CONTAINS_DATA_ON_STATIONS_OF_SAME_LINE = "div.t-metrostation-list-table";
    private static final String ELEMENT_CONTAINS_CONNECTIONS_ON_STATIONS2 = "p:contains(переход) > a:last-child";

    public static void main(String[] args) throws IOException {

        List<Connection> elementsList = new ArrayList<>();
        String htmlFile = parseFile("/Users/aleksandrshabalin/Skillbox/MyLearns_java_basics/13_FilesAndNetwork/homework_14.5.1.1/src/main/resources/#lines.html");
        Document doc = Jsoup.parse(htmlFile);

        Elements elementsStationsOfLine = doc.select(ELEMENT_CONTAINS_DATA_ON_STATIONS_OF_SAME_LINE);
        Map<Station, String> station2number = new LinkedHashMap<>();
        String numberLine;
        for (Element element : elementsStationsOfLine) {
            numberLine = element.getAllElements().attr("data-line");
            Elements elements = element.getElementsByAttributeValueContaining("span", "before");
            List<String> stationsList = element.getElementsByTag("p").eachText();
            /*stationsList.forEach(st -> {station2number
                    .put(new Station(st.replaceAll("(\\d+?\\.)([А-Яа-я\\-\\s]+)", "$2")), numberLine);
            });*/
        }
       // new Line()

    }

    public static String parseFile(String path) {
        StringBuilder builder = new StringBuilder();
        try {
            List<String> lines = Files.readAllLines(Paths.get(path));
            lines.forEach(line -> builder.append(line + "\n"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return builder.toString();
    }

    // private
}
