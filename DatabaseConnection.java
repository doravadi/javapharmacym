import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    // Veritabanı URL, kullanıcı adı ve şifre
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/pharmacy_db";
    private static final String USER = "root";
    private static final String PASSWORD = "2367";

    // Veritabanı sürücüsünü yüklemek için static blok
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found: " + e.getMessage());
        }
    }

    // Veritabanına bağlantı kurmak için metod
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Database connection failed: " + e.getMessage());
            return null;
        }
    }
}
