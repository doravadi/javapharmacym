import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class EmployeeManagement extends JFrame {
    private JTable table;
    private JTextField txtEmployeeName, txtPosition, txtContactInfo;
    private JButton btnAdd, btnUpdate, btnDelete, btnReturnToMainMenu;

    public EmployeeManagement() {
        setTitle("Employee Management");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel(new BorderLayout());
        setContentPane(mainPanel);

        String[] columnNames = {"Employee ID", "Name", "Position", "Contact Info"};
        DefaultTableModel model = new DefaultTableModel(null, columnNames);
        table = new JTable(model);
        mainPanel.add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel formPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        txtEmployeeName = new JTextField();
        txtPosition = new JTextField();
        txtContactInfo = new JTextField();
        formPanel.add(new JLabel("Name:"));
        formPanel.add(txtEmployeeName);
        formPanel.add(new JLabel("Position:"));
        formPanel.add(txtPosition);
        formPanel.add(new JLabel("Contact Info:"));
        formPanel.add(txtContactInfo);
        mainPanel.add(formPanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        btnAdd = new JButton("Add");
        btnUpdate = new JButton("Update");
        btnDelete = new JButton("Delete");
        btnReturnToMainMenu = new JButton("Return to Main Menu");
        buttonPanel.add(btnAdd);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnReturnToMainMenu);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        btnAdd.addActionListener(this::addEmployee);
        btnUpdate.addActionListener(this::updateEmployee);
        btnDelete.addActionListener(this::deleteEmployee);
        btnReturnToMainMenu.addActionListener(this::returnToMainMenu);

        refreshTable();
        setVisible(true);
    }

    private void addEmployee(ActionEvent e) {
        String name = txtEmployeeName.getText();
        String position = txtPosition.getText();
        String contactInfo = txtContactInfo.getText();
        new EmployeeManagementDAO().addEmployee(name, position, contactInfo);
        refreshTable();
    }

    private void updateEmployee(ActionEvent e) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            int employeeId = Integer.parseInt(table.getValueAt(selectedRow, 0).toString());
            String name = txtEmployeeName.getText();
            String position = txtPosition.getText();
            String contactInfo = txtContactInfo.getText();
            new EmployeeManagementDAO().updateEmployee(employeeId, name, position, contactInfo);
            refreshTable();
        } else {
            JOptionPane.showMessageDialog(this, "Please select an employee to update", "Selection Required", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void deleteEmployee(ActionEvent e) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            int employeeId = Integer.parseInt(table.getValueAt(selectedRow, 0).toString());
            new EmployeeManagementDAO().deleteEmployee(employeeId);
            refreshTable();
        } else {
            JOptionPane.showMessageDialog(this, "Please select an employee to delete", "Selection Required", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void returnToMainMenu(ActionEvent e) {
        this.dispose();
        new MainMenu();

    }

    private void refreshTable() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        List<Employee> employees = new EmployeeManagementDAO().getAllEmployees();
        for (Employee emp : employees) {
            model.addRow(new Object[]{emp.getEmployeeId(), emp.getName(), emp.getPosition(), emp.getContactInfo()});
        }
    }

    public static void main(String[] args) {
        new EmployeeManagement();
    }
}
