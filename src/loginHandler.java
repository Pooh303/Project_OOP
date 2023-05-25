
//Import section
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

public class loginHandler extends loginGUI implements ActionListener {

    private MDI mdi;
    private File logs;
    private String username;
    private String password;
    private boolean check = false;
    Connection conn = null;
    PreparedStatement stmt, stmt2 = null;
    ResultSet us, ps = null;

    public loginHandler() {
        logs = new File("login.txt");
        checkFile();
        init();

    }

    public void init() {
        getLoginBtn().addActionListener(this);

        KeyStroke enterKey = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0);
        getLoginBtn().registerKeyboardAction(this, "login", enterKey, JComponent.WHEN_IN_FOCUSED_WINDOW);
    }

    public void checkFile() {
        logs = new File("login.txt");
        if (!logs.exists()) {
            try {
                logs.createNewFile();
                FileWriter fw = new FileWriter("login.txt", true);
                fw.write("admin" + "\t" + "1234" + "\n");
                fw.close();
            } catch (IOException ev) {
                ev.printStackTrace();
            }
        }
    }

    public String getusername() {
        String temp = getTxt1().getText();
        return temp;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String userinput = getTxt1().getText();
        String passwordinput = getTxt2().getText();
        String sum = userinput + "\t" + passwordinput;
        boolean match = false;
        if (e.getSource().equals(getLoginBtn())) {

            if (getTxt1().getText().isEmpty() & getTxt2().getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter your username and password.", "Error", 0);
            } else if (getTxt1().getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter your username.", "Error", 0);
            } else if (getTxt2().getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter your password.", "Error", 0);

            } else {
                try {
                    FileReader fre = new FileReader(logs);
                    BufferedReader br = new BufferedReader(fre);
                    String data = "";

                    while ((data = br.readLine()) != null) {
                        if (data.equals(sum)) {
                            match = true;
                            break;

                        }
                    }
                    if (match) {
                        JOptionPane.showMessageDialog(null, "Login success!", "", 1);
                        if (Handler.isOpenRealMain) {
                            new REALMAIN().setVisible(true);
                            MDI.checked();
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Wrong username or password.", "Error", 0);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public boolean getCheck() {
        return this.check;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setBounds(800, 280, 300, 500);
            frame.setResizable(false);
            frame.add(new loginHandler());
            frame.setVisible(true);
        });
    }
}
