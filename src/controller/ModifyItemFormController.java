package controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import model.Item;

import java.sql.SQLException;
import java.util.List;


public class ModifyItemFormController {


    public TextField SupplierId;
    public TextField txtDescription;
    public TextField txtGetUnitePrice;
    public TextField txtSellUniteprice;
    public TextField txtDiscount;
    public ComboBox<String> cmbItemcode;
    String itemCode;

    public void initialize(){
        try {
            loadItemCode();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        cmbItemcode.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                SearchItemOnAction(newValue) ;
                itemCode=newValue;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

    }

    private void loadItemCode() throws SQLException, ClassNotFoundException {
        List<String> AllItemCodes = new ItemController()
                .getAllItemCodes();
        cmbItemcode.getItems().addAll(AllItemCodes);

    }

    public void SearchItemOnAction(String itemCode) throws SQLException, ClassNotFoundException {
        Item i1= new ItemController().getItem(itemCode);
        if (i1==null) {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set").show();
        } else {
            setData(i1);
        }
    }

    void setData(Item c1){
        SupplierId.setText(c1.getSupplierId());
        txtDescription.setText(c1.getDescription());
        txtGetUnitePrice.setText(String.valueOf(c1.getGetUnitPrice()));
        txtSellUniteprice.setText(String.valueOf(c1.getSellUnitPrice()));
        txtDiscount.setText(String.valueOf(c1.getDiscount()));
    }

    public void ModifyItemOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Item i1= new Item(
                itemCode,
                Double.parseDouble(txtGetUnitePrice.getText()),
                Double.parseDouble(txtSellUniteprice.getText()),
                Double.parseDouble(txtDiscount.getText())
        );

        if (new ItemController().updateItem(i1))
            new Alert(Alert.AlertType.CONFIRMATION,"Updated..").show();
        else
            new Alert(Alert.AlertType.WARNING,"Try Again").show();
    }
}
