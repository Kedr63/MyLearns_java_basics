public class Parking {

  private int parkingSize = 500;
  private int carCount = 0;

  public synchronized void in(){       // методы потокобезопасны раз synchronized
    if (carCount == parkingSize){
      try {
        wait();     // и поток приостановится чтоб не превысить количество парковочных мест
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }
    carCount++;
    System.out.println("Автомобиль завезен");
    System.out.println("Свободных мест: " + (parkingSize - carCount));
    notify();  // после того как добавили автомобиль мы можем возобновить поток
  }

  public synchronized void out(){
    if (carCount == 0){     // здесь надо не уйти в минус по количеству мест
      try {
        wait();
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }
    carCount--;
    System.out.println("Автомобиль вывезен");
    System.out.println("Свободных мест: " + (parkingSize - carCount));
    notify();
  }

}
