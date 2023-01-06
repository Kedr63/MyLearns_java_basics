import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.RecursiveTask;

public class HtmlParser extends RecursiveTask<Set<String>> {

  private final String url;

  public HtmlParser(String url) {
    this.url = url;
  }

  @Override
  protected Set<String> compute() {
    List<HtmlParser> tasks = new ArrayList<>();
    Set<String> paths = new HashSet<>();

    try {
      Document doc;
      try {
        doc = Jsoup.connect(url).get();
      } catch (IOException e) {
        throw new IOException("не рабочий url: " + url);
      }

      Elements searchLinks = doc.getElementsByTag("a").select("[href^=http], [href^=/]")
          .not("[href^=#]");

      // добавим проверку по тэгу title: если такая страница уже участвовала в обработке, то не будем повторно использовать эту страницу
      String titleString = doc.getElementsByTag("title").text();
      CollectionStorage.checkingAndAddToSetTitle(titleString);

      for (Element element : searchLinks) {
        String linkHref = element.attr("href");
        String domenName = Starter.titlePage;

        if (isMatchRequiredConditions(linkHref, domenName)) {
          String catalogs = linkHref.replaceAll(
              "(ht{2}ps?:)?(//[a-z]+\\.\\w+)?(/)?([/{1}][\\w/\\-]+\\.?h?t?m?l?[^/])(/)?", "$4");

          // после всех условий и фильтров получаем URL-ссылку из doc
          String urlAddress = domenName + catalogs;

          // и если URL-ссылка еще не принимала участие в обработке (нет в set-e ссылок), то добавить ее в set и ответвить fork()
          if (!CollectionStorage.setString.contains(urlAddress)) {
            paths.add(urlAddress);
            CollectionStorage.setString.add(urlAddress);
            HtmlParser task = new HtmlParser(urlAddress);
            try {
              Thread.sleep(100);
            } catch (InterruptedException e) {
              throw new RuntimeException(e);
            }
            tasks.add(task);
          }
        }
      }

      if (paths.size() == 0) {
        throw new IllegalStateException("Набор даже не набрался, здесь все пути-href использовали");
      } else {
        for (HtmlParser task : tasks) {
          task.fork();
        }
        for (HtmlParser task : tasks) {
          paths.addAll(task.join());
        }
      }
    } catch (IOException | IllegalStateException ex) {
      System.out.println(ex.getMessage());
    }
    return paths;
  }


  private static boolean isMatchRequiredConditions(String searchHref, String linkCore) {
    return
        searchHref.replaceAll("(ht{2}ps?://[a-z]+\\.\\w+)?(/)?([/{1}][\\w/\\-]+\\.?h?t?m?l?)", "$1")
            .compareTo(linkCore) == 0 ||
            searchHref.replaceAll("(ht{2}ps?://[a-z]+\\.\\w+)?(/)?([/{1}][\\w/\\-]+\\.?h?t?m?l?)",
                "$1").isEmpty() && searchHref.replaceAll(
                    "(ht{2}ps?:)?(//[a-z]+\\.\\w+)?(/)?([/{1}][\\w/\\-]+\\.?h?t?m?l?)", "$4")
                .startsWith("/");
  }
}



