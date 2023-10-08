import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeListener;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class MainForm {

  // эти поля будут появлятся автоматически
  private JPanel mainPanel;
  private JTextArea textArea;
  private JButton countButton;
  private JButton clearButton;

  // чтоб добавить события - создадим конструкторы
  // для кнопки "Очистить"
  public MainForm() {
    clearButton.addActionListener(new Action() {
      @Override
      public Object getValue(String s) {
        return null;
      }

      @Override
      public void putValue(String s, Object o) {

      }

      @Override
      public void setEnabled(boolean b) {

      }

      @Override
      public boolean isEnabled() {
        return false;
      }

      @Override
      public void addPropertyChangeListener(PropertyChangeListener propertyChangeListener) {

      }

      @Override
      public void removePropertyChangeListener(PropertyChangeListener propertyChangeListener) {

      }

      // этот метод будет вызван когда мы кликнем по кнопке "Очистить"
      @Override
      public void actionPerformed(ActionEvent actionEvent) {
        textArea.setText("");
      }
    });
    // для кнопки "Посчитать"
    countButton.addActionListener(new Action() {
      @Override
      public Object getValue(String s) {
        return null;
      }

      @Override
      public void putValue(String s, Object o) {

      }

      @Override
      public void setEnabled(boolean b) {

      }

      @Override
      public boolean isEnabled() {
        return false;
      }

      @Override
      public void addPropertyChangeListener(PropertyChangeListener propertyChangeListener) {

      }

      @Override
      public void removePropertyChangeListener(PropertyChangeListener propertyChangeListener) {

      }

      @Override
      public void actionPerformed(ActionEvent actionEvent) {
        String text = textArea.getText();
        int length = text.length();
        // И отобразим информацию во всплывающен окне
        JOptionPane.showMessageDialog(mainPanel, length + " символов", "Длина текста",
            JOptionPane.PLAIN_MESSAGE);
      }
    });

    // для примера сделаем чтоб при клике мышкой по полю оно становилось желтым
    textArea.addMouseListener(new MouseListener() {
      @Override
      public void mouseClicked(MouseEvent mouseEvent) {
        textArea.setBackground(Color.YELLOW);
      }

      @Override
      public void mousePressed(MouseEvent mouseEvent) {

      }

      @Override
      public void mouseReleased(MouseEvent mouseEvent) {

      }

      @Override
      public void mouseEntered(MouseEvent mouseEvent) {

      }

      @Override
      public void mouseExited(MouseEvent mouseEvent) {

      }
    });

    textArea.addKeyListener(new KeyListener() {
      // нажали-отпустили
      @Override
      public void keyTyped(KeyEvent keyEvent) {
        if (keyEvent.getKeyChar() == '6') {
          textArea.setText(textArea.getText() + " лошади");
        }
      }

      // клавиша клавиатуры нажата
      @Override
      public void keyPressed(KeyEvent keyEvent) {

      }

      // отпущена
      @Override
      public void keyReleased(KeyEvent keyEvent) {

      }
    });
  }


  // чтоб нашу форму добавить в окно
  public JPanel getMainPanel() {
    return mainPanel;
  }
}
