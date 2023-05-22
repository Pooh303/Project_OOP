
import java.awt.event.*;
import java.io.FileWriter;
import javax.swing.JOptionPane;

public class registerHandler implements ActionListener {

    private registerGUI regui;

    public registerHandler() {
        regui = new registerGUI();
        init();
    }

    public void init() {
        regui.getBtn1().addActionListener(this);
        regui.getBtn2().addActionListener(this);
        regui.getTxt1().addActionListener(this);
        regui.getTxt2().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(regui.getBtn1())) {
            regui.getFr().dispose();
        } else if (regui.getTxt1().getText().isEmpty() || regui.getTxt2().getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill your informations.", "Error", 0);
        } else {
            try {
                FileWriter fw = new FileWriter("login.txt", true);
                fw.write(regui.getTxt1().getText() + "\t" + regui.getTxt2().getText() + "\n");
                fw.close();
                JOptionPane.showMessageDialog(null, "Registration Successful!", "", 1);
                regui.getFr().dispose();
            } catch (Exception er) {
                er.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new registerHandler();
    }
}
