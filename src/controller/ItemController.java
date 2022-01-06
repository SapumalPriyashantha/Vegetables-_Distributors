package controller;

import db.DbConnection;
import model.Driver;
import model.Item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemController {
    public boolean saveItem(Item i1) throws SQLException, ClassNotFoundException {

        Connection con= DbConnection.getInstance().getConnection();
        String query="INSERT INTO Item VALUES (?,?,?,?,?,?,?,?)";
        PreparedStatement stm = con.prepareStatement(query);

        stm.setObject(1,i1.getItemCode());
        stm.setObject(2,i1.getSupplierId());
        stm.setObject(3,i1.getDescription());
        stm.setObject(4,i1.getGetUnitPrice());
        stm.setObject(5,i1.getSellUnitPrice());
        stm.setObject(6,i1.getStockQty());
        stm.setObject(7,i1.getQtyOnHand());
        stm.setObject(8,i1.getDiscount());
        return stm.executeUpdate()>0;
    }

    public Item getItem(String itemcode) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance()
                .getConnection().prepareStatement("SELECT * FROM Item WHERE ItemCode=?");
        stm.setObject(1, itemcode);

        ResultSet rst = stm.executeQuery();
        if (rst.next()) {
            return new Item(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getDouble(4),
                    rst.getDouble(5),
                    rst.getInt(6),
                    rst.getInt(7),
                    rst.getDouble(8)
            );

        } else {
            return null;
        }
    }

    public boolean updateItem(Item i1) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("UPDATE Item SET GetUnitPrice=?, SellUnitPrice=?, Discount=? WHERE ItemCode=?");
        stm.setObject(1,i1.getGetUnitPrice());
        stm.setObject(2,i1.getSellUnitPrice());
        stm.setObject(3,i1.getDiscount());
        stm.setObject(4,i1.getItemCode());

        return stm.executeUpdate()>0;
    }

    public String getItemId() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance()
                .getConnection().prepareStatement(
                        "SELECT ItemCode FROM Item ORDER BY ItemCode DESC LIMIT 1"
                ).executeQuery();
        if (rst.next()){

            int tempId = Integer.
                    parseInt(rst.getString(1).split("-")[1]);
            tempId=tempId+1;
            if (tempId<=9){
                return "I-00"+tempId;
            }else if(tempId<99){
                return "I-0"+tempId;
            }else{
                return "I-"+tempId;
            }

        }else{
            return "I-001";
        }
    }

    public List<String> getAllItemCodes() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance().
                getConnection().prepareStatement("SELECT * FROM Item").executeQuery();
        List<String> ids = new ArrayList<>();
        while (rst.next()){
            ids.add(
                    rst.getString(1)
            );
        }
        return ids;
    }

    public List<String> selectItem(String supplierId) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance()
                .getConnection().prepareStatement("SELECT * FROM Item WHERE SupplierId=?");
        stm.setObject(1, supplierId);

        ResultSet rst = stm.executeQuery();
        List<String> ids = new ArrayList<>();
        while (rst.next()) {
            ids.add(
                    rst.getString(1)
            );

        }
        return ids;
    }


    public List<Item> ItemTableData() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance().
                getConnection().prepareStatement("SELECT * FROM Item").executeQuery();
        List<Item> ids = new ArrayList<>();
        while (rst.next()){
            ids.add(
                    new Item(rst.getString(1),
                            rst.getString(2),
                            rst.getString(3),
                            rst.getDouble(4),
                            rst.getDouble(5),
                            rst.getInt(6),
                            rst.getInt(7),
                            rst.getDouble(8)
                    )
            );
        }
        return ids;
    }
}
