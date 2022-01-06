package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.DeliveryOrder;
import model.Item;
import views.tm.DeliveryOrderTM;

import java.sql.SQLException;
import java.util.List;

public class DeliveryComformationOrderFormController {

    public Label lblDEliveryOrder;
    public ComboBox cmbOrderId;
    public Label lblDeliveryId;
    public DatePicker dtpckSelectOrderDate;
    public TableView tblDeliveryOrder;
    public TableColumn colItemCode;
    public TableColumn colDescription;
    public TableColumn colQTY;
    public TableColumn colCost;
    public Label lblVehicalType;
    public Label lblDriverName;
    public Label lblDeliveryCharge;
    String deliveryId;


    public void initialize(){
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("ItemCode"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("Description"));
        colQTY.setCellValueFactory(new PropertyValueFactory<>("CustomerOrderQTY"));
        colCost.setCellValueFactory(new PropertyValueFactory<>("cost"));

        dtpckSelectOrderDate.valueProperty().addListener((observable, oldValue, newValue) -> {
            try {
                setOrderIds( String.valueOf(newValue));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        cmbOrderId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                setOrderData((String) newValue);
                otherDetails((String) newValue);

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

    }
    private void setOrderIds(String date) throws SQLException, ClassNotFoundException {
        List<String> orderids = new DeliveryController().comformOrderIds(date);
        cmbOrderId.getItems().clear();
        cmbOrderId.getItems().addAll(orderids);

    }

    ObservableList<DeliveryOrderTM> obList= FXCollections.observableArrayList();
    private void setOrderData(String newValue) throws SQLException, ClassNotFoundException {
        List<DeliveryOrderTM> d1 = new DeliveryController().orderDetails(newValue);
        if (d1 == null) {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set");
        } else {
            for (DeliveryOrderTM tempTm:d1
            ) {
                Item item = new ItemController().getItem(tempTm.getItemCode());

                DeliveryOrderTM tm = new DeliveryOrderTM(
                        tempTm.getItemCode(),
                        item.getDescription(),
                        tempTm.getCustomerOrderQTY(),
                        tempTm.getCost()
                );

                obList.clear();
                obList.add(tm);
                tblDeliveryOrder.setItems(obList);
            }

        }
    }

    private void otherDetails(String orderId) throws SQLException, ClassNotFoundException {

        DeliveryOrder deliveryOrder = DeliveryController.AllDeliveruDetails(orderId);
        if(deliveryOrder == null){
            new Alert(Alert.AlertType.WARNING, "Empty Result Set").show();
        }else {
            deliveryId=deliveryOrder.getDeliveryID();
            lblDeliveryId.setText(deliveryOrder.getDeliveryID());
            lblVehicalType.setText(deliveryOrder.getVehicalId());
            lblDriverName.setText(deliveryOrder.getDriverId());
            lblDeliveryCharge.setText(String.valueOf(deliveryOrder.getDeliveryCharge()));

        }
    }

    public void comformationOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        boolean updateDeliveryStatus = new DeliveryController().updateDeliveruStatus(deliveryId);
        if(updateDeliveryStatus ==false){
            new Alert(Alert.AlertType.WARNING, "Error....").show();
        }else {
            new Alert(Alert.AlertType.CONFIRMATION, "Updated.....").show();
        }
    }
}
