import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

public class TestForm extends JFrame {

  public static void main(String[] args) {

    TestForm testForm = new TestForm("Test window");
    testForm.setVisible(true);
    testForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    testForm.setSize(300, 200);
    testForm.setAutoRequestFocus(false);
    testForm.setLocationRelativeTo(null);
  /*  JFrame frame = new JFrame("Проверка Test");
    frame.setSize(500, 300);

    frame.add(new TestForm().getMainPanel());
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    frame.setResizable(false);*/

  }


  private JPanel mainPanel;
  private JTabbedPane tabbedPane1;
  private JPanel inerPan1;
  private JPanel inerPan2;
  private JTextField textField1;
  private JEditorPane editorPane1;
  private JComboBox comboBox1;

  private final JButton b1;
  private final JButton b2;
  private JLabel l1, l2, l3, l4;
  private JTextField t1, t2;
   private int i, k;
  String a, b;
  eHahdler hahdler = new eHahdler();

  public TestForm(String s) {
    super(s);
    // setLayout(new FlowLayout());
    setLayout(new FlowLayout());
    b1 = new JButton("Clear");
    b2 = new JButton("посчитать");
    l1 = new JLabel("введите первое число");
    l2 = new JLabel("введите второе число");
    l3 = new JLabel("");
    l4 = new JLabel("");
    t1 = new JTextField(10); // на 10 символов будет ширина
    t2 = new JTextField(10);
    // add to display (порядок добавления важен)
    add(b1);
    add(b2);
    add(l1);
    add(t1);
    add(l2);
    add(t2);
    add(l3);
    add(l4);
    b2.addActionListener(hahdler);
    b1.addActionListener(hahdler);
  }

  /* public JPanel getMainPanel() {
     return mainPanel;
   }*/
  public class eHahdler implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent event) {
      try {
        if (event.getSource() == b2) {
          i = Integer.parseInt(t1.getText());
          k = Integer.parseInt(t2.getText());
          i++;
          k++;
          a = "ваше первое число теперь равно " + i;
          b = "ваше второе число теперь равно " + k;
          l3.setText(a);
          l4.setText(b);
        }
        if (event.getSource() == b1){
          t1.setText(null);
          t2.setText(null);
          l3.setText(null);
          l4.setText(null);
        }
      } catch (Exception e){
        JOptionPane.showMessageDialog(null, "введите в поле число");
      }

    }
  }
}



