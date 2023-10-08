public class Test {


  // проверял как работает емкость билдера
  public static void main(String[] args) {
    StringBuilder builder = new StringBuilder();

    String a = "abc";

    System.out.println("StringBuilder length: " +builder.length() + ",      StringBuilder capacity: " + builder.capacity());
    for (int i =0; i <= 15; i++){
      builder.append(a);
    }
    System.out.println("After: StringBuilder length: " +builder.length() + ",       After: StringBuilder capacity: " + builder.capacity());


  }
}
