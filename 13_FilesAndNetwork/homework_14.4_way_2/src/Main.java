import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    private static final String URL_SOURCE = "https://lenta.ru/";
    private static final String PATH_FOR_DOWNLOADS = "/Users/aleksandrshabalin/Skillbox/java_basics/13_FilesAndNetwork/homework_14.4/images/";
    private static final String Tag_Name = "img";
    private static final String Attr_Name = "class";       // отберем теги с атрибутом "class"
    private static final String Attr_Target_Value = "__image"; // здесь можем задать и "big__image" (поиск тогда сузится)
    private static final String Attr_Not_Allow_Value = "mini__image";  // нам не интересны mini изображения
    private static final String Attr_Url_Src = "abs:src";

    public static void main(String[] args) throws IOException {

        ParseHtml parseHtml = new ParseHtml(URL_SOURCE, PATH_FOR_DOWNLOADS);

        Document doc = Jsoup.connect(parseHtml.getUrl()).get(); // via Jsoup get parsed /doc/
        Elements elements = doc.getElementsByTag(Tag_Name); // получим коллекцию элементов с тегом /img/

        for (Element element : elements) {
            if (attrElementContainRequiredValues(element)) {
                String pathUrlElement = element.attr(Attr_Url_Src);
                try (BufferedInputStream inputStream =
                        new BufferedInputStream(new URL(pathUrlElement).openStream());)
                {
                    Files.copy(inputStream, Paths.get(parseHtml.getPathForDownloads()
                            + getNameElement(pathUrlElement)), StandardCopyOption.REPLACE_EXISTING);
                }
            }
        }
        print(parseHtml.getPathForDownloads());   // распечатаем имена файлов image из директории /images/
    }

    private static String getNameElement(String urlPath) {
        Pattern pattern = Pattern.compile("(/)([\\w\\s]+\\.[jpegn]+)");
        Matcher matcher = pattern.matcher(urlPath);
        return matcher.find() ? matcher.group(2).trim() : "notFound";
    }

    private static void print(String path){
        File imageDirectory = new File(path);
        File[] files = imageDirectory.listFiles();
        for (File fileImage : files) {
            System.out.println(getNameElement(fileImage.getPath()));
        }
    }

    private static boolean attrElementContainRequiredValues(Element element){
        return element.attr(Attr_Name).contains(Attr_Target_Value)  // оставим только понятные изображения
                && !element.attr(Attr_Name).contains(Attr_Not_Allow_Value); // и уберем мини изображения
    }
}