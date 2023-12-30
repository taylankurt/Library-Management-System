import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Dashboard extends JFrame {
    private JButton removeBooksButton;
    private JButton addBooksButton;
    private JButton staffDashboardButton;
    private JButton LibraryDashboardButton;
    private JButton addStaffButton;
    private JButton removeStaffButton;
    private JButton editAdminButton;
    private JPanel mainPanel;
    private JLabel labelDashboard;
    private final JFrame thisobj;



    public static void main(String[] args) {
        var library = new Dashboard();
        library.setVisible(true);
    }
    public Dashboard(){

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
        this.setLocationRelativeTo(null);
        thisobj = this;

        LibraryDashboardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                var books = new LibraryDashboard();
                books.setVisible(true);
            }
        });

        staffDashboardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                var staffDashboard = new StaffDashboard();
                staffDashboard.setVisible(true);
            }
        });
        editAdminButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                var editAdmin = new EditAdmin();
                editAdmin.setVisible(true);
            }
        });
    }

}
