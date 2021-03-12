public class Container {
    final static int BOXES_IN_CONTAINER = 27;   //количество ящиков, которое поместится в контейнер
    public static int count;                   //счетчик контейнеров
    private int idNumberContainer;              //индентификационный номер контейнера

    public int getIdNumberContainer() {
        return idNumberContainer;
    }

    public Container() {
        count++;
        idNumberContainer = count;  //индентификационный номер контейнера приравняем к порядковому номеру контейнера при создании
    }

}
