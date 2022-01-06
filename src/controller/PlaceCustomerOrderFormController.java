package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import model.*;
import views.tm.CartTM;
import views.tm.CustomerOrderCartTM;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PlaceCustomerOrderFormController {

    public Label lblCustomerOrderId;
    public Label lblCustomerOrderDate;
    public ComboBox<String> cmbSelectCustomer;
    public DatePicker dtpckDeliveryDate;
    public Label lblDescription;
    public Label lblUnitePrice;
    public ComboBox<String> cmbSelectItem;
    public Label lblDiscount;
    public TextField txtQTY;
    public Label lblQtyOnHand;
    public TableView tblPlaceCustomerOrder;
    public TableColumn colItemCode;
    public TableColumn colDescription;
    public TableColumn colUnitePrice;
    public TableColumn colQTY;
    public TableColumn colDiscount;
    public TableColumn colTotal;
    public Label lblTotal;
    public Label lblTotalDiscount;
    public AnchorPane pic1;
    public Label lblCustomerName;
    public Label lblShopName;
    public Button btnRemoveOrder;
    public Button btnAddToCatAndModify;
    public Button btnPlaceOrder;
    int cartSelectedRowForRemove = -1;

    public void initialize(){
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colUnitePrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colQTY.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colDiscount.setCellValueFactory(new PropertyValueFactory<>("totaldiscount"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));

        loadDate();
        setCustomerOrderID();

        try {
            loadCustomerIds();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            loadItemCodes();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        cmbSelectCustomer.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                setCustomerdate( newValue);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        cmbSelectItem.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                setItemData( newValue);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        tblPlaceCustomerOrder.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            cartSelectedRowForRemove = (int) newValue;
        });

        btnPlaceOrder.setVisible(true);

    }

    private void setItemData(Object newValue) throws SQLException, ClassNotFoundException {
        Item i1 = new ItemController().getItem((String) newValue);
        if (i1 == null) {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set");
        } else {
            lblDescription.setText(i1.getDescription());
            lblUnitePrice.setText(String.valueOf(i1.getSellUnitPrice()));
            lblDiscount.setText(String.valueOf(i1.getDiscount()));
            lblQtyOnHand.setText(String.valueOf(i1.getQtyOnHand()));
        }
    }

    private void loadItemCodes() throws SQLException, ClassNotFoundException {
        List<String> ItemCodes = new ItemController()
                .getAllItemCodes();
        cmbSelectItem.getItems().addAll(ItemCodes);
    }

    private void setCustomerdate(Object newValue) throws SQLException, ClassNotFoundException {
        Customer c1 = new CustomerController().getCustomer(newValue);
        if (c1 == null) {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set");
        } else {
            lblCustomerName.setText(c1.getCustomerName());
            lblShopName.setText(c1.getShopAddress());

        }
    }

    private void loadCustomerIds() throws SQLException, ClassNotFoundException {
        List<String> CustomerIds = new CustomerController()
                .getAllCustomerIds();
        cmbSelectCustomer.getItems().addAll(CustomerIds);

    }

    private void setCustomerOrderID() {
        try {

            lblCustomerOrderId.setText(new CustomerOrderController().getCustomerOrderId());

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void loadDate() {
        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        lblCustomerOrderDate.setText(f.format(date));
    }


    ObservableList<CustomerOrderCartTM> obList= FXCollections.observableArrayList();
    public void AddToCartAndModifyOnAction(ActionEvent actionEvent) {
        String itemCode = cmbSelectItem.getValue();
        String description = lblDescription.getText();
        double unitPrice = Double.parseDouble(lblUnitePrice.getText());
        double unitdiscount=Double.parseDouble(lblDiscount.getText());
        int qty = Integer.parseInt(txtQTY.getText());
        double totaldiscount= unitdiscount*qty;
        double total = ((unitPrice*qty)-totaldiscount) ;


        CustomerOrderCartTM tm = new CustomerOrderCartTM(
                itemCode,
                description,
                unitPrice,
                qty,
                totaldiscount,
                total
        );

        int rowNumber=isExists(tm);

        if (rowNumber==-1){
            obList.add(tm);
        }else{

            CustomerOrderCartTM temp = obList.get(rowNumber);
            CustomerOrderCartTM newTm = new CustomerOrderCartTM(
                    temp.getItemCode(),
                    temp.getDescription(),
                    temp.getUnitPrice(),
                    temp.getQty()+qty,
                    temp.getTotaldiscount()+totaldiscount,
                    temp.getTotal()+total
            );

            obList.remove(rowNumber);
            obList.add(newTm);
        }
        tblPlaceCustomerOrder.setItems(obList);
        btnPlaceOrder.setVisible(true);
        calculateCost();

    }

    private int isExists(CustomerOrderCartTM tm){
        for (int i = 0; i < obList.size(); i++) {
            if (tm.getItemCode().equals(obList.get(i).getItemCode())){
                return i;
            }
        }
        return -1;
    }

    void calculateCost(){
        double total=0;
        double discount=0.0;
        for (CustomerOrderCartTM tm:obList
        ) {
            total+=tm.getTotal();
            discount+=tm.getTotaldiscount();
        }
        lblTotal.setText(total+" /=");
        lblTotalDiscount.setText(discount+" /=");

    }

    public void RemoveOrderOnAction(ActionEvent actionEvent) {
        if (cartSelectedRowForRemove==-1){
            new Alert(Alert.AlertType.WARNING, "Please Select a row").show();
        }else{
            obList.remove(cartSelectedRowForRemove);
            calculateCost();
            tblPlaceCustomerOrder.refresh();
        }
    }

    public void PlaceOrderOnAction(ActionEvent actionEvent) {
        String status="NO";
        ArrayList<CustomerOrderDetails> items= new ArrayList<>();
        double total=0;
        double discount=0;
        for (CustomerOrderCartTM tempTm:obList
        ) {
            total+=tempTm.getTotal();
            discount+=tempTm.getTotaldiscount();
            items.add(new CustomerOrderDetails(lblCustomerOrderId.getText(),tempTm.getItemCode(),
                    tempTm.getQty(),tempTm.getTotal(),status));
        }

        CustomerOrder customerOrder= new CustomerOrder(lblCustomerOrderId.getText(),lblCustomerOrderDate.getText(),
                String.valueOf(dtpckDeliveryDate.getValue()),
                cmbSelectCustomer.getValue(),
                total,discount,status,
                items

        );

        if (new CustomerOrderController().placeOrder(customerOrder)){
            new Alert(Alert.AlertType.CONFIRMATION, "Success").show();
            setCustomerOrderID();
        }else{
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }
    }
}
