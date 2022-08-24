public class Main {

  public static void main(String[] args) {
    for (int i = 0; i < 4; i++){
      new Thread(()-> {
        for (int j = 0; j < 10; j++){
          ValueStorage.incrementValue();
        }
        System.out.println(ValueStorage.getValue());
      }).start();
    }
  }
}

// При небольшом числе j < 10 у нас может если повезет выйти на печать:
// 10
// 20
// 30
// 40
//  а также можем получить:
// 17
// 32
// 32
// 22
// Почему? Потому что в классе ValueStorage инкрементация у потоков начинает идти в разнобой.
// Чтоб решить эту проблему применяют класс AtomicInteger