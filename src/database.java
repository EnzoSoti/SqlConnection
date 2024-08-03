import java.sql.*;

public class database {

    // Database connection parameters
    private static final String DATABASE_USERNAME = "root";
    private static final String DATABASE_PASSWORD = "WJ28@krhps";
    private static final String DATABASE_ADDRESS = "jdbc:mysql://localhost:3306/printing_db";

    public static Connection connect() throws SQLException {
        // Creates a new connection to the database using the specified parameters
        return DriverManager.getConnection(DATABASE_ADDRESS, DATABASE_USERNAME, DATABASE_PASSWORD);
    }

    public static boolean IsInputDB(String email, String password) {
        // SQL query to select the email and password from the login table where they match the provided values
        String query = "SELECT email, password FROM login WHERE email = ? AND password = ?";

        // Try-with-resources to ensure that resources are closed automatically
        try (Connection conn = connect(); // Establishes a database connection
             PreparedStatement preparedStatement = conn.prepareStatement(query)) { // Prepares the SQL query

            // Set the values for the placeholders in the query
            preparedStatement.setString(1, email); // Set the email in the query
            preparedStatement.setString(2, password); // Set the password in the query

            // Execute the query and get the result set
            try (ResultSet rs = preparedStatement.executeQuery()) { // Execute the query and retrieve the result set
                // Return true if the result set contains at least one record
                return rs.next(); // Returns true if a row is found; false otherwise
            }
        } catch (SQLException e) {
            // Print the SQL error message if an exception occurs
            System.err.println("SQL error: " + e.getMessage());
            return false; // Return false in case of an exception
        }
    }
}
