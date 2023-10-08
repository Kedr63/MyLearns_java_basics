import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.HashMap;

public class Loader {

    private static SimpleDateFormat birthDayFormat = new SimpleDateFormat("yyyy.MM.dd");
    private static SimpleDateFormat visitDateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");

    private static HashMap<Integer, WorkTime> voteStationWorkTimes = new HashMap<>();
    private static HashMap<Voter, Integer> voterCounts = new HashMap<>();

    public static void main(String[] args) throws Exception {
        String fileName = "/home/kedr/my_space/Skillbox/xml-files/data-1572M.xml";

        long start = System.currentTimeMillis();

        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        XMLHandler handler = new XMLHandler();
        parser.parse(new File(fileName), handler);

        DBConnection.executeMultiInsert();

        System.out.println("duration: " + (System.currentTimeMillis() - start));
        DBConnection.printVoterCounts();

        DBConnection.closeConnection();

    }
}

// 📌 Если программа вылетает по памяти - что можно сделать?
/** Есть два основных пути
 *   1. Увеличить оперативную память (если ее не хватает) и в Edit Configurations установить параметр -Xmx500M (поставил -Xmx16g)
 *      до максимальных значений, т.е. до размеров своей оперативной памяти, либо если будем делать его больше то будут возникать
 *      временные файлы на жестком диске (так называемые своп), а это не хорошо так он работает гораздо дольше чем оперативная память.
 *          Конечно можно увеличивать до размера оперативной памяти, но не всегда эту проблему так просто решить, тогда можно начать
 *          использовать оперативную память экономно - порционно.
 *      Урок _Буферизация_: Мы читали xml-файл в DOM модель в объект класса Document. Но для xml-файлов есть два вида парсеров - это
 *      DOM парсер и SAX-парсер (Simple API for XML) -(метод последовательного чтения xml-файла)  */


/** 📌 Рекомендации, когда использовать DOM, а когда SAX 📌

 Разница между данными инструментами в функциональности и скорости работы. Если вам нужен более гибкий функционал и вы можете позволить себе
 тратить производительность программы, то ваш выбор – DOM, если же ваша главная цель – сокращение затрат памяти, то DOM не лучший выбор,
 так как он считывает всю информацию из XML файла и сохраняет её. Потому, метод SAX последовательного считывания менее затратный.

 📌 Коротко: если нужна производительность – SAX, функциональность – DOM.  */