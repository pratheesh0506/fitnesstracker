import javax.swing.*;
import java.awt.*;

public class FitnessTrackerApp extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private LoginPanel loginPanel;
    private TrackerPanel trackerPanel;
    private String currentUsername; // Add this variable

    public FitnessTrackerApp() {
        setTitle("Fitness Tracker");
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        loginPanel = new LoginPanel(this);
        trackerPanel = new TrackerPanel(this);

        mainPanel.add(loginPanel, "Login");
        mainPanel.add(trackerPanel, "Tracker");

        add(mainPanel);
    }

    public void showTracker() {
        cardLayout.show(mainPanel, "Tracker");
    }

    public void setCurrentUsername(String username) { // Add this method
        this.currentUsername = username;
    }

    public String getCurrentUsername() { // Add this method
        return currentUsername;
    }

    public TrackerPanel getTrackerPanel() { // Method to access TrackerPanel
        return trackerPanel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FitnessTrackerApp fitnessTracker = new FitnessTrackerApp();
            fitnessTracker.setVisible(true);
        });
    }
}
