package model;

import java.util.ArrayList;

public class CustomerOrder {

    private String CustomerOrderID;
    private String CustomerOrderDate;
    private String CustomerOrderDeliveryDate;
    private String CustomerId;
    private double TotalCost;
    private double TotalDiscount;
    private String Status;
    private ArrayList<CustomerOrderDetails> items;

    public CustomerOrder() {
    }

    public CustomerOrder(String customerOrderID, String customerOrderDate, String customerOrderDeliveryDate, String customerId, double totalCost, double totalDiscount, String status, ArrayList<CustomerOrderDetails> items) {
        CustomerOrderID = customerOrderID;
        CustomerOrderDate = customerOrderDate;
        CustomerOrderDeliveryDate = customerOrderDeliveryDate;
        CustomerId = customerId;
        TotalCost = totalCost;
        TotalDiscount = totalDiscount;
        Status = status;
        this.items = items;
    }

    public CustomerOrder(String customerOrderID, String customerOrderDate, String customerOrderDeliveryDate, double totalCost, double totalDiscount) {
        CustomerOrderID = customerOrderID;
        CustomerOrderDate = customerOrderDate;
        CustomerOrderDeliveryDate = customerOrderDeliveryDate;
        TotalCost = totalCost;
        TotalDiscount = totalDiscount;
    }

    public CustomerOrder(String customerOrderID, String customerOrderDate, String customerOrderDeliveryDate, String customerId, double totalCost, double totalDiscount, String status) {
        CustomerOrderID = customerOrderID;
        CustomerOrderDate = customerOrderDate;
        CustomerOrderDeliveryDate = customerOrderDeliveryDate;
        CustomerId = customerId;
        TotalCost = totalCost;
        TotalDiscount = totalDiscount;
        Status = status;
    }

    public CustomerOrder(String customerOrderID, String customerOrderDate, String customerId, double totalCost) {
        CustomerOrderID = customerOrderID;
        CustomerOrderDate = customerOrderDate;
        CustomerId = customerId;
        TotalCost = totalCost;
    }

    public String getCustomerOrderID() {
        return CustomerOrderID;
    }

    public void setCustomerOrderID(String customerOrderID) {
        CustomerOrderID = customerOrderID;
    }

    public String getCustomerOrderDate() {
        return CustomerOrderDate;
    }

    public void setCustomerOrderDate(String customerOrderDate) {
        CustomerOrderDate = customerOrderDate;
    }

    public String getCustomerOrderDeliveryDate() {
        return CustomerOrderDeliveryDate;
    }

    public void setCustomerOrderDeliveryDate(String customerOrderDeliveryDate) {
        CustomerOrderDeliveryDate = customerOrderDeliveryDate;
    }

    public String getCustomerId() {
        return CustomerId;
    }

    public void setCustomerId(String customerId) {
        CustomerId = customerId;
    }

    public double getTotalCost() {
        return TotalCost;
    }

    public void setTotalCost(double totalCost) {
        TotalCost = totalCost;
    }

    public double getTotalDiscount() {
        return TotalDiscount;
    }

    public void setTotalDiscount(double totalDiscount) {
        TotalDiscount = totalDiscount;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public ArrayList<CustomerOrderDetails> getItems() {
        return items;
    }

    public void setItems(ArrayList<CustomerOrderDetails> items) {
        this.items = items;
    }
}
