import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Handler;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Loader {

    private static SimpleDateFormat birthDayFormat = new SimpleDateFormat("yyyy.MM.dd");
    private static SimpleDateFormat visitDateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");

    private static HashMap<Integer, WorkTime> voteStationWorkTimes = new HashMap<>();
    private static HashMap<Voter, Integer> voterCounts = new HashMap<>();

    public static void main(String[] args) throws Exception {
        String fileName = "/home/kedr/my_space/Skillbox/xml-files/data-18M.xml";

        long start = System.currentTimeMillis();
        RedisStorage redis = new RedisStorage();
        redis.unit();
     //   RedisStorage.clear();

        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        XMLHandler handler = new XMLHandler();
        parser.parse(new File(fileName), handler);

        System.out.println("duration: " + (System.currentTimeMillis() - start) + "   size Map = " + redis.getVoterIntegerRMap().size());


        redis.printVoterCounts();

        RedisStorage.clear();

        redis.shutdown();

        //  handler.printDuplicatedVoters();
    }

  /*  private static void parseFile(String fileName) throws Exception {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(new File(fileName));

        //  findEqualVoters(doc);
      //  fixWorkTimes(doc);
    }*/

  /*  private static void findEqualVoters(Document doc) throws Exception {
        NodeList voters = doc.getElementsByTagName("voter");
        int votersCount = voters.getLength();
        for (int i = 0; i < votersCount; i++) {
            Node node = voters.item(i);
            NamedNodeMap attributes = node.getAttributes();

            String name = attributes.getNamedItem("name").getNodeValue();
            Date birthDay = birthDayFormat
                .parse(attributes.getNamedItem("birthDay").getNodeValue());

            Voter voter = new Voter(name, birthDay);
            Integer count = voterCounts.get(voter);
            voterCounts.put(voter, count == null ? 1 : count + 1);

        }
    }*/

    private static void fixWorkTimes(Document doc) throws Exception {
        NodeList visits = doc.getElementsByTagName("visit");
        int visitCount = visits.getLength();
        for (int i = 0; i < visitCount; i++) {
            Node node = visits.item(i);
            NamedNodeMap attributes = node.getAttributes();

            Integer station = Integer.parseInt(attributes.getNamedItem("station").getNodeValue());
            Date time = visitDateFormat.parse(attributes.getNamedItem("time").getNodeValue());
            WorkTime workTime = voteStationWorkTimes.get(station);
            if (workTime == null) {
                workTime = new WorkTime();
                voteStationWorkTimes.put(station, workTime);
            }
            workTime.addVisitTime(time.getTime());
        }
    }
}

// 📌 Если программа вылетает по памяти - что можно сделать?
/** Есть два основных пути
 *   1. Увеличить оперативную память (если ее не хватает) и в Edit Configurations установить параметр -Xmx500M (поставил -Xmx16g)
 *      до максимальных значений, т.е. до размеров своей оперативной памяти, либо если будем делать его больше то будут возникать
 *      временнные файлы на жестком диске (так называемые своп), а это не хорошо так он работает гораздо дольше чем оперативная память.
 *          Конечно можно увеличивать до размера оперативной памяти, но не всегда эту проблему так просто решить, тогда можно начать
 *          использовать оперативную память экономно - порционно.
 *      Урок _Буферизация_: Мы читали xml-файл в DOM модель в объект класса Document. Но для xml-файлов есть два вида парсеров - это
 *      DOM парсер и SAX-парсер (Simple API for XML) -(метод последовательного чтения xml-файла)  */


/** 📌 Рекомендации, когда использовать DOM, а когда SAX 📌

 Разница между данными инструментами в функциональности и скорости работы. Если вам нужен более гибкий функционал и вы можете позволить себе
 тратить производительность программы, то ваш выбор – DOM, если же ваша главная цель – сокращение затрат памяти, то DOM не лучший выбор,
 так как он считывает всю информацию из XML файла и сохраняет её. Потому, метод SAX последовательного считывания менее затратный.

 📌 Коротко: если нужна производительность – SAX, функциональность – DOM.  */