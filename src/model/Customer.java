package model;

public class Customer {

    private String CustomerId;
    private String CustomerName;
    private String ShopAddress;
    private int CustomerContactNumber;

    public Customer() {
    }

    public Customer(String customerId, String customerName, String shopAddress, int customerContactNumber) {
        CustomerId = customerId;
        CustomerName = customerName;
        ShopAddress = shopAddress;
        CustomerContactNumber = customerContactNumber;
    }

    public String getCustomerId() {
        return CustomerId;
    }

    public void setCustomerId(String customerId) {
        CustomerId = customerId;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String customerName) {
        CustomerName = customerName;
    }

    public String getShopAddress() {
        return ShopAddress;
    }

    public void setShopAddress(String shopAddress) {
        ShopAddress = shopAddress;
    }

    public int getCustomerContactNumber() {
        return CustomerContactNumber;
    }

    public void setCustomerContactNumber(int customerContactNumber) {
        CustomerContactNumber = customerContactNumber;
    }
}
