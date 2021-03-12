import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) {
        System.out.println("введите количество ящиков для перевозки: ");

        Scanner scanner = new Scanner(System.in);
        String boxes = scanner.nextLine();
        int countBox = Integer.parseInt(boxes);  //обработаем, введеную с внешнего мира, строку в число int

        int countContainer = (countBox / Container.BOXES_IN_CONTAINER)
                + (int) Math.signum((double) (countBox % Container.BOXES_IN_CONTAINER));
        int countTruck = (countContainer / Truck.CONTAINER_IN_TRUCK)
                + (int) Math.signum((double) (countContainer % Truck.CONTAINER_IN_TRUCK));


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

    }
}
