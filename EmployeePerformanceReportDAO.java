import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EmployeePerformanceReportDAO {
    private static final String[] PERFORMANCE_STATUSES = {
            "Excellent", "Very Good", "Good", "Average", "Below Average",
            "Poor", "Very Poor", "Outstanding", "Exceptional", "Competent",
            "Highly Competent", "Incompetent", "Needs Improvement", "Meets Expectations",
            "Exceeds Expectations", "Does Not Meet Expectations", "Star Performer",
            "Reliable", "Unreliable", "Promising"
    };

    public List<EmployeePerformance> getEmployeePerformances() {
        List<EmployeePerformance> performances = new ArrayList<>();
        String query = "SELECT employee_id, name, position FROM Employees";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            Random rand = new Random();
            while (rs.next()) {
                int index = rand.nextInt(PERFORMANCE_STATUSES.length);
                String performanceStatus = PERFORMANCE_STATUSES[index];
                performances.add(new EmployeePerformance(
                        rs.getInt("employee_id"),
                        rs.getString("name"),
                        rs.getString("position"),
                        performanceStatus
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving employee performances: " + e.getMessage());
        }
        return performances;
    }
}
