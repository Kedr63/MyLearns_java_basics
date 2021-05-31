public class Box {
    public static int count;   //счетчик ящиков
    private int idNumberBox;    //индентификационный номер каждого ящика

   public int getIdNumberBox() {
        return idNumberBox;
    }

    public Box() {
        count++;
       idNumberBox = count;   //индентификационный номер ящика приравняем к порядковому номеру ящика при создании
    }

}
