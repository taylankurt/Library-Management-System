import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.text.MessageFormat;

public class EditBook extends JFrame {
    private JPanel mainPanel;
    private JTable mainTable;
    private JButton fetchButton;
    private JComboBox comboBox;
    private JTextField textField;
    private JButton updateButton;
    private JButton cancelButton;
    private JTextField IdTextField;
    private final JFrame thisobj;

    public static void main(String[] args) {
        var editBook = new EditBook();
        editBook.setVisible(true);
    }

    public EditBook() {
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
                String mysqlUser = "root";
                String mysqlPassword = "910401";
                String query = "SELECT * FROM BOOK;";
                try{
                    var connection = DriverManager.getConnection(url,mysqlUser,mysqlPassword);
                    var statement = connection.createStatement();
                    var resultset = statement.executeQuery(query);
                    int rowCount = model.getRowCount();
                    if (rowCount > 0){
                        for (int i = 0; i < rowCount ; i++) {
                            model.removeRow(0);
                        }
                    }
                    while (resultset.next()){
                        String bookId = resultset.getString("BOOK_ID");
                        String category = resultset.getString("CATEGORY");
                        String name = resultset.getString("NAME");
                        String author = resultset.getString("AUTHOR");
                        int copies = resultset.getInt("COPIES");
                        model.addRow(new Object[]{bookId, category, name, author, copies});
                    }
                    resultset.close();
                    statement.close();
                }
                catch (Exception ex){
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
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String url = "jdbc:mysql://localhost:3306/library?useSSL=true";
                String mysqlUser = "root";
                String mysqlPassword = "910401";
                String selectedField = comboBox.getSelectedItem().toString();
                String updatedValue = textField.getText();
                String BookId = IdTextField.getText();
                String query = MessageFormat.format("Update BOOK SET {0} = \"{1}\" WHERE BOOK_ID = {2};", selectedField, updatedValue, BookId );
                try {
                    var connection = DriverManager.getConnection(url, mysqlUser, mysqlPassword);
                    var statement = connection.createStatement();
                    int rowCount = statement.executeUpdate(query);
                    if (rowCount > 0)
                        JOptionPane.showMessageDialog(thisobj, "Updated successfully");
                    else
                        JOptionPane.showMessageDialog(thisobj, "Update failed");

                }catch (Exception ex) {
                    JOptionPane.showMessageDialog(thisobj, ex.getMessage());
                }
            }
        });
    }

    public void createTable(){
        mainTable.setModel(new DefaultTableModel(
                null,
                new String[]{"BOOK_ID", "CATEGORY", "NAME", "AUTHOR", "COPIES"}
        ));

        TableColumnModel columns = mainTable.getColumnModel();
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        leftRenderer.setHorizontalAlignment(JLabel.LEFT);

        columns.getColumn(2).setMinWidth(100);
        columns.getColumn(0).setCellRenderer(leftRenderer);
        columns.getColumn(1).setCellRenderer(leftRenderer);
        columns.getColumn(2).setCellRenderer(leftRenderer);
        columns.getColumn(3).setCellRenderer(leftRenderer);
        columns.getColumn(4).setCellRenderer(centerRenderer);

    }

}
