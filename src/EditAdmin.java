import javax.swing.*;
import java.sql.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.MessageFormat;

public class EditAdmin extends JFrame {
    private JComboBox comboBox;
    private JTextField textField;
    private JButton CancelButton;
    private JButton UpdateButton;
    private JPanel mainpanel;
    private JFrame thisobj;

    public static void main(String[] args) {
        var editAdmin = new EditAdmin();
        editAdmin.setVisible(true);
    }
    public EditAdmin() {
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setContentPane(mainpanel);
        this.pack();
        this.setLocationRelativeTo(null);
        thisobj = this;
        UpdateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String url = "jdbc:mysql://localhost:3306/library?useSSL=true";
                String mysqluser = "root";
                String mysqlpassword = "910401";
                String text = textField.getText();
                String column = comboBox.getSelectedItem().toString();
                String query = MessageFormat.format( "UPDATE ADMIN SET {0}=\"{1}\";",column, text);
                try{
                    var connection = DriverManager.getConnection(url, mysqluser, mysqlpassword);
                    var statement = connection.createStatement();
                    int rows = statement.executeUpdate(query);
                    if(rows > 0)
                        JOptionPane.showMessageDialog(thisobj, "Credentials updated successfully");
                    textField.setText("");
                }
                catch (Exception ex){
                    JOptionPane.showMessageDialog(thisobj, ex);
                }
            }
        });
        CancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                thisobj.dispose();
            }
        });
    }
}
