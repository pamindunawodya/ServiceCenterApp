package lk.ijse.akautoservice.model;

import lk.ijse.akautoservice.to.Customer;
import lk.ijse.akautoservice.to.Item;
import lk.ijse.akautoservice.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemModel {
    public static boolean save(Item item) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO Item VALUES(?, ?, ?, ?, ?, ?)",
             item.getItem_code(),
             item.getItem_name(),
             item.getDescription(),
             item.getBrand(),
             item.getStock(),
             item.getUnit_price()

        );
    }
    public static ArrayList<Item> showAllData() throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT * FROM Item;");
        ArrayList<Item> slist = new ArrayList<>();

        while (result.next()) {
           Item item=new Item(result.getString(1),
                    result.getString(2),
                    result.getString(3),
                    result.getString(4),
                    result.getString(5),
                    result.getDouble(6)

           );
           slist.add(item);
        }
        return slist;
    }
    public static boolean Update(Item r) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE Customer SET item_name = '"+r.getItem_name() +"', item_details='"+r.getDescription()+"',brand='"+r.getBrand()+"',stock='"+ r.getStock()+"',unity_price='"+ r.getUnit_price()+"'WHERE item_code='"+
                r.getItem_code()+"'");
    }

    public static boolean  delete(String x) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM Item WHERE item_code='"+x+"';");
    }
    public static ArrayList<Item> searchItemCode(String x) throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT*FROM Item WHERE item_code '%" + x + "%';");
        ArrayList<Item> ilist = new ArrayList<>();

        while (result.next()) {
           Item item=new Item(result.getString(1),
                    result.getString(2),
                    result.getString(3),
                    result.getString(4),
                    result.getString(5),
                    result.getDouble(6)
            );
           ilist.add(item);
        }
        return ilist;
    }
}
