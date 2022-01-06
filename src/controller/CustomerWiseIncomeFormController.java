package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Customer;
import model.CustomerOrder;
import model.Driver;
import views.tm.DeliveryOrderTM;

import java.sql.SQLException;
import java.util.List;

public class CustomerWiseIncomeFormController {

    public ComboBox cmbSelectCustomer;
    public Label lblCustomerId;
    public Label lblCustomerName;
    public Label lblShopAddress;
    public Label lblCustConNumber;
    public TableView tblCustomerWiseIncome;
    public TableColumn colOrderId;
    public TableColumn colOrderDate;
    public TableColumn colDeliveryDate;
    public TableColumn colDiscount;
    public TableColumn colIncome;
    public Label lblTotalIncome;

    public void initialize(){
        colOrderId.setCellValueFactory(new PropertyValueFactory<>("CustomerOrderID"));
        colOrderDate.setCellValueFactory(new PropertyValueFactory<>("CustomerOrderDate"));
        colDeliveryDate.setCellValueFactory(new PropertyValueFactory<>("CustomerOrderDeliveryDate"));
        colDiscount.setCellValueFactory(new PropertyValueFactory<>("TotalDiscount"));
        colIncome.setCellValueFactory(new PropertyValueFactory<>("TotalCost"));

        try {
            loadCustomersIds();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        cmbSelectCustomer.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                setCustomerData( newValue);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

    }

    private void setCustomerData(Object newValue) throws SQLException, ClassNotFoundException {
        Customer c1 = new CustomerController().getCustomer(newValue);
        if (c1 == null) {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set");
        } else {
            lblCustomerId.setText(c1.getCustomerId());
            lblCustomerName.setText(c1.getCustomerName());
            lblShopAddress.setText(c1.getShopAddress());
            lblCustConNumber.setText(String.valueOf(c1.getCustomerContactNumber()));

            obList.clear();
            customerwiseIncome(newValue);
        }
    }

    private void loadCustomersIds() throws SQLException, ClassNotFoundException {
        List<String> customerIds = new CustomerController()
                .getAllCustomerIds();
        cmbSelectCustomer.getItems().addAll(customerIds);
    }

    ObservableList<CustomerOrder> obList= FXCollections.observableArrayList();
    private void customerwiseIncome(Object newValue) throws SQLException, ClassNotFoundException {
        List<CustomerOrder> c1 = new CustomerIncomeController().IncomeDetails(newValue);
        if (c1 == null) {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set");
        } else {
            for (CustomerOrder tempTm:c1
            ) {
                CustomerOrder tm = new CustomerOrder(
                        tempTm.getCustomerOrderID(),
                        tempTm.getCustomerOrderDate(),
                        tempTm.getCustomerOrderDeliveryDate(),
                        tempTm.getTotalCost(),
                        tempTm.getTotalDiscount()
                );

                obList.add(tm);
                tblCustomerWiseIncome.setItems(obList);
            }
         calculateCost();
        }
    }

    void calculateCost(){
        double total=0;
        for (CustomerOrder c1:obList
        ) {
            total+=c1.getTotalCost();
        }
        lblTotalIncome.setText(total+" /=");

    }
}
