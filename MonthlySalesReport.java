import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Calendar;
import java.util.List;

public class MonthlySalesReport extends JFrame {
    private JTable reportTable;
    private DefaultTableModel model;
    private JComboBox<String> monthComboBox;
    private JComboBox<Integer> yearComboBox;
    private JButton loadReportButton;
    private JButton btnReturnToReporting;  // Geri dönüş butonu için

    public MonthlySalesReport() {
        setTitle("Monthly Sales Report");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        model = new DefaultTableModel(new Object[]{"Drug Name", "Quantity Sold", "Total Price", "Sale Date"}, 0);
        reportTable = new JTable(model);
        add(new JScrollPane(reportTable), BorderLayout.CENTER);

        JPanel controlPanel = new JPanel();
        monthComboBox = new JComboBox<>(new String[]{"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"});
        yearComboBox = new JComboBox<>();
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        for (int i = currentYear; i <= currentYear + 5; i++) {
            yearComboBox.addItem(i);
        }
        loadReportButton = new JButton("Load Report");
        loadReportButton.addActionListener(this::loadReport);

        btnReturnToReporting = new JButton("Return to Reporting Screen");
        btnReturnToReporting.addActionListener(e -> {
            this.dispose(); // Close current window
            new ReportingScreen().setVisible(true); // Open the reporting screen
        });

        controlPanel.add(monthComboBox);
        controlPanel.add(yearComboBox);
        controlPanel.add(loadReportButton);
        controlPanel.add(btnReturnToReporting); // Geri dönüş butonunu panele ekle
        add(controlPanel, BorderLayout.NORTH);

        setVisible(true);
    }

    private void loadReport(ActionEvent e) {
        int year = (int) yearComboBox.getSelectedItem();
        int month = monthComboBox.getSelectedIndex() + 1; // Since JComboBox index starts at 0
        loadReport(year, month);
    }

    public void loadReport(int year, int month) {
        List<SaleReport> report = new MonthlySalesReportDAO().getMonthlySalesReport(year, month);
        DefaultTableModel model = (DefaultTableModel) reportTable.getModel();
        model.setRowCount(0); // Clear existing rows
        for (SaleReport sale : report) {
            model.addRow(new Object[]{sale.getDrugName(), sale.getQuantitySold(), sale.getTotalPrice(), sale.getSaleDate()});
        }
    }

    public static void main(String[] args) {
        new MonthlySalesReport();
    }
}
