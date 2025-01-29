import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class BMIGraphPanel extends JFrame {
    private double bmi;
    private String status;
    private static final String[][] DIET_PLANS = new String[100][2];
    private static final String[][] EXERCISE_PLANS = new String[100][2];

    public BMIGraphPanel(double bmi, String status) {
        this.bmi = bmi;
        this.status = status;
        setTitle("BMI Trend Graph");
        setSize(600, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create the dataset
        XYSeries series = new XYSeries("BMI Over Time");

        // Example: Let's simulate the last 7 days of BMI data (just for illustration)
        for (int i = 0; i < 7; i++) {
            series.add(i + 1, bmi + (Math.random() - 0.5)); // Simulating some variation
        }

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series);

        // Create the chart
        JFreeChart chart = ChartFactory.createXYLineChart(
                "BMI Trend", // Chart title
                "Days", // X-axis label
                "BMI", // Y-axis label
                dataset // Dataset
        );

        // Add the chart to a panel
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(600, 400));

        // Create OK button
        JButton okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayPersonalizedPlan();
            }
        });

        // Create a panel to hold the chart and button
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(chartPanel, BorderLayout.CENTER);
        panel.add(okButton, BorderLayout.SOUTH);
        
        setContentPane(panel);

        // Initialize diet and exercise plans
        initializePlans();
    }

    private void initializePlans() {
        // Sample Diet Plans (100 variations)
        for (int i = 0; i < 100; i++) {
            DIET_PLANS[i][0] = "Diet Plan " + (i + 1) + ":\n" +
                    "- Breakfast: Option A\n" +
                    "- Snack: Option B\n" +
                    "- Lunch: Option C\n" +
                    "- Snack: Option D\n" +
                    "- Dinner: Option E\n";
            DIET_PLANS[i][1] = "Diet Plan " + (i + 1) + " (Healthy Choices):\n" +
                    "- Breakfast: Whole grain toast with avocado or Greek yogurt with fruit\n" +
                    "- Snack: Raw veggies with hummus or a handful of nuts\n" +
                    "- Lunch: Grilled chicken salad or quinoa with roasted vegetables\n" +
                    "- Snack: Protein shake or cottage cheese with pineapple\n" +
                    "- Dinner: Baked salmon with broccoli or turkey stir-fry with brown rice\n";
        }

        // Sample Exercise Plans (100 variations)
        for (int i = 0; i < 100; i++) {
            EXERCISE_PLANS[i][0] = "Exercise Plan " + (i + 1) + ":\n" +
                    "- Cardio: 30 minutes of jogging\n" +
                    "- Strength: 20 minutes of bodyweight exercises\n" +
                    "- Flexibility: 10 minutes of stretching\n";
            EXERCISE_PLANS[i][1] = "Exercise Plan " + (i + 1) + " (Moderate):\n" +
                    "- Cardio: 45 minutes cycling\n" +
                    "- Strength: 30 minutes of weightlifting\n" +
                    "- Flexibility: Yoga session for 30 minutes\n";
        }
    }

    private void displayPersonalizedPlan() {
        String dietPlan = generateDietPlan(bmi);
        String exercisePlan = generateExercisePlan(bmi);

        String message = "Personalized Diet Plan:\n" + dietPlan +
                "\n\nPersonalized Exercise Plan:\n" + exercisePlan;

        JOptionPane.showMessageDialog(this, message, "Personalized Plan", JOptionPane.INFORMATION_MESSAGE);
    }

    private String generateDietPlan(double bmi) {
        Random rand = new Random();
        int index = rand.nextInt(100); // Randomly select a diet plan

        if (bmi < 18.5) {
            return DIET_PLANS[index][0]; // Diet plans for weight gain
        } else if (bmi < 24.9) {
            return DIET_PLANS[index][1]; // Diet plans for maintenance
        } else if (bmi < 29.9) {
            return DIET_PLANS[index][0]; // Diet plans for weight loss
        } else {
            return DIET_PLANS[index][1]; // Diet plans for obesity management
        }
    }

    private String generateExercisePlan(double bmi) {
        Random rand = new Random();
        int index = rand.nextInt(100); // Randomly select an exercise plan

        if (bmi < 18.5) {
            return EXERCISE_PLANS[index][0]; // Exercise plans for muscle gain
        } else if (bmi < 24.9) {
            return EXERCISE_PLANS[index][1]; // Exercise plans for maintenance
        } else if (bmi < 29.9) {
            return EXERCISE_PLANS[index][0]; // Exercise plans for weight loss
        } else {
            return EXERCISE_PLANS[index][1]; // Exercise plans for obesity management
        }
    }
}
