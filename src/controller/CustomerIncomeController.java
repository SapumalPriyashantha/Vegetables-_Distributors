package controller;

import db.DbConnection;
import model.CustomerOrder;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerIncomeController {

    public List<CustomerOrder> IncomeDetails(Object newValue) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance()
                .getConnection().prepareStatement("SELECT * FROM CustomerOrder WHERE CustomerId=? AND Status=?");
        stm.setObject(1, newValue);
        stm.setObject(2, "YES");


        ResultSet rst = stm.executeQuery();
        List<CustomerOrder> ids = new ArrayList<>();
        while (rst.next()) {
            ids.add(
                    new CustomerOrder(
                            rst.getString(1),
                            rst.getString(2),
                            rst.getString(3),
                            rst.getString(4),
                            rst.getDouble(5),
                            rst.getDouble(6),
                            rst.getString(7)
                    ) );


        }
        return ids;
    }
}
