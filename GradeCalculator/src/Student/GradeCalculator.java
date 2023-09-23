package Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GradeCalculator extends JFrame {

    public JLabel[] subjectLabels;
    public JTextField[] marksTextFields;
    public JButton calculateGradesButton;
    public JButton resetButton;

    public GradeCalculator() {
        super("Student Grade Calculator");

        subjectLabels = new JLabel[6];
        marksTextFields = new JTextField[6];

        String[] subjects = {
            "Hindi", "English", "Mathematics",
            "Physics", "Chemistry", "Social Science"
        };

        for (int i = 0; i < 6; i++) {
            subjectLabels[i] = new JLabel(subjects[i]);
            marksTextFields[i] = new JTextField();
        }

        calculateGradesButton = new JButton("Calculate Grades");
        resetButton = new JButton("Reset");

        Color headerColor = new Color(52, 152, 219);
        Color buttonColor = new Color(41, 128, 185);

        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(6, 2, 14, 5));
        inputPanel.setOpaque(false);

        for (int i = 0; i < 6; i++) {
            subjectLabels[i].setFont(new Font("Segoe UI", Font.BOLD, 16));
            marksTextFields[i].setFont(new Font("Segoe UI", Font.PLAIN, 15));
            inputPanel.add(subjectLabels[i]);
            inputPanel.add(marksTextFields[i]);
        }

        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(headerColor);
        JLabel headerLabel = new JLabel("Student Grade Calculator");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 20));
        headerLabel.setForeground(Color.white);
        headerPanel.add(headerLabel);

        JPanel buttonPanel = new JPanel();
        calculateGradesButton.setBackground(buttonColor);
        calculateGradesButton.setForeground(Color.white);
        calculateGradesButton.setFocusPainted(false);
        buttonPanel.add(calculateGradesButton);

        resetButton.setBackground(buttonColor);
        resetButton.setForeground(Color.white);
        resetButton.setFocusPainted(false);
        buttonPanel.add(resetButton);

        contentPane.add(headerPanel, BorderLayout.NORTH);
        contentPane.add(inputPanel, BorderLayout.CENTER);
        contentPane.add(buttonPanel, BorderLayout.SOUTH);

        calculateGradesButton.addActionListener((ActionEvent e) -> {
            float totalMarks = 0;
            for (int i = 0; i < 6; i++) {
                try {
                    float marks = Float.parseFloat(marksTextFields[i].getText());
                    totalMarks += marks;
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Please enter valid marks for all subjects.");
                    return;
                }
            }

            float averagePercentage = totalMarks / 6;
            String grade = getGrade(averagePercentage);

            float overallMarks = totalMarks;
            String percentage = String.format("%.2f%%", averagePercentage);
            String result = "Overall Marks: " + overallMarks + "\n" +
                            "Percentage: " + percentage + "\n" +
                            "Grade: " + grade;

            JOptionPane.showMessageDialog(this, result, "Result", JOptionPane.INFORMATION_MESSAGE);
        });

        resetButton.addActionListener((ActionEvent e) -> {
            for (int i = 0; i < 6; i++) {
                marksTextFields[i].setText("");
            }
        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 400);
        setLocationRelativeTo(null);
        setResizable(false);
        setContentPane(contentPane);
        setVisible(true);
    }

    public String getGrade(float averagePercentage) {
        if (averagePercentage >= 90) {
            return "A";
        } else if (averagePercentage >= 80) {
            return "B";
        } else if (averagePercentage >= 70) {
            return "C";
        } else if (averagePercentage >= 60) {
            return "D";
        } else {
            return "F";
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new GradeCalculator();
        });
    }
}
