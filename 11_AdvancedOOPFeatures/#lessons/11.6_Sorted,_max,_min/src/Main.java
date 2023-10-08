import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

public class Main {

  public static final String STAFF_TXT = "/home/kedr/my_space/Skillbox/MyLearns_java_basics/11_AdvancedOOPFeatures/#lessons/11.6_Sorted,_max,_min/data/staff.txt";

  // ❤️ Sorted, max, min    ___________________________

  // Поговорим о двух часто используемых методах стримов - это получение минимального, максимального
  // значения  и сортировки стрим:

  public static void main(String[] args) {
    List<Employee> staff = EmployeeUtils.loadStaffFromFile(STAFF_TXT);

    // допустим хотим отсортирвать всех сотрудников по з/п
    staff.stream().sorted(Comparator.comparing(Employee::getSalary)).forEach(System.out::println);

    // в консоли получим стрим отсортированный по з/п (от меньшей к большей)

    System.out.println("______________________________________");

    // аналогичным образом мы можем получить макс. и миним. значение
    /*  staff.stream().max(Comparator.comparing(Employee::getSalary));  */
    // вот получили макс. значение - что с ним можно сделать? ➔ вот этот метод /max/ возвращает
    // объект /optional/ - создадим его ниже чтоб было очевидно ⬇. Это специальный объект, который
    // позволяет возвращать или не возвращать значения. Вместо того чтоб возвращать или не возвращать null,
    // они возвращают /optional/,
    // у которого есть всякие методы, например, /isPresent/ - /существует ли данный объект/
    /*   Optional<Employee> optional =  staff.stream().max(Comparator.comparing(Employee::getSalary));  */
    /*   optional.isPresent();  */ // существует ли данный объект/
    /*   optional.isEmpty();  */  // является ли он пустым

    // Преобразовать этот объект в сотрудника /Employee/ легко, достаточно вызвать метод /get/ у него
    /*  Employee employee = optional.get();  */
    // но можно сделать это безопасно и распечатать нашего сотрудника безопасно
    staff.stream().max(Comparator.comparing(Employee::getSalary))
        .ifPresent(System.out::println);  // если он существует ➔ мы его напечатаем

       /*   консоль:
        Анна Сетяева - 140000 - 10.05.2012   */



    System.out.println("______________________________________");
    System.out.println("отсортировать сотрудников по заработной плате и алфавиту");

    sortBySalaryAndAlphabet(staff);
    for (Employee employee : staff){
      System.out.println(employee);
    }


    System.out.println("______________________________________");
    System.out.println("вернуть сотрудника с максимальной зарплатой среди тех, кто пришёл в году, указанном в переменной year");

 //   try {
      Employee employeeMaxSalary = findEmployeeWithHighestSalary(staff, 2016);
      System.out.println(employeeMaxSalary);
 /*   } catch (NoSuchElementException e) {
      System.out.println(e.getMessage());
      System.out.println("такого года нет");
    }*/

  }

  private static void sortBySalaryAndAlphabet(List<Employee> staff) {
    //TODO Метод должен отсортировать сотрудников по заработной плате и алфавиту.
    staff.sort((e1, e2) -> {
        if (e1.getSalary().compareTo(e2.getSalary()) == 0) {
            return e1.getName().compareTo(e2.getName());
        } else {
            return e1.getSalary().compareTo(e2.getSalary());
        }
    });
  }

  private static Employee findEmployeeWithHighestSalary(List<Employee> staff, int year) {
    //TODO Метод должен вернуть сотрудника с максимальной зарплатой среди тех,
    // кто пришёл в году, указанном в переменной year
    return staff.stream().filter(employee -> employee.getWorkStart().getYear() == year)
        .max(Comparator.comparing(Employee::getSalary)).get();
  }

  // или так
  private static Employee findEmployeeWithHighestSalary1(List<Employee> staff, int year) {
    //TODO Метод должен вернуть сотрудника с максимальной зарплатой среди тех,
    // кто пришёл в году, указанном в переменной year
    return staff.stream().filter(employee -> employee.getWorkStart().getYear() == year)
        .max((e1, e2) -> e1.getSalary().compareTo(e2.getSalary())).get();
  }
}