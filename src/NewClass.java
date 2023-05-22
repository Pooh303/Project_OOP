
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NewClass {

    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement stmt, stpt = null;
        ResultSet rs, ps = null;

        try {
            // Connect to the database
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/login", "root", "");

            // Prepare the statement with parameter placeholder
            String sqluser = "SELECT Username FROM enter WHERE ID = ?"; // Assuming ID is the primary key column
            String sqlpass = "SELECT Password FROM enter WHERE ID = ?";
            stmt = conn.prepareStatement(sqluser);
            stpt = conn.prepareStatement(sqlpass);

            // Set the parameter value
            int id = 1; // Replace with the appropriate ID value
            stmt.setInt(1, id);
            stpt.setInt(1, id);

            // Execute the query
            rs = stmt.executeQuery();
            ps = stpt.executeQuery();
            // Process the result set
            if (rs.next() & ps.next()) {
                String username = rs.getString("Username");
                String password = ps.getString("Password");
                System.out.println(username);
                System.out.println(password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
