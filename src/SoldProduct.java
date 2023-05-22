public class SoldProduct extends Product {
    
    private double total;
    
    public SoldProduct(int no, String name, double price, int amount, double total) {
        super(no, name, price, amount);
        this.total = total;
    }

    public SoldProduct(double total, int no, String code, String name, double price, double cost, int amount) {
        super(no, code, name, price, cost, amount);
        this.total = total;
    }
    
    public double getTotal() {
        return total;
    }
    
    public void setTotal(double total) {
        this.total = total;
    }
    
}
