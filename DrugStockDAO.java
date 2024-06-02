import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DrugStockDAO {
    public List<Drug> getAllDrugsStock() {
        List<Drug> drugs = new ArrayList<>();
        String query = "SELECT drug_id, name, type, price, quantity FROM Drugs";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                drugs.add(new Drug(
                        rs.getInt("drug_id"),
                        rs.getString("name"),
                        rs.getString("type"),
                        rs.getDouble("price"),
                        rs.getInt("quantity")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving drugs stock: " + e.getMessage());
        }
        return drugs;
    }
}
