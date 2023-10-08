import javax.swing.WindowConstants;

public class Main {

  public static void main(String[] args) {

    ValidateForm validateForm = new ValidateForm();
    validateForm.setSize(300, 250);

    validateForm.setLocationRelativeTo(null);
    validateForm.setVisible(true);
    validateForm.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    validateForm.setResizable(false);

  }
}
