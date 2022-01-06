package controller;

import Util.ValidationUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.Driver;
import model.Supplier;
import model.Vehical;

import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.regex.Pattern;

public class AddVehicalFormController {

    public TextField txtId;
    public TextField txtNumber;
    public ComboBox cmbVehicalType;
    public Button btnAddVehical;
    public TableView tblAddVehiacl;
    public TableColumn colVehicalId;
    public TableColumn colVehicalType;
    public TableColumn colVehicalNumber;
    String vehicalType;

    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap();
    Pattern idPattern = Pattern.compile("^(V-)[0-9]{3}$");
    Pattern numberPattern = Pattern.compile("^[A-Z]{2}[-][0-9]{4}$");

    public void initialize(){
        colVehicalId.setCellValueFactory(new PropertyValueFactory<>("VehicalId"));
        colVehicalNumber.setCellValueFactory(new PropertyValueFactory<>("VehicalNumber"));
        colVehicalType.setCellValueFactory(new PropertyValueFactory<>("VehicalType"));

        try {
            previousData();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        btnAddVehical.setDisable(true);
        storeValidations();
        setVehicalId();

        try {
            loadVehicalType();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        cmbVehicalType.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            vehicalType= (String) newValue;
        });

    }
    private void previousData() throws SQLException, ClassNotFoundException {
        List<Vehical> vehical = new VehicalController().VehicalTableData();
        for (Vehical v1:vehical
        ) {
            obList.add(new Vehical(v1.getVehicalId(),
                    v1.getVehicalNumber(),
                    v1.getVehicalType()
            ));

        }
        tblAddVehiacl.setItems(obList);
    }

    private void storeValidations() {
        map.put(txtId, idPattern);
        map.put(txtNumber, numberPattern);

    }

    private void loadVehicalType() throws SQLException, ClassNotFoundException {
        List<String> vehicalType = new VehicalController()
                .getAllVehicalType();
        cmbVehicalType.getItems().addAll(vehicalType);

    }

    private void setVehicalId() {
        try {

            txtId.setText(new VehicalController().getVehicalId());

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void textFields_Key_Released(KeyEvent keyEvent) {
        Object response = ValidationUtil.validate(map,btnAddVehical);

        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (response instanceof TextField) {
                TextField errorText = (TextField) response;
                errorText.requestFocus();
            } else if (response instanceof Boolean) {
                new Alert(Alert.AlertType.INFORMATION, "Please Enter Add New Vehical Button").showAndWait();
            }
        }
    }

    ObservableList<Vehical> obList= FXCollections.observableArrayList();
    public void AddVehicalOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Vehical v1 = new Vehical(
                txtId.getText(),
                txtNumber.getText(),
                vehicalType

        );
        obList.add(v1);
        tblAddVehiacl.setItems(obList);

        if (new VehicalController().saveVehical(v1))
            new Alert(Alert.AlertType.CONFIRMATION, "Saved..").show();
        else
            new Alert(Alert.AlertType.WARNING, "Try Again..").show();
    }
}
