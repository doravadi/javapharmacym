import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class DrugStockReport extends JFrame {
    private JTable stockTable;
    private DefaultTableModel model;
    private JButton btnReturnToReporting;  // Geri dönüş butonu

    public DrugStockReport() {
        setTitle("Drug Stock Report");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        model = new DefaultTableModel(new Object[]{"Drug ID", "Name", "Type", "Price", "Quantity"}, 0);
        stockTable = new JTable(model);
        add(new JScrollPane(stockTable), BorderLayout.CENTER);

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

        refreshStockTable();
        setVisible(true);
    }

    private void refreshStockTable() {
        DefaultTableModel model = (DefaultTableModel) stockTable.getModel();
        model.setRowCount(0); // Clear the table
        List<Drug> drugs = new DrugStockDAO().getAllDrugsStock();
        for (Drug drug : drugs) {
            model.addRow(new Object[]{drug.getDrugId(), drug.getName(), drug.getType(), drug.getPrice(), drug.getQuantity()});
        }
    }

    public static void main(String[] args) {
        new DrugStockReport();
    }
}
