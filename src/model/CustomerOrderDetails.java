package model;

public class CustomerOrderDetails {

    private String CustomerOrderID;
    private String ItemCode;
    private int CustomerOrderQTY;
    private double cost;
    private String Status;

    public CustomerOrderDetails() {
    }

    public CustomerOrderDetails(String customerOrderID, String itemCode, int customerOrderQTY, double cost, String status) {
        CustomerOrderID = customerOrderID;
        ItemCode = itemCode;
        CustomerOrderQTY = customerOrderQTY;
        this.cost = cost;
        Status = status;
    }

    public String getCustomerOrderID() {
        return CustomerOrderID;
    }

    public void setCustomerOrderID(String customerOrderID) {
        CustomerOrderID = customerOrderID;
    }

    public String getItemCode() {
        return ItemCode;
    }

    public void setItemCode(String itemCode) {
        ItemCode = itemCode;
    }

    public int getCustomerOrderQTY() {
        return CustomerOrderQTY;
    }

    public void setCustomerOrderQTY(int customerOrderQTY) {
        CustomerOrderQTY = customerOrderQTY;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}
