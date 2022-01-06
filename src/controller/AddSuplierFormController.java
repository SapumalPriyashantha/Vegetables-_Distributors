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
import model.Item;
import model.Supplier;

import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.regex.Pattern;

public class AddSuplierFormController {

    public TextField txtId;
    public TextField txtName;
    public TextField txtAddress;
    public TextField txtNumber;
    public AnchorPane pic1;
    public Button btnAddSupplier;
    public TableView tblAddSupplier;
    public TableColumn colSupplierId;
    public TableColumn colSupplierName;
    public TableColumn colSupplierAddress;
    public TableColumn colSupplierCon_Num;

    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap();
    Pattern idPattern = Pattern.compile("^(S-)[0-9]{3}$");
    Pattern namePattern = Pattern.compile("^[A-z ]{3,20}$");
    Pattern addressPattern = Pattern.compile("^[A-z0-9/ ]{6,10}$");
    Pattern numberPattern = Pattern.compile("^[0-9]{10}$");



    public void initialize(){
        colSupplierId.setCellValueFactory(new PropertyValueFactory<>("SupplierId"));
        colSupplierName.setCellValueFactory(new PropertyValueFactory<>("SupplierName"));
        colSupplierAddress.setCellValueFactory(new PropertyValueFactory<>("SupplierAddress"));
        colSupplierCon_Num.setCellValueFactory(new PropertyValueFactory<>("SupplierContactNumber"));

        try {
            previousData();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        btnAddSupplier.setDisable(true);
        storeValidations();
        setSupplierId();
    }

    private void previousData() throws SQLException, ClassNotFoundException {
        List<Supplier> supplier = new SupplierController().SupplierTableData();
        for (Supplier s1:supplier
        ) {
            obList.add(new Supplier(s1.getSupplierId(),
                    s1.getSupplierName(),
                    s1.getSupplierAddress(),
                    s1.getSupplierContactNumber()
            ));

        }
        tblAddSupplier.setItems(obList);
    }

    private void setSupplierId() {
        try {

            txtId.setText(new SupplierController().getSupplierId());

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    private void storeValidations() {
        map.put(txtId, idPattern);
        map.put(txtName, namePattern);
        map.put(txtAddress, addressPattern);
        map.put(txtNumber, numberPattern);
    }

    public void textFields_Key_Released(KeyEvent keyEvent) {
        Object response = ValidationUtil.validate(map,btnAddSupplier);

        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (response instanceof TextField) {
                TextField errorText = (TextField) response;
                errorText.requestFocus();
            } else if (response instanceof Boolean) {
                new Alert(Alert.AlertType.INFORMATION, "Please Enter Add New Supplier Button").showAndWait();
            }
        }
    }

    ObservableList<Supplier> obList= FXCollections.observableArrayList();
    public void AddSupplierOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Supplier s1 = new Supplier(
                txtId.getText(),
                txtName.getText(),
                txtAddress.getText(),
                Integer.parseInt(txtNumber.getText())


        );

        obList.add(s1);
        tblAddSupplier.setItems(obList);

        if (new SupplierController().saveSupplier(s1))
            new Alert(Alert.AlertType.CONFIRMATION, "Saved..").show();
        else
            new Alert(Alert.AlertType.WARNING, "Try Again..").show();
    }
}
