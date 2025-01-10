import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserRegistrationGUI extends JFrame {
    private JTextField nameField, emailField;
    private JButton submitButton, clearButton;
    private JTextArea outputArea;

    public UserRegistrationGUI() {
        setTitle("User Registration");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(3, 2));
        inputPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        inputPanel.add(nameField);

        inputPanel.add(new JLabel("Email:"));
        emailField = new JTextField();
        inputPanel.add(emailField);

        submitButton = new JButton("Submit");
        clearButton = new JButton("Clear");
        inputPanel.add(submitButton);
        inputPanel.add(clearButton);

        add(inputPanel, BorderLayout.NORTH);

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        add(new JScrollPane(outputArea), BorderLayout.CENTER);

        // Add action listeners
        submitButton.addActionListener(new SubmitAction());
        clearButton.addActionListener(e -> clearFields());

        setVisible(true);
    }

    private void clearFields() {
        nameField.setText("");
        emailField.setText("");
        outputArea.setText("");
    }

    private class SubmitAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = nameField.getText().trim();
            String email = emailField.getText().trim();

            if (name.isEmpty() || email.isEmpty()) {
                JOptionPane.showMessageDialog(UserRegistrationGUI.this, "All fields are required.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!email.contains("@")) {
                JOptionPane.showMessageDialog(UserRegistrationGUI.this, "Invalid email format.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            outputArea.setText("User Registered Successfully!\nName: " + name + "\nEmail: " + email);
        }
    }

    public static void main(String[] args) {
        new UserRegistrationGUI();
    }
}
