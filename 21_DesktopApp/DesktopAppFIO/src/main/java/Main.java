import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class Main {

  public static void main(String[] args) {

    JFrame frame = new JFrame("Проверка Ф.И.О.");
    frame.setSize(500, 300);

    frame.add(new VerifyForm().getMainPanel());
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    frame.setResizable(false);

  }
}
