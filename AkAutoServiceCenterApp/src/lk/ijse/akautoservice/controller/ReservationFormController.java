package lk.ijse.akautoservice.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import lk.ijse.akautoservice.model.CustomerModel;
import lk.ijse.akautoservice.model.ReservationModel;
import lk.ijse.akautoservice.model.Service_model;
import lk.ijse.akautoservice.to.*;
import lk.ijse.akautoservice.view.tm.ReservationGraphtm;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class ReservationFormController {


    public AnchorPane reservepane;
    public AnchorPane reservationFormContext;
    public ComboBox cmbCustomerId;
    public TextField rtxtName;
    public TextField rtxtNic;
    public TextField rtxtAddress;
    public TextField rtxtMobileNo;
    public TableView rtblitem;
    public TableColumn colVehicleNumber;
    public TableColumn colVehicleTypke;
    public TableColumn colVehicleType;
    public TableColumn colVehicleBrand;
    public TableColumn colServicePackage;
    public TableColumn colQty;
    public TableColumn colTotal;
    public ComboBox cmbVehicleNo;
    public TextField rtxtVehicletype;
    public TextField rtxtServicePackage;
    public TextField rtxtVehicleBrand;
    public TextField rtxtQty;
    public ComboBox cmbServicePackage;
    public TextField rtxtServiceType;
    public TextField rserviceDescription;
    public TextField rserviceFee;
    public Label lblResrvationId;
    public TextField rtxtServiceFee;
    public TextField rtxtserviceDescription;
    public JFXTextField txtdate;
    public TextField txtfldcid;
    public TextField txtfldvno;
    public DatePicker datepicker;
    public Label lblTime;
    ArrayList<Customer>rslist=new ArrayList<>();
    ArrayList<Vehicle>vslist=new ArrayList<>();
    ArrayList<ServicePackage>slist=new ArrayList<>();


    public AnchorPane smwlsbrcmaincontext;
    public HBox hbox1pbar;
    public HBox hbox2pbar;
    public HBox hbox3pbar;
    public HBox hbox4pbar;
    public HBox hbox1lbl;
    public HBox hbox2lbl;
    public HBox hbox3lbl;
    public HBox hbox4lbl;
    public AnchorPane smwlsbrcpopupcontext;

    private ArrayList<ProgressBar> pbararray;
    private ArrayList<Label> lblarray ;
    private ArrayList<String> lblidarray;
    //private ArrayList<PopupWarehouseRoomTm> wtarray;
    private int count;

    public void initialize() throws SQLException, ClassNotFoundException, InterruptedException {
        pbararray = new ArrayList<>();
        lblarray = new ArrayList<>();
        lblidarray = new ArrayList<>();
        loadcmbVehicle();
        loadcmbCustomer();
        loadcmbServicePackage();
        smwlsbrcLoadData();
        loadReservationID();
        setDate();

    }
    private void loadReservationID() {    //get last ReservationID
        String id = null;
        try {
            id = ReservationModel.GetLastReservationID();
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.CONFIRMATION, e+"").show();

        }
        String[] array = id.split("(?=\\d)(?<!\\d)");
        int tempNumber=Integer.parseInt(array[1]);
        int finalizeOrderId=tempNumber+1;
        lblResrvationId.setText("R"+finalizeOrderId);
   }


    private void loadcmbServicePackage() throws SQLException, ClassNotFoundException {
        ObservableList<String> obl = FXCollections.observableArrayList();
        slist = Service_model.getServiceIds();
        ArrayList<String>servicePackagearray=new ArrayList<>();

        for(ServicePackage s:slist){
            servicePackagearray.add(s.getService_code());
        }
        obl.setAll(servicePackagearray);
        cmbServicePackage.setItems(obl);
    }

    private void loadcmbCustomer() throws SQLException, ClassNotFoundException {
        ObservableList<String> obl = FXCollections.observableArrayList();
        rslist = ReservationModel.getCustomerIds();
        ArrayList<String>custIdarray=new ArrayList<>();

        for(Customer r:rslist){
            custIdarray.add(r.getCustomer_id());
        }
        obl.setAll(custIdarray);
        cmbCustomerId.setItems(obl);
    }

    private void loadcmbVehicle() throws SQLException, ClassNotFoundException {
        ObservableList<String> obl = FXCollections.observableArrayList();
        vslist = ReservationModel.getVehicleNumbers();
        ArrayList<String>vehicleIdarray=new ArrayList<>();

        for(Vehicle r:vslist){
            vehicleIdarray.add(r.getVehicle_number());
        }
        obl.setAll(vehicleIdarray);
        cmbVehicleNo.setItems(obl);

    }

    public void cmbCustomerIdOnAction(ActionEvent actionEvent) {
        txtfldcid.setText(rslist.get(cmbCustomerId.getSelectionModel().getSelectedIndex()).getCustomer_id());
        rtxtMobileNo.setText(rslist.get(cmbCustomerId.getSelectionModel().getSelectedIndex()).getCustomer_mobile());
        rtxtAddress.setText(rslist.get(cmbCustomerId.getSelectionModel().getSelectedIndex()).getCustomer_address());
        rtxtName.setText(rslist.get(cmbCustomerId.getSelectionModel().getSelectedIndex()).getCustomer_name());
        rtxtNic.setText(rslist.get(cmbCustomerId.getSelectionModel().getSelectedIndex()).getCustomer_nic());

    }

    public void cmbVehiclenoOnAction(ActionEvent actionEvent) {
        txtfldvno.setText(vslist.get(cmbVehicleNo.getSelectionModel().getSelectedIndex()).getVehicle_number());
        rtxtVehicletype.setText(vslist.get(cmbVehicleNo.getSelectionModel().getSelectedIndex()).getVehicle_category());
        rtxtVehicleBrand.setText(vslist.get(cmbVehicleNo.getSelectionModel().getSelectedIndex()).getVehicle_brand());
    }

    public void rtxtServiceTypeOnAction(ActionEvent actionEvent) {
    }

    public void rserviceDescriptionOnAction(ActionEvent actionEvent) {
    }

    public void rserviceFeeOnAction(ActionEvent actionEvent) {
    }

    public void cmbServiceCodeOnAction(ActionEvent actionEvent) {
        rtxtServiceType.setText(slist.get(cmbServicePackage.getSelectionModel().getSelectedIndex()).getService_type());
       rtxtserviceDescription.setText(slist.get(cmbServicePackage.getSelectionModel().getSelectedIndex()).getService_description());
        rtxtServiceFee.setText(String.valueOf(slist.get(cmbServicePackage.getSelectionModel().getSelectedIndex()).getService_fee()));

    }

    void smwlsbrcLoadData() throws SQLException, ClassNotFoundException, InterruptedException {
        hbox1lbl.getChildren().clear();
        hbox1pbar.getChildren().clear();
        hbox2lbl.getChildren().clear();
        hbox2pbar.getChildren().clear();
        hbox3lbl.getChildren().clear();
        hbox3pbar.getChildren().clear();
        hbox4lbl.getChildren().clear();
        hbox4pbar.getChildren().clear();
        pbararray.clear();
        lblarray.clear();
        lblidarray.clear();
        count=0;
        ArrayList<ReservationGraphto> warray = ReservationModel.GetMonthDatesForReservedTotalAll();
        //System.out.println(warray.size());
        for (ReservationGraphto w : warray) {
            Label lbl = new Label();
            ProgressBar pbar = new ProgressBar();
            ReservationGraphtm wt = new ReservationGraphtm(
                    w.getCheckbookedOrnot(),
                    w.getMonth_Limit(),
                    w.getMonth_Day(),
                    w.getReserveCounts(),
                    pbar,
                    lbl
            );
            setPbarToArray(wt);
        }
    }

    private void setPbarToArray(ReservationGraphtm r){
        lblarray.add(new Label(" Day:"+r.getMonth_Day()+" \n Reserved:"+( r.getCheckbookedOrnot()==null?"0":r.getReserveCounts() ) ));
        lblarray.get(count).setStyle( "-fx-opacity:1;-fx-font-size:13; -fx-font-weight:bold; -fx-border-color:black;-fx-text-alignment:center;-fx-text-fill:black; -fx-pref-width:80; -fx-pref-height:120;-fx-max-height:126; -fx-max-width:80; -fx-border-width:1;");
        pbararray.add(new ProgressBar(Double.parseDouble(r.getReserveCounts())/Integer.parseInt(r.getMonth_Limit())));
        pbararray.get(count).setStyle( "-fx-accent: green;-fx-font-size:13; -fx-text-fill:white; -fx-border-color:black; -fx-max-height:126; -fx-max-width:80; -fx-border-width:1;");
        addPbarAddToContext();

    }

    private void addPbarAddToContext(){
        if(pbararray.size()<9) {
            hbox1lbl.getChildren().add(lblarray.get(count));
            hbox1pbar.getChildren().add(pbararray.get(count));
        }else if(pbararray.size()>8 && pbararray.size()<17){
            hbox2lbl.getChildren().add(lblarray.get(count));
            hbox2pbar.getChildren().add(pbararray.get(count));
        }else if(pbararray.size()>16 && pbararray.size()<25){
            hbox3lbl.getChildren().add(lblarray.get(count));
            hbox3pbar.getChildren().add(pbararray.get(count));
        }
        else if(pbararray.size()>24 && pbararray.size()<32){
            hbox4lbl.getChildren().add(lblarray.get(count));
            hbox4pbar.getChildren().add(pbararray.get(count));
        }
        count++;
    }

    public void DatePickAction(ActionEvent actionEvent) {
        txtdate.setText(String.valueOf(datepicker.getValue()));
    }

    public void createReservation(ActionEvent actionEvent) {
        Reservationto r =new Reservationto(
                lblResrvationId.getText(),
                txtfldcid.getText(),
                txtfldvno.getText(),
                rtxtServiceType.getText(),
                Double.parseDouble(rtxtServiceFee.getText()),
                java.sql.Date.valueOf(txtdate.getText())
        );
        try {
            boolean isAdded = ReservationModel.Save(r);
            if(isAdded) {
                new Alert(Alert.AlertType.CONFIRMATION, "New Reservation Added!").show();
                smwlsbrcLoadData();
            }else{
                new Alert(Alert.AlertType.WARNING, "Try Again!").show();
            }
        } catch (SQLException | ClassNotFoundException | InterruptedException e) {
            new Alert(Alert.AlertType.WARNING, e+"Try Again!").show();
    }
    }
    private void setDate() {
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String d = df.format(date);
       // lblTime.setText(d);
       lblTime.setText(new SimpleDateFormat("yyyy-mm-dd").format(new Date()));

    }

}
