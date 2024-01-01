import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.text.TableView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;


public class BooksAvailable extends JFrame {
    private JPanel mainPanel;
    private JTable mainTable;
    private JButton fetchButton;
    private JButton backButton;
    private JPanel buttonPanel;
    private final JFrame thisobj;


    public static void main(String[] args) {
        var booksAvailable = new BooksAvailable();
        booksAvailable.setVisible(true);
    }

    public BooksAvailable() {

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
                String query = "SELECT * FROM BOOK;";
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
                        String bookId = resultset.getString("BOOK_ID");
                        String category = resultset.getString("CATEGORY");
                        String name = resultset.getString("NAME");
                        String author = resultset.getString("AUTHOR");
                        int copies = resultset.getInt("COPIES");
                        model.addRow(new Object[]{bookId, category, name, author, copies});
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

    public void createTable() {
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
