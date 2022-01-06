package controller;

import db.DbConnection;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.Customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {
    public boolean matchValue(String txtUsername, String txtPassWord) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance()
                .getConnection().prepareStatement("SELECT * FROM Login ");

        ResultSet rst = stm.executeQuery();
        if (rst.next()) {
           if(txtUsername.equals(rst.getString(1)) && txtPassWord.equals(rst.getString(2)) ){
               return true;
           }else {
               return false;
           }
        } else {
            return false;
        }
    }

    public boolean updatePassWord(String text) throws SQLException, ClassNotFoundException {
        String Admin="Admin";
        PreparedStatement stm = DbConnection.getInstance().
                getConnection().
                prepareStatement("UPDATE Login SET PassWord=('"+text+"') WHERE UserName='" + Admin + "'");

        if (stm.executeUpdate() > 0) {
            return true;
        } else {
            return false;
        }
    }
}
