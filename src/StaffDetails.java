import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;

public class StaffDetails extends JFrame {
    private JPanel mainPanel;
    private JTable staffTable;
    private JLabel staffLabel;
    private JButton backButton;
    private JButton fetchButton;
    private JFrame thisobj;

    public StaffDetails() {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
        this.setLocationRelativeTo(null);
        thisobj = this;

        createTable();
        fetchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                var model = (DefaultTableModel) staffTable.getModel();
                String url = "jdbc:mysql://localhost:3306/library?useSSL=true";
                String mysqluser = "root";
                String mysqlpassword = "910401";
                String query = "SELECT * FROM STAFF;";
                try {
                    var connection = DriverManager.getConnection(url, mysqluser, mysqlpassword);
                    var statement = connection.createStatement();
                    var resultset = statement.executeQuery(query);
                    while (resultset.next()) {
                        String staffId = resultset.getString("STAFF_ID");
                        String firstName = resultset.getString("FIRST_NAME");
                        String lastName = resultset.getString("LAST_NAME");
                        String email = resultset.getString("EMAIL");
                        String phoneNumber = resultset.getString("PHONE_NUMBER");
                        model.addRow(new Object[]{staffId, firstName, lastName, email, phoneNumber,});
                    }
                    resultset.close();
                    statement.close();
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

    public static void main(String[] args) {
        var staffDetails = new StaffDetails();
        staffDetails.setVisible(true);
    }

    public void createTable() {
        staffTable.setModel(new DefaultTableModel(
                null,
                new String[]{"STAFF_ID", "FIRST_NAME", "LAST_NAME", "EMAIL", "PHONE_NUMBER"}));
        TableColumnModel columns = staffTable.getColumnModel();
    }
}

