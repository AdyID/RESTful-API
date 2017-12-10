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
            if(validate("campaign",i)==true){

            return "The campaign with number: "+i+" exists!  " +
                    "\nID: " + campaign.getId() + ", " +
                    "\nClicks: " + campaign.getClicks() + ", " +
                    "\nImpressions: " + campaign.getImpressions() + ", " +
                    "\nDate of creation: " + campaign.getCreated();
            }
            else
                return "The Campaign does not exist";

            //http://localhost:4567/campaign/4 this is the way to write it in the address bar
        });

        delete("/campaign/:id/", (request, response) -> {
            final int a = Integer.parseInt(request.params(":id"));
              campaign.setId(a);
            System.out.println(a);
            if(validate("campaign",a)==true)
            return "Campaign " + a + "was deleted!";
            return "" ;
        });



        Banner banner = new Banner();
        get("/banner/:id", (req, res) -> {
            final int i = Integer.parseInt(req.params(":id"));
            banner.setId(i);
            if(validate("banner",i)==true) {
                return "The banner with id: " + i + " exists! "
                        + " ID: " +banner.getId()+
                        " URL: " +banner.getCreative()+"    "+
                        " Created: " + banner.getCreated()+
                        " Clicks: " + banner.getClicks()+
                        " Impressions: " + banner.getImpressions();
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
