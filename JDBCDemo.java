import java.sql.Connection;
import java.sql.DriverManager;

public class JDBCDemo {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/ALM-IMS-DB";
        String user = "root";
        String password = System.getenv("DB_PASSWORD");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            System.out.println(e);
        }
        
    }
}
