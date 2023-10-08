import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ValidateForm extends JFrame {

  private JPanel mainPanel;
  private JTextField textFieldInputSurname;
  private JTextField textFieldInputNameOrOutput;
  private JTextField textFieldInputMiddleName;
  private JButton button;
  private JPanel panelSurname;
  private JLabel labelSurname;
  private JPanel panelName;
  private JPanel panelChangedLabel;
  private JLabel labelName;
  private JLabel labelFIO;
  private JPanel panelMiddleName;
  private JLabel labelMiddleName;
  private JTextField textField1;


  String surName = "";
  String name = "";
  String middleName = "";
  String fullName = "";


  public ValidateForm() {
    super("Проверка Ф.И.О.");
    setContentPane(mainPanel);

    button.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getActionCommand().equals("Collapse")) {
          surName = textFieldInputSurname.getText().strip();
          name = textFieldInputNameOrOutput.getText().strip();
          middleName = textFieldInputMiddleName.getText().strip();
          fullName = surName + " " + name + " " + middleName;
          if (surName.isEmpty() || name.isEmpty()) {
            JOptionPane.showMessageDialog(mainPanel, "Введите фамилию и имя", "Ошибка!",
                JOptionPane.PLAIN_MESSAGE);
          } else {
            panelSurname.setVisible(false);
            labelName.setVisible(
                false); // label "Имя" скроется и появится спрятаная за labelName идущая за ней labelFIO (получается подменит ее) (это CardLayout ячейка)
            labelFIO.setVisible(true);

            textFieldInputNameOrOutput.setText(fullName);

            panelMiddleName.setVisible(false);
            button.setText("Expand");
          }
        }

        if (actionEvent.getActionCommand().equals("Expand")) {
          String search = "([а-яА-Я]+-?[а-яА-Я]+)\\s([а-яА-Я]+)\\s([а-яА-Я]+)?";
          if (!fullName.matches(search)) {
            JOptionPane.showMessageDialog(mainPanel, "Введите корректное имя (только кирилица)");
          }
          panelSurname.setVisible(true);
          labelName.setVisible(true);
          labelFIO.setVisible(false);
          textFieldInputNameOrOutput.setText(name);
          panelMiddleName.setVisible(true);
          button.setText("Collapse");
        }
      }
    });
  }

}
