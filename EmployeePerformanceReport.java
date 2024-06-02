import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class EmployeePerformanceReport extends JFrame {
    private JTable performanceTable;
    private DefaultTableModel model;
    private JButton btnReturnToReporting;

    public EmployeePerformanceReport() {
        setTitle("Employee Performance Report");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Table model and table setup
        model = new DefaultTableModel(new Object[]{"Employee ID", "Name", "Position", "Performance Status"}, 0);
        performanceTable = new JTable(model);
        add(new JScrollPane(performanceTable), BorderLayout.CENTER);

        // Button to return to reporting screen
        btnReturnToReporting = new JButton("Return to Reporting Screen");
        btnReturnToReporting.addActionListener(e -> {
            this.dispose(); // Close current window
            new ReportingScreen().setVisible(true); // Open the reporting screen
        });

        // Panel to hold the button
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnReturnToReporting);
        add(buttonPanel, BorderLayout.SOUTH);

        // Load data into the table
        loadPerformanceData();
        setVisible(true);
    }

    private void loadPerformanceData() {
        DefaultTableModel model = (DefaultTableModel) performanceTable.getModel();
        model.setRowCount(0); // Clear the table
        List<EmployeePerformance> performances = new EmployeePerformanceReportDAO().getEmployeePerformances();
        for (EmployeePerformance performance : performances) {
            model.addRow(new Object[]{performance.getEmployeeId(), performance.getName(), performance.getPosition(), performance.getPerformanceStatus()});
        }
    }

    public static void main(String[] args) {
        new EmployeePerformanceReport();
    }
}
