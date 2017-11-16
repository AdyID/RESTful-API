import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

/**
 * Created by aezpr on 11/16/2017.
 */
public class ConnectionManager {
    private static final String url ="jdbc:mysql://localhost:3306/densouadform";
    private static final String username = "root";
    private static final String password ="";
    private static final String driverName = "com.mysql.jdbc.Driver";
    private static Connection con;

    public static Connection getConnection(){
        try{
            Class.forName(driverName);
            try{
                con= DriverManager.getConnection(url,username,password);

            }catch (SQLException e){
                System.out.println("Failed to create the database connection");
            }
        }catch (ClassNotFoundException ex)
        {
           ex.printStackTrace();
        }
        return con;
    }

    public static void closeConnection(Connection con){
        try{
            con.close();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
