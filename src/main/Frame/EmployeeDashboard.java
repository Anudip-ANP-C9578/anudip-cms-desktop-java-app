import javax.swing.*;

public class EmployeeDashboard extends JFrame {
    public EmployeeDashboard(Employee employee) {
        setTitle("Employee Dashboard");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel welcomeLabel = new JLabel("Welcome to the Dashboard, " + employee.getFirstName() + " " + employee.getLastName() + "!");
        add(welcomeLabel);
    }
}
