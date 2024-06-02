import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class SalesManagementDAO {
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

    public void addSale(int drugId, int quantity, Date saleDate, double totalPrice) {
        String query = "INSERT INTO Sales (drug_id, quantity, sale_date, total_price) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, drugId);
            stmt.setInt(2, quantity);
            stmt.setDate(3, saleDate);
            stmt.setDouble(4, totalPrice);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error adding sale: " + e.getMessage());
        }
    }

    public void updateDrugQuantity(int drugId, int quantitySold) {
        String query = "UPDATE Drugs SET quantity = quantity - ? WHERE drug_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, quantitySold);
            stmt.setInt(2, drugId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error updating drug quantity: " + e.getMessage());
        }
    }




}
