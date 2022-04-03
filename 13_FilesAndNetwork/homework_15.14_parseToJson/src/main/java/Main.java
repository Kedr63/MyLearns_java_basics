import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.*;

public class Main {
    private static final String URL_SOURCE = "https://www.moscowmap.ru/metro.html#lines";
    private static final String URL = "https://www.moscowmap.ru";
    private static final String PATH_TO_RESOURCES = "src/main/resources/";
    private static final String PATH_TO_RESOURCES_HTML = "src/main/resources/filesHtml/";
    private static final String FILE_JSON = "map.json";

    private static final String ELEMENT_WITH_ATTR_CONTAINS_INFO_FOR_PARSE_LINES = "span[data-line]"; // в скобках имя аттрибута
    private static final String ELEMENT_WITH_ATTR_CONTAINS_INFO_FOR_PARSE_STATIONS = "div[data-line]";
    private static final String ELEMENT_WITH_CLASS_FOR_GO_TO_LINKS_ON_LINES_HTML = "p.t-metrostation-list-headerlink > a";
    private static final String ELEMENT_CONTAINS_NAME_ROOT_STATION_WITH_CONNECTIONS_ON_SUB_STATIONS = "p:has(span.t-icon-metroln)";
    private static final String ELEMENT_CONTAINS_NAMES_SUB_STATIONS_OF_ROOT_STATION = "p:matches(Переход на станцию)";


    public static void main(String[] args) throws IOException, InterruptedException {

        ParseHtml parseHtml = new ParseHtml(URL_SOURCE, PATH_TO_RESOURCES);
        Document doc = ParseHtml.getDocFromHtml(parseHtml.getUrl());

        Elements elementsLines = doc.select(ELEMENT_WITH_ATTR_CONTAINS_INFO_FOR_PARSE_LINES);
        Elements elementsStations = doc.select(ELEMENT_WITH_ATTR_CONTAINS_INFO_FOR_PARSE_STATIONS);

        // Пропарсенные данные (станции, линии) сгруппируем по коллекциям
        Map<String, String> line2number = parseHtml.getParseToLine2Number(elementsLines);
        List<Line> lineList = parseHtml.getParseLineList(elementsStations, line2number);

        // в итоге получим объект который будет содержать все пропарсенные данные (станции и линии)
        UnderGround metro = new UnderGround();
        metro.setLineList(lineList);

        //_______________________________________________
        // 📌 задание со *
        // extract connections
        Elements linesHtml = doc.select(ELEMENT_WITH_CLASS_FOR_GO_TO_LINKS_ON_LINES_HTML); // get list all lines: line.html (from doc)
        List<String> linksLines = ParseHtml.getListWithAbsLinksLines(linesHtml, URL);

        // запишем все линии и станции, имеющие переходы на другие линии в файлы html
        // (при работе с сайтом через интернет часто была ошибка 503)
        /*       RecordHtml.writeFileHtml(linksLines, URL, PATH_TO_RESOURCES_HTML);      */
        // После того как запишем /html/ файлы, закомментируем строку выше ⬆ и будем работать с
        // записанными файлами /html/ из директории (src/main/resources/filesHtml)

        List<Document> docLinesHtmlList = ParseHtml.getListDocEachLineHtmlFromDirectory(linksLines, PATH_TO_RESOURCES_HTML);

        Set<Node> allNode = new HashSet<>();
        List<Node> uniqueNodeList = new ArrayList<>(); // лист с неповторяющимися узлами

        for (Document docLinesHtml : docLinesHtmlList) {
            String numberLine = getNumberLineByDocumentLineHtml(docLinesHtml, metro);

            // get extract from stations with connections on lines
            Elements elementsStationsWithConnections =
                    docLinesHtml.select(ELEMENT_CONTAINS_NAME_ROOT_STATION_WITH_CONNECTIONS_ON_SUB_STATIONS);

            // get root station
            for (Element element : elementsStationsWithConnections) {
                String stationName = (element.text()
                        .replaceAll("(\\d+?\\.)(.+)", "$2")).trim(); // "12. Лубянка" -> "Лубянка"
                Station stationRoot = getStation(metro, stationName, numberLine);
                Set<Station> stationSet = new TreeSet<>();
                stationSet.add(stationRoot);

                String linkStation = URL + element.getElementsByAttribute("href").attr("href");
                Document docStation = ParseHtml.getDocFromDirectoryWithStationHtml(linkStation, PATH_TO_RESOURCES_HTML);
                Elements elementsForExtractSubStation = docStation.select(ELEMENT_CONTAINS_NAMES_SUB_STATIONS_OF_ROOT_STATION);

                // get sub stations (connection root - sub)
                for (Element el : elementsForExtractSubStation) {
                    String nameSubStation = el.child(0).getElementsByAttribute("href").text();
                    String urlLineSubStation = URL + el.child(1).getElementsByAttribute("href").attr("href");

                    Document docLine = ParseHtml.getDocFromDirectoryWithLinesHtml(urlLineSubStation, PATH_TO_RESOURCES_HTML);
                    String numberLineSubStation = getNumberLineByDocumentLineHtml(docLine, metro);
                    Station subStation = getStation(metro, nameSubStation, numberLineSubStation);

                    stationSet.add(subStation);
                }

                Node nodeStation = new Node(stationSet);
                allNode.add(nodeStation);

                if (!containsNodeInList(uniqueNodeList, nodeStation)) {
                    uniqueNodeList.add(nodeStation);
                }
            }
        }
        metro.setNodeList(uniqueNodeList);
        System.out.println("Количество переходов в метро: " + metro.getNodeList().size());

        ConvertatorToJSON.writeToJson(metro, parseHtml.getPathForCreateJSON() + FILE_JSON);

        // Read Json file
        UnderGround underGround = new UnderGround();
        List<Line> lineListUnderGround = ParseJson.getParseJsonLine2Stations(PATH_TO_RESOURCES + FILE_JSON);
        underGround.setLineList(lineListUnderGround);

        underGround.getLineList().forEach(line -> {
            System.out.printf("\nКоличество станций на линии:\t%-32s - %-2d станций", line.getName(), line.getStations().size());
        });


    }

    public static Station getStation(UnderGround underGround, String nameStation, String numberLine) {
        Station stationForReturn = null;
        for (Line line : underGround.getLineList()) {
            for (Station station : line.getStations()) {
                if (station.getName().contains(nameStation) && station.getLocateOnLine().equals(numberLine)) {
                    stationForReturn = station;
                }
            }
        }
        return stationForReturn;
    }

    public static String getNumberLineByDocumentLineHtml(Document docLinesHtml, UnderGround underGround) {
        String lineName = docLinesHtml.select("span.item").text();
        String numberLine = "";
        for (Line line : underGround.getLineList()) {
            // так как линия на сайте может быть в сокращенном виде, то добавим проверку ее аббревиатуры
            // и третьим условием: аббревиатура без первой буквы (встречается на сайте сокращенная аббревиатура)
            if (line.getName().contains(lineName)
                    || getAbbreviationOfLineName(line.getName()).contains(lineName)
                    || getAbbreviationOfLineName(line.getName()).contains(lineName.substring(1))) {
                numberLine = line.getNumber();
                break;
            }
        }
        return numberLine;
    }

    public static String getAbbreviationOfLineName(String name) {
        StringBuilder builder = new StringBuilder();
        char[] chars = name.toCharArray();
        for (Character ch : chars) {
            if (ch.toString().matches("[А-Я]")) {
                builder.append(ch);
            }
        }
        return builder.toString();
    }

    public static boolean containsNodeInList(List<Node> nodeList, Node nodeStation) {
        for (Node node : nodeList) {
            if (node.compareTo(nodeStation) == 0) {
                return true;
            }
        }
        return false;
    }
}
