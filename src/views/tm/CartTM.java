package views.tm;

public class CartTM {
    private String ItemCode;
    private String Description;
    private double UnitPrice;
    private int Qty;
    private double Total;

    public CartTM() {
    }

    public CartTM(String itemCode, String description, double unitPrice, int qty, double total) {
        ItemCode = itemCode;
        Description = description;
        UnitPrice = unitPrice;
        Qty = qty;
        Total = total;
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

    public double getUnitPrice() {
        return UnitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        UnitPrice = unitPrice;
    }

    public int getQty() {
        return Qty;
    }

    public void setQty(int qty) {
        Qty = qty;
    }

    public double getTotal() {
        return Total;
    }

    public void setTotal(double total) {
        Total = total;
    }
}
