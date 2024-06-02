import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class DrugManagement extends JFrame {
    private JTable table;
    private JTextField txtName, txtType, txtPrice, txtQuantity, txtManufactureDate, txtExpiryDate;
    private JButton btnSave, btnUpdate, btnDelete, btnReturnToMainMenu;
    private DefaultTableModel model;

    public DrugManagement() {
        setTitle("Drug Management");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        initializeUIComponents();
        initializeTable();
        setVisible(true);
    }

    private void initializeUIComponents() {
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(3, 4, 10, 10));
        txtName = new JTextField();
        txtType = new JTextField();
        txtManufactureDate = new JTextField();
        txtExpiryDate = new JTextField();
        txtPrice = new JTextField();
        txtQuantity = new JTextField();
        formPanel.add(new JLabel("Name:"));
        formPanel.add(txtName);
        formPanel.add(new JLabel("Type:"));
        formPanel.add(txtType);
        formPanel.add(new JLabel("Manufacture Date:"));
        formPanel.add(txtManufactureDate);
        formPanel.add(new JLabel("Expiry Date:"));
        formPanel.add(txtExpiryDate);
        formPanel.add(new JLabel("Price:"));
        formPanel.add(txtPrice);
        formPanel.add(new JLabel("Quantity:"));
        formPanel.add(txtQuantity);
        add(formPanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        btnSave = new JButton("Save");
        btnUpdate = new JButton("Update");
        btnDelete = new JButton("Delete");
        btnReturnToMainMenu = new JButton("Return to Main Menu");
        customizeButton(btnSave);
        customizeButton(btnUpdate);
        customizeButton(btnDelete);
        customizeButton(btnReturnToMainMenu);
        buttonPanel.add(btnSave);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnReturnToMainMenu);
        add(buttonPanel, BorderLayout.SOUTH);

        btnSave.addActionListener(this::saveDrug);
        btnUpdate.addActionListener(this::updateDrug);
        btnDelete.addActionListener(this::deleteDrug);
        btnReturnToMainMenu.addActionListener(this::returnToMainMenu);
    }

    private void returnToMainMenu(ActionEvent event) {
        this.dispose(); // Şu anki pencereyi kapat
        new MainMenu().setVisible(true); // MainMenu sınıfının bir örneğini oluştur ve göster
    }

    private void initializeTable() {
        String[] columnNames = {"Drug ID", "Name", "Type", "Manufacture Date", "Expiry Date", "Price", "Quantity"};
        model = new DefaultTableModel(null, columnNames);
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
        refreshTable();
    }

    private void customizeButton(JButton button) {
        button.setOpaque(true);
        button.setContentAreaFilled(true);
        button.setBorderPainted(true);
        button.setBackground(new Color(0, 123, 255));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 12));
    }

    private void saveDrug(ActionEvent event) {
        String name = txtName.getText();
        String type = txtType.getText();
        String manufactureDate = txtManufactureDate.getText();
        String expiryDate = txtExpiryDate.getText();
        double price = Double.parseDouble(txtPrice.getText());
        int quantity = Integer.parseInt(txtQuantity.getText());
        DrugManagementDAO dao = new DrugManagementDAO();
        dao.addDrug(name, type, manufactureDate, expiryDate, price, quantity);
        refreshTable();
    }

    private void updateDrug(ActionEvent event) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            int drugId = (int) model.getValueAt(selectedRow, 0);
            String name = txtName.getText();
            String type = txtType.getText();
            String manufactureDate = txtManufactureDate.getText();
            String expiryDate = txtExpiryDate.getText();
            double price = Double.parseDouble(txtPrice.getText());
            int quantity = Integer.parseInt(txtQuantity.getText());
            DrugManagementDAO dao = new DrugManagementDAO();
            dao.updateDrug(drugId, name, type, manufactureDate, expiryDate, price, quantity);
            refreshTable();
        } else {
            JOptionPane.showMessageDialog(this, "Please select a drug to update", "Selection Required", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteDrug(ActionEvent event) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            int drugId = (int) model.getValueAt(selectedRow, 0);
            DrugManagementDAO dao = new DrugManagementDAO();
            dao.deleteDrug(drugId);
            refreshTable();
        } else {
            JOptionPane.showMessageDialog(this, "Please select a drug to delete", "Selection Required", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void refreshTable() {
        List<Drug> drugs = new DrugManagementDAO().getAllDrugs();
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); // Clear existing rows
        for (Drug drug : drugs) {
            model.addRow(new Object[]{drug.drugId, drug.name, drug.type, drug.manufactureDate.toString(), drug.expiryDate.toString(), drug.price, drug.quantity});
        }
    }

    public static void main(String[] args) {
        new DrugManagement();
    }
}
