package controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import model.Driver;
import model.Vehical;

import java.sql.SQLException;
import java.util.List;

public class RemoveVehicalFormController {

    public TextField txtVehicalNumber;
    public TextField txtVehicalType;
    public Button btnRemoveVehical;
    public ComboBox<String> cmbVehicalId;
    String vehicalId;

    public void initialize(){
        try {
            loadVehicalId();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        cmbVehicalId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                setVehicalData( newValue);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
    }

    private void setVehicalData(Object newValue) throws SQLException, ClassNotFoundException {
        Vehical d1 = new VehicalController().getVehicalData(newValue);
        if (d1 == null) {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set");
        } else {
            vehicalId=d1.getVehicalId();
            txtVehicalNumber.setText(d1.getVehicalNumber());
            txtVehicalType.setText(d1.getVehicalType());

        }
    }

    private void loadVehicalId() throws SQLException, ClassNotFoundException {
        List<String> vehicalIds = new VehicalController()
                .getAllVehicalIds();
        cmbVehicalId.getItems().addAll(vehicalIds);
    }

    public void RemoveVehicalOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (new VehicalController().deleteVehical(vehicalId)){
            new Alert(Alert.AlertType.CONFIRMATION, "Deleted").show();
        }else{
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }
    }
}
