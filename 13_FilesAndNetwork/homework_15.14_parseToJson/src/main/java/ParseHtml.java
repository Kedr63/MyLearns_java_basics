import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class ParseHtml {
    private String url;
    private String pathForCreateJSON;

    public ParseHtml(String url, String pathForCreateDirectory) {
        this.pathForCreateJSON = pathForCreateDirectory;
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPathForCreateJSON() {
        return pathForCreateJSON;
    }

    public void setPathForCreateJSON(String pathForCreateJSON) {
        this.pathForCreateJSON = pathForCreateJSON;
    }

    // будем стараться походить на человека при получении /Document/ с сайта
    protected static Document getDocFromHtml(String url) throws IOException, InterruptedException {
        int randomNumber = 5000 + (int) (Math.random() * 3000);
        Thread.sleep(randomNumber);
        UserAgents userAgent = new UserAgents();
        return Jsoup.connect(url)
                .userAgent(userAgent.getName())
                .ignoreHttpErrors(true)
                .followRedirects(true)
                .timeout(100000)
                .ignoreContentType(true)
                .maxBodySize(0)
                .get();
    }

    protected Map<String, String> getParseToLine2Number(Elements elementsLines){
        Map<String, String> line2number = new LinkedHashMap<>(); // номер линии - имя линии
        for (Element element : elementsLines) {
            String name = element.text().trim();
            String number = element.attr("data-line").trim();
            line2number.put(number, name);
        }
        return  line2number;
    }

    protected List<Line> getParseLineList(Elements elementsStations, Map<String, String> line2number){
        List<Line> lineList = new ArrayList<>();
        for (Element element : elementsStations) {
            String lineNumber = element.attr("data-line").trim();
            String[] stations = element.text().substring(2).split("\\d+\\.\\s+");
            List<Station> stationList = Arrays.stream(stations)
                    .map(station -> new Station(station.trim(), lineNumber))
                    .collect(Collectors.toList());

            String nameLine = line2number.get(lineNumber);
            Line line = new Line(nameLine, lineNumber);
            line.setStations(stationList);
            lineList.add(line);
        }
        return lineList;
    }


    protected static List<String> getListWithAbsLinksLines(Elements elements, String url){
        return elements.stream()
                .map(line -> url + line.attr("href"))
                .collect(Collectors.toList());
    }

    // этот метод используется если бы парсили через интернет
    protected static List<Document> getListDocFromEachLineHtml(List<String> linksLines) throws IOException, InterruptedException {
        List<Document> docLinesHtmlList = new ArrayList<>(); // get list doc (from each line.html)
        for (String links : linksLines) {
            docLinesHtmlList.add(getDocFromHtml(links));
        }
        return docLinesHtmlList;
    }

    // этот метод используется если бы парсили через интернет
    protected  static List<Elements> getListElementsWithLinksStations(List<Document> docList, String cssQuery){
        List<Elements> listElementsWithLinksStations = new ArrayList<>();
        for (Document docLineHtml : docList) {
            listElementsWithLinksStations.add(docLineHtml.select(cssQuery));
            for (Elements elements : listElementsWithLinksStations){
                elements.forEach(els -> {
                    els.attr("href");
                });
            }
        } return listElementsWithLinksStations;
    }

    // этот метод используется если бы парсили через интернет
    protected static List<String> getUrlLinksStations(List<Elements> elementsList, String url){
        List<String> urlLinksStations = new ArrayList<>();
        for (Elements elements : elementsList) {
            for (Element element : elements) {
                urlLinksStations.add(url + element.attr("href"));
            }
        } return urlLinksStations;
    }

    protected static String parseFile(String path) {
        StringBuilder builder = new StringBuilder();
        try {
            List<String> lines = Files.readAllLines(Paths.get(path));
            lines.forEach(line -> builder.append(line + "\n"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return builder.toString();
    }

    protected static String getUrlStation(String stationName, Document document, String url) {

        Elements elementsForExtractHref = document.select("a[href^=/metro/]>span:contains(" + stationName.trim() + ")");
        return url + elementsForExtractHref.parents().attr("href");
    }

    protected static List<Document> getListDocEachLineHtmlFromDirectory(List<String> linksLines, String directory) {
        List<Document> docLinesHtmlList = new ArrayList<>(); // get list doc (from each line.html)
        for (String links : linksLines) {
            docLinesHtmlList.add(getDocFromDirectoryWithLinesHtml(links, directory));
        }
        return docLinesHtmlList;
    }

    protected static Document getDocFromDirectoryWithLinesHtml(String url, String pathDirectory) {
        String nameFile = url.replaceAll("(.+/)(.+\\.html)", "$2");
        String file = pathDirectory + nameFile;
        String fileHtml = parseFile(file);
        return Jsoup.parse(fileHtml);
    }

    protected static Document getDocFromDirectoryWithStationHtml(String url, String pathDirectory) {
        String nameFile = url.replaceAll("(.+/)(.+)(/)(.+\\.html)", "$2_$4");
        String file = pathDirectory + nameFile;
        String fileHtml = parseFile(file);
        return Jsoup.parse(fileHtml);
    }
}

