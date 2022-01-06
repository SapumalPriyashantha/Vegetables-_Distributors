package model;

import views.tm.DeliveryOrderTM;

import java.util.ArrayList;

public class DeliveryOrder {

    private String DeliveryID;
    private String CustomerOrderID;
    private String DriverId;
    private String VehicalId;
    private int DeliveryCharge;
    private String Status;
    private ArrayList<DeliveryOrderTM> items;
    private int itemCost;
    private  int totalValue;


    public DeliveryOrder() {
    }

    public DeliveryOrder(String deliveryID, String customerOrderID, String driverId, String vehicalId, int deliveryCharge, String status) {
        DeliveryID = deliveryID;
        CustomerOrderID = customerOrderID;
        DriverId = driverId;
        VehicalId = vehicalId;
        DeliveryCharge = deliveryCharge;
        Status = status;
    }

    public DeliveryOrder(String deliveryID, String customerOrderID, String driverId, String vehicalId, int deliveryCharge, String status, ArrayList<DeliveryOrderTM> items) {
        DeliveryID = deliveryID;
        CustomerOrderID = customerOrderID;
        DriverId = driverId;
        VehicalId = vehicalId;
        DeliveryCharge = deliveryCharge;
        Status = status;
        this.items = items;
    }

    public DeliveryOrder(String deliveryID, String customerOrderID, String driverId, String vehicalId, int deliveryCharge, String status, ArrayList<DeliveryOrderTM> items, int totalValue) {
        DeliveryID = deliveryID;
        CustomerOrderID = customerOrderID;
        DriverId = driverId;
        VehicalId = vehicalId;
        DeliveryCharge = deliveryCharge;
        Status = status;
        this.items = items;
        this.totalValue = totalValue;
    }

    public int getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(int totalValue) {
        this.totalValue = totalValue;
    }

    public String getDeliveryID() {
        return DeliveryID;
    }

    public void setDeliveryID(String deliveryID) {
        DeliveryID = deliveryID;
    }

    public String getCustomerOrderID() {
        return CustomerOrderID;
    }

    public void setCustomerOrderID(String customerOrderID) {
        CustomerOrderID = customerOrderID;
    }

    public String getDriverId() {
        return DriverId;
    }

    public void setDriverId(String driverId) {
        DriverId = driverId;
    }

    public String getVehicalId() {
        return VehicalId;
    }

    public void setVehicalId(String vehicalId) {
        VehicalId = vehicalId;
    }

    public int getDeliveryCharge() {
        return DeliveryCharge;
    }

    public void setDeliveryCharge(int deliveryCharge) {
        DeliveryCharge = deliveryCharge;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public ArrayList<DeliveryOrderTM> getItems() {
        return items;
    }

    public void setItems(ArrayList<DeliveryOrderTM> items) {
        this.items = items;
    }

    public int getItemCost() {
        return itemCost;
    }

    public void setItemCost(int itemCost) {
        this.itemCost = itemCost;
    }
}
