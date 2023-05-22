
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

public class MDI {

    private JFrame frame;
    private JDesktopPane dp;
    private JInternalFrame frame1, frame2;
    private loginGUI LoginFr;
    private JMenuBar mb;
    private JMenuItem mi1, mi2;
    private JMenu m1, m2;

    public MDI() {
        LoginFr = new loginGUI();
        mb = new JMenuBar();
        m1 = new JMenu("File");
        m2 = new JMenu("Setting");
        mi1 = new JMenuItem("Login");
        mi2 = new JMenuItem("exit");

        m1.add(mi1);
        m1.add(mi2);
        frame = new JFrame();
        dp = new JDesktopPane();
        frame1 = new JInternalFrame("", false, true, false, false);
        frame2 = new JInternalFrame("", false, false, false, false);

//        frame1.add(LoginFr);
        frame1.setBounds(700, 300, 300, 500);
        frame1.validate();
        frame1.setVisible(true);

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

        dp.setBackground(Color.GRAY);
        frame.setJMenuBar(mb);
        frame.setContentPane(dp);
        frame.setMinimumSize(new Dimension(300, 300));

//        frame.setExtendedState(frame.MAXIMIZED_BOTH);
        // Set the frame to full screen
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice device = env.getDefaultScreenDevice();

        // Enter full-screen mode
        device.setFullScreenWindow(frame);

        // Validate the frame to ensure proper layout
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // Add a key listener to the frame to listen for "Escape" key press
        mb.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
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
            public void keyReleased(KeyEvent e
            ) {
            }
        });

        // Set the frame to be focused so that it can receive key events
        mb.setFocusable(true);
        mb.requestFocus();
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        SwingUtilities.invokeLater(() -> {
            MDI frame = new MDI();
        });
    }

    public JInternalFrame getFrame1() {
        return frame1;
    }
}
