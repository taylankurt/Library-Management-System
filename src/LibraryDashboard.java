import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LibraryDashboard extends JFrame{
    private JPanel mainPanel;
    private JButton booksAvailableButton;
    private JButton editBookButton;
    private JButton removeBookButton;
    private JButton addBookButton;

    public static void main(String[] args) {
        var libraryDashboard = new LibraryDashboard();
        libraryDashboard.setVisible(true);
    }

    public LibraryDashboard(){
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
        this.setLocationRelativeTo(null);
        booksAvailableButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                var booksAvailable = new BooksAvailable();
                booksAvailable.setVisible(true);
            }
        });
        addBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                var addBook = new AddBook();
                addBook.setVisible(true);
            }
        });
        removeBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                var removeBook = new RemoveBook();
                removeBook.setVisible(true);
            }
        });
        editBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                var editBook = new EditBook();
                editBook.setVisible(true);
            }
        });
    }
}
