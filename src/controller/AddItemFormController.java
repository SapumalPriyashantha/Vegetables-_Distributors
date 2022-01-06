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
import model.Item;

import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.regex.Pattern;

public class AddItemFormController {


    public TextField txtItemCode;
    public TextField txtDescription;
    public TextField txtGetUnitPrice;
    public TextField txtSellUnitPrice;
    public TextField txtDiscount;
    public ComboBox<String> cmbSupplierId;
    public Button btnSaveItem;
    public TableView tblAddItem;
    public TableColumn colSupplierId;
    public TableColumn colItemCode;
    public TableColumn colDescription;
    public TableColumn colGetUnitePrice;
    public TableColumn colSellUnitePrice;
    public TableColumn colDiscount;
    int x = 0;
    String supplierId;

    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap();
    Pattern itemCodePattern = Pattern.compile("^(I-)[0-9]{3}$");
    Pattern descriptionPattern = Pattern.compile("^[A-z ]{3,20}$");
    Pattern getUnitPricePattern = Pattern.compile("^[0-9 ]{1,5}$");
    Pattern sellUnitPricePattern = Pattern.compile("^[0-9]{1,5}$");
    Pattern discountPattern = Pattern.compile("^[0-9]{1,2}$");

    public void initialize(){
        colSupplierId.setCellValueFactory(new PropertyValueFactory<>("SupplierId"));
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("ItemCode"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("Description"));
        colGetUnitePrice.setCellValueFactory(new PropertyValueFactory<>("GetUnitPrice"));
        colSellUnitePrice.setCellValueFactory(new PropertyValueFactory<>("SellUnitPrice"));
        colDiscount.setCellValueFactory(new PropertyValueFactory<>("Discount"));

        try {
            previousData();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        btnSaveItem.setDisable(true);
        storeValidations();

        try {
            loadSupplierId();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        setItemId();

        cmbSupplierId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            supplierId= newValue ;
        });
    }

    private void previousData() throws SQLException, ClassNotFoundException {
        List<Item> item = new ItemController().ItemTableData();
        for (Item i1:item
        ) {
            obList.add(new Item(i1.getItemCode(),
                    i1.getSupplierId(),
                    i1.getDescription(),
                    i1.getGetUnitPrice(),
                    i1.getSellUnitPrice(),
                    i1.getStockQty(),
                    i1.getQtyOnHand(),
                    i1.getDiscount()
            ));

        }
        tblAddItem.setItems(obList);
    }

    private void storeValidations() {
        map.put(txtItemCode, itemCodePattern);
        map.put(txtDescription, descriptionPattern);
        map.put(txtGetUnitPrice, getUnitPricePattern);
        map.put(txtSellUnitPrice, sellUnitPricePattern);
        map.put(txtDiscount, discountPattern);
    }

    private void loadSupplierId() throws SQLException, ClassNotFoundException {
        List<String> supplierIds = new SupplierController()
                .getAllSupplierIds();
        cmbSupplierId.getItems().addAll(supplierIds);

    }
    public void textFields_Key_Released(KeyEvent keyEvent) {
        Object response = ValidationUtil.validate(map,btnSaveItem);

        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (response instanceof TextField) {
                TextField errorText = (TextField) response;
                errorText.requestFocus();
            } else if (response instanceof Boolean) {
                new Alert(Alert.AlertType.INFORMATION, "Please Enter Add New Driver Button").showAndWait();
            }
        }
    }

    ObservableList<Item> obList= FXCollections.observableArrayList();
    public void SaveItemOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        Item i1 = new Item(
                txtItemCode.getText(),
                supplierId,
                txtDescription.getText(),
                Double.parseDouble(txtGetUnitPrice.getText()),
                Double.parseDouble(txtSellUnitPrice.getText()),
                x,
                x,
                Double.parseDouble(txtDiscount.getText())
        );

        obList.add(i1);
        tblAddItem.setItems(obList);

        if (new ItemController().saveItem(i1))
            new Alert(Alert.AlertType.CONFIRMATION, "Saved..").show();
        else
            new Alert(Alert.AlertType.WARNING, "Try Again..").show();
    }

    private void setItemId() {
        try {

            txtItemCode.setText(new ItemController().getItemId());

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


}
