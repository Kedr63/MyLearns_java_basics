public class Task implements Runnable {

  private long counter;
 //  private boolean isRunning; // при таком раскладе на консоли: Main: 3892217525
    private volatile boolean isRunning; // мы говорим что эту переменную не нужно кешировать нигде,
  // она должна быть ровно такой какая она есть, т.е. если она изменилась то она изменилась
  // здесь уже консоль:
  // Task: 6420416730
  // Main: 6420416730

  public Task(){
    isRunning = true;
  }

  @Override
  public void run() {
    while (isRunning) {
      counter++;
    }
    System.out.println("Task: " + counter);
  }


  public void stop(){
    isRunning = false;
  }

  public long getCounterValue(){
    return counter;
  }




}
