package lk.ijse.akautoservice.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.akautoservice.model.CustomerModel;
import lk.ijse.akautoservice.model.VehicleModel;
import lk.ijse.akautoservice.to.Customer;
import lk.ijse.akautoservice.to.Vehicle;

import java.sql.SQLException;
import java.util.ArrayList;

public class VehicleFormController {

    public JFXButton btnAdd;
    public JFXButton btnModify;
    public JFXButton btnDelete;
    public AnchorPane homeVehicleContext;
    public TextField txtSearch1;
    public ImageView btnSearch1;
    public AnchorPane addVehicleFormContext;
    public TextField atxtVehicleNo;
    public AnchorPane deleteVehicleFormContext;
    public TextField dtxtSearch;
    public ImageView dtbtnSearch;
    public TextField dtxtVehicleNo;
    public TextField dtxtVehicleBrand;
    public AnchorPane modifyVehicleFormContext1;
    public TextField dtxtSearch1;
    public ImageView dtbtnSearch1;
    public TextField mtxtVehicleNo;
    public TextField mtxtVehicleBrand;
    public TextField mtxtVehicleCategory;
    public TextField atxtVehicleCategory;
    public TextField atxtVehicleBrand;
    public ComboBox acmbCustomerID;
    public TextField atxtFldCustomerId;
    public TableView atblVehicle;
    public TableColumn acolCustomerId;
    public TableColumn aColVehicleNo;
    public TableColumn aColVehicleBrand;
    public TableColumn acolVehicleCategory;
    public TreeTableView tblVehicle;
    public TreeTableColumn VehicleCol1;
    public TreeTableColumn vehicleBrandCol1;
    public TreeTableColumn vehicleCategoryCol1;
    public TableView mtblVehicle;
    public TableColumn mcolCustomerId;
    public TableColumn mColVehicleNo;
    public TableColumn mColVehicleBrand;
    public TableColumn mcolVehicleCategory;
    public TextField dtxtVehicleCategory;
    public TableView dtblVehicle;
    public TableColumn dcolCustomerId;
    public TableColumn dColVehicleNo;
    public TableColumn dColVehicleBrand;
    public TableColumn dcolVehicleCategory;

    public TextField mtxtCustomerID;
    public ComboBox mcmbCustomerID;
    public TextField mtxtSearch;
    public ComboBox dcmbCustomerID;
    public TextField dtxtCustomerID;
    public TableView htblVehicle;
    public TableColumn hcolCustomerId;
    public TableColumn hColVehicleNo;
    public TableColumn hColVehicleBrand;
    public TableColumn hcolVehicleCategory;
    public TextField htxtSearch;
    ArrayList<Vehicle> rarray = null;

    public void initialize() throws SQLException, ClassNotFoundException {
        clearallcontexts();
        homeVehicleContext.setVisible(true);

        ObservableList<String> obList = FXCollections.observableArrayList();
        obList.addAll(VehicleModel.getCustomerIds());
        dcmbCustomerID.setItems(obList);

        dcolCustomerId.setCellValueFactory(new PropertyValueFactory<>("customer_id"));
        dColVehicleNo.setCellValueFactory(new PropertyValueFactory<>("vehicle_number"));
        dcolVehicleCategory.setCellValueFactory(new PropertyValueFactory<>("vehicle_category"));
        dColVehicleBrand.setCellValueFactory(new PropertyValueFactory<>("vehicle_brand"));


        obList.addAll(VehicleModel.getCustomerIds());
        mcmbCustomerID.setItems(obList);

        mcolCustomerId.setCellValueFactory(new PropertyValueFactory<>("customer_id"));
        mColVehicleNo.setCellValueFactory(new PropertyValueFactory<>("vehicle_number"));
        mcolVehicleCategory.setCellValueFactory(new PropertyValueFactory<>("vehicle_category"));
        mColVehicleBrand.setCellValueFactory(new PropertyValueFactory<>("vehicle_brand"));

        obList.addAll(VehicleModel.getCustomerIds());
        acmbCustomerID.setItems(obList);

        acolCustomerId.setCellValueFactory(new PropertyValueFactory<>("customer_id"));
        aColVehicleNo.setCellValueFactory(new PropertyValueFactory<>("vehicle_number"));
        acolVehicleCategory.setCellValueFactory(new PropertyValueFactory<>("vehicle_category"));
        aColVehicleBrand.setCellValueFactory(new PropertyValueFactory<>("vehicle_brand"));

        hcolCustomerId.setCellValueFactory(new PropertyValueFactory<>("customer_id"));
        hColVehicleNo.setCellValueFactory(new PropertyValueFactory<>("vehicle_number"));
        hcolVehicleCategory.setCellValueFactory(new PropertyValueFactory<>("vehicle_category"));
        hColVehicleBrand.setCellValueFactory(new PropertyValueFactory<>("vehicle_brand"));



    }



    private void clearallcontexts() {
        homeVehicleContext.setVisible(false);
        addVehicleFormContext.setVisible(false);
        modifyVehicleFormContext1.setVisible(false);
        deleteVehicleFormContext.setVisible(false);
    }


   public void addVehicleOnAction(ActionEvent actionEvent) {

    }

    public void ModifyVehicleOnAction(ActionEvent actionEvent) {

    }

    public void deleteVehicleOnAction(ActionEvent actionEvent) {
    }

    public void aBtnSearchOnAction(MouseEvent mouseEvent) {

    }

    public void dtbtnSearchOnAction(MouseEvent mouseEvent) {

    }

    public void modifyBtnOnAction(ActionEvent actionEvent) {
    Vehicle vehicle = new Vehicle(

                mtxtVehicleNo.getText(),
                mtxtVehicleCategory.getText(),
                mtxtVehicleBrand.getText(),
                mtxtCustomerID.getText()

        );
        try {
           VehicleModel.Update(vehicle);
            new Alert(Alert.AlertType.CONFIRMATION, "Updated!").show();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Data not Updated!" + e).show();

        }


            }




    public void btnAddVehicleOnAction(ActionEvent actionEvent) {
        clearallcontexts();
        addVehicleFormContext.setVisible(true);
    }

    public void btnDeleteVehicleOnAction(ActionEvent actionEvent) {
        clearallcontexts();
        deleteVehicleFormContext.setVisible(true);
    }

    public void btnModifyVehicleOnAction(ActionEvent actionEvent) {
        clearallcontexts();
        modifyVehicleFormContext1.setVisible(true);
    }


       public void cmbCustomerfIdOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        //System.out.println( acmbCustomerID.getSelectionModel().selectedItemProperty().getValue());
       // acmbCustomerID.getSelectionModel().selectedItemProperty();
        atxtFldCustomerId.setText((String) acmbCustomerID.getSelectionModel().selectedItemProperty().getValue());
        ObservableList<Vehicle> obl =FXCollections.observableArrayList();
        obl.setAll(
                VehicleModel.searchVehicleNo(atxtFldCustomerId.getText())
        );
        atblVehicle.setItems(obl);

    }

    public void atxtFldCustomerIdOnAction(ActionEvent actionEvent) {


    }


    public void btnAddOnAction(ActionEvent actionEvent) {


        String vehicle_number = atxtVehicleNo.getText();
        String vehicle_category = atxtVehicleCategory.getText();
        String vehicle_brand = atxtVehicleBrand.getText();
        String customer_id=atxtFldCustomerId.getText();


    Vehicle vehicle = new Vehicle(vehicle_number,vehicle_category,vehicle_brand,customer_id);
        try {
            boolean isAdded = VehicleModel.save(vehicle);
            if (isAdded) {
                new Alert(Alert.AlertType.CONFIRMATION, "Vehicle Added!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        try {
            if (VehicleModel.delete(dtxtVehicleNo.getText())) {
                new Alert(Alert.AlertType.CONFIRMATION, "Deleted!").show();

            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, "Not Deleted.Try Again!").show();

        }

    }

    public void mtxtCustomerIdOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

    }

    public void mcmbCustomerIDOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        mtxtCustomerID.setText((String) mcmbCustomerID.getSelectionModel().selectedItemProperty().getValue());
        ObservableList<Vehicle> obl =FXCollections.observableArrayList();
        obl.setAll(
                VehicleModel.searchVehicleNo(mtxtCustomerID.getText())
        );
        mtblVehicle.setItems(obl);


    }

    public void msearchvehicleIDOnAction(KeyEvent keyEvent) {
        mtblVehicle.getItems().clear();
        ObservableList<Vehicle> obl = FXCollections.observableArrayList();

        try {
            rarray = VehicleModel.searchVehicleNo(mtxtSearch.getText());
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();

        }
        obl.setAll(rarray);
        mtblVehicle.setItems(obl);
    }

    public void mtblVehicleOnAction(MouseEvent mouseEvent) {

            mtxtCustomerID.setText(rarray.get(mtblVehicle.getSelectionModel().getSelectedIndex()).getCustomer_id());
            mtxtVehicleNo.setText(rarray.get(mtblVehicle.getSelectionModel().getSelectedIndex()).getVehicle_number());
            mtxtVehicleCategory.setText(rarray.get(mtblVehicle.getSelectionModel().getSelectedIndex()).getVehicle_category());
            mtxtVehicleBrand.setText(rarray.get(mtblVehicle.getSelectionModel().getSelectedIndex()).getVehicle_brand());


    }

    public void dtxtSearchFldOnAction(KeyEvent keyEvent) {
        dtblVehicle.getItems().clear();
        ObservableList<Vehicle> obl = FXCollections.observableArrayList();

        try {
            rarray = VehicleModel.searchVehicleNo(dtxtSearch.getText());
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();

        }
        obl.setAll(rarray);
        dtblVehicle.setItems(obl);
    }


    public void dtblVehicleOnAction(MouseEvent mouseEvent) {

        dtxtCustomerID.setText(rarray.get(dtblVehicle.getSelectionModel().getSelectedIndex()).getCustomer_id());
        dtxtVehicleNo.setText(rarray.get(dtblVehicle.getSelectionModel().getSelectedIndex()).getVehicle_number());
        dtxtVehicleCategory.setText(rarray.get(dtblVehicle.getSelectionModel().getSelectedIndex()).getVehicle_category());
        dtxtVehicleBrand.setText(rarray.get(dtblVehicle.getSelectionModel().getSelectedIndex()).getVehicle_brand());

    }

    public void cmbVehicleIDOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        dtxtCustomerID.setText((String) dcmbCustomerID.getSelectionModel().selectedItemProperty().getValue());
        ObservableList<Vehicle> obl =FXCollections.observableArrayList();
        obl.setAll(
                VehicleModel.searchVehicleNo(dtxtCustomerID.getText())
        );
        dtblVehicle.setItems(obl);

    }

    public void htxtSearchVehicleNumber(KeyEvent keyEvent) {
        htblVehicle.getItems().clear();
        ObservableList<Vehicle> obl = FXCollections.observableArrayList();

        try {
            rarray = VehicleModel.searchVehicleNo(htxtSearch.getText());
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();

        }
        obl.setAll(rarray);
        htblVehicle.setItems(obl);
    }

    public void dbtnVehicleOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {


    }
}


