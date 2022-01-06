package controller;

import db.DbConnection;
import model.CustomerOrder;
import model.Item;
import views.tm.MovableTM;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class movableItemController {

    public List<Item> movableItem() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance().
                getConnection().prepareStatement("SELECT * FROM Item").executeQuery();

        List<Item> ids = new ArrayList<>();
        while (rst.next()) {
            ids.add(
                    new Item(
                            rst.getString(1),
                            rst.getString(2),
                            rst.getString(3),
                            rst.getDouble(4),
                            rst.getDouble(5),
                            rst.getInt(6),
                            rst.getInt(7),
                            rst.getDouble(8)
                    ) );

        }
        return ids;
    }
}
