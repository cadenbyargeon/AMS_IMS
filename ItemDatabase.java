import java.sql.*;

public class ItemDatabase {

    public static void main(String[] args) {
        String url = "jdbc:mysql://ams-ims-db-amsims.i.aivencloud.com:24455/ams-ims-db"
           + "?sslMode=VERIFY_CA"
           + "&sslCa=/workspaces/AMS_IMS/ca.pem";
        String user = "avnadmin";
        String password = "AVNS_gYcciwH3FiHQ6J6WzGi";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection(url, user, password);
                        System.out.println("Connected successfully!");



            connection.close();
        } 
        catch (Exception e) {
            System.out.println(e);
        }
        
    } // end main

    /*public void non_serialized_object_to_database(Non_Serialized n)
    {
        

        
    }*/


}

