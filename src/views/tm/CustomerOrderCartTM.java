package views.tm;

public class CustomerOrderCartTM {
    private String itemCode;
    private String description ;
    private double unitPrice;
    private int qty ;
    private double totaldiscount;
    private double total ;

    public CustomerOrderCartTM() {
    }

    public CustomerOrderCartTM(String itemCode, String description, double unitPrice, int qty, double totaldiscount, double total) {
        this.itemCode = itemCode;
        this.description = description;
        this.unitPrice = unitPrice;
        this.qty = qty;
        this.totaldiscount = totaldiscount;
        this.total = total;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getTotaldiscount() {
        return totaldiscount;
    }

    public void setTotaldiscount(double totaldiscount) {
        this.totaldiscount = totaldiscount;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
