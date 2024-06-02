import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ReportingScreen extends JFrame {
    public ReportingScreen() {
        setTitle("Reports");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 1));

        JPanel mainPanel = new JPanel(new GridLayout(4, 1)) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setPaint(new GradientPaint(0, 0, Color.BLUE, getHeight(), getWidth(), Color.GREEN, true));
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        setContentPane(mainPanel);

        JButton btnSalesReport = new JButton("Monthly Sales Report");
        JButton btnStockReport = new JButton("Drug Stock Report");
        JButton btnPerformanceReport = new JButton("Employee Performance Report");
        JButton btnReturnToMainMenu = new JButton("Main Menu");
        customizeButton(btnSalesReport);
        customizeButton(btnStockReport);
        customizeButton(btnPerformanceReport);
        customizeButton(btnReturnToMainMenu);

        btnSalesReport.addActionListener(e -> {
            MonthlySalesReport msr = new MonthlySalesReport();
            msr.setVisible(true);
            this.dispose();  // Close this window
        });

        btnStockReport.addActionListener(e -> {
            DrugStockReport dsr = new DrugStockReport();
            dsr.setVisible(true);
            this.dispose();  // Close this window
        });

        btnPerformanceReport.addActionListener(e -> {
            EmployeePerformanceReport epr = new EmployeePerformanceReport();
            epr.setVisible(true);
            this.dispose();  // Close this window
        });

        btnReturnToMainMenu.addActionListener(e -> returnToMainMenu());

        mainPanel.add(btnSalesReport);
        mainPanel.add(btnStockReport);
        mainPanel.add(btnPerformanceReport);
        mainPanel.add(btnReturnToMainMenu);

        setVisible(true);
    }

    private void returnToMainMenu() {
        new MainMenu().setVisible(true);
        this.dispose();  // Close this window
    }

    private void customizeButton(JButton button) {
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setOpaque(true);
                button.setBackground(new Color(0, 123, 255));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setOpaque(false);
            }
        });
    }

    public static void main(String[] args) {
        new ReportingScreen();
    }
}
