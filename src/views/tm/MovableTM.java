package views.tm;

public class MovableTM {
    private String ItemCode;
    private String Description;
    private double SellUnitPrice;
    private int QtyOnHand;
    private int StockQty;
    private String present_age;

    public MovableTM(String itemCode, String description, double sellUnitPrice, int qtyOnHand, int stockQty, String present_age) {
        ItemCode = itemCode;
        Description = description;
        SellUnitPrice = sellUnitPrice;
        QtyOnHand = qtyOnHand;
        StockQty = stockQty;
        this.present_age = present_age;
    }

    public String getItemCode() {
        return ItemCode;
    }

    public void setItemCode(String itemCode) {
        ItemCode = itemCode;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public double getSellUnitPrice() {
        return SellUnitPrice;
    }

    public void setSellUnitPrice(double sellUnitPrice) {
        SellUnitPrice = sellUnitPrice;
    }

    public int getQtyOnHand() {
        return QtyOnHand;
    }

    public void setQtyOnHand(int qtyOnHand) {
        QtyOnHand = qtyOnHand;
    }

    public int getStockQty() {
        return StockQty;
    }

    public void setStockQty(int stockQty) {
        StockQty = stockQty;
    }

    public String getPresent_age() {
        return present_age;
    }

    public void setPresent_age(String present_age) {
        this.present_age = present_age;
    }
}
