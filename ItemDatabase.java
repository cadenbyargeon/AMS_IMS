import java.sql.*;

public class ItemDatabase {

    public static void main(String[] args) {
        String url = "";
        String user = "";
        String password = "";

        try {
            //Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from personInfo");

            while(resultSet.next())
            {
                System.out.println(resultSet.getInt(1)+" "+resultSet.getString(2)+" "+resultSet.getString(3)+" "+resultSet.getInt(4));


            }

            connection.close();
        } 
        catch (Exception e) {
            System.out.println(e);
        }
        
    }
}

