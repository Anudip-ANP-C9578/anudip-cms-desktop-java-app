import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginFrame extends JFrame {
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton signInButton, clearButton;

    public LoginFrame() {
        setTitle("Login");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 2));

        emailField = new JTextField();
        passwordField = new JPasswordField();

        signInButton = new JButton("Sign In");
        clearButton = new JButton("Clear");

        add(new JLabel("Email:"));
        add(emailField);
        add(new JLabel("Password:"));
        add(passwordField);
        add(signInButton);
        add(clearButton);

        signInButton.addActionListener(e -> {
            try {
                String email = emailField.getText();
                String password = new String(passwordField.getPassword());

                EmployeeDAO dao = new EmployeeDAO();
                Employee employee = dao.validateCredentials(email, password);

                if (employee != null) {
                    JOptionPane.showMessageDialog(this, "Welcome " + employee.getFirstName() + " " + employee.getLastName(), "Login Successful", JOptionPane.INFORMATION_MESSAGE);
                    new EmployeeDashboard(employee).setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid Access. Try Again", "Login Failed", JOptionPane.ERROR_MESSAGE);
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        clearButton.addActionListener(e -> {
            emailField.setText("");
            passwordField.setText("");
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginFrame().setVisible(true));
    }
}
