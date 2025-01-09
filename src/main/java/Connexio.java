import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connexio {
    private static final String URL = "jdbc:mariadb://localhost:3306/llibres"; // accedint des de xampp
    // private static final String URL = "jdbc:mariadb://192.168.1.50:3303/llibres"; per si vols accedir des de la màquina virtual amb una ip
    private static final String USER = "root";
    private static final String PASSWORD = ""; // accedint des de xampp
    // private static final String PASSWORD = "123abc";

    public static Connection getConnexio() throws SQLException, ClassNotFoundException {
        Class.forName("org.mariadb.jdbc.Driver");
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
