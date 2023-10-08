import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class CarNumberFactory implements Runnable {

  private final char[] letters;
  private final int indexForStartRange;
  private final int rangeNumber;
  private final int regionCode;
  private final int numberOfFile;
  private final long start;

  public CarNumberFactory(char[] letters, int indexForStartRange, int rangeNumber, int regionCode,
      int numberOfFile, long star)
  {
    this.letters = letters;
    this.indexForStartRange = indexForStartRange;
    this.rangeNumber = indexForStartRange + rangeNumber;
    this.regionCode = regionCode;
    this.numberOfFile = numberOfFile;
    this.start = star;
  }

  @Override
  public void run() {
     BufferedWriter writer;
    try {
      writer = new BufferedWriter(
          new FileWriter("Performance/homework_CarNumberGenerator_optimization/CarNumberGenerator_optimization/res/numbers" + numberOfFile + ".txt"));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    for (int number = indexForStartRange; number < rangeNumber; number++) {
      StringBuilder builder = new StringBuilder();
      for (char firstLetter : letters) {
        for (char secondLetter : letters) {
          for (char thirdLetter : letters) {
            builder.append(firstLetter);
            builder.append(padNumber(number, 3));
            builder.append(secondLetter);
            builder.append(thirdLetter);
            builder.append(padNumber(regionCode, 2));
            builder.append('\n');
          }
        }
      }
      try {
        writer.write(builder.toString());
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }
    try {
      writer.flush();
      writer.close();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    System.out.println(
        "Finished after start: " + (System.currentTimeMillis() - start) + " ms.  Работал поток: "
            + Thread.currentThread().getName());
  }

  private static String padNumber(int number, int numberLength) {
    String numberStr = Integer.toString(number);
    int padSize = numberLength - numberStr.length();

    if (padSize > 0) {
      StringBuilder builder = new StringBuilder();
      builder.append("0".repeat(padSize));
      numberStr = builder.append(numberStr).toString();
    }
    return numberStr;
  }
}
