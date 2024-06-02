import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomeScreen extends JFrame {
    public WelcomeScreen() {
        setTitle("Welcome to Pharmacy Management System");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Gradyan arka planı ayarla
        JPanel mainPanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setPaint(new GradientPaint(0, 0, Color.BLUE, getWidth(), getHeight(), Color.GREEN, true));
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        setContentPane(mainPanel);

        // Üst kısımda hoş geldiniz mesajı
        JLabel welcomeLabel = new JLabel("Welcome To Pharmacy Management System", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        welcomeLabel.setForeground(Color.WHITE);
        mainPanel.add(welcomeLabel, BorderLayout.NORTH);

        // Orta kısımda resim
        ImageIcon imageIcon = new ImageIcon("imgs/pharmacylogo.jpg"); // Resmin yolu
        JLabel imageLabel = new JLabel(imageIcon);
        mainPanel.add(imageLabel, BorderLayout.CENTER);

        // Alt kısımda "Enter" butonu
        JButton enterButton = new JButton("Enter");
        customizeButton(enterButton);
        enterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Welcome ekranını kapat
                new MainMenu(); // Ana menüyü aç
            }
        });
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.add(enterButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

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

    public static void main(String[] args) {
        new WelcomeScreen();
    }
}
