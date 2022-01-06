package controller;

import db.DbConnection;
import model.CustomerOrder;
import model.SupplierOrder;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierWiseCostController {

    public List<SupplierOrder> CostDetails(Object newValue) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance()
                .getConnection().prepareStatement("SELECT * FROM SupplierOrder WHERE SupplierId=? ");
        stm.setObject(1, newValue);

        ResultSet rst = stm.executeQuery();
        List<SupplierOrder> ids = new ArrayList<>();
        while (rst.next()) {
            ids.add(
                    new SupplierOrder(
                            rst.getString(1),
                            rst.getString(2),
                            rst.getString(3),
                            rst.getDouble(4)
                    ) );


        }
        return ids;
    }

}
