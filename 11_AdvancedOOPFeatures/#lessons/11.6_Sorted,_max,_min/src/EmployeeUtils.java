import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public final class EmployeeUtils {

    private EmployeeUtils() {
        throw new RuntimeException("Нельзя создать объект!");
    }

    private static final DateTimeFormatter dateFormatter =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public static List<Employee> loadStaffFromFile(String path) {
        List<Employee> staff = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get(path));
            for (String line : lines) {
                String[] fragments = line.split("\t");
                if (fragments.length != 3) {
                    System.out.println("Wrong line: " + line);
                    continue;
                }
                staff.add(new Employee(
                        fragments[0],
                        Integer.parseInt(fragments[1]),
                        LocalDate.parse(fragments[2], dateFormatter)
                ));
            }
        } catch (Exception ex) {
            throw new RuntimeException("Ошибка чтения файла с сотрудниками", ex);
        }
        return staff;
    }
}
