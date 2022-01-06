package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;

public class AdminDashBoardFormController {

    public AnchorPane pic1;
    public AnchorPane pic2;
    public AnchorPane pic3;

    public void CustomerWiseIncomeOnAction(ActionEvent actionEvent) throws IOException { //-----------------
        URL resource = getClass().getResource("../views/CustomerWiseIncomeForm.fxml");
        Parent load = FXMLLoader.load(resource);
        pic3.getChildren().clear();
        pic3.getChildren().add(load);
    }

    public void MovableItemOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../views/MovableItemForm.fxml");
        Parent load = FXMLLoader.load(resource);
        pic3.getChildren().clear();
        pic3.getChildren().add(load);
    }

    public void IncomeReportOnAction(ActionEvent actionEvent) throws IOException { //-------------------
        URL resource = getClass().getResource("../views/IncomeReportForm.fxml");
        Parent load = FXMLLoader.load(resource);
        pic3.getChildren().clear();
        pic3.getChildren().add(load);
    }

    public void AddItemOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../views/AddItemForm.fxml");
        Parent load = FXMLLoader.load(resource);
        pic3.getChildren().clear();
        pic3.getChildren().add(load);
    }



    public void AddSupplierOnaction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../views/AddSuplierForm.fxml");
        Parent load = FXMLLoader.load(resource);
        pic3.getChildren().clear();
        pic3.getChildren().add(load);
    }

    public void RemoveSupplierOnaction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../views/RemoveSupplierForm.fxml");
        Parent load = FXMLLoader.load(resource);
        pic3.getChildren().clear();
        pic3.getChildren().add(load);
    }


    public void ModifyItemOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../views/ModifyItemForm.fxml");
        Parent load = FXMLLoader.load(resource);
        pic3.getChildren().clear();
        pic3.getChildren().add(load);
    }

    public void SupplierWiseCostOnAction(ActionEvent actionEvent) throws IOException { //----------------
        URL resource = getClass().getResource("../views/SupplierWiseCostForm.fxml");
        Parent load = FXMLLoader.load(resource);
        pic3.getChildren().clear();
        pic3.getChildren().add(load);
    }

    public void BackOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../views/CashierDashBoardForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) pic1.getScene().getWindow();
        window.setScene(new Scene(load));
    }
}
