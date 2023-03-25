package lk.ijse.akautoservice.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TouchEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import lk.ijse.akautoservice.model.CustomerModel;
import lk.ijse.akautoservice.model.VehicleModel;
import lk.ijse.akautoservice.to.Customer;
import lk.ijse.akautoservice.to.Vehicle;
import lk.ijse.akautoservice.util.CrudUtil;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Set;
import java.util.regex.Pattern;

public class CustomerFormController {
    public AnchorPane pane;
    public TextField txtName;
    public TextField txtNic;
    public TextField txtAddress;
    public TextField txtMobile;
    public TextField txtId;
    public TextField txtSearch;
    public AnchorPane modifycontext;
    public TextField mtxtldname;
    public TextField mtxtldnic;
    public TextField mtxtldaddress;
    public TextField mtxtfldsearchcustomer;
    public TextField mtxtldcustomerid;
    public TextField mtxtldmobilenumber;
    public AnchorPane addcontext;
    public TextField atxtfldname;
    public TextField atxtfldnic;
    public TextField atxtfldaddress;
    public TextField atxtfldsearch;
    public TextField atxtfldcustomerid;
    public TextField atxtfldmobile;
    public AnchorPane deletecontext;
    public TextField dtxtfldname;
    public TextField dtxtfldnic;
    public TextField dtxtfldaddress;
    public TextField dtxtfldsearch;
    public TextField dtxtfldcustomerid;
    public AnchorPane homecontext;
    public TableColumn mcolCustomerID;
    public TableColumn colCustomerName;
    public TableColumn colCustomerNic;
    public TableColumn mcolCustomerAddress;
    public TableColumn mcolMobileNo;
    public TableColumn acolCustomerID;
    public TableColumn acolCustomerName;
    public TableColumn acolCustomerNic;
    public TableColumn acolCustomerAddress;
    public TableColumn acolCustomerMobileNo;
    public TextField dtxtfldmobile;
    public TableColumn dcolCustomerID;
    public TableColumn dcolCustomerName;
    public TableColumn dcolCustomerNic;
    public TableColumn dcolCustomerAddress;
    public TableColumn dcolCustomerMobileNo;
    public TableColumn mcolCustomerName;
    public TableColumn mcolCustomerNic;
    public TableColumn colCustomerAddress;
    public TableColumn colCustomerMobileNo;
    public TableColumn colCustomerID;
    public AnchorPane customerHomeContext;
    public TableView dcustomertbl;
    public TableView mtblCustomer;
    public TableView htblCustomer;
    public TableColumn hcolCustomerID;
    public TableColumn hcolCustomerName;
    public TableColumn hcolCustomerNic;
    public TableColumn hcolCustomerAddress;
    public TableColumn hcolCustomerMobileNo;
    public TextField htxtSearch;
    public TableView atblCustomer;
    public Label lblCustomerWarning;
    ArrayList<Customer> rarray = null;

    @FXML

    private Pattern customerIDPattern;
    private Pattern customernamePattern;
    private Pattern customerNicPattern;
    private Pattern customerAddressPattern;
    private Pattern mobilePattern;

    public void initialize(){
        clearallContexts();
       customerHomeContext.setVisible(true);
       viewAllCustomerData();

        customerIDPattern = Pattern.compile("^[a-z0-9]{4,}$");
        customernamePattern = Pattern.compile("^[a-z0-9]{4,}$");
        customerNicPattern = Pattern.compile("^[a-z0-9]{4,}$");
        customerAddressPattern = Pattern.compile ("^[a-zA-Z-\\s]+$");
        mobilePattern = Pattern.compile("^07(7|6|8|1|2|5|0|4)-[0-9]{7}$");






        dcolCustomerID.setCellValueFactory(new PropertyValueFactory<>("customer_id"));
         dcolCustomerName.setCellValueFactory(new PropertyValueFactory<>("customer_name"));
         dcolCustomerNic.setCellValueFactory(new PropertyValueFactory<>("customer_nic"));
         dcolCustomerAddress.setCellValueFactory(new PropertyValueFactory<>("customer_address"));
         dcolCustomerMobileNo.setCellValueFactory(new PropertyValueFactory<>("customer_mobile"));

        mcolCustomerID.setCellValueFactory(new PropertyValueFactory<>("customer_id"));
        mcolCustomerName.setCellValueFactory(new PropertyValueFactory<>("customer_name"));
        mcolCustomerNic.setCellValueFactory(new PropertyValueFactory<>("customer_nic"));
        mcolCustomerAddress.setCellValueFactory(new PropertyValueFactory<>("customer_address"));
        mcolMobileNo.setCellValueFactory(new PropertyValueFactory<>("customer_mobile"));

        acolCustomerID.setCellValueFactory(new PropertyValueFactory<>("customer_id"));
        acolCustomerName.setCellValueFactory(new PropertyValueFactory<>("customer_name"));
        acolCustomerNic.setCellValueFactory(new PropertyValueFactory<>("customer_nic"));
        acolCustomerAddress.setCellValueFactory(new PropertyValueFactory<>("customer_address"));
        acolCustomerMobileNo.setCellValueFactory(new PropertyValueFactory<>("customer_mobile"));

        hcolCustomerID.setCellValueFactory(new PropertyValueFactory<>("customer_id"));
        hcolCustomerName.setCellValueFactory(new PropertyValueFactory<>("customer_name"));
        hcolCustomerNic.setCellValueFactory(new PropertyValueFactory<>("customer_nic"));
        hcolCustomerAddress.setCellValueFactory(new PropertyValueFactory<>("customer_address"));
        hcolCustomerMobileNo.setCellValueFactory(new PropertyValueFactory<>("customer_mobile"));


    }

    private void clearallContexts() {
            modifycontext.setVisible(false);
            addcontext.setVisible(false);
            deletecontext.setVisible(false);
           customerHomeContext.setVisible(false);
    }





    public void serachCustomerOnAction(ActionEvent actionEvent) {
          /*  public void customerSearchOnAcion(ActionEvent mouseEvent) {
        String customer_id = txtId.getText();
        try {
            Customer customer = CustomerModel.search(customer_id);
            if (customer != null) {
                fillData(customer);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }*/

        }



    public void btnAddOnAction(ActionEvent actionEvent) {
        clearallContexts();
        addcontext.setVisible(true);
    }

    public void btnModifyOnAction(ActionEvent actionEvent) {
        clearallContexts();
        modifycontext.setVisible(true);
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        clearallContexts();
        deletecontext.setVisible(true);

    }


    public void mbtnmodifucustomerOnAction(ActionEvent actionEvent) {
        Customer customer = new Customer(
                mtxtldcustomerid.getText(),
                mtxtldname.getText(),
                mtxtldnic.getText(),
                mtxtldaddress.getText(),
                mtxtldmobilenumber.getText()

        );
        try {
            CustomerModel.Update(customer);
            new Alert(Alert.AlertType.CONFIRMATION, "Updated!").show();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Data not Updated!" + e).show();

        }
    }


    public void abtnaddcustomerOnAction(ActionEvent actionEvent) {
        String customer_id = atxtfldcustomerid.getText();
        String customer_name = atxtfldname.getText();
        String customer_nic = atxtfldnic.getText();
        String customer_address = atxtfldaddress.getText();
        String customer_mobile = atxtfldmobile.getText();

        Customer customer = new Customer(customer_id, customer_name, customer_nic, customer_address, customer_mobile);
        try {
            boolean isAdded = CustomerModel.save(customer);
            if (isAdded) {
                new Alert(Alert.AlertType.CONFIRMATION, "Customer Added!").show();
                viewAllCustomerData();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Customer Data Not Added!" + e).show();
        }
        boolean isCustIDMatched =customerIDPattern.matcher(atxtfldcustomerid.getText()).matches();
        boolean isUserNameMatched = customernamePattern.matcher(atxtfldname.getText()).matches();
        boolean isMobileMatched = mobilePattern.matcher(atxtfldmobile.getText()).matches();
        boolean isaddressMatched = customerAddressPattern.matcher(atxtfldaddress.getText()).matches();
        boolean isNicMatched = customerNicPattern.matcher(atxtfldnic.getText()).matches();

        if(isUserNameMatched) {
            if(isCustIDMatched)  {
                if(isMobileMatched) {
                    if(isaddressMatched) {
                        if(isNicMatched) {
                            System.out.println("Registration started!");
                        } else {
                            atxtfldname.setText("Invalid");
                            clearFields();
                            atxtfldname.requestFocus();
                        }
                    } else {
                        atxtfldcustomerid.setText("Invalid CustomerID");
                        clearFields();
                      atxtfldcustomerid.requestFocus();
                    }
                } else {
                    atxtfldmobile.setText("Invalid Mobile No");
                    clearFields();
                   atxtfldmobile.requestFocus();
                }
            } else {
                atxtfldnic.setText("Invalid ");
                clearFields();
               atxtfldnic.requestFocus();
            }

        }
    }

    public void ddeleteCustomerOnAction(ActionEvent actionEvent) {
        try {
            if(CustomerModel.delete(dtxtfldcustomerid.getText())) {
                new Alert(Alert.AlertType.CONFIRMATION, "Deleted!").show();

            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, "Not Deleted.Try Again!").show();
}


    }


    public void dtxtSearchFldOnAction(KeyEvent keyEvent) {
        clearFields();

        ObservableList<Customer> obl = FXCollections.observableArrayList();

        try {
            rarray = CustomerModel.showAllOrderDetailsFromShopsUsingorderId(dtxtfldsearch.getText());
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();

        }
        obl.setAll(rarray);
        dcustomertbl.setItems(obl);
    }

    public void dtblCustomerOnAction(MouseEvent mouseEvent) {
        dtxtfldcustomerid.setText(rarray.get(dcustomertbl.getSelectionModel().getSelectedIndex()).getCustomer_id());
        dtxtfldname.setText(rarray.get(dcustomertbl.getSelectionModel().getSelectedIndex()).getCustomer_name());
        dtxtfldnic.setText(rarray.get(dcustomertbl.getSelectionModel().getSelectedIndex()).getCustomer_nic());
        dtxtfldaddress.setText(rarray.get(dcustomertbl.getSelectionModel().getSelectedIndex()).getCustomer_address());
        dtxtfldmobile.setText(rarray.get(dcustomertbl.getSelectionModel().getSelectedIndex()).getCustomer_mobile());
    }



    public void mtblCustomerOnAction(MouseEvent mouseEvent) {

        mtxtldcustomerid.setText(rarray.get(mtblCustomer.getSelectionModel().getSelectedIndex()).getCustomer_id());
        mtxtldname.setText(rarray.get(mtblCustomer.getSelectionModel().getSelectedIndex()).getCustomer_name());
        mtxtldnic.setText(rarray.get(mtblCustomer.getSelectionModel().getSelectedIndex()).getCustomer_nic());
        mtxtldaddress.setText(rarray.get(mtblCustomer.getSelectionModel().getSelectedIndex()).getCustomer_address());
        mtxtldmobilenumber.setText(rarray.get(mtblCustomer.getSelectionModel().getSelectedIndex()).getCustomer_mobile());
    }


    public void mtxtSearchCustOnAction(KeyEvent keyEvent) {


        mtblCustomer.getItems().clear();
        ObservableList<Customer> obl = FXCollections.observableArrayList();

        try {
            rarray = CustomerModel.showAllOrderDetailsFromShopsUsingorderId(mtxtfldsearchcustomer.getText());
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();

        }
        obl.setAll(rarray);
        mtblCustomer.setItems(obl);

        clearFields();
    }

    public void htxtSearchIDOnAction(KeyEvent keyEvent) {

        ObservableList<Customer> obl = FXCollections.observableArrayList();
        viewAllCustomerData();

        try {
            rarray = CustomerModel.showAllOrderDetailsFromShopsUsingorderId(htxtSearch.getText());
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();


        }
        obl.setAll(rarray);
        htblCustomer.setItems(obl);
    }
    private void clearFields(){
       mtxtldcustomerid.clear();
       mtxtldname.clear();
       mtxtldnic.clear();
       mtxtldaddress.clear();
       mtxtldmobilenumber.clear();
       dtxtfldcustomerid.clear();
       dtxtfldname.clear();
       dtxtfldnic.clear();
       dtxtfldaddress.clear();
       dtxtfldmobile.clear();
    }
    private void viewAllCustomerData() {
        ArrayList<Customer>carray=new ArrayList();

        ObservableList<Customer> obl = FXCollections.observableArrayList();
        try {
           carray = CustomerModel.showAllData();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();

        }
        obl.setAll(carray);
        atblCustomer.setItems(obl);
        htblCustomer.setItems(obl);







            }
}









