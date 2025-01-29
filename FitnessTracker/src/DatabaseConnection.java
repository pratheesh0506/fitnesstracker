import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/fitness_tracker";
    private static final String USER = "root"; // replace with your MySQL username
    private static final String PASSWORD = "1234"; // replace with your MySQL password

    public static Connection getConnection() throws Exception {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
