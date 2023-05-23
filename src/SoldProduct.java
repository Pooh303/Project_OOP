public class SoldProduct extends Product {
    
    private double total;
    private String billKey;
    private String date;
    private double tcost;
    private double tprice;
    
    public SoldProduct(int no, String name, double price, int amount, double total) {
        super(no, name, price, amount);
        this.total = total;
    }

    public SoldProduct(double total, int no, String code, String name, double price, double cost, int amount) {
        super(no, code, name, price, cost, amount);
        this.total = total;
    }

    public SoldProduct(String billKey, String date, double tcost, double tprice, int no, String code, String name, double price, double cost, int amount) {
        super(no, code, name, price, cost, amount);
        this.billKey = billKey;
        this.date = date;
        this.tcost = tcost;
        this.tprice = tprice;
    }

    public SoldProduct() {
    }
    
    
    public double getTotal() {
        return total;
    }
    
    public void setTotal(double total) {
        this.total = total;
    }

    public String getBillKey() {
        return billKey;
    }

    public void setBillKey(String billKey) {
        this.billKey = billKey;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getTcost() {
        return tcost;
    }

    public void setTcost(double tcost) {
        this.tcost = tcost;
    }

    public double getTprice() {
        return tprice;
    }

    public void setTprice(double tprice) {
        this.tprice = tprice;
    }
    
    
    
}
