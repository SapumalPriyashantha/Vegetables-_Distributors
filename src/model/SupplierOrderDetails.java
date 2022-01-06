package model;

public class SupplierOrderDetails {

    private String SupplierOrderID;
    private String ItemCode;
    private int OrderQTY;
    private double cost;

    public SupplierOrderDetails() {
    }

    public SupplierOrderDetails(String supplierOrderID, String itemCode, int orderQTY, double cost) {
        SupplierOrderID = supplierOrderID;
        ItemCode = itemCode;
        OrderQTY = orderQTY;
        this.cost = cost;
    }

    public String getSupplierOrderID() {
        return SupplierOrderID;
    }

    public void setSupplierOrderID(String supplierOrderID) {
        SupplierOrderID = supplierOrderID;
    }

    public String getItemCode() {
        return ItemCode;
    }

    public void setItemCode(String itemCode) {
        ItemCode = itemCode;
    }

    public int getOrderQTY() {
        return OrderQTY;
    }

    public void setOrderQTY(int orderQTY) {
        OrderQTY = orderQTY;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}
