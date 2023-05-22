import javax.swing.*;
import java.awt.*;

public class ReportsGUI {

    private JFrame fr;
    private JPanel ptop,pmid,pbottom, pleft,pright;
    private JButton b1, b2;
    private JLabel l1, l2, l3, lstart, lend;
    private JTextField txt1, txt2, txt3, txtstart, txtend;
    private JScrollPane scrollPane;
    private ProductTableModel tableModel;
    private JTable table;

    public ReportsGUI() {
        
        /// Frame ///
        
        fr = new JFrame("Report");
        fr.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        fr.setBounds(580, 250, 850, 650);
        fr.setLayout(new BorderLayout());
        
        /// Panel Setting ///
        
        ptop = new JPanel();
        ptop.setSize(new Dimension(850,50));
        fr.add(ptop, BorderLayout.NORTH);
        lstart = new JLabel("Start Date");
        txtstart = new JTextField();
        txtstart.setFont(new Font("CLOUD", Font.BOLD, 15));
        txtstart.setColumns(15);
        lend = new JLabel("                   End Date");
        txtend = new JTextField();
        txtend.setFont(new Font("CLOUD", Font.BOLD, 15));
        txtend.setColumns(15);
        
        ptop.add(lstart);
        ptop.add(txtstart);
        ptop.add(lend);
        ptop.add(txtend);
        
        
        
        
        
        ///////// add JTable in pmid ///////
        pmid = new JPanel();
        pmid.setSize(new Dimension(800, 540));
        scrollPane = new JScrollPane();
        scrollPane.setPreferredSize(new Dimension(800, 540));
        table = new JTable();
        pmid.add(scrollPane);
        tableModel = new ProductTableModel();
        table.setModel(tableModel);
        scrollPane.setViewportView(table);
        //table.setPreferredScrollableViewportSize(table.getPreferredSize());
        //table.setFillsViewportHeight(true);
        table.getTableHeader().setReorderingAllowed(false);
        fr.add(pmid, BorderLayout.CENTER);
        
        pbottom = new JPanel();
        pbottom.setSize(new Dimension(750,60));
        fr.add(pbottom, BorderLayout.SOUTH);
        pbottom.setLayout(new FlowLayout());
        
        /// bottom companent ///
        l1 = new JLabel("Total Buy cost");
        l1.setForeground(Color.WHITE);
        l2 = new JLabel("      Total Sell price");
        l2.setForeground(Color.WHITE);
        l3 = new JLabel("      Profit");
        l3.setForeground(Color.WHITE);
        
        lstart.setForeground(Color.WHITE);
        lend.setForeground(Color.WHITE);
        
        txt1 = new JTextField();
        txt1.setColumns(15);
        txt2 = new JTextField();
        txt2.setColumns(15);
        txt3 = new JTextField();
        txt3.setColumns(15);
        txt1.setEditable(false);
        txt2.setEditable(false);
        txt3.setEditable(false);
        
        pbottom.add(l1);
        pbottom.add(txt1);
        pbottom.add(l2);
        pbottom.add(txt2);
        pbottom.add(l3);
        pbottom.add(txt3);
        
        
        pleft = new JPanel();
        pleft.setSize(new Dimension(30, 400));
        fr.add(pleft, BorderLayout.WEST);
        
        pright = new JPanel();
        pright.setSize(new Dimension(30, 400));
        fr.add(pright, BorderLayout.EAST);
        
        pright.setBackground(new Color(47,47,47));
        pleft.setBackground(new Color(47,47,47));
        ptop.setBackground(new Color(47,47,47));
        pmid.setBackground(new Color(47,47,47));
        pbottom.setBackground(new Color(47,47,47));
        
        
        
        fr.setVisible(true);
        
        

    }
    public static void main(String[] args) {
        new ReportsGUI();
    }
}
