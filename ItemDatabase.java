import java.sql.*;

public class ItemDatabase {

    public static void main(String[] args) {
        String url = "jdbc:mysql://avnadmin:AVNS_gYcciwH3FiHQ6J6WzGi@ams-ims-db-amsims.i.aivencloud.com:24455/defaultdb?ssl-mode=REQUIRED";
        String user = "avnadmin";
        String password = "AVNS_gYcciwH3FiHQ6J6WzGi";

        try {
            //Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();


            connection.close();
        } 
        catch (Exception e) {
            System.out.println(e);
        }
        
    } // end main

    public void non_serialized_object_to_database(Non_Serialized n)
    {
        

        
    }


}

