import java.io.File;
import java.sql.*;
//import java.io.File;


public class ItemDatabase {

    public static void main(String[] args) {
        File certFile = new File("ca.pem");
        System.out.println("Absolute path: " + certFile.getAbsolutePath());
        System.out.println("Exists? " + certFile.exists());

        String path = certFile.getAbsolutePath();

        //File f = new File("/workspaces/my-java-project/ca.pem");
        // System.out.println("Exists? " + f.exists());
        //String path = new File("ca.pem").getAbsolutePath();

        String url = "jdbc:mysql://ams-ims-db-amsims.i.aivencloud.com:24455/AMS-IMS"
           + "?sslMode=REQUIRED"
           + "&sslCa=" + path;

        /*String url = "jdbc:mysql://ams-ims-db-amsims.i.aivencloud.com:24455/defaultdb"
           + "?sslMode=VERIFY_CA"
           + "&sslCa=/workspaces/AMS_IMS/ca.pem";*/
        String user = "avnadmin";
        String password = "AVNS_gYcciwH3FiHQ6J6WzGi";
        
       try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, user, password);
            Statement statement = conn.createStatement();

            //test
            ResultSet resultSet = statement.executeQuery("SHOW TABLES");

            while(resultSet.next())
            {
                //System.out.println(resultSet.getInt(1)+" "+resultSet.getString(2)+" "+resultSet.getString(3)+resultSet.getInt(4));
                System.out.println("Table found: " + resultSet.getString(1));


            }

            System.out.println("Done iterating.");

            System.out.println("Connected successfully!");
            conn.close();
        } catch (Exception e) {
            System.out.println("Connection failed!");
            e.printStackTrace();
        }
        
    } // end main

    public void non_serialized_object_to_database(Non_Serialized n)
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

