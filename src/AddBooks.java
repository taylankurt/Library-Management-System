import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;

public class AddBooks extends JFrame {
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
    private JFrame thisobj;

    public static void main(String[] args) {
        var addBook = new AddBooks();
        addBook.setVisible(true);
    }

    public AddBooks() {
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
                String mysqlpassword = "wahcaW-5cuzsi-haxfic";
                String query = "INSERT INTO BOOK (CATEGORY, NAME, AUTHOR, COPIES) VALUES(?,?,?,?)";
                String category = categoryTextField.getText().replace("'","\'");
                String name = nameTextField.getText().replace("'","\'");
                String author = authorTextField.getText().replace("'","\'");
                int copies = Integer.parseInt(copiesTextField.getText());
                String checkQuery = "UPDATE BOOK SET COPIES = COPIES +"+copies+" WHERE NAME ='"+ name +"' AND CATEGORY ='"+ category +"' AND AUTHOR ='"+ author +"';";
                try {
                    var connection = DriverManager.getConnection(url, mysqluser, mysqlpassword);
                    var statement = connection.createStatement();
                    int rowCount = statement.executeUpdate(checkQuery);
                    if (rowCount > 0) {
                        var update = new StringBuilder();
                        update.append("Record ").append("'").append(name).append("'").append(" ").append("is updated successfuly ")
                                .append("with ").append(copies).append(" copie(s)");
                        JOptionPane.showMessageDialog(thisobj, update);
                    } else {
                        var queryStatement = connection.prepareCall(query);
                        queryStatement.setString(1, category);
                        queryStatement.setString(2, name);
                        queryStatement.setString(3, author);
                        queryStatement.setInt(4, copies);
                        queryStatement.execute();
                        var message = new StringBuilder();
                        message.append("Record ").append("'").append(name).append("'").append(" ").append("is added successfuly ")
                                .append("with ").append(copies).append(" copie(s)");
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
