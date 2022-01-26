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

    protected static Document getDocFromHtml(String url) throws IOException, InterruptedException {
        Thread.sleep(5000);
        return Jsoup.connect(url)
                .userAgent("Mozilla / 5.0 (Macintosh; Intel Mac OS X 10.14; rv: 75.0) Gecko / 20100101 Firefox / 75.0 — на MacOS")
                //Mozilla / 5.0 (Macintosh; Intel Mac OS X 10_15_4) AppleWebKit / 605.1.15 (KHTML, как Gecko) Версия / 13.1 Safari / 605.1.15 — на MacOS
                // Mozilla / 5.0 (Macintosh; Intel Mac OS X 10.14; rv: 75.0) Gecko / 20100101 Firefox / 75.0 — на MacOS
                .ignoreHttpErrors(true).followRedirects(true).timeout(100000)
                .ignoreContentType(true).maxBodySize(0).get();
    }

    protected Map<String, String> getParseToLine2Number(Elements elementsLines){
        Map<String, String> line2number = new LinkedHashMap<>(); // номер линии - имя линии
        for (Element element : elementsLines) {
            String name = element.text();
            String number = element.attr("data-line");
            line2number.put(number, name);
        }
        return  line2number;
    }

    protected List<Line> getParseLineList(Elements elementsStations, Map<String, String> line2number){
        List<Line> lineList = new ArrayList<>();
        for (Element element : elementsStations) {
            String lineNumber = element.attr("data-line");
            String[] stations = (element.text()).substring(2).trim().split("\\d+\\.\\s+");
            List<Station> stationList = Arrays.stream(stations)
                    .map(station -> new Station(station, lineNumber)).collect(Collectors.toList());

            String nameLine = line2number.get(lineNumber);
            Line line = new Line(nameLine, lineNumber);
            line.setStations(stationList);
            lineList.add(line);
        }
        return lineList;
    }

    protected List<Connection> getParseConnectionsList(Elements elements){

        return null;
    }

    protected static List<String> getListWithAbsLinksLines(Elements elements, String url){
        return elements.stream()
                .map(line -> url + line.attr("href"))
                .collect(Collectors.toList());
    }

    protected static List<Document> getListDocFromEachLineHtml(List<String> linksLines) throws IOException, InterruptedException {
        List<Document> docLinesHtmlList = new ArrayList<>(); // get list doc (from each line.html)
        for (String links : linksLines) {
            docLinesHtmlList.add(getDocFromHtml(links));
        }
        return docLinesHtmlList;
    }

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

    protected static List<String> getUrlLinksStations(List<Elements> elementsList, String url){
        List<String> urlLinksStations = new ArrayList<>();
        for (Elements elements : elementsList) {
            for (Element element : elements) {
                urlLinksStations.add(url + element.attr("href"));
            }
        } return urlLinksStations;
    }

    protected String parseFile(String path){
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

