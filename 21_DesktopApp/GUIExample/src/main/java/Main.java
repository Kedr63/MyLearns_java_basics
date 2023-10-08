import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class Main {

  // javax.swing - считается основным пакетом для такой работы

  public static void main(String[] args) {
    // создадим окно
    JFrame frame = new JFrame();
    frame.setSize(600, 400);

    // добавим кнопку, сначала установим компановщик
    frame.setLayout(new FlowLayout());
    frame.add(new JButton("Click Me :)"));

    // чтоб приложение приостанавливало свою работу при закрытии окна
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    // сделаем чтоб оно отображалось по центру
    frame.setLocationRelativeTo(null);
    // сделаем его видимым
    frame.setVisible(true);

    // Итог: таким образом (способом) создавать интерфейсы сложно, если много кнопок будет, то лучше
    // делать в спецальном графическом редакторе
    // чтоб его активировать нужно создать специальный объект:
    // папка /java/ над классом -> правая кнопка мышки -> new -> Swing GUI Designer -> GUI Form
    // см. ниже метод main для него
  }
}

class Main_for_GUI_form{

  public static void main(String[] args) {
    JFrame frame = new JFrame();
    frame.setSize(600, 400);

    frame.add(new MainForm().getMainPanel());

    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);

    // Например, напишем приложение которое будет считать длину текста которое ввели в текстовое поле
  }
}

class Main_for_GUI_form2{
 // Кастомизация интерфеса (нарисуем что-нибудь)
  public static void main(String[] args) {
    JFrame frame = new JFrame();
    frame.setSize(600, 400);

    frame.add(new MainForm2().getMainPanel());

    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }
}
  // В гарфическом интерфейсе у нас бывают компоненты и контейнеры, в данном случае контейнеры являются
// тоже компонентами
