import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws IOException {

        /** Здесь находим картинки с тегом /img/, затем прогоняем по циклу и отбираем по атрибуту /class/ и отсекаем
         * мини картинки.
         * Если файл будет повторяться, то изменим его имя добавив впереди цифру.  */

        Document doc = Jsoup.connect("https://lenta.ru/").get();
        Elements jpegs = doc.getElementsByTag("img");

        String pathForDownloads = "13_FilesAndNetwork/homework_14.4/images/";
        String pathUrl = "";
        int countImg = 0;
        int forRepeatFiles = 0;

        for (Element jpeg : jpegs) {
            if (jpeg.attr("class").contains("__image") && !jpeg.attr("class").contains("mini__image")) {
                try (BufferedInputStream inputStream = new BufferedInputStream(new URL(jpeg.attr("abs:src")).openStream());) {
                    pathUrl = jpeg.attr("abs:src");

                    if (!Files.exists(Paths.get(pathForDownloads + getNameImg(pathUrl)))) {
                        Files.copy(inputStream, Paths.get(pathForDownloads + getNameImg(pathUrl)));
                    } else {
                        forRepeatFiles += 1;
                        Files.copy(inputStream, Paths.get(pathForDownloads + String.valueOf(forRepeatFiles) + "______" + getNameImg(pathUrl)));
                    }
                }
                countImg += 1;
                System.out.printf("\n Number image: %s Name file: %s", countImg, getNameImg(pathUrl));
            }
        }
        System.out.println("\n\tAmount images: " + countImg);
    }

    private static String getNameImg(String urlPath) {
        Pattern pattern = Pattern.compile("(/)([\\w\\s]+\\.[jpegn]+)");
        Matcher matcher = pattern.matcher(urlPath);
        return matcher.find() ? matcher.group(2).trim() : "notFound";
    }
}