import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Main {

     // ❤️ Парсинг HTML-файлов

    /** Рассмотрим сложный вариант работы с HTML-файлом.
     Например, попробуем получить список курсов с сайта Skillbox.ru. Зайдем на сайте где все курсы ->
     Откроем "посмотреть код страницы" и скопируем код в файл, который создадим в нашем проекте и положим
     в папку /data/.
     Теперь его нужно как-то прочитать ⬇
     */
    public static void main(String[] args) {
        // Создадим метод, который позволит в строчку прочитать этот файл
         String htmlFile = parseFile("data/code_1.html");
        Document doc = Jsoup.parse(htmlFile);  // используем библ-ку /Jsoup/
        Elements elements = doc.select("a.card__title"); // элементы с классом "card__title" содержат имена профессий
        elements.forEach(element -> {
            System.out.println(element.text()); // распечатается список профессий
        });

       // System.out.println(htmlFile);
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
    // Теперь нужно его пропарсить, для этого лучше скачать какую-нибудь библиотеку для этих целей.
    // Одна из таких хороших библиотек - /Jsoup/📌, с помощью нее можно парсить файлы. В поиске наберем Jsoup,
    // и пройдем на сайт Jsoup.org. Можно скачать, а можно подключать через интернет к сайту.
    // Скачаем. И создадим папку lib в которую положим этот файл и подключим эту библиотеку. И будем ее
    // использовать
}
