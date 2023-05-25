
//Import section
import com.toedter.calendar.*;
import javax.swing.*;
import java.awt.*;
import javax.swing.table.*;

public class ReportsGUI {

    private JFrame fr;
    private JPanel ptop, pmid, pbottom, pleft, pright, pjchooser, pjbutt, p1, p2, p3;
    private JButton b1, b2;
    private JLabel l1, l2, l3, lstart, lend;
    private JTextField txt1, txt2, txt3, txtstart, txtend;
    private JScrollPane scrollPane;
    private ReportsTableModel tableModel;
    private JTable table;
    private JDateChooser dateChooser;

    public ReportsGUI() {

        /// Frame ///
        fr = new JFrame("Report");
        fr.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        fr.setBounds(700, 200, 850, 650);
        fr.setLayout(new BorderLayout());

        /// Panel Setting ///
        pjchooser = new JPanel();
        pjchooser.setLayout(new GridLayout(1, 1));
        p1 = new JPanel();
        p2 = new JPanel();
        p3 = new JPanel();
        pjbutt = new JPanel();
        pjbutt.setLayout(new GridLayout(1, 4));
        ptop = new JPanel();
        ptop.setSize(new Dimension(850, 50));
        ptop.setLayout(new FlowLayout());
        fr.add(ptop, BorderLayout.NORTH);
        lstart = new JLabel("Search Date");
        dateChooser = new JDateChooser();
        dateChooser.setDateFormatString("MM/dd/yyyy");
        pjchooser.add(dateChooser);

        pjchooser.setPreferredSize(new Dimension(220, 25));

        lend = new JLabel("                                ");
        txtend = new JTextField();
        txtend.setFont(new Font("CLOUD", Font.BOLD, 15));
        txtend.setColumns(15);
        txtend.setEditable(false);
        txtend.setBorder(BorderFactory.createEmptyBorder());
        txtend.setBackground(new Color(47, 47, 47));
        b1 = new JButton("🔃");
        b1.setFocusable(false);
        pjbutt.add(b1);
        pjbutt.add(p1);
        pjbutt.add(p2);
        pjbutt.add(p3);

        ptop.add(pjbutt);
        ptop.add(lstart);
        ptop.add(pjchooser);
        ptop.add(lend);
        ptop.add(txtend);

        ///////// add JTable in pmid ///////
        pmid = new JPanel();
        pmid.setSize(new Dimension(800, 540));
        scrollPane = new JScrollPane();
        scrollPane.setPreferredSize(new Dimension(800, 540));
        //221,219,203,255
        table = new JTable();
        JTableHeader Theader = table.getTableHeader();
        Theader.setBackground(Color.WHITE);
        pmid.add(scrollPane);
        tableModel = new ReportsTableModel();
        table.setModel(tableModel);
        scrollPane.setViewportView(table);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.getColumnModel().getColumn(0).setPreferredWidth(100);
        table.getColumnModel().getColumn(1).setPreferredWidth(100);
        table.getColumnModel().getColumn(2).setPreferredWidth(100);
        table.getColumnModel().getColumn(3).setPreferredWidth(100);
        table.getColumnModel().getColumn(4).setPreferredWidth(100);
        table.getColumnModel().getColumn(5).setPreferredWidth(100);
        table.getColumnModel().getColumn(6).setPreferredWidth(100);
        table.getColumnModel().getColumn(7).setPreferredWidth(100);
        table.getTableHeader().setReorderingAllowed(false);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        fr.add(pmid, BorderLayout.CENTER);
        pbottom = new JPanel();
        pbottom.setSize(new Dimension(750, 60));
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
        txt1.setColumns(12);
        txt1.setHorizontalAlignment(JTextField.CENTER);
        txt1.setFont(new Font("CLOUD", Font.PLAIN, 15));
        txt2 = new JTextField();
        txt2.setColumns(12);
        txt2.setHorizontalAlignment(JTextField.CENTER);
        txt2.setFont(new Font("CLOUD", Font.PLAIN, 15));
        txt3 = new JTextField();
        txt3.setColumns(12);
        txt3.setHorizontalAlignment(JTextField.CENTER);
        txt3.setFont(new Font("CLOUD", Font.PLAIN, 15));
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

        pright.setBackground(new Color(47, 47, 47));
        pleft.setBackground(new Color(47, 47, 47));
        ptop.setBackground(new Color(47, 47, 47));
        pmid.setBackground(new Color(47, 47, 47));
        pbottom.setBackground(new Color(47, 47, 47));
        p1.setBackground(new Color(47, 47, 47));
        p2.setBackground(new Color(47, 47, 47));
        p3.setBackground(new Color(47, 47, 47));
        fr.setResizable(false);
        fr.setVisible(true);
    }

    public static void main(String[] args) {
        new ReportsGUI();
    }

    public JFrame getFr() {
        return fr;
    }

    public void setFr(JFrame fr) {
        this.fr = fr;
    }

    public JButton getB1() {
        return b1;
    }

    public void setB1(JButton b1) {
        this.b1 = b1;
    }

    public JButton getB2() {
        return b2;
    }

    public void setB2(JButton b2) {
        this.b2 = b2;
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

    public JTextField getTxt3() {
        return txt3;
    }

    public void setTxt3(JTextField txt3) {
        this.txt3 = txt3;
    }

    public JTextField getTxtstart() {
        return txtstart;
    }

    public void setTxtstart(JTextField txtstart) {
        this.txtstart = txtstart;
    }

    public JTextField getTxtend() {
        return txtend;
    }

    public void setTxtend(JTextField txtend) {
        this.txtend = txtend;
    }

    public ReportsTableModel getTableModel() {
        return tableModel;
    }

    public void setTableModel(ReportsTableModel tableModel) {
        this.tableModel = tableModel;
    }

    public JTable getTable() {
        return table;
    }

    public void setTable(JTable table) {
        this.table = table;
    }

    public JDateChooser getDateChooser() {
        return dateChooser;
    }

    public void setDateChooser(JDateChooser dateChooser) {
        this.dateChooser = dateChooser;
    }

}
