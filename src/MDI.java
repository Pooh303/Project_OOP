
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import javax.swing.plaf.basic.BasicInternalFrameUI;

public class MDI implements ActionListener, KeyListener {

    private JFrame frame;
    private JDesktopPane dp;
    private JInternalFrame frame1, frame2;
    private loginHandler LoginFr;
    private JMenuBar mb;
    private JMenuItem mi1, mi2, mi3, mi5;
    private JMenu m1, m2, m3;
    private REALMAIN rm;

    public MDI() {
        rm = new REALMAIN();
        LoginFr = new loginHandler();
        mb = new JMenuBar();
        m1 = new JMenu("System");
        m2 = new JMenu("Setting");
        m3 = new JMenu("Background");
        mi1 = new JMenuItem("Login");
        mi2 = new JMenuItem("exit");
        mi3 = new JMenuItem("Color");
//        mi4 = new JMenuItem("Browse photo");
        mi5 = new JMenuItem("");

        m1.add(mi1);
        m1.add(mi2);
        m2.add(m3);
        m3.add(mi3);
//        m3.add(mi4);
        frame = new JFrame("Cashier Management");
        dp = new JDesktopPane();
        frame1 = new JInternalFrame("", false, true, false, false);
        frame2 = new JInternalFrame("", false, false, false, false);

        frame1.add(LoginFr);
        frame1.setBounds(65, 40, 300, 500);

        frame1.validate();
        frame1.setVisible(true);
        BasicInternalFrameTitlePane titlePane = (BasicInternalFrameTitlePane) ((BasicInternalFrameUI) frame1.getUI()).getNorthPane();
        frame1.remove(titlePane);

        dp.add(frame1);
        mb.add(m1);
        mb.add(m2);

        dp.setBackground(Color.WHITE);
        frame.setJMenuBar(mb);
        frame.setContentPane(dp);
        frame.setMinimumSize(new Dimension(450, 650));
        frame.setLocation(700, 150);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);

        // Add a key listener to the frame to listen for "Escape" key press
        mi1.addActionListener(this);
        mi2.addActionListener(this);
        mi3.addActionListener(this);
        mb.addKeyListener(this); // Set the frame to be focused so that it can receive key events
        mb.setFocusable(true);
        mb.requestFocus();
    }

    public void actionPerformed(ActionEvent ev) {
        //Color Chooser
        if (ev.getSource().equals(mi1)) {
            if (frame1.isClosed()) {
                OpenFrame1();
            } else {
                JOptionPane.showMessageDialog(frame, "Already opened!");
            }

        } else if (ev.getSource().equals(mi2)) {
            System.exit(0);
        } else if (ev.getSource().equals(mi3)) {
            Color initialColor = frame.getContentPane().getBackground();
            Color selectedColor = JColorChooser.showDialog(frame, "Choose Theme Color", initialColor);
            if (selectedColor != null) {
                frame.getContentPane().setBackground(selectedColor);
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e
    ) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            int result = JOptionPane.showConfirmDialog(frame, "Do you wish to stop the program?", "", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                frame.dispose();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e
    ) {
    }

    @Override
    public void keyTyped(KeyEvent e
    ) {
    }

    public void OpenFrame1() {
        frame1 = new JInternalFrame("", false, true, false, false);
        frame1.add(LoginFr);
        frame1.setBounds(65, 40, 300, 500);
        frame1.validate();
        frame1.setVisible(true);
        dp.add(frame1);
        BasicInternalFrameTitlePane titlePane = (BasicInternalFrameTitlePane) ((BasicInternalFrameUI) frame1.getUI()).getNorthPane();
        frame1.remove(titlePane);
    }

    public JFrame getFr() {
        return this.frame;
    }

    public void closeFr() {
        this.frame.dispose();
    }

    public static void checked() {
        JFrame frame = Handler.mdi.getFr();
        if (Handler.isOpenRealMain) {
            frame.setVisible(!Handler.isOpenRealMain);
            if (!frame.isVisible()) {
                frame.dispose();
            }
            return;
        }
        frame.setVisible(true);
        Handler.isOpenRealMain = true;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                if (Handler.mdi == null) {
                    Handler.mdi = new MDI();
                }
                checked();
            }
        }
        );

    }
}
