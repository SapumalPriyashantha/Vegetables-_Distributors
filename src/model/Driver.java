package model;

public class Driver {

    private String DriverId;
    private String DriverName;
    private int DriverLicenceNumber;
    private int DriverContactNumber;

    public Driver() {
    }

    public Driver(String driverId, String driverName, int driverLicenceNumber, int driverContactNumber) {
        DriverId = driverId;
        DriverName = driverName;
        DriverLicenceNumber = driverLicenceNumber;
        DriverContactNumber = driverContactNumber;
    }

    public String getDriverId() {
        return DriverId;
    }

    public void setDriverId(String driverId) {
        DriverId = driverId;
    }

    public String getDriverName() {
        return DriverName;
    }

    public void setDriverName(String driverName) {
        DriverName = driverName;
    }

    public int getDriverLicenceNumber() {
        return DriverLicenceNumber;
    }

    public void setDriverLicenceNumber(int driverLicenceNumber) {
        DriverLicenceNumber = driverLicenceNumber;
    }

    public int getDriverContactNumber() {
        return DriverContactNumber;
    }

    public void setDriverContactNumber(int driverContactNumber) {
        DriverContactNumber = driverContactNumber;
    }
}
