import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StaffDashboard extends JFrame {
    private JButton staffDetailsButton;
    private JButton editStaffButton;
    private JButton removeStaffButton;
    private JButton addStaffButton;
    private JPanel mainPanel;

    public static void main(String[] args) {
        var staffDashboard = new StaffDashboard();
        staffDashboard.setVisible(true);
    }

    public StaffDashboard(){
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
        this.setLocationRelativeTo(null);

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
        removeStaffButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                var removeStaff = new RemoveStaff();
                removeStaff.setVisible(true);
            }
        });
    }
}
