import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.List;

public class SalesManagement extends JFrame {
    private JTable drugsTable;
    private JTextField txtQuantity, txtSaleDate;
    private JButton btnCompleteSale,btnReturnToMainMenu;
    private DefaultTableModel model;

    public SalesManagement() {
        setTitle("Sales Management");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        model = new DefaultTableModel(new Object[]{"Drug ID", "Name", "Type", "Manufacture Date", "Expiry Date", "Price", "Quantity"}, 0);
        drugsTable = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(drugsTable);
        add(scrollPane, BorderLayout.CENTER);

        JPanel salePanel = new JPanel(new FlowLayout());
        txtQuantity = new JTextField(5);
        txtSaleDate = new JTextField(10);
        btnCompleteSale = new JButton("Complete Sale");
        btnReturnToMainMenu = new JButton("Return to Main Menu");
        salePanel.add(new JLabel("Quantity:"));
        salePanel.add(txtQuantity);
        salePanel.add(new JLabel("Sale Date (yyyy-mm-dd):"));
        salePanel.add(txtSaleDate);
        salePanel.add(btnCompleteSale);
        salePanel.add(btnReturnToMainMenu);
        add(salePanel, BorderLayout.SOUTH);

        btnCompleteSale.addActionListener(this::completeSale);
        btnReturnToMainMenu.addActionListener(this::returnToMainMenu);

        refreshDrugsTable();
        setVisible(true);
    }

    private void returnToMainMenu(ActionEvent e) {
        this.dispose();
        new MainMenu();

    }

    private void refreshDrugsTable() {
        DefaultTableModel model = (DefaultTableModel) drugsTable.getModel();
        model.setRowCount(0);
        List<Drug> drugs = new SalesManagementDAO().getAllDrugs();
        for (Drug drug : drugs) {
            model.addRow(new Object[]{drug.getDrugId(), drug.getName(), drug.getType(), drug.getManufactureDate(), drug.getExpiryDate(), drug.getPrice(), drug.getQuantity()});
        }
    }

    private void completeSale(ActionEvent e) {
        int selectedRow = drugsTable.getSelectedRow();
        if (selectedRow != -1) {
            int drugId = Integer.parseInt(drugsTable.getValueAt(selectedRow, 0).toString());
            int quantity = Integer.parseInt(txtQuantity.getText());
            double price = Double.parseDouble(drugsTable.getValueAt(selectedRow, 5).toString());
            double totalPrice = quantity * price;

            try {
                java.sql.Date saleDate = new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(txtSaleDate.getText()).getTime());
                SalesManagementDAO dao = new SalesManagementDAO();
                dao.addSale(drugId, quantity, saleDate, totalPrice);
                dao.updateDrugQuantity(drugId, quantity);  // Stok miktarını güncelle
                JOptionPane.showMessageDialog(this, "Sale completed successfully.", "Sale Complete", JOptionPane.INFORMATION_MESSAGE);
                refreshDrugsTable();  // Tabloyu yenile
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid date format or database error.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a drug to sell.", "Selection Required", JOptionPane.WARNING_MESSAGE);
        }
    }


    public static void main(String[] args) {
        new SalesManagement();
    }
}
