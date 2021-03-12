import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String boxes = scanner.nextLine();
        int countBox = Integer.parseInt(boxes);  //обработаем, введеную с внешнего мира, строку в число int

        int countContainer = (int) Math.ceil((double) countBox / Container.BOXES_IN_CONTAINER);
        int countTruck = (int) Math.ceil((double) countContainer / Truck.CONTAINER_IN_TRUCK);

        for (int i = 1; i <= countTruck; i++) {
            Truck truck = new Truck();
            System.out.println("Грузовик: " + truck.getIdNumberTruck());

            for (int i1 = 1; i1 <= Truck.CONTAINER_IN_TRUCK; i1++) {
                if (Container.count < countContainer) {
                    Container container = new Container();
                    System.out.println("\tКонтейнер: " + container.getIdNumberContainer());
                }
                for (int i2 = 1; i2 <= Container.BOXES_IN_CONTAINER; i2++) {
                    if (Box.count < countBox) {
                        Box box = new Box();
                        System.out.println("\t\tЯщик: " + box.getIdNumberBox());
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
