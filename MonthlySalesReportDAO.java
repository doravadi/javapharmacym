import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;  // SQL tarih işlemleri için
import java.util.List;  // Koleksiyonlar için
import java.util.ArrayList;  // ArrayList kullanımı için


public class MonthlySalesReportDAO {
    public List<SaleReport> getMonthlySalesReport(int year, int month) {
        List<SaleReport> sales = new ArrayList<>();
        String query = "SELECT D.name, S.quantity, S.total_price, S.sale_date " +
                "FROM Sales S INNER JOIN Drugs D ON S.drug_id = D.drug_id " +
                "WHERE YEAR(S.sale_date) = ? AND MONTH(S.sale_date) = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, year);
            stmt.setInt(2, month);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                sales.add(new SaleReport(
                        rs.getString("name"),
                        rs.getInt("quantity"),
                        rs.getDouble("total_price"),
                        rs.getDate("sale_date")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving monthly sales report: " + e.getMessage());
        }
        return sales;
    }
}
