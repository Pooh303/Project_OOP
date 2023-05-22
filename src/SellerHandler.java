
import static com.barcodelib.barcode.a.b.h.i;
import com.github.sarxos.webcam.WebcamLockException;
import java.awt.event.*;
import javax.swing.JOptionPane;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;

public class SellerHandler extends Thread implements ActionListener, WindowListener, KeyListener, MouseListener {

    ArrayList<Product> products;
    private SellerGUI view1;
    private SoldProduct obj_sp1;
    private File logs;
    private Camera cam;
    private Thread t1;
    private boolean pauseSell = true;

    String pattern = "MM/dd/yyyy";
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
    String date = simpleDateFormat.format(new Date());

    public SellerHandler() {
        cam = new Camera();
        view1 = new SellerGUI();
        cam = new Camera();
        t1 = new Thread(cam);
        t1.start();
        products = new ArrayList<Product>();
        logs = new File("Products.dat");

        try {
            logs.createNewFile();
        } catch (IOException ev) {
            ev.printStackTrace();
        }
        init();

        // Add Delete button shortcut
        KeyStroke delete = KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0);
        view1.getTable().getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(delete, "delete");
        view1.getTable().getActionMap().put("delete", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleDelete();
            }
        });

        // Add Enter button shortcut
        KeyStroke keyCal = KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0);
        view1.getTxtTotalPrice().registerKeyboardAction(this, "Calculator", keyCal, JComponent.WHEN_FOCUSED);
        view1.getTxtIncome().registerKeyboardAction(this, "Calculator", keyCal, JComponent.WHEN_FOCUSED);

    }

    public void init() {
        view1.getFr().addWindowListener(this);
        view1.getBnRecord().addActionListener(this);
        view1.getBnPrint().addActionListener(this);
        view1.getBnCancel().addActionListener(this);
        view1.getBnScan().addActionListener(this);
        view1.getCheckText().addActionListener(this);
    }

    //DeleteDataEventHandler
    private void handleDelete() {
        if (view1.getTable().getSelectedRowCount() == 1) {

            int num1 = view1.getTable().getSelectedRow();
            System.out.println(num1);
            view1.getTablemodel().fireTableDataChanged();
            double oldtotal = view1.getTablemodel().soldProducts.get(num1).getTotal();
            double total = Double.parseDouble(view1.getTxtTotalPrice().getText());
            double newtotal = total - oldtotal;
            view1.getTxtTotalPrice().setText(newtotal + "");
            view1.getTablemodel().soldProducts.remove(num1);
            setTxtBill();
            JOptionPane.showMessageDialog(view1.getFr(), "Deleted.");
        } else {
            if (view1.getTable().getRowCount() == 0) {
                JOptionPane.showMessageDialog(view1.getFr(), "Table is Empty.");
            } else {
                JOptionPane.showMessageDialog(view1.getFr(), "Please select Single Row For Delete.");
            }
        }
    }

    public int checkNo(String code, ArrayList<Product> list) {
        int i = 0;
        while (i < list.size()) {
            if (code.equals(list.get(i).getCode())) {
                return list.get(i).getNo();
            }
            i += 1;
        }
        return -1;
    }

    public double checkCost(String code, ArrayList<Product> list) {
        int i = 0;
        while (i < list.size()) {
            if (code.equals(list.get(i).getCode())) {
                return list.get(i).getCost();
            }
            i += 1;
        }
        return -1;
    }

    public String checkCode(String code, ArrayList<Product> list) {
        int i = 0;
        while (i < list.size()) {
            if (code.equals(list.get(i).getCode())) {
                return list.get(i).getCode();
            }
            i += 1;
        }
        return null;
    }

    public int totalPrice(ArrayList<SoldProduct> list) {
        int i = 0;
        int total = 0;
        while (i < list.size()) {
            total += list.get(i).getTotal();
            i += 1;
        }
        return total;
    }

    public Product searchProduct(String code, ArrayList<Product> list) {
        int i = 0;
        while (i < list.size()) {
            if (code.equals(list.get(i).getCode())) {
                return list.get(i);
            }
            i += 1;
        }
        return null;
    }

    public void calChange(double total, double receive) {
        double ans = total - receive;
        if (ans >= 0) {
            view1.getTxtChange().setText(ans + "");
        } else {
            JOptionPane.showMessageDialog(view1.getFr(), "Your Change must not be minus", "Error", MIN_PRIORITY);
        }
    }

    public int checkItem(int no, ArrayList<SoldProduct> list) {
        int i = 0;
        while (i < list.size()) {
            if (no == list.get(i).getNo()) {
                return i;
            }
            i += 1;
        }
        return -1;
    }

    public void clearTxtfield(SellerGUI view) {
        view.getTxtAmount().setText("");
        view.getTxtCode().setText("");
        view.getTxtName().setText("");
        view.getTxtPrice().setText("");
        view.getTxtChange().setText("");
        view.getTxtIncome().setText("");
    }

    @Override
    //Press F1 Shortcut
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Calculator")) {
            try {
                double recieve = Double.parseDouble(view1.getTxtTotalPrice().getText());
                double total = Double.parseDouble(view1.getTxtIncome().getText());
                calChange(total, recieve);
                setTxtBill();
            } catch (NumberFormatException ex) {
                System.out.println(ex);
            }
        }
        if (e.getSource().equals(view1.getBnRecord())) {
            autoFresh();
            if (view1.getTxtAmount().getText().equals("") & view1.getTxtCode().getText().equals("") || view1.getTxtCode().getText().equals("")) {
                JOptionPane.showMessageDialog(view1.getFr(), "Please Enter All Data!");
            } else if (checkNo(view1.getTxtCode().getText(), this.products) == -1) {
                JOptionPane.showMessageDialog(view1.getFr(), "This product does not exist in the system.");
                ResetTxtField();
            } else {
                try {
                    if (view1.getTxtAmount().getText().equals("") & !view1.getTxtCode().getText().equals("")) {
                        Product obj = new Product();
                        obj = searchProduct(view1.getTxtCode().getText(), products);
                        view1.getTxtName().setText(obj.getName());
                        view1.getTxtPrice().setText(obj.getPrice() + "");
                        view1.getTxtAmount().setText("1");

                        int no = checkNo(view1.getTxtCode().getText(), this.products);
                        int amount = Integer.parseInt(view1.getTxtAmount().getText());

                        if (view1.getTablemodel().soldProducts.size() >= 1) {
                            if (checkItem(no, view1.getTablemodel().soldProducts) != -1) {
                                int index = checkItem(no, view1.getTablemodel().soldProducts);
                                view1.getTablemodel().soldProducts.get(index).setAmount(view1.getTablemodel().soldProducts.get(index).getAmount() + amount);
                                view1.getTxtTotalPrice().setText(totalPrice(view1.getTablemodel().soldProducts) + "");
                                view1.getTablemodel().fireTableDataChanged();
                                view1.getTablemodel().printArray();
                            }
                        }
                    } else if (!view1.getTxtAmount().getText().equals("") & !view1.getTxtCode().getText().equals("") & !view1.getTxtPrice().getText().equals("") & !view1.getTxtPrice().getText().equals("")) {
                        Product obj = new Product();
                        obj = searchProduct(view1.getTxtCode().getText(), products);
                        view1.getTxtName().setText(obj.getName());
                        view1.getTxtPrice().setText(obj.getPrice() + "");
                    }
                    int no = checkNo(view1.getTxtCode().getText(), this.products);
                    String name = view1.getTxtName().getText();
                    double price = Double.parseDouble(view1.getTxtPrice().getText());
                    int amount = Integer.parseInt(view1.getTxtAmount().getText());
                    double cost = checkCost(view1.getTxtCode().getText(), this.products);
                    String code = checkCode(view1.getTxtCode().getText(), this.products);
                    double total = amount * price;
                    if (amount > 0) {
                        if (view1.getTablemodel().soldProducts.size() >= 1) {
                            if (checkItem(no, view1.getTablemodel().soldProducts) != -1) {
                                int index = checkItem(no, view1.getTablemodel().soldProducts);
                                view1.getTablemodel().soldProducts.get(index).setAmount(view1.getTablemodel().soldProducts.get(index).getAmount() + amount);
                                view1.getTablemodel().soldProducts.get(index).setTotal(view1.getTablemodel().soldProducts.get(index).getTotal() + total);
                                view1.getTxtTotalPrice().setText(totalPrice(view1.getTablemodel().soldProducts) + "");
                                view1.getTablemodel().fireTableDataChanged();
                                view1.getTablemodel().printArray();
                            } else {
                                obj_sp1 = new SoldProduct(total, no, code, name, price, cost, amount);
                                view1.getTablemodel().soldProducts.add(obj_sp1);
                                view1.getTxtTotalPrice().setText(totalPrice(view1.getTablemodel().soldProducts) + "");
                                view1.getTablemodel().fireTableDataChanged();
                                view1.getTablemodel().printArray();
                            }
                        } else {
                            obj_sp1 = new SoldProduct(total, no, code, name, price, cost, amount);
                            view1.getTablemodel().soldProducts.add(obj_sp1);
                            view1.getTxtTotalPrice().setText(totalPrice(view1.getTablemodel().soldProducts) + "");
                            view1.getTablemodel().fireTableDataChanged();
                            view1.getTablemodel().printArray();
                        }
                    } else {
                        JOptionPane.showMessageDialog(view1.getFr(), "amount can not be " + view1.getTxtAmount().getText() + " !");
                    }
                } catch (NumberFormatException nfe) {
                    JOptionPane.showMessageDialog(view1.getFr(), "amount can not be " + view1.getTxtAmount().getText() + " !");
                }
                setTxtBill();
            }
        }
        if (e.getSource().equals(view1.getBnPrint())) {
            if (view1.getTxtAmount().getText().equals("") || view1.getTxtPrice().getText().equals("") || view1.getTxtName().getText().equals("") || view1.getTxtCode().getText().equals("")) {
                JOptionPane.showMessageDialog(view1.getFr(), "No any Data!");
            } else {
                try {
                    setTxtBill();
                    view1.area.print(); //print
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        }
        if (e.getSource().equals(view1.getBnCancel())) {
            if (view1.getTablemodel().soldProducts.size() >= 1) {
                view1.getTablemodel().soldProducts.clear();
                clearTxtfield(view1);
                view1.getTablemodel().fireTableDataChanged();
                view1.getTxtTotalPrice().setText("0");
                view1.area.setText("");
            }
        }
        if (e.getSource().equals(view1.getBnScan())) {
            view1.getBnScan().setText(view1.getBnScan().getText().equals("⏹ ️Stop") ? "Scan" : "⏹ ️Stop");
            if (view1.getBnScan().getText().equals("⏹ ️Stop")) {
                cam.resumeThread();
                useAuto();
            } else {
                useManual();
                cam.pauseThread();
            }
        }

        if (e.getSource() == view1.getCheckText()) {
            if (view1.getCheckText().isSelected()) {
                view1.getTxtAmount().setEditable(true);
            } else {
                view1.getTxtAmount().setEditable(false);
            }
        }
    }

    public void setTxtBill() {
        view1.area.setText("     King Mongkut's Institute of Technology Ladkrabang \n");
        view1.area.setText(view1.area.getText() + "                 1 Chalong Krung 1 Alley, Lat Krabang, \n");
        view1.area.setText(view1.area.getText() + "                        Bangkok 10520, Thailand \n");
        view1.area.setText(view1.area.getText() + " --------------------------------------------------------------------------\n");
        view1.area.setText(view1.area.getText() + " Item \t\tQty. \tPrice" + "\n");
        view1.area.setText(view1.area.getText() + " --------------------------------------------------------------------------\n");

        // get table Product details
        for (int i = 0; i < view1.getTablemodel().soldProducts.size(); i++) {

            String Name = view1.getTablemodel().getValueAt(i, 1).toString();
            String Qty = view1.getTablemodel().getValueAt(i, 3).toString();
            String Price = view1.getTablemodel().getValueAt(i, 4).toString();
            view1.area.setText(view1.area.getText() + "  " + Name + "\t\t" + Qty + "\t" + Price + "\n");
        }

        view1.area.setText(view1.area.getText() + " --------------------------------------------------------------------------\n");
        view1.area.setText(view1.area.getText() + " Sub Total : " + view1.getTxtTotalPrice().getText() + "\n");
        view1.area.setText(view1.area.getText() + " Cash      : " + view1.getTxtIncome().getText() + "\n");
        view1.area.setText(view1.area.getText() + " Balance   : " + view1.getTxtChange().getText() + "\n");
        view1.area.setText(view1.area.getText() + " --------------------------------------------------------------------------\n");
        view1.area.setText(view1.area.getText() + "                       Thanks For Your Business...!" + "\n");
        view1.area.setText(view1.area.getText() + " --------------------------------------------------------------------------\n");
        view1.area.setText(view1.area.getText() + "                            Cashier Management" + "\n");
    }

    public void ResetTxtField() {
        view1.getTxtCode().setText("");
        view1.getTxtName().setText("");
        view1.getTxtPrice().setText("");
        view1.getTxtAmount().setText("");
    }

    public void autoFresh() {
        try (FileInputStream stream = new FileInputStream(logs); ObjectInputStream ips = new ObjectInputStream(stream);) {
            this.products = (ArrayList<Product>) ips.readObject();
//            System.out.println("Success");
        } catch (Exception ex) {
//            System.out.println(ex);
//            ex.printStackTrace();
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {
        if (logs.exists()) {
            try (FileInputStream stream = new FileInputStream(logs); ObjectInputStream ips = new ObjectInputStream(stream);) {
                this.products = (ArrayList<Product>) ips.readObject();
            } catch (Exception ex) {
//                System.out.println(ex);
            }
        }
    }

    @Override
    public void windowClosing(WindowEvent e) {

    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public void setBtnScan(String text) {
        view1.getBnScan().setText(text);
    }

    //Check to pause/resume to use manual input
    private synchronized void checkPaused() {
        while (pauseSell) {
            try {
                this.wait();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void useManual() {
        pauseSell = true;
    }

    public synchronized void useAuto() {
        pauseSell = false;
        this.notify();
    }

    public void run() {
        while (true) {
            autoFresh();
            checkPaused();
            if (cam.isActive()) {
                view1.getBnScan().setText("Scan");
                useManual();
            } else {
                ResetTxtField();
                view1.getBnScan().setText("⏹ ️Stop");
                useAuto();
            }
            try {
                Thread.sleep(1000);
                view1.getTxtCode().setText(cam.getSN());

                if (view1.getTxtCode().getText().isEmpty()) {
                    //while getText not found any num
                    view1.getTxtCode().setText("");

                } else if (searchProduct(view1.getTxtCode().getText(), products) == null) {
                    //found products in WareHouse
                    JOptionPane.showMessageDialog(view1.getFr(), "This product does not exist in the system.");
                    view1.getTxtCode().setText("");
                    cam.setSN("");

                } else {
                    //found in WareHouse
                    Product obj = new Product();
                    obj = searchProduct(view1.getTxtCode().getText(), products);
                    view1.getTxtName().setText(obj.getName());
                    view1.getTxtPrice().setText(obj.getPrice() + "");
                    view1.getTxtAmount().setText("1");
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(SellerHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void main(String[] args) {
        SellerHandler sell = new SellerHandler();
        sell.start();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
    
    public JFrame getFr(){
        return view1.getFr();
    }

}
