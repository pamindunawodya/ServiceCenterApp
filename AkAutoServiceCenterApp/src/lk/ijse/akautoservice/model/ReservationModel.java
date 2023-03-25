package lk.ijse.akautoservice.model;

import lk.ijse.akautoservice.to.*;
import lk.ijse.akautoservice.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReservationModel {


    public static ArrayList<Customer> getCustomerIds() throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT * FROM Customer;");
        ArrayList<Customer> custList = new ArrayList<>();

        while (result.next()) {
            Customer c = new Customer(result.getString(1),
                    result.getString(2),
                    result.getString(3),
                    result.getString(4),
                    result.getString(5)
                    );
            custList.add(c);
        }
        return custList;
    }

        public static ArrayList getVehicleNumbers() throws SQLException, ClassNotFoundException {
            ResultSet result = CrudUtil.execute("SELECT * FROM Vehicle;");
            ArrayList<Vehicle> vlist = new ArrayList<>();

            while (result.next()) {
                Vehicle vehicle=new Vehicle(result.getString(1),
                result.getString(2),
                result.getString(3),
                result.getString(4)


                );
               vlist.add(vehicle);
            }
            return vlist;
        }

    public static ArrayList<ReservationGraphto> GetMonthDatesForReservedTotalAll() throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT\n" +
                "Reservations.Reserved_Id,ReservationsLimitsForMonthDates.Month_Limit,ReservationsLimitsForMonthDates.Month_Day,COUNT(*)\n" +
                "FROM \n" +
                "ReservationsLimitsForMonthDates LEFT JOIN Reservations ON Day(Reserved_Date)=Month_Day\n" +
                "GROUP BY ReservationsLimitsForMonthDates.Month_Day\n" +
                "HAVING COUNT(*) >0\n" +
                "ORDER BY ReservationsLimitsForMonthDates.Month_Day ASC;");
        ArrayList<ReservationGraphto> slist = new ArrayList<>();

        while (result.next()) {
            slist.add( new ReservationGraphto(
                    result.getString(1),
                    result.getString(2),
                    result.getString(3),
                    result.getString(4)
            ));
        }
        System.out.println(slist.size());
        return slist;
    }

    public static String GetLastReservationID() throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT Reserved_Id FROM Reservations ORDER BY Reserved_Id DESC LIMIT 1;");
        String id = null;
        while (result.next()) {
            id=result.getString(1);
        }
        return id;

    }

    public static boolean Save (Reservationto r) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO Reservations VALUES(?,?,?,?,?,?)",
                r.getReserved_Id(),
                r.getReserved_CustomerID(),
                r.getReserved_VehicleNo(),
                r.getReserved_ServiceType(),
                r.getReserved_ServiceFee(),
                r.getReserved_Date()
                 );
 }
    public static ArrayList<Reservationto> showAllData() throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT * FROM Reservations;");
        ArrayList<Reservationto> slist = new ArrayList<>();

        while (result.next()) {
            slist.add( new Reservationto(

                    result.getString(1),
                    result.getString(2),
                    result.getString(3),
                    result.getString(4),
                    result.getDouble(5),
                    result.getDate(6)


            ));
        }
        return slist;
    }
    }








