
//Import section
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class ReportsHandler implements ActionListener, WindowListener, PropertyChangeListener {

    private ReportsGUI view;
    private SoldProduct obj1;
    private File flogs;
    ArrayList<SoldProduct> defaultList, products;

    public ReportsHandler() {
        flogs = new File("SoldProducts.dat");
        defaultList = new ArrayList<SoldProduct>();
        products = new ArrayList<SoldProduct>();
        try {
            flogs.createNewFile();
        } catch (IOException ev) {
            ev.printStackTrace();
        }
        view = new ReportsGUI();
        init();
    }

    private void searchDate() {
        view.getTableModel().soldProducts.clear();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        String date = sdf.format(view.getDateChooser().getDate());
        for (int i = 0; i < defaultList.size(); i++) {
            if (defaultList.get(i).getDate().equals(date)) {
                view.getTableModel().soldProducts.add(defaultList.get(i));
            }
        }
        view.getTableModel().fireTableDataChanged();
        updateStat();
    }

    //Set the total price, total cost, and total profit on a JTextField.
    private void updateStat() {
        double t_price = 0;
        double t_cost = 0;
        for (SoldProduct info : view.getTableModel().soldProducts) {
            t_price += info.getPrice() * info.getAmount();
            t_cost += info.getCost() * info.getAmount();
        }
        double profit = t_price - t_cost;
        //setText
        view.getTxt2().setText(t_price + "");
        view.getTxt1().setText(t_cost + "");
        view.getTxt3().setText(profit + "");
    }

    //Bill Search Method
    private void searchBill(String keyBill) {
        view.getTableModel().soldProducts.clear();
        for (SoldProduct info : defaultList) {
            if (info.getBillKey().equals(keyBill)) {
                view.getTableModel().soldProducts.add(info);
            }
        }
        view.getTableModel().fireTableDataChanged();
        updateStat();
    }

    public void init() {
        view.getDateChooser().getDateEditor().addPropertyChangeListener(this);
        view.getFr().addWindowListener(this);
        view.getB1().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(view.getB1())) {
            if (flogs.exists()) {
                try (FileInputStream stream = new FileInputStream(flogs); ObjectInputStream ips = new ObjectInputStream(stream);) {
                    view.getTableModel().soldProducts = (ArrayList<SoldProduct>) ips.readObject();
                    defaultList = (ArrayList<SoldProduct>) view.getTableModel().soldProducts.clone();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            view.getTableModel().fireTableDataChanged();
            updateStat();
            ((JTextField) view.getDateChooser().getDateEditor().getUiComponent()).setText("");
            view.getTableModel().fireTableDataChanged();
            updateStat();
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {
        if (flogs.exists()) {
            try (FileInputStream stream = new FileInputStream(flogs); ObjectInputStream ips = new ObjectInputStream(stream);) {
                view.getTableModel().soldProducts = (ArrayList<SoldProduct>) ips.readObject();
                defaultList = (ArrayList<SoldProduct>) view.getTableModel().soldProducts.clone();
            } catch (Exception ex) {
//                ex.printStackTrace();
            }
        }
        view.getTableModel().fireTableDataChanged();
        updateStat();
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
    public void propertyChange(PropertyChangeEvent evt) {
        if ("date".equals(evt.getPropertyName())) {
            searchDate();
        }
    }

    public JFrame getFr() {
        return this.view.getFr();
    }

    public static void main(String[] args) {
        new ReportsHandler();
    }
}
