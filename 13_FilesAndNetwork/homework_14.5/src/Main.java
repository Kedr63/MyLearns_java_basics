
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Main {
    private static final String URL_SOURCE = "https://www.moscowmap.ru/metro.html#lines";
    private static final String PATH_FOR_DOWNLOADS = "/Users/aleksandrshabalin/Skillbox/MyLearns_java_basics/13_FilesAndNetwork/homework_14.5/downloads";

    public static void main(String[] args) throws IOException {

        ParseHtml parseHtml = new ParseHtml(URL_SOURCE, PATH_FOR_DOWNLOADS);
        Document doc = Jsoup.connect(parseHtml.getUrl()).get();
        Elements elements = doc.select("span[data-line]");

        Map<String, Integer> line2number = new HashMap<>();
        line2number.put("www", 1);
        line2number.put("qqq", 2);
        line2number.put("qyt", 3);
       /* for (Element element : elements){
            String line = element.text();
            int number = Integer.parseInt(element.attr("data-line"));
            line2number.put(line, number);
        }*/

       /* JSONObject jsonObject = new JSONObject(line2number);
        FileWriter writer = new FileWriter("/Users/aleksandrshabalin/Skillbox/MyLearns_java_basics/13_FilesAndNetwork/homework_14.5/downloads/new.json");
        jsonObject.put("Линии метро", line2number);
        jsonObject.write(writer);*/


    }
}
