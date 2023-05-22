
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class loginHandler extends JPanel implements ActionListener {

    private loginGUI viewlogin;
    private File logs;
    private String username, password;
    Connection conn = null;
    PreparedStatement stmt, stmt2 = null;
    ResultSet us, ps = null;

    public loginHandler() {
        viewlogin = new loginGUI();
        CheckServer();
        init();

    }

    public void init() {
        viewlogin.getLoginBtn().addActionListener(this);
        viewlogin.getTxt1().addActionListener(this);
        viewlogin.getTxt2().addActionListener(this);
    }

    public void CheckServer() {
        try {
            // Connect to the database
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/login", "root", "");

            // Prepare the statement with parameter placeholder
            String sqluser = "SELECT Username FROM enter WHERE ID = ?"; // Assuming ID is the primary key column
            String sqlpass = "SELECT Password FROM enter WHERE ID = ?";
            stmt = conn.prepareStatement(sqluser);
            stmt2 = conn.prepareStatement(sqlpass);

            // Set the parameter value
            int id = 1; // Replace with the appropriate ID value
            stmt.setInt(1, id);
            stmt2.setInt(1, id);

            // Execute the query
            us = stmt.executeQuery();
            ps = stmt2.executeQuery();
            // Process the result set
            if (us.next() & ps.next()) {
                username = us.getString("Username");
                password = ps.getString("Password");
                // See username and password
//                System.out.println(username);
//                System.out.println(password);
            }
        } catch (SQLException e) {
//            e.printStackTrace();
        }
    }

    public String getusername() {
        String temp = viewlogin.getTxt1().getText();
        return temp;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String userinput = viewlogin.getTxt1().getText();
        String passwordinput = viewlogin.getTxt2().getText();
        String sum = userinput + "\t" + passwordinput;
        boolean match = false;
        if (e.getSource().equals(viewlogin.getLoginBtn())) {
            CheckServer();
            if (viewlogin.getTxt1().getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please insert your name.", "Error", 0);
            } else if (viewlogin.getTxt2().getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please insert your Password.", "Error", 0);
            } else if (username == null & password == null) {
                JOptionPane.showMessageDialog(null, "Server is offline", "Error", 0);
            } else {
                try {
                    while (!sum.equals("")) {
                        if (sum.equals(username + "\t" + password)) {
                            match = true;
                            break;
                        } else {
                            match = false;
                            break;
                        }
                    }
                    if (match) {
                        viewlogin.getFr().dispose();
                        new WareHouseHandler();
                    } else {
                        JOptionPane.showMessageDialog(null, "Wrong Username or Password.", "Error", 0);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        new loginHandler();
    }

}
