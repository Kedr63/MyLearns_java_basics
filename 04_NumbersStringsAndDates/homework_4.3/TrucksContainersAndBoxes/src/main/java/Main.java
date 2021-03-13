import java.util.Scanner;

public class Main {
    final static int CONTAINER_IN_TRUCK = 12;
    final static int BOXES_IN_CONTAINER = 27;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String boxes = scanner.nextLine();
        int countBox = Integer.parseInt(boxes);  //обработаем, введеную с внешнего мира, строку в число int

        // первый вариант вычисления количества контейнеров и грузовиков
        int countContainer = (int) Math.ceil((double) countBox / BOXES_IN_CONTAINER);
        int countTruck = (int) Math.ceil((double) countContainer / CONTAINER_IN_TRUCK);

        // второй вариант вычисления количества контейнеров и грузовиков
       /* int countContainer = (countBox / Container.BOXES_IN_CONTAINER)
                + (int) Math.signum((double) (countBox % Container.BOXES_IN_CONTAINER));
        int countTruck = (countContainer / Truck.CONTAINER_IN_TRUCK)
                + (int) Math.signum((double) (countContainer % Truck.CONTAINER_IN_TRUCK));*/


        int x = 1;
        int y = 1;
        for (int i = 1; i <= countTruck; i++) {
            System.out.println("Грузовик: " + i);

            for (int i1 = 1; i1 <= CONTAINER_IN_TRUCK; i1++) {
                if (x <= countContainer) {
                    System.out.println("\tКонтейнер: " + x);
                    x++;
                }
                for (int i2 = 1; i2 <= BOXES_IN_CONTAINER; i2++) {
                    if (y <= countBox) {
                        System.out.println("\t\tЯщик: " + y);
                        y++;
                    }
                }
            }
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
