import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JPanel;

public class MainForm2 {

  private JPanel mainPanel;
  private JPanel drawPanel;
  private JButton countButton;

  public MainForm2(){
    // будем по клику по кнопке рисовать прямоугольник
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
        Graphics2D graphics2D = (Graphics2D)  drawPanel.getGraphics();
        graphics2D.setColor(Color.RED);
        // координаты от верхнего левого угла
        graphics2D.drawRect(50, 30, 40, 50);
      }
    });

  }

  public JPanel getMainPanel() {
    return mainPanel;
  }
}
