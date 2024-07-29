import java.sql.*;
import java.util.Scanner;

public class Main {

    // global scanner
    static Scanner scan = new Scanner(System.in);

    // database connection parameters
    private static final String username = "root";
    private static final String password = "WJ28@krhps";
    private static final String Address = "jdbc:mysql://localhost:3306/printing_db";
    private static PreparedStatement preparedStatement = null;
    private static Connection connection = null;
    private static ResultSet rs;

    // String regex
    static String emailRegex = " ^[a-zA-Z0-9_+&*-]+(?:\\\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\\\.)+[a-zA-Z]{2,7}$";

    public static void main(String[] args) {

        boolean choice = true;

        try {
            // 1. Establish SQL connection once before entering the loop
            connection = DriverManager.getConnection(Address, username, password);

            // Loop to get valid user input
            while (choice) {
                // user email and password
                System.out.print("Email address: ");
                String email_address = scan.nextLine();

                System.out.print("Password: ");
                String pass = scan.nextLine();

                // Check if empty
                if (email_address.isEmpty() || pass.isEmpty()) {
                    System.out.println("Cannot leave the field empty. Please try again.");
                    continue; // Skip the rest of the loop and prompt again
                }

                // 2. SQL query
                String query = "INSERT INTO login (email, password) VALUES (?, ?)";

                // 3. Prepare statement
                preparedStatement = connection.prepareStatement(query);

                // 4. Set the value for the placeholders
                preparedStatement.setString(1, email_address);
                preparedStatement.setString(2, pass);

                // 5. Execute the query
                int rowsAffected = preparedStatement.executeUpdate();
                System.out.println("Data inserted successfully. Rows affected: " + rowsAffected);

                // Exit loop after successful insertion
                choice = false;
            }
        } catch (SQLException e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
    }
}
