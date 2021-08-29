import net.sf.saxon.style.XSLOutput;

public class Main {
    public static void main(String[] args) {
        PhysicalPerson physicalPerson = new PhysicalPerson();
        physicalPerson.put(physicalPerson,-120);
        physicalPerson.put(physicalPerson,10000);
        physicalPerson.take(physicalPerson,300);
        physicalPerson.printInformation();


        System.out.println();

        IndividualBusinessman individualBusinessman = new IndividualBusinessman();
        individualBusinessman.put(individualBusinessman,20000);
        individualBusinessman.take(individualBusinessman,500);
        individualBusinessman.printInformation();
        System.out.println(individualBusinessman.getDepositCommission(500));
        System.out.println(individualBusinessman.getDepositCommission(1500));
        System.out.println(individualBusinessman.getWithdrawalCommission(20000));


        System.out.println();

        LegalPerson legalPerson = new LegalPerson();
        legalPerson.put(legalPerson,5000);
        legalPerson.take(legalPerson, 500);
        legalPerson.printInformation();
        System.out.println(legalPerson.getWithdrawalCommission(100));
        System.out.println(legalPerson.getDepositCommission(100));

    }
}
