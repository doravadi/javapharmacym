public class TestDatabaseConnection {
    public static void main(String[] args) {
        // DatabaseConnection sınıfından bir bağlantı al
        java.sql.Connection connection = DatabaseConnection.getConnection();

        // Bağlantının başarılı olup olmadığını kontrol et
        if (connection != null) {
            System.out.println("Database connection is successful!");
            // Bağlantıyı kapat
            try {
                connection.close();
            } catch (java.sql.SQLException e) {
                System.out.println("Failed to close the connection: " + e.getMessage());
            }
        } else {
            System.out.println("Failed to connect to the database.");
        }
    }
}
