import java.io.File;
import java.sql.*;
import java.util.ArrayList;

//import com.mysql.cj.x.protobuf.MysqlxPrepare.Prepare;

public class ItemDatabase {

    private static final String URL = System.getenv("URL");
    private static final String USER = System.getenv("USER");
    private static final String PASSWORD = System.getenv("PASSWORD");

    private Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public ArrayList<Non_Serialized> getNonSerialized(String name) throws SQLException, ClassNotFoundException {
        ArrayList<Non_Serialized> nonSerializedList = new ArrayList<Non_Serialized>();
        
        String sql = "SELECT id, name, model, partNum, qty, qty_semester, qty_next_semester "
                   + "FROM non_serialized WHERE LOWER(name) LIKE LOWER(?)";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, "%" +  name + "%");

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String itemName = rs.getString("name");
                    String model = rs.getString("model");   
                    String partNum = rs.getString("partNum");
                    double qty = rs.getDouble("qty");
                    double qty_semester = rs.getDouble("qty_semester");
                    double qty_next_semester = rs.getDouble("qty_next_semester");

                    Non_Serialized result = new Non_Serialized(itemName, model, partNum, qty, qty_semester, qty_next_semester);
                    nonSerializedList.add(result);

                }
                
                           
            }

            return nonSerializedList;
        }
    }

    public ArrayList<Serialized> getSerialized(String name) throws SQLException, ClassNotFoundException {
        ArrayList<Serialized> serializedList = new ArrayList<Serialized>();
        String sql = "SELECT id, name, model, partNum, serialNum "
                   + "FROM serialized WHERE LOWER(name) LIKE LOWER(?)";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, "%" +  name + "%");

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String itemName = rs.getString("name");
                    String model = rs.getString("model");   
                    String partNum = rs.getString("partNum");
                    String serialNum = rs.getString("serialNum");

                    Serialized result = new Serialized(itemName, model, partNum, serialNum);
                    serializedList.add(result);
                    

                    
                }
                return serializedList; 
            }
        }
    }

    public ArrayList<Consumable> getConsumable(String name) throws SQLException, ClassNotFoundException {
        ArrayList<Consumable> consumables = new ArrayList<Consumable>();
        String sql = "SELECT id, name, model, partNum, qty, qty_semester, qty_next_semester, qtyType "
                   + "FROM consumable WHERE LOWER(name) LIKE LOWER(?)";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, "%" +  name + "%");

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String itemName = rs.getString("name");
                    String model = rs.getString("model");   
                    String partNum = rs.getString("partNum");
                    double qty = rs.getDouble("qty");
                    double qty_semester = rs.getDouble("qty_semester");
                    double qty_next_semester = rs.getDouble("qty_next_semester");
                    String qtyType = rs.getString("qtyType");

                    Consumable result = new Consumable(itemName, model, partNum, qty, qty_semester, qty_next_semester, qtyType);
                    consumables.add(result);
                }
                
                return consumables;

            }
        }
    }

    public ArrayList<Manual> getManual(String manualName) throws SQLException, ClassNotFoundException {
        ArrayList<Manual> manuals = new ArrayList<Manual>();
        String sql = "SELECT id, name, model, partNum, qty, revision "
                   + "FROM manual WHERE LOWER(name) LIKE LOWER(?)";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, "%" +  manualName + "%");

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String name = rs.getString("name");
                    String model = rs.getString("model");
                    String partNum = rs.getString("partNum");
                    int qty = rs.getInt("qty");
                    String revision = rs.getString("revision");

                    Manual result = new Manual(name, model, partNum, qty, revision);
                    manuals.add(result);
                    
                    
                }
                
                return manuals;

            }
        }
    }


//Name, model, and partnumber general
//qty, this, next shared with non_serial/Consumable
//Manual needs qty and rev
//Serial needs serial




public void createNonSerialized(Non_Serialized n)
    {
        /**************************************************
         * SQL Query to insert the item into the database:
         * INSERT INTO non_serialized(name, model, partNum, qty, qty_semester, qty_next_semester, alert)
         * VALUES (n.name, n.model, n.partNum, n.qty, n.qty_semester, n.qty_next_semester, n.alert);

        */ 
        

        
    }


public void serialized_object_to_database(Serialized s)
{
        /**************************************************
         * SQL Query to insert the item into the database:
         * INSERT INTO non_serialized(name, model, partNum, qty, qty_semester, qty_next_semester, serialNum, alert)
         * VALUES (s.name, s.model, s.partNum, s.qty, s.qty_semester, s.qty_next_semester, s.serialNum, s.alert);

        */
        

}

    public void createConsumable(Consumable c)
    {
        /**************************************************
         * SQL Query to insert the item into the database:
         * INSERT INTO non_serialized(name, model, partNum, qty, qty_semester, qty_next_semester, qtyType, alert)
         * VALUES (c.name, c.model, c.partNum, c.qty, c.qty_semester, c.qty_next_semester, c.qtyType, c.alert);

        */ 
        

        
    }

    public void createManual(Manual m)
    {
        /**************************************************
         * SQL Query to insert the item into the database:
         * INSERT INTO non_serialized(name, model, partNum, qty, qty_semester, qty_next_semester, revision, alert)
         * VALUES (m.name, m.model, m.partNum, m.qty, m.qty_semester, m.qty_next_semester, m.revision, m.alert);

        */ 
        

        
    }

    public void changeGlobal(String column, String edit, int id){
        try{
            String sql = "UPDATE items SET " + column + " = ? WHERE id = ?";
            Connection conn = getConnection();
            System.out.println(conn.getCatalog());
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, edit);
            stmt.setInt(2,id);
            int rows = stmt.executeUpdate();
            System.out.println("Rows affected: " + rows);
            if(rows > 0){
                System.out.println("Item updated!");
            }else{
                System.out.println("Error");
            }
            stmt.close();
            conn.close();
        }catch(Exception e){
        e.printStackTrace();
        }
    }

    public void changeQty(String column, Double edit, int id){
        try{
            String sql = "UPDATE items SET " + column + " = ? WHERE id = ?";
            Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setDouble(1, edit);
            stmt.setInt(2,id);
            int rows = stmt.executeUpdate();
            if(rows > 0){
                System.out.println("Item updated!");
            }else{
                System.out.println("Error");
            }
            stmt.close();
            conn.close();
        }catch(Exception e){
        e.printStackTrace();
        }
    }
    
}