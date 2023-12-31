import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.text.MessageFormat;

public class AddBook extends JFrame {
    private JPanel mainPanel;
    private JTextField categoryTextField;
    private JTextField nameTextField;
    private JTextField authorTextField;
    private JTextField copiesTextField;
    private JButton backButton;
    private JButton addButton;
    private JLabel categoryLabel;
    private JLabel nameLabe;
    private JLabel authorLabel;
    private JLabel copiesLabel;
    private JPanel headerPanel;
    private JLabel headerTextfield;
    private final JFrame thisobj;

    public static void main(String[] args) {
        var addBook = new AddBook();
        addBook.setVisible(true);
    }

    public AddBook() {
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
                String query = "INSERT INTO BOOK (CATEGORY, NAME, AUTHOR, COPIES) VALUES(?,?,?,?)";
                String category = categoryTextField.getText().replace("'", "'");
                String name = nameTextField.getText();
                String author = authorTextField.getText().replace("'", "'");
                int copies = Integer.parseInt(copiesTextField.getText());
                String checkQuery = MessageFormat.format("UPDATE BOOK SET COPIES = COPIES + \"{0}\" WHERE NAME =\"{1}\" AND CATEGORY =\"{2}\" AND AUTHOR =\"{3}\";", copies, name, category, author);
                try {
                    var connection = DriverManager.getConnection(url, mysqluser, mysqlpassword);
                    var statement = connection.createStatement();
                    int rowCount = statement.executeUpdate(checkQuery);
                    if (rowCount > 0) {
                        String update = MessageFormat.format("Record \"{0}\" is updated successfully with \"{1}\" copies(s)", name, copies);
                        JOptionPane.showMessageDialog(thisobj, update);
                    } else {
                        var queryStatement = connection.prepareCall(query);
                        queryStatement.setString(1, category);
                        queryStatement.setString(2, name);
                        queryStatement.setString(3, author);
                        queryStatement.setInt(4, copies);
                        queryStatement.execute();
                        var message = new StringBuilder();
                        message.append("Record ").append("'").append(name).append("'").append(" ").append("is added successfuly ").append("with ").append(copies).append(" copie(s)");
                        JOptionPane.showMessageDialog(thisobj, message);
                    }
                    nameTextField.setText(null);
                    authorTextField.setText(null);
                    categoryTextField.setText(null);
                    copiesTextField.setText(null);
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
