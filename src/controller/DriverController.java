package controller;

import db.DbConnection;
import model.Customer;
import model.Driver;
import model.Supplier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DriverController {
    public String getDriverId() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance()
                .getConnection().prepareStatement(
                        "SELECT DriverId FROM Driver ORDER BY DriverId DESC LIMIT 1"
                ).executeQuery();
        if (rst.next()){

            int tempId = Integer.
                    parseInt(rst.getString(1).split("-")[1]);
            tempId=tempId+1;
            if (tempId<=9){
                return "D-00"+tempId;
            }else if(tempId<99){
                return "D-0"+tempId;
            }else{
                return "D-"+tempId;
            }

        }else{
            return "D-001";
        }
    }

    public boolean saveDriver(Driver d1) throws SQLException, ClassNotFoundException {
        Connection con= DbConnection.getInstance().getConnection();
        String query="INSERT INTO Driver VALUES (?,?,?,?)";
        PreparedStatement stm = con.prepareStatement(query);

        stm.setObject(1,d1.getDriverId());
        stm.setObject(2,d1.getDriverName());
        stm.setObject(3,d1.getDriverLicenceNumber());
        stm.setObject(4,d1.getDriverContactNumber());

        return stm.executeUpdate()>0;
    }

    public List<String> getAllDriverIds() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance().
                getConnection().prepareStatement("SELECT * FROM Driver").executeQuery();
        List<String> ids = new ArrayList<>();
        while (rst.next()){
            ids.add(
                    rst.getString(1)
            );
        }
        return ids;
    }

    public Driver getDriverData(String supplierId) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance()
                .getConnection().prepareStatement("SELECT * FROM Driver WHERE DriverId=?");
        stm.setObject(1, supplierId);

        ResultSet rst = stm.executeQuery();
        if (rst.next()) {
            return new Driver(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getInt(3),
                    rst.getInt(4)
            );

        } else {
            return null;
        }
    }

    public boolean deleteDriverr(String driverId) throws SQLException, ClassNotFoundException {
        if (DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM Driver WHERE DriverId='"+driverId+"'").executeUpdate()>0){
            return true;
        }else{
            return false;
        }
    }

    public List<Driver> DriverTableData() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance().
                getConnection().prepareStatement("SELECT * FROM Driver").executeQuery();
        List<Driver> ids = new ArrayList<>();
        while (rst.next()){
            ids.add(
                    new Driver(rst.getString(1),
                            rst.getString(2),
                            rst.getInt(3),
                            rst.getInt(4)
                    )
            );
        }
        return ids;
    }
}
