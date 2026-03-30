import java.io.File;
import java.sql.*;
//import java.io.File;


public class ItemDatabase {

    public static void main(String[] args) {

        //File f = new File("/workspaces/my-java-project/ca.pem");
        // System.out.println("Exists? " + f.exists());
        String path = new File("ca.pem").getAbsolutePath();

        String url = "jdbc:mysql://ams-ims-db-amsims.i.aivencloud.com:24455/ams-ims-db"
           + "?sslMode=VERIFY_CA"
           + "&sslCa=" + path;

        /*String url = "jdbc:mysql://ams-ims-db-amsims.i.aivencloud.com:24455/ams-ims-db"
           + "?sslMode=VERIFY_CA"
           + "&sslCa=/workspaces/AMS_IMS/ca.pem";*/
        String user = "avnadmin";
        String password = "AVNS_gYcciwH3FiHQ6J6WzGi";
        
       try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected successfully!");
            conn.close();
        } catch (Exception e) {
            System.out.println("Connection failed!");
            e.printStackTrace();
        }
        
    } // end main

    /*public void non_serialized_object_to_database(Non_Serialized n)
    {
        

        
    }*/


}

