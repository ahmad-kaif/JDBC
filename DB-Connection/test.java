import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class test {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/user_management";
        String user = "root";
        String password = "";
        // Connection object
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the database!");
            // string createTableTeachers = "create table teachers"
        } catch (SQLException e) {
            e.printStackTrace();
        } 
    }
}
