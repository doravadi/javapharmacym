import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeManagementDAO {
    public void addEmployee(String name, String position, String contactInfo) {
        String query = "INSERT INTO Employees (name, position, contact_info) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, name);
            stmt.setString(2, position);
            stmt.setString(3, contactInfo);
            stmt.executeUpdate();
            System.out.println("Employee added successfully.");
        } catch (SQLException e) {
            System.out.println("Error adding employee: " + e.getMessage());
        }
    }

    public void updateEmployee(int employeeId, String name, String position, String contactInfo) {
        String query = "UPDATE Employees SET name = ?, position = ?, contact_info = ? WHERE employee_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, name);
            stmt.setString(2, position);
            stmt.setString(3, contactInfo);
            stmt.setInt(4, employeeId);
            stmt.executeUpdate();
            System.out.println("Employee updated successfully.");
        } catch (SQLException e) {
            System.out.println("Error updating employee: " + e.getMessage());
        }
    }

    public void deleteEmployee(int employeeId) {
        String query = "DELETE FROM Employees WHERE employee_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, employeeId);
            stmt.executeUpdate();
            System.out.println("Employee deleted successfully.");
        } catch (SQLException e) {
            System.out.println("Error deleting employee: " + e.getMessage());
        }
    }

    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        String query = "SELECT employee_id, name, position, contact_info FROM Employees";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                employees.add(new Employee(
                        rs.getInt("employee_id"),
                        rs.getString("name"),
                        rs.getString("position"),
                        rs.getString("contact_info")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving employees: " + e.getMessage());
        }
        return employees;
    }
}
