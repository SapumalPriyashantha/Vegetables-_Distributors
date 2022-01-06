package views.tm;

public class DeliveryOrderTM {
    private String itemCode;
    private String description;
    private int customerOrderQTY;
    private double cost;


    public DeliveryOrderTM() {
    }

    public DeliveryOrderTM(String itemCode, int customerOrderQTY, double cost) {
        this.itemCode = itemCode;
        this.customerOrderQTY = customerOrderQTY;
        this.cost = cost;
    }

    public DeliveryOrderTM(String itemCode, String description, int customerOrderQTY, double cost) {
        this.itemCode = itemCode;
        this.description = description;
        this.customerOrderQTY = customerOrderQTY;
        this.cost = cost;
    }


    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public int getCustomerOrderQTY() {
        return customerOrderQTY;
    }

    public void setCustomerOrderQTY(int customerOrderQTY) {
        this.customerOrderQTY = customerOrderQTY;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
