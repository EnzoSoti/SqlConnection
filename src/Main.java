import java.sql.*;
import java.util.Scanner;

public class Main {

    // Global scanner
    static Scanner scan = new Scanner(System.in);

    // Database connection parameters
    private static final String DATABASE_USERNAME = "root";
    private static final String DATABASE_PASSWORD = "WJ28@krhps";
    private static final String DATABASE_ADDRESS = "jdbc:mysql://localhost:3306/printing_db";
    private static PreparedStatement preparedStatement = null;
    private static Connection connection = null;
    private static ResultSet rs;

    public static void main(String[] args) {

        // Loop condition
        boolean choice = true;

        try {
            // 1. Establish SQL connection once before entering the loop
            connection = DriverManager.getConnection(DATABASE_ADDRESS, DATABASE_USERNAME, DATABASE_PASSWORD);

            // Loop to get valid user input
            while (choice) {
                // User email and password
                System.out.print("Email address: ");
                String email_address = scan.nextLine();

                System.out.print("Password: ");
                String pass = scan.nextLine();

                // Regular expression, check if the email format is valid
                // Example: "enzodaniela@gmail.com"
                String emailPattern = "^[a-zA-Z][a-zA-Z0-9._%+-]*@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,7}$";
                if (!email_address.matches(emailPattern)) {
                    System.out.println("Invalid email format");
                    continue;
                } else if (email_address.isEmpty() || email_address.isBlank() || pass.isEmpty() || pass.isBlank()) {
                    System.out.println("Cannot leave the field empty. Please try again.");
                    continue;
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

            // Test login your account
            System.out.println("Test login your account");
            // Prompt the user to enter their credentials
            System.out.print("Enter your email: ");
            String login_email = scan.nextLine();
            System.out.print("Enter your password: ");
            String login_password = scan.nextLine();

            // Check if login is successful
            boolean login_success = database.IsInputDB(login_email, login_password);
            if (login_success) {
                System.out.println("Login successful!");
                // Proceed with further actions after successful login
            } else {
                System.out.println("Invalid email or password.");
            }

        } catch (SQLException e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
    }
}
