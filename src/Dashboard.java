import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Dashboard extends JFrame {
    private JButton removeBooksButton;
    private JButton addBooksButton;
    private JButton staffDetailsButton;
    private JButton booksAvailableButton;
    private JButton addStaffButton;
    private JButton removeStaffButton;
    private JButton editAdminButton;
    private JPanel mainPanel;
    private JLabel labelDashboard;
    private JFrame thisobj;



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

        booksAvailableButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                var books = new BooksAvailable();
                books.setVisible(true);
            }
        });

        addBooksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                var books = new AddBooks();
                books.setVisible(true);
            }
        });
        staffDetailsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                var staffDetails = new StaffDetails();
                staffDetails.setVisible(true);
            }
        });
        addStaffButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                var addStaff = new AddStaff();
                addStaff.setVisible(true);
            }
        });
        removeBooksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                var removeBook = new RemoveBook();
                removeBook.setVisible(true);
            }
        });
    }

}
