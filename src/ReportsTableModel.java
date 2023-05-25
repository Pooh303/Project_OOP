
//Import section
import java.io.*;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class ReportsTableModel extends AbstractTableModel implements Serializable {

    ArrayList<SoldProduct> soldProducts;
    String[] header = {"Sold Date", "Code", "Name", "Cost", "Price", "Amount", "Total Cost", "Total Price"};

    public ReportsTableModel() {
        soldProducts = new ArrayList<SoldProduct>();
        initDatas();
    }

    private void initDatas() {

    }

    @Override
    public String getColumnName(int columnId) {
        return header[columnId];
    }

    @Override
    public int getRowCount() {
        return soldProducts.size();
    }

    @Override
    public int getColumnCount() {
        return header.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (soldProducts.isEmpty()) {
            return null;
        } else {
            SoldProduct s = soldProducts.get(rowIndex);
            //If you have more fields, you should specify more cases here.
            switch (columnIndex) {
                case 0:
                    return s.getDate();
                case 1:
                    return s.getCode();
                case 2: // id first
                    return s.getName();
                case 3:
                    return s.getCost();
                case 4:
                    return s.getPrice();
                case 5:
                    return s.getAmount();
                case 6:
                    return s.getTcost();
                case 7:
                    return s.getTprice();

                default:
                    return null;
            }
        }
    }

    public boolean checkCode(String code, ArrayList<SoldProduct> list) {
        int i = 0;
        while (i < list.size()) {
            if (code.equals((list.get(i).getCode()))) {
                return true;
            }
            i += 1;
        }
        return false;
    }

    public void printArray() {
        for (Product info : soldProducts) {
            System.out.println(info.toString());
        }
    }
}
