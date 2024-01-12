import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.text.MessageFormat;

public class AddStaff extends JFrame {

    private JPanel mainPanel;
    private JLabel headerLabel;
    private JTextField firstNameTextField;
    private JTextField emailTextField;
    private JButton backButton;
    private JButton addButton;
    private JLabel firstNameLabel;
    private JLabel contactLabel;
    private JTextField lastNameTextfield;
    private JLabel lastNameLabel;
    private JTextField phoneTextField;
    private final JFrame thisobj;

    public static void main(String[] args) {
        var addStaff = new AddStaff();
        addStaff.setVisible(true);
    }

    public AddStaff() {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
        this.setLocationRelativeTo(null);
        thisobj = this;
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String url = "jdbc:mysql://localhost:3306/library?useSSL=true";
                String mysqluser = "root";
                String mysqlpassword = "910401";

                String query = "INSERT INTO STAFF (FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER) VALUES(?,?,?,?)";
                String firstName = firstNameTextField.getText();
                String lastName = lastNameTextfield.getText();
                String email = emailTextField.getText();
                String phoneNumber = phoneTextField.getText();
                try {
                    var connection = DriverManager.getConnection(url, mysqluser, mysqlpassword);
                    var statement = connection.prepareCall(query);
                    statement.setString(1, firstName);
                    statement.setString(2, lastName);
                    statement.setString(3, email);
                    statement.setString(4, phoneNumber);
                    statement.execute();

                    String message = MessageFormat.format("Staff  \"{0}\" \"{1}\" is added successfully", firstName, lastName);
                    JOptionPane.showMessageDialog(thisobj, message);

                    firstNameTextField.setText(null);
                    lastNameTextfield.setText(null);
                    emailTextField.setText(null);
                    phoneTextField.setText(null);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(thisobj, ex.getMessage());
                }
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                thisobj.dispose();
            }
        });
    }
}
