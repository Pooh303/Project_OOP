
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import javax.swing.plaf.basic.BasicInternalFrameUI;

public class MDI implements ActionListener, KeyListener {

    private JFrame frame;
    private JDesktopPane dp;
    private JInternalFrame frame1, frame2;
    private loginHandler LoginFr;
    private JMenuBar mb;
    private JMenuItem mi1, mi2, mi3, mi4, mi5;
    private JMenu m1, m2, m3;

    public MDI() {
        LoginFr = new loginHandler();
        mb = new JMenuBar();
        m1 = new JMenu("File");
        m2 = new JMenu("Setting");
        m3 = new JMenu("Theme");
        mi1 = new JMenuItem("Login");
        mi2 = new JMenuItem("exit");
        mi3 = new JMenuItem("Choose Color");
        mi4 = new JMenuItem("Browse photo");
        mi5 = new JMenuItem("");

        m1.add(mi1);
        m1.add(mi2);
        m2.add(m3);
        m3.add(mi3);
        m3.add(mi4);
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

        frame2.add(new JLabel("Frame 1 Contens. ."));
        frame2.setBounds(0, 0, 0, 0);
        frame2.validate();
        frame2.setVisible(true);

        int x2 = frame1.getX() + frame1.getWidth() + 10;
        int y2 = frame1.getY();
        frame2.setLocation(x2, y2);

        dp.add(frame1);
        dp.add(frame2);

        mb.add(m1);
        mb.add(m2);

        dp.setBackground(Color.WHITE);
        frame.setJMenuBar(mb);
        frame.setContentPane(dp);
        frame.setMinimumSize(new Dimension(450, 650));

//        frame.setExtendedState(frame.MAXIMIZED_BOTH);
        // Set the frame to full screen
//        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
//        GraphicsDevice device = env.getDefaultScreenDevice();
        // Enter full-screen mode
//        device.setFullScreenWindow(frame);
        // Validate the frame to ensure proper layout
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);

        // Add a key listener to the frame to listen for "Escape" key press
        mi1.addActionListener(this);
        mi2.addActionListener(this);
        mi3.addActionListener(this);
        mi4.addActionListener(this);
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
                JOptionPane.showMessageDialog(frame, "already opened!");
            }

        } else if (ev.getSource().equals(mi2)) {
            System.exit(0);
        } else if (ev.getSource().equals(mi3)) {
            Color initialColor = frame.getContentPane().getBackground();
            Color selectedColor = JColorChooser.showDialog(frame, "Choose Theme Color", initialColor);
            if (selectedColor != null) {
                frame.getContentPane().setBackground(selectedColor);
            }

        } else if (ev.getSource().equals(mi4)) {

        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            int result = JOptionPane.showConfirmDialog(frame, "Do you wish to stop the program?", "", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                frame.dispose();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

//    public static void main(String[] args) {
//        try {
//            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        SwingUtilities.invokeLater(() -> {
//            MDI frame = new MDI();
//        });
//    }
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MDI();
            }
        });
    }

}
