package lk.ijse.akautoservice.model;

import lk.ijse.akautoservice.to.ServicePackage;
import lk.ijse.akautoservice.to.Vehicle;
import lk.ijse.akautoservice.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Service_model {
    public static ArrayList<ServicePackage> getServiceIds() throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT * FROM Service_package;");
        ArrayList<ServicePackage> slist = new ArrayList<>();

        while (result.next()) {
            ServicePackage servicePackage=new ServicePackage(result.getString(1),
                    result.getString(2),
                    result.getString(3),
                    result.getDouble(4)

            );
            slist.add(servicePackage);
        }
        return slist;


    }

}
