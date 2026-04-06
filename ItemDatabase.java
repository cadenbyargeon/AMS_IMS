import java.io.File;
import java.sql.*;

public class ItemDatabase {

    private static final String URL = "jdbc:mysql://ams-ims-db-amsims.i.aivencloud.com:24455/AMS-IMS"
        + "?sslMode=REQUIRED"
        + "&sslCa=/workspaces/AMS_IMS/ca.pem";
    private static final String USER = "avnadmin";
    private static final String PASSWORD = "AVNS_gYcciwH3FiHQ6J6WzGi";

    private Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public Non_Serialized getNonSerialized(String name) throws SQLException, ClassNotFoundException {
        String sql = "SELECT itemName, model, partNum, qty, qty_semester, qty_next_semester "
                   + "FROM non_serialized WHERE LOWER(itemName) = LOWER(?)";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, name);

            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) {
                    return null;
                }
                String itemName = rs.getString("itemName");
                String model = rs.getString("model");   
                String partNum = rs.getString("partNum");
                double qty = rs.getDouble("qty");
                double qty_semester = rs.getDouble("qty_semester");
                double qty_next_semester = rs.getDouble("qty_next_semester");
                           
                Non_Serialized result = new Non_Serialized(itemName, model, partNum, qty, qty_semester, qty_next_semester);
                return result;
            }
        }
    }

    public Serialized getSerialized(String name) throws SQLException, ClassNotFoundException {
        String sql = "SELECT itemName, model, partNum, serialNum "
                   + "FROM serialized WHERE LOWER(itemName) = LOWER(?)";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, name);

            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) {
                    return null;
                }
                String itemName = rs.getString("itemName");
                String model = rs.getString("model");   
                String partNum = rs.getString("partNum");
                String serialNum = rs.getString("serialNum");

                Serialized result = new Serialized(itemName, model, partNum, serialNum);
                return result; 
            }
        }
    }

    public Consumable getConsumable(String name) throws SQLException, ClassNotFoundException {
        String sql = "SELECT itemName, model, partNum, qty, qty_semester, qty_next_semester, qtyType "
                   + "FROM consumable WHERE LOWER(itemName) = LOWER(?)";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, name);

            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) {
                    return null;
                }
                String itemName = rs.getString("itemName");
                String model = rs.getString("model");   
                String partNum = rs.getString("partNum");
                double qty = rs.getDouble("qty");
                double qty_semester = rs.getDouble("qty_semester");
                double qty_next_semester = rs.getDouble("qty_next_semester");
                String qtyType = rs.getString("qtyType");

                Consumable result = new Consumable(itemName, model, partNum, qty, qty_semester, qty_next_semester, qtyType);
                return result;

            }
        }
    }

    public Manual getManual(String manualName) throws SQLException, ClassNotFoundException {
        String sql = "SELECT name, model, partNum, qty, revision "
                   + "FROM manual WHERE LOWER(name) = LOWER(?)";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, manualName);

            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) {
                    return null;
                }
                String name = rs.getString("name");
                String model = rs.getString("model");
                String partNum = rs.getString("partNum");
                int qty = rs.getInt("qty");
                String revision = rs.getString("revision");

                Manual result = new Manual(name, model, partNum, qty, revision);
                return result;

            }
        }
    }


public void non_serialized_object_to_database(Non_Serialized n)
    {
        /**************************************************
         * SQL Query to insert the item into the database:
         * INSERT INTO non_serialized(name, model, partNum, qty, qty_semester, qty_next_semester, alert)
         * VALUES (n.name, n.model, n.partNum, n.qty, n.qty_semester, n.qty_next_semester, n.alert);

        */ 
        

        
    //}

    /*public void serialized_object_to_database(Serialized s)
    {
        /**************************************************
         * SQL Query to insert the item into the database:
         * INSERT INTO non_serialized(name, model, partNum, qty, qty_semester, qty_next_semester, serialNum, alert)
         * VALUES (s.name, s.model, s.partNum, s.qty, s.qty_semester, s.qty_next_semester, s.serialNum, s.alert);

        */
        

        
    }

    public void consumable_object_to_database(Consumable c)
    {
        /**************************************************
         * SQL Query to insert the item into the database:
         * INSERT INTO non_serialized(name, model, partNum, qty, qty_semester, qty_next_semester, qtyType, alert)
         * VALUES (c.name, c.model, c.partNum, c.qty, c.qty_semester, c.qty_next_semester, c.qtyType, c.alert);

        */ 
        

        
    }

    public void manual_object_to_database(Manual m)
    {
        /**************************************************
         * SQL Query to insert the item into the database:
         * INSERT INTO non_serialized(name, model, partNum, qty, qty_semester, qty_next_semester, revision, alert)
         * VALUES (m.name, m.model, m.partNum, m.qty, m.qty_semester, m.qty_next_semester, m.revision, m.alert);

        */ 
        

        
    }

}