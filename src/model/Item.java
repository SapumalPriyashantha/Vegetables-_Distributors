package model;

public class Item {

    private String ItemCode;
    private String SupplierId;
    private String Description;
    private double GetUnitPrice;
    private double SellUnitPrice;
    private int StockQty;
    private int QtyOnHand;
    private double Discount;

    public Item() {
    }

    public Item(String itemCode, double getUnitPrice, double sellUnitPrice, double discount) {
        ItemCode = itemCode;
        GetUnitPrice = getUnitPrice;
        SellUnitPrice = sellUnitPrice;
        Discount = discount;
    }

    public Item(String itemCode, String supplierId, String description, double getUnitPrice, double sellUnitPrice, int stockQty, int qtyOnHand, double discount) {
        ItemCode = itemCode;
        SupplierId = supplierId;
        Description = description;
        GetUnitPrice = getUnitPrice;
        SellUnitPrice = sellUnitPrice;
        StockQty = stockQty;
        QtyOnHand = qtyOnHand;
        Discount = discount;
    }

    public String getSupplierId() {
        return SupplierId;
    }

    public void setSupplierId(String supplierId) {
        SupplierId = supplierId;
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

    public double getGetUnitPrice() {
        return GetUnitPrice;
    }

    public void setGetUnitPrice(double getUnitPrice) {
        GetUnitPrice = getUnitPrice;
    }

    public double getSellUnitPrice() {
        return SellUnitPrice;
    }

    public void setSellUnitPrice(double sellUnitPrice) {
        SellUnitPrice = sellUnitPrice;
    }

    public int getStockQty() {
        return StockQty;
    }

    public void setStockQty(int stockQty) {
        StockQty = stockQty;
    }

    public int getQtyOnHand() {
        return QtyOnHand;
    }

    public void setQtyOnHand(int qtyOnHand) {
        QtyOnHand = qtyOnHand;
    }

    public double getDiscount() {
        return Discount;
    }

    public void setDiscount(double discount) {
        Discount = discount;
    }
}
