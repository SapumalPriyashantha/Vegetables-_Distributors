package controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import model.Supplier;

import java.sql.SQLException;
import java.util.List;

public class RemoveSupplierFormController {

    public ComboBox<String> cmbSupplierId;
    public TextField txtName;
    public TextField txtAddress;
    public TextField txtNumber;
    String SupplierId;

    public void initialize(){
        try {
            loadSupplierId();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        cmbSupplierId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                setSupplierData( newValue);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

    }

    private void loadSupplierId() throws SQLException, ClassNotFoundException {
        List<String> supplierIds = new SupplierController()
                .getAllSupplierIds();
        cmbSupplierId.getItems().addAll(supplierIds);

    }

    private void setSupplierData(String supplierId) throws SQLException, ClassNotFoundException {
        Supplier si = new SupplierController().getSupplierData(supplierId);
        if (si == null) {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set");
        } else {
            SupplierId=si.getSupplierId();
            txtName.setText(si.getSupplierName());
            txtAddress.setText(si.getSupplierAddress());
            txtNumber.setText(String.valueOf(si.getSupplierContactNumber()));


        }
    }

    public void RemoveSupplierOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (new SupplierController().deleteSupplier(SupplierId)){
            new Alert(Alert.AlertType.CONFIRMATION, "Deleted").show();
        }else{
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }

    }

}
