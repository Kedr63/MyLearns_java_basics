public class Main {

    public static void main(String[] args) {
        String textNews = "M’r Karlson 3000 said was about to take a dip near the resort where he and " +
                "1000 Mr Karlson said was about to take a dip near the resort where he and family" +
                " were staying at in Geographe Bay when he saw what he thought 2000 family" +
                " were staying at in Geographe Bay when he saw what he thought 3456 He " +
                "discovered it was an octopus only when he walked closer with his two-year";
        System.out.println(splitTextIntoWords(textNews));
    }

    public static String splitTextIntoWords(String text) {
        //TODO реализуйте метод
        return text.replaceAll("[^a-zA-Z’]+", System.lineSeparator()).trim();
    }
}