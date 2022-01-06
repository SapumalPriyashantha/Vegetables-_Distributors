package controller;

import db.DbConnection;
import model.Item;
import model.Supplier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierController {
    public String getSupplierId() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance()
                .getConnection().prepareStatement(
                        "SELECT SupplierId FROM Supplier ORDER BY SupplierId DESC LIMIT 1"
                ).executeQuery();
        if (rst.next()){

            int tempId = Integer.
                    parseInt(rst.getString(1).split("-")[1]);
            tempId=tempId+1;
            if (tempId<=9){
                return "S-00"+tempId;
            }else if(tempId<99){
                return "S-0"+tempId;
            }else{
                return "S-"+tempId;
            }

        }else{
            return "S-001";
        }
    }

    public boolean saveSupplier(Supplier s1) throws SQLException, ClassNotFoundException {

        Connection con= DbConnection.getInstance().getConnection();
        String query="INSERT INTO Supplier VALUES (?,?,?,?)";
        PreparedStatement stm = con.prepareStatement(query);

        stm.setObject(1,s1.getSupplierId());
        stm.setObject(2,s1.getSupplierName());
        stm.setObject(3,s1.getSupplierAddress());
        stm.setObject(4,s1.getSupplierContactNumber());

        return stm.executeUpdate()>0;
    }

    public List<String> getAllSupplierIds() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance().
                getConnection().prepareStatement("SELECT * FROM Supplier").executeQuery();
        List<String> ids = new ArrayList<>();
        while (rst.next()){
            ids.add(
                    rst.getString(1)
            );
        }
        return ids;
    }

    public Supplier getSupplierData(String supplierId) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance()
                .getConnection().prepareStatement("SELECT * FROM Supplier WHERE SupplierId=?");
        stm.setObject(1, supplierId);

        ResultSet rst = stm.executeQuery();
        if (rst.next()) {
            return new Supplier(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getInt(4)
            );

        } else {
            return null;
        }
    }

    public boolean deleteSupplier(String SupplierId) throws SQLException, ClassNotFoundException {
        if (DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM Supplier WHERE SupplierId='"+SupplierId+"'").executeUpdate()>0){
            return true;
        }else{
            return false;
        }
    }


    public List<Supplier> SupplierTableData() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance().
                getConnection().prepareStatement("SELECT * FROM Supplier").executeQuery();
        List<Supplier> ids = new ArrayList<>();
        while (rst.next()){
            ids.add(
                    new Supplier(rst.getString(1),
                            rst.getString(2),
                            rst.getString(3),
                            rst.getInt(4)
                    )
            );
        }
        return ids;
    }
}
