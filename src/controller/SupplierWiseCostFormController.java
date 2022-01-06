package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.*;

import java.sql.SQLException;
import java.util.List;

public class SupplierWiseCostFormController {

    public ComboBox cmbSelectSupplierId;
    public Label lblSupplierName;
    public Label lblSupplierAddress;
    public Label lblSupplierNumber;
    public ComboBox cmbSupplierItem;
    public TableView tblSupplierwiseCost;
    public TableColumn colOrderDate;
    public TableColumn colOrderId;
    public TableColumn colCost;
    public Label lblTotalCost;
    public Label lblDescription;
    public Label lblGetUnitPrice;

    public void initialize(){
        colOrderDate.setCellValueFactory(new PropertyValueFactory<>("SupplierOrderDate"));
        colOrderId.setCellValueFactory(new PropertyValueFactory<>("SupplierOrderID"));
        colCost.setCellValueFactory(new PropertyValueFactory<>("TotalCost"));

        try {
            loadSupplierIds();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        cmbSelectSupplierId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                setSupplierData( newValue);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        cmbSupplierItem.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                setItemData( newValue);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
    }

    private void setItemData(Object newValue) throws SQLException, ClassNotFoundException {
        Item i1 = new ItemController().getItem((String) newValue);
        if (i1 == null) {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set");
        } else {
            lblDescription.setText(i1.getDescription());
            lblGetUnitPrice.setText(String.valueOf(i1.getGetUnitPrice()));

        }
    }

    private void setSupplierData(Object newValue) throws SQLException, ClassNotFoundException {
        Supplier s1 = new SupplierController().getSupplierData((String) newValue);
        if (s1 == null) {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set");
        } else {
            lblSupplierName.setText(s1.getSupplierName());
            lblSupplierAddress.setText(s1.getSupplierAddress());
            lblSupplierNumber.setText(String.valueOf(s1.getSupplierContactNumber()));

            loadItems(newValue);
            obList.clear();
            supplierwiseCost(newValue);
        }
    }

    private void loadItems(Object newValue) throws SQLException, ClassNotFoundException {
        List<String> itemIds = new ItemController()
                .selectItem((String) newValue);
        cmbSupplierItem.getItems().clear();
        cmbSupplierItem.getItems().addAll(itemIds);
    }

    private void loadSupplierIds() throws SQLException, ClassNotFoundException {
        List<String> supplierIds = new SupplierController()
                .getAllSupplierIds();
        cmbSelectSupplierId.getItems().addAll(supplierIds);
    }

    ObservableList<SupplierOrder> obList= FXCollections.observableArrayList();
    private void supplierwiseCost(Object newValue) throws SQLException, ClassNotFoundException {
        List<SupplierOrder> s1 = new SupplierWiseCostController().CostDetails(newValue);
        if (s1 == null) {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set");
        } else {
            for (SupplierOrder tempTm:s1
            ) {
                SupplierOrder tm = new SupplierOrder(
                        tempTm.getSupplierOrderID(),
                        tempTm.getSupplierOrderDate(),
                        tempTm.getTotalCost()
                );

                obList.add(tm);
                tblSupplierwiseCost.setItems(obList);
            }
            calculateCost();
        }
    }

    void calculateCost(){
        double total=0;
        for (SupplierOrder s1:obList
        ) {
            total+=s1.getTotalCost();
        }
        lblTotalCost.setText(total+" /=");

    }
}
