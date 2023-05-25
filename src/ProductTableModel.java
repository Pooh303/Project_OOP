
//Import section
import java.io.*;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class ProductTableModel extends AbstractTableModel implements Serializable {

    ArrayList<Product> products;
    String[] header = {"No", "Code", "Name", "Price", "Cost", "Amount"};

    public ProductTableModel() {
        products = new ArrayList<Product>();
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
        return products.size();
    }

    @Override
    public int getColumnCount() {
        return header.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (products.isEmpty()) {
            return null;
        } else {
            Product s = products.get(rowIndex);
            //If you have more fields, you should specify more cases here.
            switch (columnIndex) {
                case 0:
                    return s.getNo();
                case 1: // id first
                    return s.getCode();
                case 2:
                    return s.getName();
                case 3:
                    return s.getPrice();
                case 4:
                    return s.getCost();
                case 5:
                    return s.getAmount();

                default:
                    return null;
            }
        }
    }

    public boolean checkCode(String code, int index, ArrayList<Product> list) {
        int i = 0;
        try {
            String c_code = list.get(index).getCode();
            if (c_code.equals(code)) {
                return false;
            }
            while (i < list.size()) {
                if (code.equals((list.get(i).getCode()))) {
                    return true;
                }
                i += 1;
            }
        } catch (Exception ex) {
            System.out.println("Please select a row in the table.");
        }
        return false;
    }

    public boolean checkCode(String code, ArrayList<Product> list) {
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
        for (Product info : products) {
            System.out.println(info.toString());
        }
    }

}
