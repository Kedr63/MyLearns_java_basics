import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ForkJoinPool;

public class Starter {

  // protected static final String titlePage = "https://lenta.ru";
   protected static final String titlePage = "https://skillbox.ru";
 // protected static final String titlePage = "https://pumpmuscles.ru";
  protected static final String fileForWrite = "Multithreading/TreeHtml_via_ForkJoinPool/src/main/data/treeHtml.txt";

  public static void main(String[] args) throws IOException {

    Date start = new Date();

    ForkJoinPool forkJoinPool = new ForkJoinPool();
    HtmlParser htmlParser = new HtmlParser(titlePage);
    Set<String> setParsedHtml = forkJoinPool.invoke(htmlParser);
    
    List<Node> nodeList = getListNodes(setParsedHtml);
    nodeList.sort(new NodeComparator());

    Path pathWrite = Path.of(fileForWrite);
    for (Node node : nodeList) {
      // node.getStringList().forEach(System.out::println);
      Files.write(pathWrite, node.getStringList(), StandardOpenOption.APPEND);
    }

    Date end = new Date();
    System.out.println("Start parse: " + start);
    System.out.println("End parse: " + end);
    System.out.println("nodeList.size() = " + nodeList.size());
  }

  // из полученных ссылок вернем список с готовыми Узлами Node (Node: родитель -> дети)
  private static List<Node> getListNodes(Set<String> stringSet) {
    List<Node> listPathsPages = new ArrayList<>();
    for (String path : stringSet) {
      List<String> pathsPage = new ArrayList<>();
      String catalogs = path.replaceAll("(ht{2}ps?://[a-z]+\\.\\w+)?(/.+)?", "$2");
      String[] arrayPath = catalogs.split("/");
      StringBuilder builder = new StringBuilder();
      for (int i = 0; i < arrayPath.length; i++) {
        String st = "\t".repeat(i) + titlePage + builder.append(arrayPath[i]).append("/");
        pathsPage.add(st);
      }
      listPathsPages.add(new Node(pathsPage));
    }
    return listPathsPages;
  }
}



