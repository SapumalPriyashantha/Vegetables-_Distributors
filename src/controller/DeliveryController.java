package controller;

import db.DbConnection;
import model.*;
import views.tm.DeliveryOrderTM;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DeliveryController {
    String updateStatus="YES";

    public static DeliveryOrder AllDeliveruDetails(String orderId) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance()
                .getConnection().prepareStatement("SELECT * FROM DeliveryOrder WHERE CustomerOrderID=?");
        stm.setObject(1, orderId);

        ResultSet rst = stm.executeQuery();
        if (rst.next()) {
            return new DeliveryOrder(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getInt(5),
                    rst.getString(6)

            );

        } else {
            return null;
        }
    }

    public static List<Customer> getOrderId(String orderId) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance()
                .getConnection().prepareStatement("SELECT * FROM CustomerOrder WHERE CustomerOrderID=?");
        stm.setObject(1, orderId);
        ResultSet rst = stm.executeQuery();
        if (rst.next()) {

            List<Customer> customerData = getCustomerData(rst.getString(4));
            return customerData;
        }

        return null;
    }

    private static List<Customer> getCustomerData(String string) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance()
                .getConnection().prepareStatement("SELECT * FROM Customer WHERE CustomerId=? ");
        stm.setObject(1, string);


        ResultSet rst = stm.executeQuery();
        List<Customer> ids = new ArrayList<>();
        if (rst.next()) {
            ids.add(
                    new Customer(
                            rst.getString(1),
                            rst.getString(2),
                            rst.getString(3),
                            rst.getInt(4)
                    )
            );

        }
        return ids;
    }

    public List<String> comformOrderIds(String date) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance()
                .getConnection().prepareStatement("SELECT * FROM CustomerOrder WHERE CustomerOrderDeliveryDate=? AND Status=?");
        stm.setObject(1, date);
        stm.setObject(2, "YES");

        ResultSet rst = stm.executeQuery();
        List<String> ids = new ArrayList<>();
        while (rst.next()) {
            ids.add(
                    rst.getString(1)
            );

        }
        return ids;
    }

    public List<String> selectOrderIds(String date) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance()
                .getConnection().prepareStatement("SELECT * FROM CustomerOrder WHERE CustomerOrderDeliveryDate=? AND Status=?");
        stm.setObject(1, date);
        stm.setObject(2, "NO");

        ResultSet rst = stm.executeQuery();
        List<String> ids = new ArrayList<>();
        while (rst.next()) {
            ids.add(
                    rst.getString(1)
            );

        }
        return ids;
    }


    public List<DeliveryOrderTM> orderDetails(String newValue) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance()
                .getConnection().prepareStatement("SELECT * FROM CustomerOrderDetails WHERE CustomerOrderID=?");
        stm.setObject(1, newValue);

        ResultSet rst = stm.executeQuery();
        List<DeliveryOrderTM> ids = new ArrayList<>();
        while (rst.next()) {
            ids.add(
                    new DeliveryOrderTM(
                    rst.getString(2),
                    rst.getInt(3),
                    rst.getDouble(4)
                    ) );


        }
        return ids;
    }

    public Vehical getVehicaldata(String newValue) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance()
                .getConnection().prepareStatement("SELECT * FROM Vehical WHERE VehicalId=?");
        stm.setObject(1, newValue);

        ResultSet rst = stm.executeQuery();
        if (rst.next()) {
            return new Vehical(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3)
            );

        } else {
            return null;
        }
    }

    public Driver getDriverdata(String newValue) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance()
                .getConnection().prepareStatement("SELECT * FROM Driver WHERE DriverId=?");
        stm.setObject(1, newValue);

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

    public String getDeliveryOrderId() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance()
                .getConnection().prepareStatement(
                        "SELECT DeliveryID FROM DeliveryOrder ORDER BY DeliveryID DESC LIMIT 1"
                ).executeQuery();
        if (rst.next()){

            int tempId = Integer.
                    parseInt(rst.getString(1).split("-")[1]);
            tempId=tempId+1;
            if (tempId<=9){
                return "DO-00"+tempId;
            }else if(tempId<99){
                return "DO-0"+tempId;
            }else{
                return "DO-"+tempId;
            }

        }else{
            return "DO-001";
        }
    }

    public boolean updateDeliveruStatus(String deliveryId) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().
                getConnection().
                prepareStatement("UPDATE DeliveryOrder SET Status=('"+updateStatus+"') WHERE DeliveryID='" + deliveryId + "'");

        if (stm.executeUpdate() > 0) {
            return true;
        } else {
            return false;
        }
    }
}
