
public abstract class absWareHouseHandler {
    protected int num = 0;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
    
    public abstract void saveDataToFile();

}
