
//Import section
import java.awt.*;
import javax.swing.*;

public class registerGUI {

    private JFrame fr;
    private JPanel pBackground, pbacktop, cleft, cmiddle, cright, mid1, mid2, mid3;
    private JLabel header, name, pass, username, email, phone;
    private JTextField txt1, txt2;
    private JButton btn1, btn2;

    public registerGUI() {

        ///  JFrame setup  ///
        fr = new JFrame();
        fr.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        fr.setBounds(800, 200, 300, 500);
        fr.setResizable(false);

        ///  Background setup  ///
        pBackground = new JPanel();
        pBackground.setLayout(new BorderLayout());

        ///  Header Setup  ///
        pbacktop = new JPanel();
        pbacktop.setPreferredSize(new Dimension(750, 90));
        ImageIcon backgroundImage = new ImageIcon("pic/register.png");
        JLabel backgroundLabel = new JLabel(backgroundImage);
        pbacktop.add(backgroundLabel);

        pBackground.add(pbacktop, BorderLayout.NORTH);

        header = new JLabel("Register");
        header.setFont(new Font("CLOUD", Font.BOLD, 30));
        pbacktop.add(header);

        ///  Middle frame Layout  ///
        cleft = new JPanel();
        cleft.setPreferredSize(new Dimension(50, 450));
        pBackground.add(cleft, BorderLayout.WEST);

        cright = new JPanel();
        cright.setPreferredSize(new Dimension(50, 450));
        pBackground.add(cright, BorderLayout.EAST);

        cmiddle = new JPanel();
        cmiddle.setLayout(new GridLayout(3, 1));
        pBackground.add(cmiddle, BorderLayout.CENTER);

        mid1 = new JPanel();
        mid3 = new JPanel();

        /// middle componenet ///
        mid2 = new JPanel();
        mid2.setLayout(new FlowLayout());

        name = new JLabel("Username                      ");
        name.setFont(new Font("CLOUD", Font.BOLD, 13));
        username = new JLabel("Password                      ");
        username.setFont(new Font("CLOUD", Font.BOLD, 13));

        txt1 = new JTextField();
        txt1.setColumns(15);
        txt2 = new JTextField();
        txt2.setColumns(15);

        btn1 = new JButton("Cancel");
        btn1.setFont(new Font("CLOUD", Font.BOLD, 13));
        btn2 = new JButton("Register");
        btn2.setFont(new Font("CLOUD", Font.BOLD, 13));

        mid2.add(name);
        mid2.add(txt1);
        mid2.add(username);
        mid2.add(txt2);
        mid2.add(btn1);
        mid2.add(btn2);

        cmiddle.add(mid1);
        cmiddle.add(mid2);
        cmiddle.add(mid3);

        Color white = new Color(242, 242, 242);
        pBackground.setBackground(white);
        pbacktop.setBackground(white);
        cmiddle.setBackground(white);
        mid2.setBackground(white);
        mid1.setBackground(white);
        mid3.setBackground(white);
        cleft.setBackground(white);
        cright.setBackground(white);
        cmiddle.setBackground(white);

        fr.setUndecorated(true);
        fr.getContentPane();

        fr.add(pBackground);
        fr.setVisible(true);

        fr.add(pBackground);
        fr.setVisible(true);
    }

    public JTextField getTxt1() {
        return txt1;
    }

    public void setTxt1(JTextField txt1) {
        this.txt1 = txt1;
    }

    public JTextField getTxt2() {
        return txt2;
    }

    public void setTxt2(JTextField txt2) {
        this.txt2 = txt2;
    }

    public JButton getBtn1() {
        return btn1;
    }

    public void setBtn1(JButton btn1) {
        this.btn1 = btn1;
    }

    public JButton getBtn2() {
        return btn2;
    }

    public void setBtn2(JButton btn2) {
        this.btn2 = btn2;
    }

    public JFrame getFr() {
        return fr;
    }

    public void setFr(JFrame fr) {
        this.fr = fr;
    }

    public static void main(String[] args) {
        new registerGUI();
    }
}
