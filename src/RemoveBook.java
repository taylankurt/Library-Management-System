import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.text.MessageFormat;

public class RemoveBook extends JFrame {
    private JPanel mainPanel;
    private JTextField textField;
    private JButton deleteButton;
    private JButton cancelButton;
    private JTextField textFieldName;
    private final JFrame thisobj;


    public static void main(String[] args) {
        var removeBook = new RemoveBook();
        removeBook.setVisible(true);
    }

    public RemoveBook() {
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
        this.setLocationRelativeTo(null);
        thisobj = this;

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String url = "jdbc:mysql://localhost:3306/library?useSSL=true";
                String mysqlUser = "root";
                String mysqlPassword = "910401";
                String text = textField.getText();
                String queryString = MessageFormat.format("Delete From BOOK Where NAME= \"{0}\";", text);
                String queryInt = MessageFormat.format("Delete From BOOK Where BOOK_ID= \"{0}\";", text);
                try {
                    var connection = DriverManager.getConnection(url, mysqlUser, mysqlPassword);
                    var statement = connection.createStatement();
                    int rowCount = 0;
                    if (text.matches(".*[a-z].*")) {
                        int rows = statement.executeUpdate(queryString);
                        if(rows >0 )
                            rowCount ++;
                    } else {
                        int rows = statement.executeUpdate(queryInt);
                        if(rows >0 )
                            rowCount ++;
                    }
                    if (rowCount > 0)
                        JOptionPane.showMessageDialog(thisobj, "Book is removed from library");
                    else
                        JOptionPane.showMessageDialog(thisobj, "No such book available");
                    statement.close();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(thisobj, ex.getMessage());
                }
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                thisobj.dispose();

            }
        });
    }
}
