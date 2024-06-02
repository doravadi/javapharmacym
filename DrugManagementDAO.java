import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class DrugManagementDAO {
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public void addDrug(String name, String type, String manufactureDate, String expiryDate, double price, int quantity) {
        String query = "INSERT INTO Drugs (name, type, manufacture_date, expiry_date, price, quantity) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, name);
            stmt.setString(2, type);
            stmt.setDate(3, new java.sql.Date(dateFormat.parse(manufactureDate).getTime()));
            stmt.setDate(4, new java.sql.Date(dateFormat.parse(expiryDate).getTime()));
            stmt.setDouble(5, price);
            stmt.setInt(6, quantity);
            stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error adding drug: " + e.getMessage());
        }
    }

    public void updateDrug(int drugId, String name, String type, String manufactureDate, String expiryDate, double price, int quantity) {
        String query = "UPDATE Drugs SET name = ?, type = ?, manufacture_date = ?, expiry_date = ?, price = ?, quantity = ? WHERE drug_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, name);
            stmt.setString(2, type);
            stmt.setDate(3, new java.sql.Date(dateFormat.parse(manufactureDate).getTime()));
            stmt.setDate(4, new java.sql.Date(dateFormat.parse(expiryDate).getTime()));
            stmt.setDouble(5, price);
            stmt.setInt(6, quantity);
            stmt.setInt(7, drugId);
            stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error updating drug: " + e.getMessage());
        }
    }

    public void deleteDrug(int drugId) {
        String query = "DELETE FROM Drugs WHERE drug_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, drugId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error deleting drug: " + e.getMessage());
        }
    }

    public List<Drug> getAllDrugs() {
        List<Drug> drugs = new ArrayList<>();
        String query = "SELECT drug_id, name, type, manufacture_date, expiry_date, price, quantity FROM Drugs";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                drugs.add(new Drug(
                        rs.getInt("drug_id"),
                        rs.getString("name"),
                        rs.getString("type"),
                        rs.getDate("manufacture_date"),
                        rs.getDate("expiry_date"),
                        rs.getDouble("price"),
                        rs.getInt("quantity")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving drugs: " + e.getMessage());
        }
        return drugs;
    }
}
