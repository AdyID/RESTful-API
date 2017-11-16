/**
 * Created by aezpr on 11/15/2017.
 */
import endpoints.Banner;
import endpoints.Campaign;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static spark.Spark.*;

public class Main {
    public static void main(String[] args) {
        Campaign campaign = new Campaign();
        get("/campaign/:id", (req, res) -> {
            final int i = Integer.parseInt(req.params(":id"));
            campaign.setId(i);
            System.out.println(i);
            if(validate("campaign",i)==true)
            return "THE CAMPAIGN WITH NUMBER: "+i+" EXISTS!";
            else
                return "The Campaign does not exist";

        });
        Banner banner = new Banner();
        get("/banner/:id", (req, res) -> {
            final int i = Integer.parseInt(req.params(":id"));
            banner.setId(i);
            if(validate("banner",i)==true) {
                return "THE BANNER WITH NUMBER: " + i + " EXISTS!";
            }
            else
                return "The Banner does not exist";
        });
    }

    public static boolean validate(String type, int id){
        Connection con = null;
        String sel= null;
        if(type.equals("campaign")){
         sel= "SELECT id FROM densouadform.campaign WHERE id =?";
        }
        else if(type.equals("banner")){
            sel= "SELECT id FROM densouadform.banner WHERE id =?";
        }

        PreparedStatement ps = null;
        try{
            con = ConnectionManager.getConnection();
            ps = con.prepareStatement(sel);
            ps.setInt(1,id);
            ResultSet resultSet = ps.executeQuery();
            if(resultSet.next()){
                ConnectionManager.closeConnection(con);
                return true;
            }
        }catch (Exception e){
            e.getMessage();
        }finally {
            ConnectionManager.closeConnection(con);
        }
        return false;
    }
}
