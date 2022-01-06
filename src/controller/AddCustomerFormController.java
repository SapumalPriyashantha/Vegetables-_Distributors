package controller;

import Util.ValidationUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.Customer;
import views.tm.CustomerOrderCartTM;

import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.regex.Pattern;

public class AddCustomerFormController {

    public TextField txtId;
    public TextField txtName;
    public TextField txtAddress;
    public TextField txtNumber;
    public Button btnAddCustomer;
    public TableView tblAddNewCustomer;
    public TableColumn colCustomerId;
    public TableColumn colCustomerName;
    public TableColumn colShopAddress;
    public TableColumn colCust_Con_Number;

    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap();
    Pattern idPattern = Pattern.compile("^(C-)[0-9]{3}$");
    Pattern namePattern = Pattern.compile("^[A-z ]{3,20}$");
    Pattern addressPattern = Pattern.compile("^[A-z0-9/ ]{6,30}$");
    Pattern salaryPattern = Pattern.compile("^[0-9]{10}$");

    public void initialize(){
        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("CustomerId"));
        colCustomerName.setCellValueFactory(new PropertyValueFactory<>("CustomerName"));
        colShopAddress.setCellValueFactory(new PropertyValueFactory<>("ShopAddress"));
        colCust_Con_Number.setCellValueFactory(new PropertyValueFactory<>("CustomerContactNumber"));

        try {
            previousData();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        btnAddCustomer.setDisable(true);
        storeValidations();
        setCustomerId();
    }

    private void previousData() throws SQLException, ClassNotFoundException {
        List<Customer> customers = new CustomerController().customerTableData();
        for (Customer c1:customers
             ) {
            obList.add(new Customer(c1.getCustomerId(),
                    c1.getCustomerName(),
                    c1.getShopAddress(),
                    c1.getCustomerContactNumber()
            ));

        }
        tblAddNewCustomer.setItems(obList);
    }

    private void setCustomerId() {
        try {

            txtId.setText(new CustomerController().getCustomerId());

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
        map.put(txtNumber, salaryPattern);
    }

    ObservableList<Customer> obList= FXCollections.observableArrayList();
    public void AddCustomerOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        Customer c1 = new Customer(
                txtId.getText(),
                txtName.getText(),
                txtAddress.getText(),
                Integer.parseInt(txtNumber.getText())


        );
        obList.add(c1);
        tblAddNewCustomer.setItems(obList);

        if (new CustomerController().saveCustomer(c1))
            new Alert(Alert.AlertType.CONFIRMATION, "Saved..").show();
        else
            new Alert(Alert.AlertType.WARNING, "Try Again..").show();
    }

    public void textFields_Key_Released(KeyEvent keyEvent) {
        Object response = ValidationUtil.validate(map,btnAddCustomer);

        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (response instanceof TextField) {
                TextField errorText = (TextField) response;
                errorText.requestFocus();
            } else if (response instanceof Boolean) {
                new Alert(Alert.AlertType.INFORMATION, "Please Enter Add New Customer Button").showAndWait();
            }
        }
    }

}
