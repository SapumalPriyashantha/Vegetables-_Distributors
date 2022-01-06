package controller;

import db.DbConnection;
import model.Customer;
import model.CustomerOrder;
import model.Item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerController {

    public String getCustomerId() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance()
                .getConnection().prepareStatement(
                        "SELECT CustomerId FROM Customer ORDER BY CustomerId DESC LIMIT 1"
                ).executeQuery();
        if (rst.next()){

            int tempId = Integer.
                    parseInt(rst.getString(1).split("-")[1]);
            tempId=tempId+1;
            if (tempId<=9){
                return "C-00"+tempId;
            }else if(tempId<99){
                return "C-0"+tempId;
            }else{
                return "C-"+tempId;
            }

        }else{
            return "C-001";
        }
    }

    public boolean saveCustomer(Customer c1) throws SQLException, ClassNotFoundException {
        Connection con= DbConnection.getInstance().getConnection();
        String query="INSERT INTO Customer VALUES (?,?,?,?)";
        PreparedStatement stm = con.prepareStatement(query);

        stm.setObject(1,c1.getCustomerId());
        stm.setObject(2,c1.getCustomerName());
        stm.setObject(3,c1.getShopAddress());
        stm.setObject(4,c1.getCustomerContactNumber());

        return stm.executeUpdate()>0;
    }

    public List<String> getAllCustomerIds() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance().
                getConnection().prepareStatement("SELECT * FROM Customer").executeQuery();
        List<String> ids = new ArrayList<>();
        while (rst.next()){
            ids.add(
                    rst.getString(1)
            );
        }
        return ids;
    }

    public Customer getCustomer(Object newValue) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance()
                .getConnection().prepareStatement("SELECT * FROM Customer WHERE CustomerId=?");
        stm.setObject(1, newValue);

        ResultSet rst = stm.executeQuery();
        if (rst.next()) {
            return new Customer(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getInt(4)
            );

        } else {
            return null;
        }
    }
    public List<Customer> customerTableData() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance().
                getConnection().prepareStatement("SELECT * FROM Customer").executeQuery();
        List<Customer> ids = new ArrayList<>();
        while (rst.next()){
            ids.add(
                    new Customer(rst.getString(1),
                            rst.getString(2),
                            rst.getString(3),
                            rst.getInt(4)
                    )
            );
        }
        return ids;
    }


}
