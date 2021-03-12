public class Truck {
    final static int CONTAINER_IN_TRUCK = 12;   //количество контейнеров, которое поместится в грузовик
    public static int count;                   //счетчик грузовиков
    private int idNumberTruck;                  //индентификационный номер грузовика


    public int getIdNumberTruck() {
        return idNumberTruck;
    }

    public Truck() {
        count++;
        idNumberTruck = count;  //индентификационный номер грузовика приравняем к порядковому номеру грузовика при создании
    }
}
