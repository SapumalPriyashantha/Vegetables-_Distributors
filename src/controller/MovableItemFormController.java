package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.CustomerOrder;
import model.Item;
import views.tm.MovableTM;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.List;

public class MovableItemFormController {

    public TableView tblMovableItem;
    public TableColumn colItemCode;
    public TableColumn colDescription;
    public TableColumn colUnitePrice;
    public TableColumn colQTYOnHand;
    public TableColumn colStockQTY;
    public TableColumn colMovable;

    public void initialize(){
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("ItemCode"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("Description"));
        colUnitePrice.setCellValueFactory(new PropertyValueFactory<>("SellUnitPrice"));
        colQTYOnHand.setCellValueFactory(new PropertyValueFactory<>("QtyOnHand"));
        colStockQTY.setCellValueFactory(new PropertyValueFactory<>("StockQty"));
        colMovable.setCellValueFactory(new PropertyValueFactory<>("present_age"));


        try {
            movable();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    ObservableList<MovableTM> obList= FXCollections.observableArrayList();
    private void movable() throws SQLException, ClassNotFoundException {
        DecimalFormat df = new DecimalFormat("#.##");
        List<Item> i1 = new movableItemController().movableItem();
        if (i1 == null) {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set");
        } else {
            for (Item tempTm:i1
            ) {
                MovableTM tm = new MovableTM(
                        tempTm.getItemCode(),
                        tempTm.getDescription(),
                        tempTm.getSellUnitPrice(),
                        tempTm.getQtyOnHand(),
                        tempTm.getStockQty(),
                        df.format((100-(((double)tempTm.getQtyOnHand() / (double)tempTm.getStockQty())*100)))
                );
                obList.add(tm);
                tblMovableItem.setItems(obList);
            }
        }
    }
}
