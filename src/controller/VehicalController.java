package controller;

import db.DbConnection;
import javafx.event.ActionEvent;
import model.Driver;
import model.Supplier;
import model.Vehical;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VehicalController {

    public String getVehicalId() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance()
                .getConnection().prepareStatement(
                        "SELECT VehicalId FROM Vehical ORDER BY VehicalId DESC LIMIT 1"
                ).executeQuery();
        if (rst.next()){

            int tempId = Integer.
                    parseInt(rst.getString(1).split("-")[1]);
            tempId=tempId+1;
            if (tempId<=9){
                return "V-00"+tempId;
            }else if(tempId<99){
                return "V-0"+tempId;
            }else{
                return "V-"+tempId;
            }

        }else{
            return "V-001";
        }
    }

    public List<String> getAllVehicalType() {
        String[] Vehicaltype=new String[2];
        Vehicaltype[0]="Van";
        Vehicaltype[1]="Lorry";

        List<String> type = new ArrayList<>();
        for (int i=0;i<2;i++){
            type.add(
                    Vehicaltype[i]
            );
        }
        return type;
    }

    public boolean saveVehical(Vehical v1) throws SQLException, ClassNotFoundException {
        Connection con= DbConnection.getInstance().getConnection();
        String query="INSERT INTO Vehical VALUES (?,?,?)";
        PreparedStatement stm = con.prepareStatement(query);

        stm.setObject(1,v1.getVehicalId());
        stm.setObject(2,v1.getVehicalNumber());
        stm.setObject(3,v1.getVehicalType());

        return stm.executeUpdate()>0;
    }

    public List<String> getAllVehicalIds() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance().
                getConnection().prepareStatement("SELECT * FROM Vehical").executeQuery();
        List<String> ids = new ArrayList<>();
        while (rst.next()){
            ids.add(
                    rst.getString(1)
            );
        }
        return ids;
    }

    public Vehical getVehicalData(Object newValue) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance()
                .getConnection().prepareStatement("SELECT * FROM Vehical WHERE VehicalId=?");
        stm.setObject(1, newValue);

        ResultSet rst = stm.executeQuery();
        if (rst.next()) {
            return new Vehical(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3)
            );

        } else {
            return null;
        }
    }

    public boolean deleteVehical(String vehicalId) throws SQLException, ClassNotFoundException {
        if (DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM Vehical WHERE VehicalId='"+vehicalId+"'").executeUpdate()>0){
            return true;
        }else{
            return false;
        }
    }

    public List<Vehical> VehicalTableData() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance().
                getConnection().prepareStatement("SELECT * FROM Vehical").executeQuery();
        List<Vehical> ids = new ArrayList<>();
        while (rst.next()){
            ids.add(
                    new Vehical(rst.getString(1),
                            rst.getString(2),
                            rst.getString(3)
                    )
            );
        }
        return ids;
    }
}
