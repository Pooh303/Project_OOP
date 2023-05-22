
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

    //กำหนดค่าเริ่มต้นให้กับข้อมูล
    private void initDatas() {
        products.add(new Product(111,"11","111",111,111,111));
    }

    @Override
    public String getColumnName(int columnId) {
        //return ชื่อของแต่ละ column ที่ต้องการแสดงใน table
        return header[columnId];
    }

    @Override
    public int getRowCount() {
        //return จำนวนแถวข้อมูลทั้งหมด
        return products.size();
    }

    @Override
    public int getColumnCount() {
        //return จำนวนของ column
        return header.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (products.isEmpty()) {
            return null;
        } else {
            Product s = products.get(rowIndex);
            //if you have more field should specify more case here
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
