package controller;

import db.DbConnection;
import model.CustomerOrder;
import model.CustomerOrderDetails;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerOrderController {

    public String getCustomerOrderId() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance()
                .getConnection().prepareStatement(
                        "SELECT CustomerOrderID FROM CustomerOrder ORDER BY CustomerOrderID DESC LIMIT 1"
                ).executeQuery();
        if (rst.next()){

            int tempId = Integer.
                    parseInt(rst.getString(1).split("-")[1]);
            tempId=tempId+1;
            if (tempId<=9){
                return "CO-00"+tempId;
            }else if(tempId<99){
                return "CO-0"+tempId;
            }else{
                return "CO-"+tempId;
            }

        }else{
            return "CO-001";
        }
    }

    public boolean placeOrder(CustomerOrder customerOrder) {
        Connection con=null;
        try {
            con= DbConnection.getInstance().getConnection();
            con.setAutoCommit(false);
            PreparedStatement stm =con.
                    prepareStatement("INSERT INTO CustomerOrder VALUES(?,?,?,?,?,?,?)");


            stm.setObject(1, customerOrder.getCustomerOrderID());
            stm.setObject(2, customerOrder.getCustomerOrderDate());
            stm.setObject(3, customerOrder.getCustomerOrderDeliveryDate());
            stm.setObject(4, customerOrder.getCustomerId());
            stm.setObject(5, customerOrder.getTotalCost());
            stm.setObject(6, customerOrder.getTotalDiscount());
            stm.setObject(7, customerOrder.getStatus());

            if (stm.executeUpdate() > 0){

                if (saveOrderDetail(customerOrder.getCustomerOrderID(), customerOrder.getItems())){
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

    private boolean saveOrderDetail(String orderId, ArrayList<CustomerOrderDetails> items) throws SQLException, ClassNotFoundException {
        for (CustomerOrderDetails temp : items
        ) {
            PreparedStatement stm = DbConnection.getInstance().
                    getConnection().
                    prepareStatement("INSERT INTO CustomerOrderDetails VALUES(?,?,?,?,?)");
            stm.setObject(1, orderId);
            stm.setObject(2, temp.getItemCode());
            stm.setObject(3, temp.getCustomerOrderQTY());
            stm.setObject(4, temp.getCost());
            stm.setObject(5, temp.getStatus());
            if (stm.executeUpdate() > 0) {

            } else {
                return false;
            }
        }
        return true;
    }

}
