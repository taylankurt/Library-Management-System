import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.text.MessageFormat;

public class RemoveStaff extends JFrame {
    private JPanel mainPanel;
    private JTextField textField;
    private JButton CancelButton;
    private JButton DeleteButton;
    private final JFrame  thisobj;

    public static void main(String[] args) {
        var removeStaff = new RemoveStaff();
        removeStaff.setVisible(true);
    }

    public RemoveStaff(){
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
        this.setLocationRelativeTo(null);
        thisobj = this;
        DeleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String url = "jdbc:mysql://localhost:3306/library?useSSL=true";
                String mysqluser = "root";
                String mysqlpassword = "wahcaW-5cuzsi-haxfic";
                String text = textField.getText();
                String query = MessageFormat.format("Delete From STAFF Where STAFF_ID= \"{0}\";", text);
                try {
                    var connection = DriverManager.getConnection(url, mysqluser, mysqlpassword);
                    var statement = connection.createStatement();
                    int rows = statement.executeUpdate(query);
                    if(rows > 0)
                        JOptionPane.showMessageDialog(thisobj, MessageFormat.format("Staff \"{0}\" is removed from library", text));
                    else
                        JOptionPane.showMessageDialog(thisobj, "No such staff available");
                    statement.close();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(thisobj, ex.getMessage());
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
