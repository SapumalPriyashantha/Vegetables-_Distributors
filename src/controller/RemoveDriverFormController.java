package controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import model.Driver;

import java.sql.SQLException;
import java.util.List;

public class RemoveDriverFormController {

    public TextField txtName;
    public TextField txtLicenseNumber;
    public TextField txtContactNumber;
    public ComboBox<String> cmbDriverId;
    String DriverId;

    public void initialize(){
        try {
            loadDriverId();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        cmbDriverId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                setDriverData( newValue);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

    }

    private void loadDriverId() throws SQLException, ClassNotFoundException {
        List<String> driverIds = new DriverController()
                .getAllDriverIds();
        cmbDriverId.getItems().addAll(driverIds);

    }

    private void setDriverData(String supplierId) throws SQLException, ClassNotFoundException {
        Driver d1 = new DriverController().getDriverData(supplierId);
        if (d1 == null) {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set");
        } else {
            DriverId=d1.getDriverId();
            txtName.setText(d1.getDriverName());
            txtLicenseNumber.setText(String.valueOf(d1.getDriverLicenceNumber()));
            txtContactNumber.setText(String.valueOf(d1.getDriverContactNumber()));

        }
    }


    public void RemoveDriverOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (new DriverController().deleteDriverr(DriverId)){
            new Alert(Alert.AlertType.CONFIRMATION, "Deleted").show();
        }else{
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }

    }
}
