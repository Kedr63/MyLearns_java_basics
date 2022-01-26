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
import java.util.stream.Collectors;

public class Main {
    private static final String URL_SOURCE = "https://www.moscowmap.ru/metro.html#lines";
    private static final String URL = "https://www.moscowmap.ru";
    private static final String PATH_TO_RESOURCES = "/Users/aleksandrshabalin/Skillbox/MyLearns_java_basics/13_FilesAndNetwork/homework_14.5.1.1/src/main/resources/";
    private static final String FILE_JSON = "map.json";
    private static final String FILE_LINES_HTML = "#lines.html";

    private static final String ELEMENT_WITH_ATTR_CONTAINS_INFO_FOR_PARSE_LINES = "span[data-line]";
    private static final String ELEMENT_WITH_ATTR_CONTAINS_INFO_FOR_PARSE_STATIONS = "div[data-line]";

    private static final String ELEMENT_WITH_CLASS_FOR_GO_TO_LINKS_ON_LINES_HTML = "p.t-metrostation-list-headerlink > a";
    //  private static final String CLASS_WITH_LINKS_FOR_GO_ON_STATION_HTML = "div.t-content-wrapper > p >a";
    private static final String ELEMENT_WITH_LINKS_FOR_GO_ON_STATION_HTML = "a[href^=/metro/]"; // value attr starting with "/metro/"

    private static final String ELEMENTS_CONTAINS_NAME_STATIONS_CONNECTIONS = "p:contains(переход)>a:nth-child(1)";
    private static final String ELEMENTS_CONTAINS_NAME_LINE_OF_STATION_CONNECTIONS = "p:contains(переход)>a:nth-child(2)";


   // private static final int randomNumber = 3000 + (int) (Math.random() * 10000);

    public static void main(String[] args) throws IOException, InterruptedException {

        //int randomNumber = 3000+ (int) (Math.random() * 10000);
        // System.out.println(randomNumber);
        // System.exit(0);

        ParseHtml parseHtml = new ParseHtml(URL_SOURCE, PATH_TO_RESOURCES);
       // Document doc = Jsoup.connect(parseHtml.getUrl()).maxBodySize(0).get();
        String htmlFile = parseHtml.parseFile(PATH_TO_RESOURCES + FILE_LINES_HTML);
        Document doc = Jsoup.parse(htmlFile);
        Elements elementsLines = doc.select(ELEMENT_WITH_ATTR_CONTAINS_INFO_FOR_PARSE_LINES);
        Elements elementsStations = doc.select(ELEMENT_WITH_ATTR_CONTAINS_INFO_FOR_PARSE_STATIONS);

        // Пропарсенные данные (станции, линии) сгруппируем по коллекциям
        Map<String, String> line2number = parseHtml.getParseToLine2Number(elementsLines);
        List<Line> lineList = parseHtml.getParseLineList(elementsStations, line2number);

        // в итоге получим объект который будет содержать все пропарсенные данные (станции и линии)
        UnderGround metro = new UnderGround(lineList);


        // 📌 задание со *
        // переходы extract
        Elements linesHtml = doc.select(ELEMENT_WITH_CLASS_FOR_GO_TO_LINKS_ON_LINES_HTML); // get list all lines: line.html (from doc)
        List<String> linksLines = ParseHtml.getListWithAbsLinksLines(linesHtml, URL);

        List<Document> docLinesHtmlList = ParseHtml.getListDocFromEachLineHtml(linksLines);

        List<Elements> listElementsWithLinksStations = ParseHtml
                .getListElementsWithLinksStations(docLinesHtmlList, ELEMENT_WITH_LINKS_FOR_GO_ON_STATION_HTML);

        List<String> urlLinksStations = ParseHtml.getUrlLinksStations(listElementsWithLinksStations, URL);


        List<Connection> listConnections = new ArrayList<>();
        for (String url : urlLinksStations) {
            Document docStation = ParseHtml.getDocFromHtml(url);
            if (docStation.getElementsContainingText("переход на").hasText()) {
                Map<Station, String> stationsOfConnection = new LinkedHashMap<>();
                String searchString = docStation.select("meta[content^=Станция]").attr("content");
                String resultString = searchString
                        .replaceAll("(Станция метро )([А-Яа-я\\s]+)( на карте .+)", "$2");

                String nameLineOfStation = docStation.select("a[href$= linija.html]").text();

                String numberLine = null;
                for (Line line : metro.getLineList()){
                    if(line.getName().contains(nameLineOfStation)){
                        numberLine = line.getNumber();
                        break;
                    }
                }

                /*Station stationsForConnection = null;
                for (Line line : metro.getLineList()){
                    for (Station station :line.getStations()){
                        if (station.getName().contains(resultString) && station.getLocateOnLine().equals(numberLine)){
                            stationsForConnection = station;
                        }
                    }
                }*/
                Station stationsForConnection = getStation(metro, resultString, numberLine);

                stationsOfConnection.put(stationsForConnection, numberLine);

                //extract station
                Elements elementsStation = docStation.select(ELEMENTS_CONTAINS_NAME_STATIONS_CONNECTIONS);
                Elements elementsNameLines = docStation.select(ELEMENTS_CONTAINS_NAME_LINE_OF_STATION_CONNECTIONS);

                List<String> listNumberLine = new ArrayList<>();
                for (Element element : elementsNameLines){
                    String forSearch = element.text().substring(0, 2);
                    for (Line line : metro.getLineList()){
                       if(line.getName().contains(forSearch)){
                           String number = line.getNumber();
                           listNumberLine.add(number);
                           break;
                       }
                    }
                }

                int i = 0;
                for (Element element : elementsStation){
                   Station station = getStation(metro,element.text(), listNumberLine.get(i));
                   stationsOfConnection.put(station, listNumberLine.get(i));
                    i++;
                }

                listConnections.add(new Connection(stationsOfConnection));








                // stringStationsOfConnection.add(resultString);

                /*Elements elementsStation = docStation.select(ELEMENT_CONTAINS_CONNECTIONS_ON_STATIONS);
                elementsStation.eachText().forEach(el ->stringStationsOfConnection.add(el));*/


               /* List<Station> stationsOfConnection = stringStationsOfConnection.stream().map(Station::new).collect(Collectors.toList());
                elementsList.add(new Connection(stationsOfConnection));*/
            }

            // elements.stream().map(e -> e.text().split("<").toString());
        }
        metro.setConnectionList(listConnections);


        ConvertatorToJSON.writeToJson(metro, parseHtml.getPathForCreateJSON() + FILE_JSON);
    }

    public static Station getStation(UnderGround underGround, String nameStation, String number){
        Station stationsForConnection = null;
        for (Line line : underGround.getLineList()){
            for (Station station :line.getStations()){
                if (station.getName().contains(nameStation) && station.getLocateOnLine().contains(number)){
                    stationsForConnection = station;
                }
            }
        }
        return stationsForConnection;
    }

}
