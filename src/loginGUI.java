
//Import section
import java.awt.*;
import javax.swing.*;

public class loginGUI extends JPanel {

    private JFrame fr;
    private JPanel pBackground, pbacktop, cleft, cmiddle, cbottom, cright, mid1, mid2, mid3, btnsocket, btnsoc1, btnsoc2, btnsocket_v2;
    private JLabel header, name, pass;
    protected JTextField txt1;
    protected JPasswordField txt2;
    protected JButton btn1;

    public loginGUI() {

        //  Background setup  //
        setLayout(new BorderLayout());

        //  Header Setup  //
        pbacktop = new JPanel();
        pbacktop.setPreferredSize(new Dimension(300, 90));
        pbacktop.setBackground(Color.WHITE);
        add(pbacktop, BorderLayout.NORTH);

        header = new JLabel("");
        ImageIcon backgroundImage = new ImageIcon("pic/login.png");
        JLabel backgroundLabel = new JLabel(backgroundImage);
        pbacktop.add(backgroundLabel);
        pbacktop.add(header);

        // Middle frame Layout //
        cleft = new JPanel();
        cleft.setPreferredSize(new Dimension(50, 450));
        add(cleft, BorderLayout.WEST);

        cright = new JPanel();
        cright.setPreferredSize(new Dimension(50, 450));
        add(cright, BorderLayout.EAST);
        cmiddle = new JPanel();
        cmiddle.setLayout(new GridLayout(3, 1));

        cbottom = new JPanel();

        mid1 = new JPanel();
        // middle component //
        mid2 = new JPanel();
        mid2.setLayout(new GridLayout(5, 1));

        mid3 = new JPanel();

        btnsocket = new JPanel();
        btnsocket.setLayout(new FlowLayout());
        btnsocket_v2 = new JPanel();
        btnsocket_v2.setLayout(new FlowLayout());

        btnsoc1 = new JPanel();
        btnsoc2 = new JPanel();

        name = new JLabel("USERNAME                           ");
        name.setFont(new Font("CLOUD", Font.BOLD, 12));
        pass = new JLabel("PASSWORD                           ");
        pass.setFont(new Font("CLOUD", Font.BOLD, 12));
        txt1 = new JTextField();
        txt1.setColumns(30);
        txt2 = new JPasswordField();
        txt2.setColumns(15);
        btn1 = new JButton("Login");
        btn1.setFont(new Font("CLOUD", Font.BOLD, 13));

        mid2.add(name);
        mid2.add(txt1);
        mid2.add(pass);
        mid2.add(txt2);
        mid2.add(btnsocket);

        btnsocket.add(btn1);
        mid2.add(btnsocket);

        cmiddle.add(mid1);
        cmiddle.add(mid2);
        cmiddle.add(btnsocket);

        add(cmiddle, BorderLayout.CENTER);
        add(cbottom, BorderLayout.SOUTH);

        pbacktop.setBackground(new Color(47, 47, 47));
        cmiddle.setBackground(new Color(250, 152, 132));
        mid2.setBackground(new Color(250, 152, 132));
        mid1.setBackground(new Color(250, 152, 132));
        mid3.setBackground(new Color(250, 152, 132));
        cleft.setBackground(new Color(250, 152, 132));
        cright.setBackground(new Color(250, 152, 132));
        btnsocket.setBackground(new Color(250, 152, 132));
        cbottom.setBackground(new Color(250, 152, 132));
        btnsoc1.setBackground(new Color(250, 152, 132));
        btnsoc2.setBackground(new Color(250, 152, 132));
    }

    public String getusername() {
        String temp = txt1.getText();
        return temp;
    }

    public JFrame getFr() {
        return fr;
    }

    public void setFr(JFrame fr) {
        this.fr = fr;
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

    public void setTxt2(JPasswordField txt2) {
        this.txt2 = txt2;
    }

    public JButton getLoginBtn() {
        return this.btn1;
    }

    public void setBtn1(JButton btn1) {
        this.btn1 = btn1;
    }
}
