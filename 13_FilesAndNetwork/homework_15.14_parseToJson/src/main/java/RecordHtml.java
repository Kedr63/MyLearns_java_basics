import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;

public class RecordHtml {
    private static final int TIME_DELAY = 8000;
    private static final int TIME_DELAY_DELTA = 8000;
    protected static void writeFileHtml(List<String> linksLines, String urlBase, String path) throws InterruptedException, IOException {
       // write html of lines
        for (String link : linksLines) {
            int randomNumber = TIME_DELAY + (int) (Math.random() * TIME_DELAY_DELTA);
            Thread.sleep(randomNumber);

            String fileName = link.replaceAll("(.+/)(.+\\.html)", "$2");
            URL url = new URL(link);
            InputStream inputStream = url.openStream();

            Path fileHtml = Path.of(path + fileName);
            Files.copy(inputStream, fileHtml, StandardCopyOption.REPLACE_EXISTING);

            String fileLineHtml = ParseHtml.parseFile(path + fileName);
            Document document = Jsoup.parse(fileLineHtml);
            Elements elements = document.select("p:has(span.t-icon-metroln)");

            // write html of stations
            for (Element element : elements) {
                Thread.sleep(randomNumber);

                String stationName = (element.text().replaceAll("(\\d+?\\.)(.+)", "$2")).trim();
                String pathStation = ParseHtml.getUrlStation(stationName, document, urlBase);
                String fileNameForStationLink = pathStation.replaceAll("(.+/)(.+)(/)(.+\\.html)", "$2_$4");

                URL urlFile = new URL(pathStation);
                InputStream inputStream1 = urlFile.openStream();

                Path fileStationHtml = Path.of(path + fileNameForStationLink);
                Files.copy(inputStream1, fileStationHtml, StandardCopyOption.REPLACE_EXISTING);
            }
        }
    }
}
