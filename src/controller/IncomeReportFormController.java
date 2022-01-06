package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Customer;
import model.CustomerOrder;

import java.sql.SQLException;
import java.util.List;

public class IncomeReportFormController {

    public DatePicker dtpckForm;
    public DatePicker dtpckTo;
    public Button btnSearch;
    public TableView tblIncomeReport;
    public TableColumn colOrderId;
    public TableColumn colOrderDate;
    public TableColumn colCustomerId;
    public TableColumn colIncomeReport;
    public Label lblTotal;
    String from;
    String to;

    public void initialize(){
        colOrderId.setCellValueFactory(new PropertyValueFactory<>("CustomerOrderID"));
        colOrderDate.setCellValueFactory(new PropertyValueFactory<>("CustomerOrderDate"));
        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("CustomerId"));
        colIncomeReport.setCellValueFactory(new PropertyValueFactory<>("TotalCost"));


        dtpckForm.valueProperty().addListener((observable, oldValue, newValue) -> {
            from= String.valueOf(newValue);
        });

        dtpckTo.valueProperty().addListener((observable, oldValue, newValue) -> {
            to= String.valueOf(newValue);
        });
    }

    ObservableList<CustomerOrder> obList= FXCollections.observableArrayList();
    public void SearchOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        obList.clear();
        List<CustomerOrder> c1 = new CustomerIncomeReportController().incomeDetails(from,to);
        if (c1 == null) {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set").show();
        } else {
            for (CustomerOrder tempTm:c1
            ) {
                CustomerOrder tm = new CustomerOrder(
                        tempTm.getCustomerOrderID(),
                        tempTm.getCustomerOrderDate(),
                        tempTm.getCustomerId(),
                        tempTm.getTotalCost()
                );
                obList.add(tm);
                tblIncomeReport.setItems(obList);
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
        lblTotal.setText(total+" /=");

    }
}
