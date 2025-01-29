import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class TrackerPanel extends JPanel {
    private JTextField heightInput, weightInput, ageInput;
    private JComboBox<String> genderComboBox;
    private FitnessTrackerApp app;

    public TrackerPanel(FitnessTrackerApp app) {
        this.app = app;
        setLayout(new BorderLayout());

        JPanel centerPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;

        heightInput = new JTextField(5);
        weightInput = new JTextField(5);
        ageInput = new JTextField(3);
        String[] genders = {"Male", "Female", "Other"};
        genderComboBox = new JComboBox<>(genders);

        JButton bmiButton = new JButton("Calculate BMI");
        bmiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double height = Double.parseDouble(heightInput.getText());
                    double weight = Double.parseDouble(weightInput.getText());
                    int age = Integer.parseInt(ageInput.getText());
                    String gender = (String) genderComboBox.getSelectedItem();
                    double bmi = calculateBMI(height, weight);
                    String status = getBMIStatus(bmi);
                    
                    // Save user details
                    saveUserDetails(app.getCurrentUsername(), height, weight, age, gender);
                    
                    // Show BMI status dialog
                    JOptionPane.showMessageDialog(TrackerPanel.this, "Your BMI status is: " + status);
                    
                    // Show graphical representation
                    new BMIGraphPanel(bmi, status).setVisible(true);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(TrackerPanel.this, "Please enter valid numbers", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 0;
        centerPanel.add(new JLabel("Height (cm):"), gbc);
        gbc.gridx = 1;
        centerPanel.add(heightInput, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        centerPanel.add(new JLabel("Weight (kg):"), gbc);
        gbc.gridx = 1;
        centerPanel.add(weightInput, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        centerPanel.add(new JLabel("Age:"), gbc);
        gbc.gridx = 1;
        centerPanel.add(ageInput, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        centerPanel.add(new JLabel("Gender:"), gbc);
        gbc.gridx = 1;
        centerPanel.add(genderComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        centerPanel.add(bmiButton, gbc);

        add(centerPanel);
    }

    private double calculateBMI(double height, double weight) {
        double heightInMeters = height / 100; // convert height to meters
        return weight / (heightInMeters * heightInMeters);
    }

    private String getBMIStatus(double bmi) {
        if (bmi < 18.5) return "Underweight";
        else if (bmi < 24.9) return "Normal weight";
        else if (bmi < 29.9) return "Overweight";
        else return "Obesity";
    }

    public void setUserDetails(double height, double weight, int age, String gender) {
        heightInput.setText(String.valueOf(height));
        weightInput.setText(String.valueOf(weight));
        ageInput.setText(String.valueOf(age));
        genderComboBox.setSelectedItem(gender);
    }

    private void saveUserDetails(String username, double height, double weight, int age, String gender) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO user_details (username, height, weight, age, gender) VALUES (?, ?, ?, ?, ?) ON DUPLICATE KEY UPDATE height=?, weight=?, age=?, gender=?")) {
            stmt.setString(1, username);
            stmt.setDouble(2, height);
            stmt.setDouble(3, weight);
            stmt.setInt(4, age);
            stmt.setString(5, gender);
            stmt.setDouble(6, height);
            stmt.setDouble(7, weight);
            stmt.setInt(8, age);
            stmt.setString(9, gender);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
