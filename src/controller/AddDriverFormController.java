package controller;

import Util.ValidationUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import model.Customer;
import model.Driver;
import model.Supplier;

import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.regex.Pattern;

public class AddDriverFormController {

    public TextField txtId;
    public TextField txtName;
    public TextField txtLicenseNumber;
    public TextField txtContactNumber;
    public AnchorPane pic1;
    public Button btnAddSupplier;
    public TableView tblAddNewDriver;
    public TableColumn colDriverId;
    public TableColumn colDriverName;
    public TableColumn colDri_Lic_Num;
    public TableColumn colDriver_Con_Num;

    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap();
    Pattern idPattern = Pattern.compile("^(D-)[0-9]{3}$");
    Pattern namePattern = Pattern.compile("^[A-z ]{3,20}$");
    Pattern licensePattern = Pattern.compile("^[0-9 ]{6,10}$");
    Pattern contactPattern = Pattern.compile("^[0-9]{10}$");

    public void initialize(){
        colDriverId.setCellValueFactory(new PropertyValueFactory<>("DriverId"));
        colDriverName.setCellValueFactory(new PropertyValueFactory<>("DriverName"));
        colDri_Lic_Num.setCellValueFactory(new PropertyValueFactory<>("DriverLicenceNumber"));
        colDriver_Con_Num.setCellValueFactory(new PropertyValueFactory<>("DriverContactNumber"));

        try {
            previousData();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        btnAddSupplier.setDisable(true);
        storeValidations();
        setDriverId();
    }

    private void setDriverId() {
        try {

            txtId.setText(new DriverController().getDriverId());

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    private void storeValidations() {
        map.put(txtId, idPattern);
        map.put(txtName, namePattern);
        map.put(txtLicenseNumber, licensePattern);
        map.put(txtContactNumber, contactPattern);
    }

    private void previousData() throws SQLException, ClassNotFoundException {
        List<Driver> driver = new DriverController().DriverTableData();
        for (Driver d1:driver
        ) {
            obList.add(new Driver(d1.getDriverId(),
                    d1.getDriverName(),
                    d1.getDriverLicenceNumber(),
                    d1.getDriverContactNumber()
            ));

        }
        tblAddNewDriver.setItems(obList);
    }

    ObservableList<Driver> obList= FXCollections.observableArrayList();
    public void AddDriverOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Driver d1 = new Driver(
                txtId.getText(),
                txtName.getText(),
                Integer.parseInt(txtLicenseNumber.getText()),
                Integer.parseInt(txtContactNumber.getText())


        );
        obList.add(d1);
        tblAddNewDriver.setItems(obList);

        if (new DriverController().saveDriver(d1))
            new Alert(Alert.AlertType.CONFIRMATION, "Saved..").show();
        else
            new Alert(Alert.AlertType.WARNING, "Try Again..").show();
        
    }
    public void textFields_Key_Released(KeyEvent keyEvent) {
        Object response = ValidationUtil.validate(map,btnAddSupplier);

        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (response instanceof TextField) {
                TextField errorText = (TextField) response;
                errorText.requestFocus();
            } else if (response instanceof Boolean) {
                new Alert(Alert.AlertType.INFORMATION, "Please Enter Add New Driver Button").showAndWait();
            }
        }
    }
}
