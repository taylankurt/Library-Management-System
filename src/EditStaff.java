import org.w3c.dom.ls.LSOutput;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.text.MessageFormat;

public class EditStaff extends JFrame {
    private JPanel mainPanel;
    private JTable mainTable;
    private JButton fetchButton;
    private JTextField IdTextField;
    private JComboBox comboBox;
    private JTextField textField;
    private JButton updateButton;
    private JButton cancelButton;
    private JFrame thisobj;

    public static void main(String[] args) {
        var editStaff = new EditStaff();
        editStaff.setVisible(true);
    }

    public EditStaff() {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
        this.setLocationRelativeTo(null);
        thisobj = this;

        createTable();
        fetchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                var model = (DefaultTableModel) mainTable.getModel();
                String url = "jdbc:mysql://localhost:3306/library?useSSL=true";
                String mysqluser = "root";
                String mysqlpassword = "910401";
                String query = "SELECT * FROM STAFF;";
                try {
                    var connection = DriverManager.getConnection(url, mysqluser, mysqlpassword);
                    var statement = connection.createStatement();
                    var resultset = statement.executeQuery(query);
                    int rowCount = model.getRowCount();

                    if (rowCount > 0) {
                        for (int i = 0; i < rowCount; i++) {
                            model.removeRow(0);
                        }
                    }

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
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String url = "jdbc:mysql://localhost:3306/library?useSSL=true";
                String mysqlUser = "root";
                String mysqlPassword = "910401";
                String selectedField = comboBox.getSelectedItem().toString();
                String updatedValue = textField.getText();
                String staffId = IdTextField.getText();
                String query = MessageFormat.format("Update STAFF SET {0} = \"{1}\" WHERE STAFF_ID = {2};", selectedField, updatedValue, staffId);
                try {
                    var connection = DriverManager.getConnection(url, mysqlUser, mysqlPassword);
                    var statement = connection.createStatement();
                    int rowCount = statement.executeUpdate(query);
                    if (rowCount > 0) {
                        JOptionPane.showMessageDialog(thisobj, "Updated successfully");
                    } else
                        JOptionPane.showMessageDialog(thisobj, "Update failed");

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

    public void createTable() {
        mainTable.setModel(new DefaultTableModel(
                null,
                new String[]{"STAFF_ID", "FIRST_NAME", "LAST_NAME", "EMAIL", "PHONE_NUMBER"}));
        TableColumnModel columns = mainTable.getColumnModel();
        columns.getColumn(0).setMaxWidth(60);
        columns.getColumn(3).setMinWidth(100);
    }

}
