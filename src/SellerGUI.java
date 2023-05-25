
//Import section
import javax.swing.*;
import java.awt.*;
import javax.swing.table.JTableHeader;

public class SellerGUI {

    private JFrame Fr;
    private JTextField txtCode, txtName, txtPrice, txtAmount, txtTotalPrice, txtIncome, txtChange;
    private JButton bnRecord, bnCancel, bnSell, bnPrint, bnScan;
    private JLabel laCode, laName, laPrice, laAmount, laTotalPrice, laIncome, laChange, laBath1, laBath2, laBath3;
    private JTable table;
    private JScrollPane scrollPane, TextAreaSP;
    private SellerTableModel tablemodel;
    private JPanel gL1, gL2, gL3, gL4, gL5, fL1, fL2, fL3, fL4, fL9, fL10, lefttop, leftspace, leftbottom, btmleft, btmbtm;
    private JCheckBox checkText;
    public JTextArea area;

    public SellerGUI() {
        Fr = new JFrame();
        Fr.setBounds(700, 150, 850, 750);
        Fr.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        txtCode = new JTextField(12);
        txtName = new JTextField(12);
        txtName.setEditable(false);
        txtPrice = new JTextField(12);
        txtPrice.setEditable(false);
        txtAmount = new JTextField(12);
        txtAmount.setEditable(false);
        txtTotalPrice = new JTextField(12);
        txtTotalPrice.setHorizontalAlignment(JTextField.CENTER);
        txtTotalPrice.setText("0");
        txtTotalPrice.setEditable(false);
        txtTotalPrice.setFont(new Font("Angsana New", Font.BOLD, 25));
        txtIncome = new JTextField(12);
        txtIncome.setFont(new Font("Angsana New", Font.BOLD, 25));
        txtIncome.setHorizontalAlignment(JTextField.CENTER);
        txtChange = new JTextField(12);
        txtChange.setHorizontalAlignment(JTextField.CENTER);
        txtChange.setEditable(false);
        txtChange.setFont(new Font("Angsana New", Font.BOLD, 25));

        bnRecord = new JButton("Record");
        bnRecord.setFocusable(false);
        bnCancel = new JButton("Cancel");
        bnCancel.setFocusable(false);
        //button for test searching method
        bnScan = new JButton("Scan");
        bnScan.setFocusable(false);
        bnSell = new JButton("Sell");
        bnSell.setFont(new Font("CLOUD", Font.PLAIN, 20));
        bnSell.setPreferredSize(new Dimension(150, 30));
        bnSell.setFocusable(false);
        bnPrint = new JButton("Print");
        bnPrint.setFont(new Font("CLOUD", Font.PLAIN, 20));
        bnPrint.setPreferredSize(new Dimension(150, 30));
        bnPrint.setFocusable(false);

        laCode = new JLabel(" Serial number :");
        laCode.setForeground(Color.WHITE);
        laName = new JLabel(" Name :");
        laName.setForeground(Color.WHITE);
        laPrice = new JLabel(" Price :");
        laPrice.setForeground(Color.WHITE);
        laAmount = new JLabel(" Amount :");
        laAmount.setForeground(Color.WHITE);
        laTotalPrice = new JLabel("           Total : ");
        laTotalPrice.setForeground(Color.WHITE);
        laTotalPrice.setFont(new Font("CLOUD", Font.PLAIN, 23));
        laIncome = new JLabel("Recieve[F1] : ");
        laIncome.setFont(new Font("CLOUD", Font.PLAIN, 23));
        laIncome.setForeground(Color.WHITE);
        laChange = new JLabel("        Change : ");
        laChange.setFont(new Font("CLOUD", Font.PLAIN, 23));
        laChange.setForeground(Color.WHITE);
        laBath1 = new JLabel("Baht.");
        laBath1.setFont(new Font("CLOUD", Font.PLAIN, 23));
        laBath1.setForeground(Color.WHITE);
        laBath2 = new JLabel("Baht.");
        laBath2.setFont(new Font("CLOUD", Font.PLAIN, 23));
        laBath2.setForeground(Color.WHITE);
        laBath3 = new JLabel("Baht.");
        laBath3.setFont(new Font("CLOUD", Font.PLAIN, 23));
        laBath3.setForeground(Color.WHITE);

        checkText = new JCheckBox();
        checkText.setText("Edit amount");
        checkText.setFocusable(false);
        checkText.setFont(new Font("CLOUD", Font.PLAIN, 12));

        Fr.setLayout(new GridLayout(1, 2));
        gL1 = new JPanel();
        gL1.setLayout(new GridLayout(2, 1));
        gL2 = new JPanel();
        gL2.setLayout(new GridLayout(4, 1));
        gL3 = new JPanel();
        gL3.setLayout(new GridLayout(4, 1));
        gL4 = new JPanel();
        gL4.setLayout(new GridLayout(9, 1));
        gL5 = new JPanel();
        gL5.setLayout(new GridLayout(2, 1));
        fL1 = new JPanel();
        fL1.setLayout(new FlowLayout());
        fL2 = new JPanel();
        fL2.setLayout(new FlowLayout());
        fL3 = new JPanel();
        fL3.setLayout(new FlowLayout());
        fL4 = new JPanel();
        fL4.setLayout(new FlowLayout());
        fL9 = new JPanel();
        fL9.setLayout(new FlowLayout());
        fL10 = new JPanel();
        fL10.setLayout(new FlowLayout());
        lefttop = new JPanel();
        lefttop.setLayout(new BorderLayout());
        leftspace = new JPanel();
        lefttop.add(leftspace, BorderLayout.WEST);
        lefttop.add(gL4, BorderLayout.CENTER);
        leftbottom = new JPanel();
        leftbottom.setLayout(new BorderLayout());
        btmleft = new JPanel();
        btmbtm = new JPanel();
        leftbottom.add(btmleft, BorderLayout.WEST);
        leftbottom.add(btmbtm, BorderLayout.SOUTH);

        table = new JTable();
        //Set scrollPane for JTable
        scrollPane = new JScrollPane();
//        scrollPane.setBounds(245, 80, 188, 125);
        table = new JTable();

        //Set Model for Jtable(ProductTableModel)
        tablemodel = new SellerTableModel();
        table.setModel(tablemodel);
        scrollPane.setViewportView(table);
        table.getTableHeader().setReorderingAllowed(false);

        area = new JTextArea();
        area.setColumns(28);
        area.setRows(20);

        area.setEditable(false);
        area.setText("     King Mongkut's Institute of Technology Ladkrabang \n");
        area.setText(area.getText() + "                 1 Chalong Krung 1 Alley, Lat Krabang, \n");
        area.setText(area.getText() + "                        Bangkok 10520, Thailand \n");
        area.setText(area.getText() + " --------------------------------------------------------------------------\n");
        area.setText(area.getText() + " Item \t\tQty. \tPrice" + "\n");
        area.setText(area.getText() + " --------------------------------------------------------------------------\n");

        area.setText(area.getText() + " --------------------------------------------------------------------------\n");
        area.setText(area.getText() + " Sub Total : " + getTxtTotalPrice().getText() + "\n");
        area.setText(area.getText() + " Cash      : " + getTxtIncome().getText() + "\n");
        area.setText(area.getText() + " Balance   : " + getTxtChange().getText() + "\n");
        area.setText(area.getText() + " --------------------------------------------------------------------------\n");
        area.setText(area.getText() + "                       Thanks For Your Business...!" + "\n");
        area.setText(area.getText() + " --------------------------------------------------------------------------\n");
        area.setText(area.getText() + "                            Cashier Management" + "\n");
        TextAreaSP = new JScrollPane(area);

        leftbottom.add(scrollPane, BorderLayout.CENTER);

        Fr.add(gL1);
        Fr.add(gL5);
        fL10.add(TextAreaSP);
        gL5.add(fL10);
        gL5.add(gL2);

        fL1.add(checkText);
        fL1.add(bnRecord);
        fL1.add(bnCancel);
        fL1.add(bnScan);
        fL2.add(laTotalPrice);
        fL2.add(txtTotalPrice);
        fL2.add(laBath1);
        fL3.add(laIncome);
        fL3.add(txtIncome);
        fL3.add(laBath2);
        fL4.add(laChange);
        fL4.add(txtChange);
        fL4.add(laBath3);
        fL9.add(bnSell);
        fL9.add(bnPrint);

        gL2.add(fL2);
        gL2.add(fL3);
        gL2.add(fL4);
        gL2.add(fL9);

        gL4.add(laCode);
        gL4.add(txtCode);
        gL4.add(laName);
        gL4.add(txtName);
        gL4.add(laPrice);
        gL4.add(txtPrice);
        gL4.add(laAmount);
        gL4.add(txtAmount);
        gL4.add(fL1);

        gL1.setBackground(new Color(47, 47, 47));
        gL5.setBackground(new Color(47, 47, 47));
        gL4.setBackground(new Color(47, 47, 47));
        gL2.setBackground(new Color(47, 47, 47));
        gL3.setBackground(new Color(47, 47, 47));
        fL1.setBackground(new Color(47, 47, 47));
        fL2.setBackground(new Color(47, 47, 47));
        fL3.setBackground(new Color(47, 47, 47));
        fL4.setBackground(new Color(47, 47, 47));
        fL9.setBackground(new Color(47, 47, 47));
        fL10.setBackground(new Color(47, 47, 47));
        lefttop.setBackground(new Color(47, 47, 47));
        leftspace.setBackground(new Color(47, 47, 47));
        leftbottom.setBackground(new Color(47, 47, 47));
        btmleft.setBackground(new Color(47, 47, 47));
        btmbtm.setBackground(new Color(47, 47, 47));

        JTableHeader Theader = table.getTableHeader();
        Theader.setBackground(Color.white);

        gL1.add(lefttop);
        gL1.add(leftbottom);
        Fr.setResizable(false);
        Fr.setVisible(true);

    }

    public static void main(String[] args) {
        new SellerGUI();
    }

    public JFrame getFr() {
        return Fr;
    }

    public void setFr(JFrame Fr) {
        this.Fr = Fr;
    }

    public JTextField getTxtCode() {
        return txtCode;
    }

    public void setTxtCode(JTextField txtCode) {
        this.txtCode = txtCode;
    }

    public JTextField getTxtName() {
        return txtName;
    }

    public void setTxtName(JTextField txtName) {
        this.txtName = txtName;
    }

    public JTextField getTxtPrice() {
        return txtPrice;
    }

    public void setTxtPrice(JTextField txtPrice) {
        this.txtPrice = txtPrice;
    }

    public JTextField getTxtAmount() {
        return txtAmount;
    }

    public void setTxtAmount(JTextField txtAmount) {
        this.txtAmount = txtAmount;
    }

    public JTextField getTxtTotalPrice() {
        return txtTotalPrice;
    }

    public void setTxtTotalPrice(JTextField txtTotalPrice) {
        this.txtTotalPrice = txtTotalPrice;
    }

    public JTextField getTxtIncome() {
        return txtIncome;
    }

    public void setTxtIncome(JTextField txtIncome) {
        this.txtIncome = txtIncome;
    }

    public JTextField getTxtChange() {
        return txtChange;
    }

    public void setTxtChange(JTextField txtChange) {
        this.txtChange = txtChange;
    }

    public JButton getBnRecord() {
        return bnRecord;
    }

    public void setBnRecord(JButton bnRecord) {
        this.bnRecord = bnRecord;
    }

    public JButton getBnCancel() {
        return bnCancel;
    }

    public void setBnCancel(JButton bnCancel) {
        this.bnCancel = bnCancel;
    }

    public JButton getBnSell() {
        return bnSell;
    }

    public void setBnSell(JButton bnSell) {
        this.bnSell = bnSell;
    }

    public JButton getBnPrint() {
        return bnPrint;
    }

    public void setBnPrint(JButton bnPrint) {
        this.bnPrint = bnPrint;
    }

    public JLabel getLaCode() {
        return laCode;
    }

    public void setLaCode(JLabel laCode) {
        this.laCode = laCode;
    }

    public JLabel getLaName() {
        return laName;
    }

    public void setLaName(JLabel laName) {
        this.laName = laName;
    }

    public JLabel getLaPrice() {
        return laPrice;
    }

    public void setLaPrice(JLabel laPrice) {
        this.laPrice = laPrice;
    }

    public JLabel getLaAmount() {
        return laAmount;
    }

    public void setLaAmount(JLabel laAmount) {
        this.laAmount = laAmount;
    }

    public JLabel getLaTotalPrice() {
        return laTotalPrice;
    }

    public void setLaTotalPrice(JLabel laTotalPrice) {
        this.laTotalPrice = laTotalPrice;
    }

    public JLabel getLaIncome() {
        return laIncome;
    }

    public void setLaIncome(JLabel laIncome) {
        this.laIncome = laIncome;
    }

    public JLabel getLaChange() {
        return laChange;
    }

    public void setLaChange(JLabel laChange) {
        this.laChange = laChange;
    }

    public JLabel getLaBath1() {
        return laBath1;
    }

    public void setLaBath1(JLabel laBath1) {
        this.laBath1 = laBath1;
    }

    public JLabel getLaBath2() {
        return laBath2;
    }

    public void setLaBath2(JLabel laBath2) {
        this.laBath2 = laBath2;
    }

    public JLabel getLaBath3() {
        return laBath3;
    }

    public void setLaBath3(JLabel laBath3) {
        this.laBath3 = laBath3;
    }

    public JTable getTable() {
        return table;
    }

    public void setTable(JTable table) {
        this.table = table;
    }

    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    public void setScrollPane(JScrollPane scrollPane) {
        this.scrollPane = scrollPane;
    }

    public SellerTableModel getTablemodel() {
        return tablemodel;
    }

    public void setTablemodel(SellerTableModel tablemodel) {
        this.tablemodel = tablemodel;
    }

    public JPanel getgL1() {
        return gL1;
    }

    public void setgL1(JPanel gL1) {
        this.gL1 = gL1;
    }

    public JPanel getgL2() {
        return gL2;
    }

    public void setgL2(JPanel gL2) {
        this.gL2 = gL2;
    }

    public JPanel getgL3() {
        return gL3;
    }

    public void setgL3(JPanel gL3) {
        this.gL3 = gL3;
    }

    public JPanel getgL4() {
        return gL4;
    }

    public void setgL4(JPanel gL4) {
        this.gL4 = gL4;
    }

    public JPanel getgL5() {
        return gL5;
    }

    public void setgL5(JPanel gL5) {
        this.gL5 = gL5;
    }

    public JPanel getfL1() {
        return fL1;
    }

    public void setfL1(JPanel fL1) {
        this.fL1 = fL1;
    }

    public JPanel getfL2() {
        return fL2;
    }

    public void setfL2(JPanel fL2) {
        this.fL2 = fL2;
    }

    public JPanel getfL3() {
        return fL3;
    }

    public void setfL3(JPanel fL3) {
        this.fL3 = fL3;
    }

    public JPanel getfL4() {
        return fL4;
    }

    public void setfL4(JPanel fL4) {
        this.fL4 = fL4;
    }

    public JPanel getfL9() {
        return fL9;
    }

    public void setfL9(JPanel fL9) {
        this.fL9 = fL9;
    }

    public JPanel getfL10() {
        return fL10;
    }

    public void setfL10(JPanel fL10) {
        this.fL10 = fL10;
    }

    public JButton getBnScan() {
        return bnScan;
    }

    public void setBnScan(JButton bnScan) {
        this.bnScan = bnScan;
    }

    public JCheckBox getCheckText() {
        return checkText;
    }

    public void setCheckText(JCheckBox checkText) {
        this.checkText = checkText;
    }
}
