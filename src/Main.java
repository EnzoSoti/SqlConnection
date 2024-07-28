import java.util.Scanner;
import java.sql.*;

public class Main {

    // global scanner
    static Scanner scan = new Scanner(System.in);

    // database connection
    private static final String username = "root";
    private static final String password = "WJ28@krhps";
    private static final String dataConn = "jdbc:mysql://localhost:3306/printing_db";
    private static PreparedStatement pst;
    private static ResultSet rs;
    private static Connection connection;

    public static void main(String[] args) throws SQLException {

        try {
            System.out.println("Enter full name: ");
            var fullname = scan.nextLine();

            // 1. SQL connection
            connection = DriverManager.getConnection(dataConn, username, password);

            // 2. SQL query
            var query = "INSERT INTO reg (fullname) VALUES (?)";

            // 3. prepared statement
            pst = connection.prepareStatement(query);

            // 4. Set the value for the placeholder
            pst.setString(1, fullname);

            // 5. execute the query
            var rowsAffected = pst.executeUpdate();
            System.out.println("Inserted " + rowsAffected + " row(s) into the database.");

        } catch (SQLException e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage()); 
        }



    }
}