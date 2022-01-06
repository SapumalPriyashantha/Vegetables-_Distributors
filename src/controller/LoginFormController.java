package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;

public class LoginFormController {
    public TextField txtUsername;
    public PasswordField txtPassWord;
    public AnchorPane pic1;
    public AnchorPane pic2;

    public void LoginOnAction(ActionEvent actionEvent) throws IOException, SQLException, ClassNotFoundException {
        boolean match = new LoginController().matchValue(txtUsername.getText(), txtPassWord.getText());

        if (match ==false) {
            new Alert(Alert.AlertType.WARNING, " username or password is incorrect " +
                    "please enter correct username or password").show();
        } else {
            URL resource = getClass().getResource("../views/AdminDashBoardForm.fxml");
            Parent load = FXMLLoader.load(resource);
            Stage window = (Stage) pic1.getScene().getWindow();
            window.setScene(new Scene(load));
        }
    }

    public void ForgottenPasswordOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../views/ResetPasswordForm.fxml");
        Parent load = FXMLLoader.load(resource);
        pic2.getChildren().clear();
        pic2.getChildren().add(load);
    }
}
