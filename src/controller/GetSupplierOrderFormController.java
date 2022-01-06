package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Item;
import model.SupplierOrder;
import model.SupplierOrderDetails;
import views.tm.CartTM;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GetSupplierOrderFormController {
    public ComboBox<String> cmbSupplierId;
    public ComboBox<String> cmbItemCode;
    public TextField txtDescription;
    public TextField txtUnitPrice;
    public TextField txtQTY;
    public Label txtDate;
    public Label txtSupplierOrderId;
    public TableView tblGetSupplierOrder;
    public TableColumn colItemCode;
    public TableColumn colDescription;
    public TableColumn colUnitPrice;
    public TableColumn colQTY;
    public TableColumn colTotal;
    public Label txtSupplierCost;
    public Button btnPlaceSupplierOrder;

    int cartSelectedRowForRemove = -1;
    public void initialize(){
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("ItemCode"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("Description"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("UnitPrice"));
        colQTY.setCellValueFactory(new PropertyValueFactory<>("Qty"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("Total"));


        loadDate();
        setSupplierOrderID();

        try {
            loadSupplierIds();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        cmbSupplierId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                setSupplierItem( newValue);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        cmbItemCode.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                setItemData( newValue);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        tblGetSupplierOrder.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            cartSelectedRowForRemove = (int) newValue;
        });

        btnPlaceSupplierOrder.setVisible(false);

    }

    private void loadDate(){
        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        txtDate.setText(f.format(date));
    }

    private void setSupplierOrderID() {
        try {

            txtSupplierOrderId.setText(new SupplierOrderController().getSupplierOrderId());

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void loadSupplierIds() throws SQLException, ClassNotFoundException {
        List<String> SupplierIds = new SupplierController()
                .getAllSupplierIds();
        cmbSupplierId.getItems().addAll(SupplierIds);

    }

    private void setSupplierItem(String supplierId) throws SQLException, ClassNotFoundException {
        List<String> itemids = new ItemController().selectItem(supplierId);
        cmbItemCode.getItems().clear();
        cmbItemCode.getItems().addAll(itemids);
    }

    private void setItemData(String itemCode) throws SQLException, ClassNotFoundException {
        Item i1 = new ItemController().getItem(itemCode);
        if (i1 == null) {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set");
        } else {
            txtDescription.setText(i1.getDescription());
            txtUnitPrice.setText(String.valueOf(i1.getSellUnitPrice()));

        }
    }

    ObservableList<CartTM> obList= FXCollections.observableArrayList();
    public void AddToCartAndModifyOnAction(ActionEvent actionEvent) {
        String itemCode = cmbItemCode.getValue();
        String description = txtDescription.getText();
        double unitPrice = Double.parseDouble(txtUnitPrice.getText());
        int qty = Integer.parseInt(txtQTY.getText());
        double total = (unitPrice*qty) ;


        CartTM tm = new CartTM(
                itemCode,
                description,
                unitPrice,
                qty,
                total
        );

        int rowNumber=isExists(tm);

        if (rowNumber==-1){
            obList.add(tm);
        }else{

            CartTM temp = obList.get(rowNumber);
            CartTM newTm = new CartTM(
                    temp.getItemCode(),
                    temp.getDescription(),
                    temp.getUnitPrice(),
                    temp.getQty()+qty,
                    temp.getTotal()+total
            );

            obList.remove(rowNumber);
            obList.add(newTm);
        }
        tblGetSupplierOrder.setItems(obList);
        btnPlaceSupplierOrder.setVisible(true);
        calculateCost();
    }

    private int isExists(CartTM tm){
        for (int i = 0; i < obList.size(); i++) {
            if (tm.getItemCode().equals(obList.get(i).getItemCode())){
                return i;
            }
        }
        return -1;
    }

    void calculateCost(){
        double total=0;
        for (CartTM tm:obList
        ) {
            total+=tm.getTotal();
        }
        txtSupplierCost.setText(total+" /=");

    }

    public void RemoveOrderOnAction(ActionEvent actionEvent) {
        if (cartSelectedRowForRemove==-1){
            new Alert(Alert.AlertType.WARNING, "Please Select a row").show();
        }else{
            obList.remove(cartSelectedRowForRemove);
            calculateCost();
            tblGetSupplierOrder.refresh();
        }
    }

    public void PlaceSupplierOrderOnAction(ActionEvent actionEvent) {
        ArrayList<SupplierOrderDetails> items= new ArrayList<>();
        double total=0;
        for (CartTM tempTm:obList
        ) {
            total+=tempTm.getTotal();
            items.add(new SupplierOrderDetails(txtSupplierOrderId.getText(),tempTm.getItemCode(),
                    tempTm.getQty(),tempTm.getTotal()));
        }

        SupplierOrder SupplierOrder= new SupplierOrder(txtSupplierOrderId.getText(),
                txtDate.getText(),
                cmbSupplierId.getValue(), total,
                items

        );

        if (new SupplierOrderController().placeOrder(SupplierOrder)){
            new Alert(Alert.AlertType.CONFIRMATION, "Success").show();
            setSupplierOrderID();
        }else{
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }
    }

}
