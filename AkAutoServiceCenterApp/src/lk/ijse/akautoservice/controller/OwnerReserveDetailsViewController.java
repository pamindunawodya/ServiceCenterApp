package lk.ijse.akautoservice.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


import lk.ijse.akautoservice.model.ReservationModel;

import lk.ijse.akautoservice.to.Reservationto;


import java.sql.SQLException;
import java.util.ArrayList;

public class OwnerReserveDetailsViewController {

    public TableView tblReservationDetails;
    public TableColumn colReserveID;
    public TableColumn colCustID;
    public TableColumn colVnumber;
    public TableColumn colServicecode;
    public TableColumn colServiceType;
    public TableColumn colServiceDate;
    public TableColumn colServiceFee;

    public void initialize() throws SQLException, ClassNotFoundException, InterruptedException {
        viewAllReserveData();

        colReserveID.setCellValueFactory(new PropertyValueFactory<>("Reserved_Id"));
       colCustID.setCellValueFactory(new PropertyValueFactory<>("Reserved_CustomerID"));
       colVnumber.setCellValueFactory(new PropertyValueFactory<>("reserved_VehicleNo"));
        colServiceType.setCellValueFactory(new PropertyValueFactory<>("Reserved_ServiceType"));
        colServiceDate.setCellValueFactory(new PropertyValueFactory<>("reserved_Date"));
      colServiceFee.setCellValueFactory(new PropertyValueFactory<>("reserved_ServiceFee"));
    }
    private void viewAllReserveData() {
        ArrayList<Reservationto> relist = new ArrayList();
        ObservableList<Reservationto> obl = FXCollections.observableArrayList();
        try {
            relist = ReservationModel.showAllData();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();

        }
        obl.setAll(relist);
      tblReservationDetails.setItems(obl);

    }
}
