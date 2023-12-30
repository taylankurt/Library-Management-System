import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class LoginPage extends  JFrame {
    private JLabel libraryLabel;
    private JPanel mainPanel;
    private JButton loginButton;
    private JPasswordField passwordField;
    private JTextField usernameField;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private final JFrame thisobj;

    public static void main(String[] args) {
        var login = new LoginPage();
        login.setVisible(true);
    }

    public LoginPage() {
        super();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
        this.setLocationRelativeTo(null);

        thisobj = this;
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String url = "jdbc:mysql://localhost:3306/library?useSSL=true";
                String mysqluser = "root";
                String mysqlpassword = "910401";
                String password = new String(passwordField.getPassword());
                String username = usernameField.getText();
                String query = ("SELECT PASSWORD FROM ADMIN WHERE USER_ID ='" + username + "';");

                try {
                    var connection = DriverManager.getConnection(url, mysqluser, mysqlpassword);
                    var statement = connection.createStatement();
                    var resulSet = statement.executeQuery(query);
                    if (resulSet.next()) {
                        String realPassword = resulSet.getString("PASSWORD");
                        if (realPassword.equals(password)) {
                            Dashboard dashboard = new Dashboard();
                            dashboard.setVisible(true);
                            thisobj.dispose();
                        } else
                            JOptionPane.showMessageDialog(thisobj, "username or password is false");
                    } else
                        JOptionPane.showMessageDialog(thisobj, "Wrong Username");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(thisobj, ex.getMessage());
                }
            }
        });
    }

}

