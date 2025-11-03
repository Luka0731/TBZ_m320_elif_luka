package Design;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SwingUserInputExample {

    public static void main(String[] args) {
        // Run GUI in the Event Dispatch Thread for thread safety
        SwingUtilities.invokeLater(SwingUserInputExample::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Swing User Input Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLayout(new FlowLayout());

        JButton btnDialog = new JButton("Get Input (Custom Dialog)");
        JButton btnSimple = new JButton("Get Input (Simple)");

        // Custom dialog with JTextField
        btnDialog.addActionListener(e -> {
            JTextField textField = new JTextField(15);
            JPanel panel = new JPanel();
            panel.add(new JLabel("Enter your name:"));
            panel.add(textField);

            int result = JOptionPane.showConfirmDialog(
                    frame,
                    panel,
                    "User Input",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE
            );

            if (result == JOptionPane.OK_OPTION) {
                String input = textField.getText().trim();
                if (input.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Input cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(frame, "Hello, " + input + "!");
                }
            }
        });

        // Simple one-line input dialog
        btnSimple.addActionListener(e -> {
            String input = JOptionPane.showInputDialog(frame, "Enter your age:");
            if (input != null) { // null means user pressed Cancel
                input = input.trim();
                try {
                    int age = Integer.parseInt(input);
                    if (age <= 0) {
                        JOptionPane.showMessageDialog(frame, "Age must be positive!", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(frame, "You are " + age + " years old.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Please enter a valid number!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        frame.add(btnDialog);
        frame.add(btnSimple);
        frame.setLocationRelativeTo(null); // Center window
        frame.setVisible(true);
    }
}
