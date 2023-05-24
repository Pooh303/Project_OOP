
//Import section
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Main implements ActionListener {

    JFrame fr;
    JPanel p1, p2, p3, p4, p5, p6, p7, p8, p9;
    JButton b1, b2, b3;

    public Main() {
        fr = new JFrame();
        p1 = new JPanel();
        p2 = new JPanel();
        p3 = new JPanel();
        p4 = new JPanel();
        p5 = new JPanel();
        p6 = new JPanel();
        p7 = new JPanel();
        p8 = new JPanel();
        p9 = new JPanel();

        b1 = new JButton("Stock");
        b1.setFont(new Font("CLOUD", Font.BOLD, 35));
        b1.setBackground(Color.WHITE);
        b1.setFocusable(false);

        b2 = new JButton("Sell");
        b2.setFont(new Font("CLOUD", Font.BOLD, 35));
        b2.setBackground(Color.WHITE);
        b2.setFocusable(false);

        b3 = new JButton("Report");
        b3.setFont(new Font("CLOUD", Font.BOLD, 35));
        b3.setBackground(Color.WHITE);
        b3.setFocusable(false);
        fr.setLayout(new BorderLayout());
        p7.setLayout(new GridLayout(3, 3, 10, 0));

        p7.add(p1);
        p7.add(p2);
        p7.add(p3);
        p7.add(b1);
        p7.add(b2);
        p7.add(b3);
        p7.add(p4);
        p7.add(p5);
        p7.add(p6);
        fr.add(p7, BorderLayout.CENTER);
        fr.add(p8, BorderLayout.EAST);
        fr.add(p9, BorderLayout.WEST);

        fr.setSize(700, 500);
        fr.setVisible(true);
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
    }

    public void actionPerformed(ActionEvent ev) {
        if (ev.getSource().equals(b1)) {
            new WareHouseHandler();
        } else if (ev.getSource().equals(b2)) {
            new SellerHandler();
        } else if (ev.getSource().equals(b3)) {
            new ReportsHandler();
        }
    }

    public static void main(String[] args) {
        new Main();
    }
}
