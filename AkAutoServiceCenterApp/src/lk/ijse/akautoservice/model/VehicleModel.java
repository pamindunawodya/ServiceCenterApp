package lk.ijse.akautoservice.model;

import lk.ijse.akautoservice.to.Customer;
import lk.ijse.akautoservice.to.Vehicle;
import lk.ijse.akautoservice.util.CrudUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class VehicleModel {
    public static boolean save(Vehicle vehicle) throws SQLException, ClassNotFoundException {


        return CrudUtil.execute("INSERT INTO Vehicle VALUES(?, ?, ?, ?)",
                vehicle.getVehicle_number(),
                vehicle.getVehicle_category(),
                vehicle.getVehicle_brand(),
                vehicle.getCustomer_id()


        );
    }

    public static ArrayList getCustomerIds() throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT c_id FROM Customer;");
        ArrayList custList = new ArrayList<>();

        while (result.next()) {
            custList.add(result.getString(1));
        }
        return custList;


    }





   /* public boolean update(Vehicle vehicle) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE Customer SET name=?,nic=?,address=?,contact=? WHERE id=?",
                customer.getCustomer_name(), customer.getCustomer_nic(), customer.getCustomer_address(), customer.getCustomer_mobile());

    }*/

    public static ArrayList<Vehicle> searchVehicleNo(String x) throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT*FROM Vehicle WHERE v_number LIKE '%" + x + "%';");
        ArrayList<Vehicle> vlist = new ArrayList<>();

        while (result.next()) {
            vlist.add(new Vehicle(
                    result.getString(1),
                    result.getString(2),
                    result.getString(3),
                    result.getString(4)
            ));
        }
        return vlist;
    }

    public static ArrayList<Vehicle> search(String x) throws SQLException, ClassNotFoundException {


        ResultSet result = CrudUtil.execute("SELECT*FROM VEHICLE WHERE C_ID='" + x + "';");
        ArrayList<Vehicle> vlist = new ArrayList<>();

        while (result.next()) {
            vlist.add(new Vehicle(

                    result.getString(1),
                    result.getString(2),
                    result.getString(3),
                    result.getString(4)
            ));
        }
        return vlist;
    }

        public static Object Update(Vehicle v) throws SQLException, ClassNotFoundException {
            return CrudUtil.execute(" UPDATE Vehicle SET c_id='"+v.getCustomer_id() +"', v_category='"+v.getVehicle_category()+"',v_brand='"+v.getVehicle_brand()+"WHERE c_id='"+ v.getVehicle_number()+"'");

        }

        public static boolean  delete(String x) throws SQLException, ClassNotFoundException {
            return CrudUtil.execute("DELETE FROM Vehicle WHERE  v_number='"+x+"';");
        }







    }



