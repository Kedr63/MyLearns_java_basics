import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class VerifyForm {

  private JPanel mainPanel;
  private JLabel labelSurName;
  private JLabel labelName;
  private JLabel labelMiddleName;
  private JLabel labelOutput;
  private JPanel fieldInput;
  private JTextField textFieldSurName;
  private JTextField textFieldMiddleName;
  private JTextField textFieldName;
  private JTextField textFieldOutput;
  private JPanel panelInputText;
  private JPanel panelOutput;
  private JPanel buttonPanel;
  private JButton buttonCollapse;

  String surName = "";
  String name = "";
  String middleName = "";
  String fullName = "";

  public VerifyForm() {
    panelOutput.setVisible(false);

    buttonCollapse.addActionListener(new Action() {
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
        String actionCommand = actionEvent.getActionCommand();
        if (actionCommand.equals("Collapse")) {
          surName = textFieldSurName.getText();
          name = textFieldName.getText();
          middleName = textFieldMiddleName.getText();
          fullName = surName + " " + name + " " + middleName;
          if (surName.isEmpty() || name.isEmpty()) {
            JOptionPane.showMessageDialog(mainPanel, "Введите фамилию и имя", "Ошибка!",
                JOptionPane.PLAIN_MESSAGE);
          } else {
            panelInputText.setVisible(false);
            buttonCollapse.setText("Expand");
            panelOutput.setVisible(true);
            textFieldOutput.setText(fullName);
          }
        }

        if (actionCommand.equals("Expand")) {
          String search = "([а-яА-Я]+-?[а-яА-Я]+)\\s([а-яА-Я]+)\\s([а-яА-Я]+)?";
          if (!fullName.matches(search)) {
            JOptionPane.showMessageDialog(mainPanel, "Введите корректное имя (только кирилица)");
          }
          panelInputText.setVisible(true);
          panelOutput.setVisible(false);
          buttonCollapse.setText("Collapse");
        }
      }
    });
  }

  public JPanel getMainPanel() {
    return mainPanel;
  }

}
