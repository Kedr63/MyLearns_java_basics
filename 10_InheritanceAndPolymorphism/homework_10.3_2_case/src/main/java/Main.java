import net.sf.saxon.style.XSLOutput;

public class Main {
    public static void main(String[] args) {
        PhysicalPerson physicalPerson = new PhysicalPerson();
        physicalPerson.put(-120);
        physicalPerson.put(10000);
        physicalPerson.take(300);
        physicalPerson.printInformation();

        System.out.println();

        IndividualBusinessman individualBusinessman = new IndividualBusinessman();
        individualBusinessman.put(20000);
        individualBusinessman.take(500);
        individualBusinessman.printInformation();

        System.out.println();

        LegalPerson legalPerson = new LegalPerson();
        legalPerson.put(5000);
        legalPerson.printInformation();
        legalPerson.printInformation();

    }
}
