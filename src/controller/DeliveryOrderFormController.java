package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import model.*;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import views.tm.DeliveryOrderTM;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DeliveryOrderFormController {

    public AnchorPane pic1;
    public ComboBox<String> cmbOrderId;
    public Label lblDeliveryId;
    public DatePicker dtpckSelectOrderDate;
    public TableView tblDeliveryOrder;
    public TableColumn colItemCode;
    public TableColumn colQTY;
    public TableColumn colCost;
    public TextField txtDeliveryCharge;
    public ComboBox<String> cmbSelectVehical;
    public ComboBox<String> cmbSelectDriver;
    public Label lblVehicalType;
    public Label lblDriverName;
    public TableColumn colDescription;
    int Bill_total;


    public void initialize() throws SQLException, ClassNotFoundException {
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("ItemCode"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("Description"));
        colQTY.setCellValueFactory(new PropertyValueFactory<>("CustomerOrderQTY"));
        colCost.setCellValueFactory(new PropertyValueFactory<>("cost"));

        try {
            loadVehical();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        loadDriver();
        setDelivryOrderID();

        dtpckSelectOrderDate.valueProperty().addListener((observable, oldValue, newValue) -> {
            try {
                setOrderIds( String.valueOf(newValue));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        cmbOrderId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                setOrderData( newValue);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        cmbSelectVehical.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                setVehicalType( newValue);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        cmbSelectDriver.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                setDriverName( newValue);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

    }

    private void setDelivryOrderID() {
        try {

            lblDeliveryId.setText(new DeliveryController().getDeliveryOrderId());

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void loadDriver() throws SQLException, ClassNotFoundException {
        List<String> driverIds = new DriverController()
                .getAllDriverIds();
        cmbSelectDriver.getItems().addAll(driverIds);
    }

    private void setDriverName(String newValue) throws SQLException, ClassNotFoundException {
        Driver d1 = new DeliveryController().getDriverdata(newValue);
        if (d1 == null) {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set");
        } else {
            lblDriverName.setText(d1.getDriverName());


        }
    }

    private void setVehicalType(String newValue) throws SQLException, ClassNotFoundException {
        Vehical v1 = new DeliveryController().getVehicaldata(newValue);
        if (v1 == null) {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set");
        } else {
            lblVehicalType.setText(v1.getVehicalType());


        }
    }

    private void loadVehical() throws SQLException, ClassNotFoundException {
        List<String> vehicalIds = new VehicalController()
                .getAllVehicalIds();
        cmbSelectVehical.getItems().addAll(vehicalIds);
    }

    private void setOrderIds(String date) throws SQLException, ClassNotFoundException {
        List<String> orderids = new DeliveryController().selectOrderIds(date);
        cmbOrderId.getItems().clear();
        cmbOrderId.getItems().addAll(orderids);

    }
    ObservableList<DeliveryOrderTM> obList= FXCollections.observableArrayList();
    private void setOrderData(String newValue) throws SQLException, ClassNotFoundException {
        List<DeliveryOrderTM> d1 = new DeliveryController().orderDetails(newValue);
        if (d1 == null) {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set").show();
        } else {
           int total=0;
            for (DeliveryOrderTM tempTm:d1
            ) {

                Item item = new ItemController().getItem(tempTm.getItemCode());

                DeliveryOrderTM tm = new DeliveryOrderTM(
                        tempTm.getItemCode(),
                        item.getDescription(),
                        tempTm.getCustomerOrderQTY(),
                        tempTm.getCost()
                );
                total+=tempTm.getCost();

                obList.clear();
                obList.add(tm);
                tblDeliveryOrder.setItems(obList);
            }
            Bill_total=total;

        }
    }


    public void DeliveryOnAction(ActionEvent actionEvent) {
        String status="NO";

        ArrayList<DeliveryOrderTM> items= new ArrayList<>();

        for (DeliveryOrderTM tempTm:obList
        ) {

            items.add(new DeliveryOrderTM(tempTm.getItemCode(),
                    tempTm.getCustomerOrderQTY(),tempTm.getCost()));
        }

        DeliveryOrder deliveryOrder= new DeliveryOrder(lblDeliveryId.getText(),
                cmbOrderId.getValue(),
                cmbSelectDriver.getValue(), cmbSelectVehical.getValue(),
                Integer.valueOf(txtDeliveryCharge.getText()),
                status,items

        );

        if (new DeliveryOrderController().deliveryOrder(deliveryOrder)){
           deliveryBill();
            new Alert(Alert.AlertType.CONFIRMATION, "Success").show();
            setDelivryOrderID();
        }else{
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }

    }

    private void deliveryBill() {
        try {

            JasperDesign design = JRXmlLoader.load(this.getClass().getResourceAsStream("/views/Reports/Vegitable Distridutor.jrxml"));
            JasperReport compileReport = JasperCompileManager.compileReport(design);

            String deliveryId = lblDeliveryId.getText();
            String orderId = cmbOrderId.getValue();
            String driverId = cmbSelectDriver.getValue();
            String vehicalId = cmbSelectVehical.getValue();
            int deliveryCharge = Integer.parseInt(txtDeliveryCharge.getText());
            int totalBill=Bill_total+deliveryCharge;

            String customerId = null;
            String customerName = null;
            String shopAddress = null;
            int contactNumber = 0;
         
            List<Customer> customerData = DeliveryController.getOrderId(orderId);
            for (Customer c1:customerData
                 ) {
                 customerId=c1.getCustomerId();
                customerName=c1.getCustomerName();
                shopAddress= c1.getShopAddress();
                contactNumber=c1.getCustomerContactNumber();

            }

            HashMap map = new HashMap();
            map.put("DeliveryID", deliveryId);
            map.put("CustomerOrderID", orderId);
            map.put("DeliveryCharge", deliveryCharge);
            map.put("totalValue", totalBill);
            map.put("itemCost",Bill_total);
            map.put("CustomerName",customerName);
            map.put("ShopAddress",shopAddress);
            map.put("CustomerContactNumber",contactNumber);

            JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport, map, new JRBeanArrayDataSource(obList.toArray()));
            JasperViewer.viewReport(jasperPrint, false);
        } catch (JRException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
