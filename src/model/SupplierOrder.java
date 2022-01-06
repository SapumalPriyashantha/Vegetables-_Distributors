package model;

import java.util.ArrayList;

public class SupplierOrder {
    private String SupplierOrderID;
    private String SupplierOrderDate;
    private String SupplierId;
    private double TotalCost;
    private ArrayList<SupplierOrderDetails> items;

    public SupplierOrder() {
    }

    public SupplierOrder(String supplierOrderID, String supplierOrderDate, String supplierId, double totalCost, ArrayList<SupplierOrderDetails> items) {
        SupplierOrderID = supplierOrderID;
        SupplierOrderDate = supplierOrderDate;
        SupplierId = supplierId;
        TotalCost = totalCost;
        this.items = items;
    }

    public SupplierOrder(String supplierOrderID, String supplierOrderDate, String supplierId, double totalCost) {
        SupplierOrderID = supplierOrderID;
        SupplierOrderDate = supplierOrderDate;
        SupplierId = supplierId;
        TotalCost = totalCost;
    }

    public SupplierOrder(String supplierOrderID, String supplierOrderDate, double totalCost) {
        SupplierOrderID = supplierOrderID;
        SupplierOrderDate = supplierOrderDate;
        TotalCost = totalCost;
    }

    public String getSupplierOrderID() {
        return SupplierOrderID;
    }

    public void setSupplierOrderID(String supplierOrderID) {
        SupplierOrderID = supplierOrderID;
    }

    public String getSupplierOrderDate() {
        return SupplierOrderDate;
    }

    public void setSupplierOrderDate(String supplierOrderDate) {
        SupplierOrderDate = supplierOrderDate;
    }

    public String getSupplierId() {
        return SupplierId;
    }

    public void setSupplierId(String supplierId) {
        SupplierId = supplierId;
    }

    public double getTotalCost() {
        return TotalCost;
    }

    public void setTotalCost(double totalCost) {
        TotalCost = totalCost;
    }

    public ArrayList<SupplierOrderDetails> getItems() {
        return items;
    }

    public void setItems(ArrayList<SupplierOrderDetails> items) {
        this.items = items;
    }
}
