package controller;

import db.DbConnection;
import model.SupplierOrder;
import model.SupplierOrderDetails;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierOrderController {
    public String getSupplierOrderId() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance()
                .getConnection().prepareStatement(
                        "SELECT SupplierOrderID FROM SupplierOrder ORDER BY SupplierOrderID DESC LIMIT 1"
                ).executeQuery();
        if (rst.next()){

            int tempId = Integer.
                    parseInt(rst.getString(1).split("-")[1]);
            tempId=tempId+1;
            if (tempId<=9){
                return "SO-00"+tempId;
            }else if(tempId<99){
                return "SO-0"+tempId;
            }else{
                return "SO-"+tempId;
            }

        }else{
            return "SO-001";
        }
    }

    public boolean placeOrder(SupplierOrder order) {
        Connection con=null;
        try {
            con= DbConnection.getInstance().getConnection();
            con.setAutoCommit(false);
            PreparedStatement stm =con.
                    prepareStatement("INSERT INTO SupplierOrder VALUES(?,?,?,?)");


            stm.setObject(1, order.getSupplierOrderID());
            stm.setObject(2, order.getSupplierOrderDate());
            stm.setObject(3, order.getSupplierId());
            stm.setObject(4, order.getTotalCost());


            if (stm.executeUpdate() > 0){
                if (saveOrderDetail(order.getSupplierOrderID(), order.getItems())){
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

    private boolean saveOrderDetail(String orderId, ArrayList<SupplierOrderDetails> items) throws SQLException, ClassNotFoundException {
        for (SupplierOrderDetails temp : items
        ) {
            PreparedStatement stm = DbConnection.getInstance().
                    getConnection().
                    prepareStatement("INSERT INTO SupplierOrderDetails VALUES(?,?,?,?)");
            stm.setObject(1, orderId);
            stm.setObject(2, temp.getItemCode());
            stm.setObject(3, temp.getOrderQTY());
            stm.setObject(4, temp.getCost());
            if (stm.executeUpdate() > 0) {

                if (updateQty(temp.getItemCode(), temp.getOrderQTY())){

                }else{
                    return false;
                }
            } else {
                return false;
            }
        }
        return true;
    }

    private boolean updateQty(String itemCode, int qty) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection()
                .prepareStatement
                        ("UPDATE Item SET StockQty=(StockQty+" + qty+"), QtyOnHand=(QtyOnHand+" +qty
                                + ") WHERE ItemCode='" + itemCode + "'");
        return stm.executeUpdate()>0;
    }
}
