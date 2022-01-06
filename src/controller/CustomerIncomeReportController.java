package controller;

import db.DbConnection;
import model.CustomerOrder;
import model.SupplierOrder;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerIncomeReportController {
    public List<CustomerOrder> incomeDetails(String from, String to) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance()
                .getConnection().prepareStatement("SELECT * FROM CustomerOrder WHERE CustomerOrderDate BETWEEN ? AND ? ");
        stm.setObject(1, from);
        stm.setObject(2, to);

        ResultSet rst = stm.executeQuery();
        List<CustomerOrder> ids = new ArrayList<>();
        while (rst.next()) {
            ids.add(
                    new CustomerOrder(
                            rst.getString(1),
                            rst.getString(2),
                            rst.getString(4),
                            rst.getDouble(5)
                    ) );


        }
        return ids;
    }
}
