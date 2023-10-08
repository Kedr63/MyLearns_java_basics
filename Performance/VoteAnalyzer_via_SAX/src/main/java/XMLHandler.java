import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XMLHandler extends DefaultHandler {

  private Voter voter;
  private static SimpleDateFormat birthDayFormat = new SimpleDateFormat("yyyy.MM.dd");
  // для подсчета voter-ов которые проголосовали несколько раз создадим коллекцию
  private HashMap<Voter, Integer> voterCounts;  //

  public XMLHandler(){
    voterCounts = new HashMap<>(); // сюда будем складывать voter-ов каждый раз когда попадается visit (посещение избир. участка)
  }

  // этот метод срабатывает когда парсер натыкается на какой-то элемент (начало тэга)
  // qName - имя тэга
  @Override
  public void startElement(String uri, String localName, String qName, Attributes attributes)
      throws SAXException
  {
    try {
      if (qName.equals("voter") && voter ==null) {
        Date birthDay = birthDayFormat.parse(attributes.getValue("birthDay"));
        voter = new Voter(attributes.getValue("name"), birthDay);
      }

      if (qName.equals("visit") && voter != null){ // если сталкиваемся с тэгом /visit/
        int count = voterCounts.getOrDefault(voter, 0);   // новый метод, вместо contains. Здесь если в коллекции нет voter-а, то вернется
        // value по умолчанию = 0, и код ниже добавит в map voter-а и присвоит ему значение (0+1 = 1), если же в коллекции уже есть этот же voter,
        // то вернется value (Integer) voter-а, допустим 1 и код ниже добавит в map voter-а (а так как там только уникальные элементы, то он так и
        // останется одним voter-ом) и присвоит ему значение (1+1 = 2), станет value посещений voter-а изб.участка = 2
        voterCounts.put(voter, count + 1);
      }
    } catch (ParseException e) {
      e.printStackTrace();
    }
  }

  // конец тэга
  @Override
  public void endElement(String uri, String localName, String qName) throws SAXException {
    if (qName.equals("voter")) {
      voter = null;  // и при выходе из элемента стерем избирателя /voter/
    }
  }

  public void printDuplicatedVoters(){
    for (Voter voter : voterCounts.keySet()){
      int count = voterCounts.get(voter);
      if (count > 1){
        System.out.println(voter.toString() + " - " + count);
      }
    }
  }
}
