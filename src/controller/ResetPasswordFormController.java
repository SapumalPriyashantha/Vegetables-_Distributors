package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;

public class ResetPasswordFormController {

    public AnchorPane pic1;
    public TextField txtNewPassword;
    public TextField txtComformNewPassword;

    public void ContinueOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if(txtNewPassword.getText().equals(txtComformNewPassword.getText())){
            boolean update = new LoginController().updatePassWord(txtNewPassword.getText());
            if(update ==false){
                new Alert(Alert.AlertType.WARNING, "cannot update this value").show();
            }else {
                new Alert(Alert.AlertType.CONFIRMATION, "updated.....").show();
            }
        }else {
            new Alert(Alert.AlertType.WARNING, "New password & Comformation password is not match").show();
        }

    }

    public void BackOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../views/LoginForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) pic1.getScene().getWindow();
        window.setScene(new Scene(load));
    }
}
