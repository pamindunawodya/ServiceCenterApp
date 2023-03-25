package lk.ijse.akautoservice.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.akautoservice.model.CustomerModel;
import lk.ijse.akautoservice.model.ItemModel;
import lk.ijse.akautoservice.to.Item;

import java.sql.SQLException;
import java.util.ArrayList;

public class ItemFormController {
    public AnchorPane addItemContext;
    public TextField atxtItemName;
    public TextField atxtItemDetails;
    public TextField atxtbrand;
    public TextField atxtSearchItem;
    public TextField atxtItemCode;
    public TextField atxtItemStock;
    public TableView atblItem;
    public TableColumn acolItemCode;
    public TableColumn acolItemName;
    public TableColumn acolDetails;
    public TableColumn acolBrand;
    public TableColumn acolStock;
    public TableColumn acolUnitPrice;
    public TextField atxtItemUnitPrice;
    public AnchorPane modifyItemContext;
    public TextField mtxtItemName;
    public TextField mtxtDetails;
    public TextField mtxtBrand;
    public TextField mtxtSearchItemOnAction;
    public TextField mtxtItemCode;
    public TextField mtxtStock;
    public TableView mtblItem;
    public TableColumn mcolCustomerImColItemCode1;
    public TableColumn mColItemName;
    public TableColumn mColDetails;
    public TableColumn mColBrand;
    public TableColumn mcolStock;
    public TableColumn mcolUnityPrice;
    public TextField mtxtUnitPrice;
    public AnchorPane deleteItemContext;
    public TextField dtxtItemName;
    public TextField dtxtDetails;
    public TextField dtxtBrand;
    public TextField dtxtSearchItems;
    public TextField dtxtItemCode;
    public TextField dtxtStocks;
    public TableView dtblItem;
    public TableColumn dColItemCode;
    public TableColumn dColItemName;
    public TableColumn dColdetails;
    public TableColumn dColBrand;
    public TableColumn dColStock;
    public TableColumn dColUnitPrice;
    public TextField dtxtUnitPrice;
    public TableColumn mcolItemCode;
   ArrayList<Item>rarry=null;


    public void initialize() throws SQLException, ClassNotFoundException {
        clearallContexts();

        viewAllItemData();


        acolItemCode.setCellValueFactory(new PropertyValueFactory<>("item_code"));
        acolItemName.setCellValueFactory(new PropertyValueFactory<>("item_name"));
        acolDetails.setCellValueFactory(new PropertyValueFactory<>("description"));
        acolBrand.setCellValueFactory(new PropertyValueFactory<>("brand"));
        acolStock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        acolUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unit_price"));

        mcolItemCode.setCellValueFactory(new PropertyValueFactory<>("item_code"));
        mColItemName.setCellValueFactory(new PropertyValueFactory<>("item_name"));
        mColDetails.setCellValueFactory(new PropertyValueFactory<>("description"));
        mColBrand.setCellValueFactory(new PropertyValueFactory<>("brand"));
        mcolStock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        mcolUnityPrice.setCellValueFactory(new PropertyValueFactory<>("unit_price"));

        dColItemCode.setCellValueFactory(new PropertyValueFactory<>("item_code"));
        dColItemName.setCellValueFactory(new PropertyValueFactory<>("item_name"));
        dColdetails.setCellValueFactory(new PropertyValueFactory<>("description"));
        dColBrand.setCellValueFactory(new PropertyValueFactory<>("brand"));
        dColStock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        dColUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unit_price"));


    }

    private void clearallContexts() {
        modifyItemContext.setVisible(false);
        addItemContext.setVisible(false);
        deleteItemContext.setVisible(false);
    }

    public void atxtSearchItemOnAction(KeyEvent keyEvent) {
        atblItem.getItems().clear();
        ObservableList<Item> obl = FXCollections.observableArrayList();

        try {
            rarry= ItemModel.searchItemCode(atxtSearchItem.getText());
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();

        }
        obl.setAll(rarry);
     atblItem.setItems(obl);

    }

    public void abtnAddItemOnAction(ActionEvent actionEvent) {
        String item_code = atxtItemCode.getText();
        String item_name = atxtItemName.getText();
        String description =atxtItemDetails.getText();
        String brand =atxtbrand.getText();
        String stock = atxtItemStock.getText();
        double unit_price = Double.parseDouble(atxtItemUnitPrice.getText());

        Item item = new Item(item_code,item_name,description,brand,stock,unit_price);
        try {
            boolean isAdded = ItemModel.save(item);
            if (isAdded) {
                new Alert(Alert.AlertType.CONFIRMATION, "Item Added!").show();
                viewAllItemData();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Item Data Not Added!" + e).show();
        }
    }

    private void viewAllItemData() throws SQLException, ClassNotFoundException {
        ArrayList<Item> iarray = new ArrayList();

        ObservableList<Item> obsl = FXCollections.observableArrayList();
        try {
            iarray = ItemModel.showAllData();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();

        }
        obsl.setAll(iarray);
        atblItem.setItems(obsl);
        mtblItem.setItems(obsl);


    }


    public void mtblCustomerOnAction(MouseEvent mouseEvent) {
        mtxtItemCode.setText(rarry.get(mtblItem.getSelectionModel().getSelectedIndex()).getItem_code());
        mtxtItemName.setText(rarry.get(mtblItem.getSelectionModel().getSelectedIndex()).getItem_name());
       mtxtDetails.setText(rarry.get(mtblItem.getSelectionModel().getSelectedIndex()).getDescription());
        mtxtBrand.setText(rarry.get(mtblItem.getSelectionModel().getSelectedIndex()).getBrand());
        mtxtStock.setText(rarry.get(mtblItem.getSelectionModel().getSelectedIndex()).getStock());
       mtxtUnitPrice.setText(String.valueOf(rarry.get(mtblItem.getSelectionModel().getSelectedIndex()).getUnit_price()));

    }

    public void mtxtSearchItemOnAction(KeyEvent keyEvent) {

    }

    public void mbtnmodifyItmOnAction(ActionEvent actionEvent) {
       Item item = new Item(
                mtxtItemCode.getText(),
                mtxtItemName.getText(),
                mtxtDetails.getText(),
                mtxtBrand.getText(),
                mtxtStock.getText(),
                 Double.parseDouble(mtxtUnitPrice.getText()

        ));
        try {
            ItemModel.Update(item);
            new Alert(Alert.AlertType.CONFIRMATION, "Updated!").show();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Data not Updated!" + e).show();

        }
    }

    public void dtxtSearchItemOnAction(KeyEvent keyEvent) {
       dtblItem.getItems().clear();
        ObservableList<Item> obl = FXCollections.observableArrayList();

        try {
            rarry= ItemModel.searchItemCode(dtxtSearchItems.getText());
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();

        }
        obl.setAll(rarry);
        dtblItem.setItems(obl);
    }

    public void dtxtDeleteItemOnAction(ActionEvent actionEvent) {
        try {
            if(CustomerModel.delete(dtxtItemCode.getText())) {
                new Alert(Alert.AlertType.CONFIRMATION, "Deleted!").show();

            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, "Not Deleted.Try Again!").show();
        }
    }

    public void dtblItemOnAction(MouseEvent mouseEvent) {


    }

    public void btnOpenAddItemFormOnAction(ActionEvent actionEvent) {
        clearallContexts();
        addItemContext.setVisible(true);
    }

    public void btnOpenModifyFormOnAction(ActionEvent actionEvent) {
        clearallContexts();
        modifyItemContext.setVisible(true);
    }

    public void btnOpenDeleteItemFormOnAction(ActionEvent actionEvent) {
        clearallContexts();
        deleteItemContext.setVisible(true);
    }

    public void atblItemOnAction(MouseEvent mouseEvent) {


    }

    public void aatblItemOnAction(MouseEvent mouseEvent) {
        atxtItemCode.setText(rarry.get(atblItem.getSelectionModel().getSelectedIndex()).getItem_code());
        atxtItemName.setText(rarry.get(atblItem.getSelectionModel().getSelectedIndex()).getItem_name());
        atxtItemDetails.setText(rarry.get(atblItem.getSelectionModel().getSelectedIndex()).getDescription());
        atxtbrand.setText(rarry.get(atblItem.getSelectionModel().getSelectedIndex()).getBrand());
        atxtItemStock.setText(rarry.get(atblItem.getSelectionModel().getSelectedIndex()).getStock());
        atxtItemUnitPrice.setText(String.valueOf(rarry.get(atblItem.getSelectionModel().getSelectedIndex()).getUnit_price()));


    }
}
