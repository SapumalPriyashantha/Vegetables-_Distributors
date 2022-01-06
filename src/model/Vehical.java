package model;

public class Vehical {

    private String VehicalId;
    private String VehicalNumber;
    private String VehicalType;

    public Vehical() {
    }

    public Vehical(String vehicalId, String vehicalNumber, String vehicalType) {
        VehicalId = vehicalId;
        VehicalNumber = vehicalNumber;
        VehicalType = vehicalType;
    }

    public String getVehicalId() {
        return VehicalId;
    }

    public void setVehicalId(String vehicalId) {
        VehicalId = vehicalId;
    }

    public String getVehicalNumber() {
        return VehicalNumber;
    }

    public void setVehicalNumber(String vehicalNumber) {
        VehicalNumber = vehicalNumber;
    }

    public String getVehicalType() {
        return VehicalType;
    }

    public void setVehicalType(String vehicalType) {
        VehicalType = vehicalType;
    }
}
