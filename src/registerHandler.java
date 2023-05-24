
//Import section
import java.awt.event.*;
import java.io.FileWriter;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class registerHandler implements ActionListener {

    private registerGUI regis;

    public registerHandler() {
        regis = new registerGUI();
        init();
    }

    public void init() {
        regis.getBtn1().addActionListener(this);
        regis.getBtn2().addActionListener(this);
        regis.getTxt1().addActionListener(this);
        regis.getTxt2().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(regis.getBtn1())) {
            regis.getFr().dispose();
        } else if (regis.getTxt1().getText().isEmpty() || regis.getTxt2().getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill your informations.", "Error", 0);
        } else {
            try {
                FileWriter fw = new FileWriter("login.txt", true);
                fw.write(regis.getTxt1().getText() + "\t" + regis.getTxt2().getText() + "\n");
                fw.close();
                JOptionPane.showMessageDialog(null, "Registration Successful!", "", 1);
                regis.getFr().dispose();
            } catch (Exception er) {
                er.printStackTrace();
            }
        }
    }

    public JFrame getFr() {
        return this.regis.getFr();
    }

    public static void main(String[] args) {
        new registerHandler();
    }
}
