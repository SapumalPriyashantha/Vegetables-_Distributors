package model;

public class Supplier {
    private String SupplierId;
    private String SupplierName;
    private String SupplierAddress;
    private int  SupplierContactNumber;

    public Supplier() {
    }

    public Supplier(String supplierId, String supplierName, String supplierAddress, int supplierContactNumber) {
        SupplierId = supplierId;
        SupplierName = supplierName;
        SupplierAddress = supplierAddress;
        SupplierContactNumber = supplierContactNumber;
    }

    public String getSupplierId() {
        return SupplierId;
    }

    public void setSupplierId(String supplierId) {
        SupplierId = supplierId;
    }

    public String getSupplierName() {
        return SupplierName;
    }

    public void setSupplierName(String supplierName) {
        SupplierName = supplierName;
    }

    public String getSupplierAddress() {
        return SupplierAddress;
    }

    public void setSupplierAddress(String supplierAddress) {
        SupplierAddress = supplierAddress;
    }

    public int getSupplierContactNumber() {
        return SupplierContactNumber;
    }

    public void setSupplierContactNumber(int supplierContactNumber) {
        SupplierContactNumber = supplierContactNumber;
    }
}
