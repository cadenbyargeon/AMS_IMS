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
                    int id = rs.getInt("id");

                    Non_Serialized result = new Non_Serialized(itemName, model, partNum, qty, qty_semester, qty_next_semester);
                    result.setID(id);
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
                    int id = rs.getInt("id");

                    Serialized result = new Serialized(itemName, model, partNum, serialNum);
                    result.setID(id);
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
                    int id = rs.getInt("id");

                    Consumable result = new Consumable(itemName, model, partNum, qty, qty_semester, qty_next_semester, qtyType);
                    result.setID(id);
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
                    int id = rs.getInt("id");

                    Manual result = new Manual(name, model, partNum, qty, revision);
                    result.setID(id);
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




public void createNonSerialized(Non_Serialized n) throws SQLException, ClassNotFoundException
    {
         String sql = "INSERT INTO non_serialized(name, model, partNum, qty, qty_semester, qty_next_semester)" + 
         "VALUES (?, ?, ?, ?, ?, ?)";

         try(Connection conn = getConnection();
         PreparedStatement ps = conn.prepareStatement(sql))
         {
            ps.setString(1, n.getName());
            ps.setString(2, n.getModel());
            ps.setString(3, n.getPartNum());
            ps.setDouble(4, n.getQty());
            ps.setDouble(5, n.getQtySemester());
            ps.setDouble(6, n.getQtyNextSem());

            ps.executeUpdate();



         }

         



         
        

        
    }


public void createSerialized(Serialized s) throws SQLException, ClassNotFoundException
{
        String sql = "INSERT INTO serialized(name, model, partNum, serialNum)" + 
         "VALUES (?, ?, ?, ?)";

         try(Connection conn = getConnection();
         PreparedStatement ps = conn.prepareStatement(sql))
         {
            ps.setString(1, s.getName());
            ps.setString(2, s.getModel());
            ps.setString(3, s.getPartNum());
            ps.setString(4, s.getSerialNum());
            
            ps.executeUpdate();



         }

         


        

}

    public void createConsumable(Consumable c) throws SQLException, ClassNotFoundException
    {
        String sql = "INSERT INTO non_serialized(name, model, partNum, qty, qty_semester, qty_next_semester, qtyType)" + 
         "VALUES (?, ?, ?, ?, ?, ?, ?)";

         try(Connection conn = getConnection();
         PreparedStatement ps = conn.prepareStatement(sql))
         {
            ps.setString(1, c.getName());
            ps.setString(2, c.getModel());
            ps.setString(3, c.getPartNum());
            ps.setDouble(4, c.getQty());
            ps.setDouble(5, c.getQtySemester());
            ps.setDouble(6, c.getQtyNextSem());
            ps.setString(7, c.getQtyType());

            ps.executeUpdate();



         }


        
        

        
    }

    public void createManual(Manual m) throws SQLException, ClassNotFoundException
    {
         String sql = "INSERT INTO non_serialized(name, model, partNum, qty, qty_semester, qty_next_semester, qtyType)" + 
         "VALUES (?, ?, ?, ?, ?)";

         try(Connection conn = getConnection();
         PreparedStatement ps = conn.prepareStatement(sql))
         {
            ps.setString(1, m.getName());
            ps.setString(2, m.getModel());
            ps.setString(3, m.getPartNum());
            ps.setDouble(4, m.getQty());
            ps.setString(5, m.getRevision());

            ps.executeUpdate();



         }


        
        

        
    }

    public void changeGlobal(String column, String edit, int id, String type){
        try{
            String sql = "UPDATE " + type + " SET " + column + " = ? WHERE id = ?";
            Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, edit);
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

    public void changeQty(String column, Double edit, int id, String type){
        try{
            String sql = "UPDATE " + type + " SET " + column + " = ? WHERE id = ?";
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