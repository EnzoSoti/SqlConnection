import java.sql.*;
public class database {

    // database connection parameters
    private static final String username = "root";
    private static final String password = "WJ28@krhps";
    private static final String Address = "jdbc:mysql://localhost:3306/printing_db";
    private static PreparedStatement preparedStatement = null;
    private static Connection connection = null;
    private static ResultSet rs;

    public static Connection connect() throws SQLException {
        // establish connection to database
        connection = DriverManager.getConnection(Address, username, password);
        return connection;
    }

    public static boolean IsInputDB(String username, String password){

        boolean condition = true;

        try {
            connection = DriverManager.getConnection(Address, username, password);
            Statement statement = connection.createStatement();
            String query = "select email, password FROM printing_db.login";
            rs = statement.executeQuery(query);

            // loop
            while (rs.next()){
                if(rs.getString(1).equalsIgnoreCase(username) && rs.getString(2).equalsIgnoreCase(password)){
                    return condition = true;
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return condition;
    }
}
