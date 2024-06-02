import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JFrame {
    public MainMenu() {
        setTitle("Pharmacy Management System - Main Menu");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 1, 10, 10));

        // Gradyan arka planı ayarla
        JPanel mainPanel = new JPanel(new GridLayout(4, 1, 10, 10)) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setPaint(new GradientPaint(0, 0, Color.BLUE, getWidth(), getHeight(), Color.GREEN, true));
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        setContentPane(mainPanel);

        // Menü butonları
        JButton btnManageDrugs = new JButton("Manage Drugs");
        JButton btnManageEmployees = new JButton("Manage Employees");
        JButton btnManageSales = new JButton("Manage Sales");
        JButton btnReports = new JButton("Reports");

        // Butonları özelleştir
        customizeButton(btnManageDrugs);
        customizeButton(btnManageEmployees);
        customizeButton(btnManageSales);
        customizeButton(btnReports);

        // Butonlar için ActionListener ekleme
        btnManageDrugs.addActionListener(e -> openDrugManagement());
        btnManageEmployees.addActionListener(e -> openEmployeeManagement());
        btnManageSales.addActionListener(e -> openSalesManagement());
        btnReports.addActionListener(e -> openReports());

        // Butonları ana panele ekleme
        mainPanel.add(btnManageDrugs);
        mainPanel.add(btnManageEmployees);
        mainPanel.add(btnManageSales);
        mainPanel.add(btnReports);

        setVisible(true);
    }

    private void customizeButton(JButton button) {
        // Buton için gradyan efekti uygula
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 12));
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

    private void openDrugManagement() {
        this.dispose(); // Şu anki pencereyi kapat
        new DrugManagement().setVisible(true);
    }

    private void openEmployeeManagement() {
        dispose();
        new EmployeeManagement();
    }

    private void openSalesManagement() {
        dispose();
        new SalesManagement();
    }

    private void openReports() {
        dispose(); // Welcome ekranını kapat
        new ReportingScreen();
    }

    public static void main(String[] args) {
        new MainMenu();
    }
}
