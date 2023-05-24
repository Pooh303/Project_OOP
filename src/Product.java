
//Import section
import java.io.*;

public class Product implements Serializable {

    private String code;
    private String name;
    private double price;
    private double cost;
    private int amount;
    private int no;

    public Product(int no, String code, String name, double price, double cost, int amount) {
        this.no = no;
        this.code = code;
        this.name = name;
        this.price = price;
        this.cost = cost;
        this.amount = amount;
    }

    public Product(int no, String name, double price, int amount) {
        this.no = no;
        this.name = name;
        this.price = price;
        this.amount = amount;
    }

    public Product(String code, String name, double price, int amount) {
        this.code = code;
        this.name = name;
        this.price = price;
        this.amount = amount;
    }

    public Product() {
        this.code = null;
        this.name = null;
        this.price = 0;
        this.amount = 0;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Product{" + "code=" + code + ", name=" + name + ", price=" + price + ", cost=" + cost + ", amount=" + amount + ", no=" + no + '}';
    }

}
