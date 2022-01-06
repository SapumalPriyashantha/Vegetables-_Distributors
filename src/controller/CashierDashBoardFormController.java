package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;

public class CashierDashBoardFormController {

    public AnchorPane pic1;
    public AnchorPane pic2;
    public AnchorPane pic3;

    public void AddNewDriverOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../views/AddDriverForm.fxml");
        Parent load = FXMLLoader.load(resource);
        pic3.getChildren().clear();
        pic3.getChildren().add(load);
    }

    public void AddNewVehicalOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../views/AddVehicalForm.fxml");
        Parent load = FXMLLoader.load(resource);
        pic3.getChildren().clear();
        pic3.getChildren().add(load);

    }

    public void RemoveVehicalOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../views/RemoveVehicalForm.fxml");
        Parent load = FXMLLoader.load(resource);
        pic3.getChildren().clear();
        pic3.getChildren().add(load);
    }

    public void AddNewCustomerOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../views/AddCustomerForm.fxml");
        Parent load = FXMLLoader.load(resource);
        pic3.getChildren().clear();
        pic3.getChildren().add(load);
    }

    public void SupplierOrderOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../views/GetSupplierOrderForm.fxml");
        Parent load = FXMLLoader.load(resource);
        pic3.getChildren().clear();
        pic3.getChildren().add(load);
    }

    public void CustomerOrderOnAction(ActionEvent actionEvent) throws IOException { //---------------
        URL resource = getClass().getResource("../views/PlaceCustomerOrderForm.fxml");
        Parent load = FXMLLoader.load(resource);
        pic3.getChildren().clear();
        pic3.getChildren().add(load);
    }

    public void DeliverOrderOnAction(ActionEvent actionEvent) throws IOException { //------------------
        URL resource = getClass().getResource("../views/DeliveryOrderForm.fxml");
        Parent load = FXMLLoader.load(resource);
        pic3.getChildren().clear();
        pic3.getChildren().add(load);
    }

    public void RemoveDriverOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../views/RemoveDriverForm.fxml");
        Parent load = FXMLLoader.load(resource);
        pic3.getChildren().clear();
        pic3.getChildren().add(load);
    }


    public void AdminLoginONAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../views/LoginForm.fxml");
        Parent load = FXMLLoader.load(resource);
        pic2.getChildren().clear();
        pic2.getChildren().add(load);
    }

    public void completeDeliveryOrder(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../views/DeliveryComformationOrderForm.fxml");
        Parent load = FXMLLoader.load(resource);
        pic3.getChildren().clear();
        pic3.getChildren().add(load);
    }
}
