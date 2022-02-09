import java.util.LinkedHashMap;
import java.util.Map;

public class UserAgents {
    String name;

    public UserAgents() {
        this.name = generateAgent();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

   private static String[] userAgentArray = {"Opera/9.80 (Windows NT 5.2; U; uk) Presto/2.5.24 Version/10.54",
            "Mozilla/5.0 (Linux; Android 7.1.1; Lenovo TB-X304L Build/NMF26F) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.111 Safari/537.36 OPR/46.3.2246.127744",
            "Mozilla/5.0 (Windows NT 5.1; rv:39.0; WUID=17788a228f5368c9f6d9ef6c90407add; WTB=29369) Gecko/20100101 Firefox/39.0",
            "Mozilla/5.0 (Windows; U; Windows NT 6.0; en-US) AppleWebKit/532.5 (KHTML, like Gecko) Chrome/4.0.249.78 Safari/532.5",
            "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.87 Safari/537.36 OPR/54.0.2952.60",
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/43.0.2357.130 Safari/537.36 OPR/30.0.1835.125 (Edition Yx 01)",
            "Mozilla/5.0 (Windows NT 6.1; rv:38.0) Gecko/20100101 Firefox/38.0 AlexaToolbar/alxf-2.21",
            "Mozilla/5.0 (Windows NT 6.2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36",
            "Mozilla/5.0 (Windows NT 6.2; WOW64) AppleWebKit/535.11 (KHTML, like Gecko) Chrome/17.0.963.47 Safari/535.11 MRCHROME ODKL",
            "Opera/9.80 (Windows NT 5.0; WOW64; Edition Yx) Presto/2.12.388 Version/12.16"};

    private static String generateAgent() {
        Map<Integer, String> nameMap = new LinkedHashMap<>();
        String name = "";

        int number = 1;
        for (String str : userAgentArray){
            nameMap.put(number, str);
            number++;
        }

        int randomNumber = 1 + (int) (Math.random() * nameMap.size());
        for (Integer integer : nameMap.keySet()) {
            if (integer == randomNumber) {
                name = nameMap.get(integer);
            }
        }
        return name;
    }
}
