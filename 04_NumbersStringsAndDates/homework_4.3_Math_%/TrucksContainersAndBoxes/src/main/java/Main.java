import java.util.Scanner;

public class Main {

    final static int CONTAINER_IN_TRUCK = 12;
    final static int BOXES_IN_CONTAINER = 27;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String boxes = scanner.nextLine();
        int countBox = Integer.parseInt(boxes);  //обработаем, введеную с внешнего мира, строку в число int

        int countTruck = 0;
        int countContainer = 0;
        for (int i = 1; i <= countBox; i++) {
            if (i % (BOXES_IN_CONTAINER * CONTAINER_IN_TRUCK) == 1) {
                countTruck++;
                System.out.println("Грузовик: " + countTruck);
            }
            if (i % BOXES_IN_CONTAINER == 1) {
                countContainer++;
                System.out.println("\tКонтейнер: " + countContainer);
            }
            System.out.println("\t\tЯщик: " + i);
        }

        System.out.println("Необходимо:"
                + System.lineSeparator() + "грузовиков - " + countTruck + " шт."
                + System.lineSeparator() + "контейнеров - " + countContainer + " шт.");



        // TODO: вывести в консоль коробки разложенные по грузовикам и контейнерам
        // пример вывода при вводе 2
        // для отступа используйте табуляцию - \t

        /*
        Грузовик: 1
            Контейнер: 1
                Ящик: 1
                Ящик: 2
        Необходимо:
        грузовиков - 1 шт.
        контейнеров - 1 шт.
        */

    }

}
