package controller;

import db.DbConnection;
import model.DeliveryOrder;
import views.tm.DeliveryOrderTM;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class DeliveryOrderController {
    String updateStatus="YES";

    public boolean deliveryOrder(DeliveryOrder deliveryOrder) {

        Connection con=null;
        try {
            con= DbConnection.getInstance().getConnection();
            con.setAutoCommit(false);
            PreparedStatement stm =con.
                    prepareStatement("INSERT INTO DeliveryOrder VALUES(?,?,?,?,?,?)");


            stm.setObject(1, deliveryOrder.getDeliveryID());
            stm.setObject(2, deliveryOrder.getCustomerOrderID());
            stm.setObject(3, deliveryOrder.getDriverId());
            stm.setObject(4, deliveryOrder.getVehicalId());
            stm.setObject(5, deliveryOrder.getDeliveryCharge());
            stm.setObject(6, deliveryOrder.getStatus());


            if (stm.executeUpdate() > 0){
                if (DeliverOrderDetail(deliveryOrder.getCustomerOrderID(), deliveryOrder.getItems(),updateStatus)){
                    con.commit();
                    return true;
                }else{
                    con.rollback();
                    return false;
                }
            }else{
                con.rollback();
                return false;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            try {

                con.setAutoCommit(true);

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        return false;
    }

    private boolean DeliverOrderDetail(String orderId, ArrayList<DeliveryOrderTM> items, String updateStatus) throws SQLException, ClassNotFoundException {
        for (DeliveryOrderTM temp : items
        ) {
            PreparedStatement stm = DbConnection.getInstance().
                    getConnection().
                    prepareStatement("UPDATE Item SET QtyOnHand=(QtyOnHand-" + temp.getCustomerOrderQTY() + ") WHERE ItemCode='" + temp.getItemCode() + "'");

            if (stm.executeUpdate() > 0) {

            } else {
                return false;
            }

        }if (updateCustomerPlaceOrder(orderId,updateStatus )){

        }else{
            return false;
        }
        return true;
    }

    private boolean updateCustomerPlaceOrder(String orderId, String updateStatus) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection()
                .prepareStatement
                        ("UPDATE CustomerOrder SET Status=('"+updateStatus+"') WHERE CustomerOrderID='" + orderId + "'");

        if (stm.executeUpdate() > 0) {

            if (updateCustomerPlaceOrderDetails(orderId,updateStatus )){

            }else{
                return false;
            }
        } else {
            return false;
        }
        return true;
    }

    private boolean updateCustomerPlaceOrderDetails(String orderId, String updateStatus) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection()
                .prepareStatement
                        ("UPDATE  CustomerOrderDetails SET Status=('"+ updateStatus+"') WHERE CustomerOrderID='" + orderId + "'");
        if (stm.executeUpdate() > 0) {

        } else {
            return false;
        }
        return true;
    }
}
