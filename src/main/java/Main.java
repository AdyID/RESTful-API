/**
 * Created by aezpr on 11/15/2017.
 */
import endpoints.Banner;
import endpoints.Campaign;
import spark.ModelAndView;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import static spark.Spark.*;

public class Main {
    public static void main(String[] args) {

        delete("/campaign/:id", (request, response) -> {
            final int a = Integer.parseInt(request.params(":id"));
            System.out.println(a);
            if(deleted("campaign",a)==true)
                return "Campaign " + a + " was deleted!";
            return "" ;
        });
        get("/campaign/:id", (req, res) -> {
            Campaign campaign = new Campaign();
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


        delete("/banner/:id", (request, response) -> {
            final int a = Integer.parseInt(request.params(":id"));
            System.out.println(a);
            if(deleted("banner",a)==true)
                return "Banner " + a + " was deleted!";
            return "" ;
        });
        get("/banner/:id", (req, res) -> {
            Banner banner = new Banner();
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

        post("/campaign/:id/:name/:banner/:click/:impression",(req,res)->{
           Campaign campaign = new Campaign();
           final int id = Integer.parseInt(req.params(":id"));
           final String name = req.params(":name");
           final String bann = req.params(":banner");
           final int click = Integer.parseInt(req.params(":click"));
           final int imp = Integer.parseInt(req.params(":impression"));

           campaign.setId(id);
           campaign.setName(name);
           campaign.setBanners(bann);
           campaign.setClicks(click);
           campaign.setImpressions(imp);

           if(createdCamp(id,name,bann,click,imp)==true){
               return "Campaign added succesfull";
           }
           return "Theres been an error while trying to create the Campaign";

        });

        post("/banner/:id/:creative/:click/:impression",(req,res)->{
            Banner banner = new Banner();
            final int id = Integer.parseInt(req.params(":id"));
            final String creative = req.params(":creative");
            final int click = Integer.parseInt(req.params(":click"));
            final int imp = Integer.parseInt(req.params(":impression"));

            banner.setId(id);
            banner.setCreative(creative);
            banner.setClicks(click);
            banner.setImpressions(imp);

            if(createdBanner(id,creative,click,imp)==true){
                return "Banner added succesfull";
            }
            return "Theres been an error while trying to create the Banner";

        });




    }

    public static boolean validate(String type, int id){
        Connection con = null;
        String sel= null;
        if(type.equals("campaign")){
         sel= "SELECT * FROM densouadform.campaign WHERE id =?";
        }
        else if(type.equals("banner")){
            sel= "SELECT * FROM densouadform.banner WHERE id =?";
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

    public static boolean deleted(String type,int id){
        Connection con = null;
        String sel= null;
        if(type.equals("campaign")){
            sel= "DELETE FROM densouadform.campaign WHERE id =?";
        }
        else if(type.equals("banner")){
            sel= "DELETE FROM densouadform.banner WHERE id =?";
        }

        PreparedStatement ps = null;
        try{
            con = ConnectionManager.getConnection();
            ps = con.prepareStatement(sel);
            ps.setInt(1,id);
            ps.execute();
            ps.close();
            return true;
        }catch (Exception e){
            e.getMessage();
        }finally {
            ConnectionManager.closeConnection(con);
        }
        return false;
    }

    public static boolean createdCamp(int id,String name,String banner, int click, int impr){


        Connection con = null;
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date utilDate = new Date();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        String sel= "INSERT INTO densouadform.campaign (id,name,banner,created,clicks,impressions) VALUES(?,?,?,?,?,?)";

        PreparedStatement ps =null;
        try{
            con= ConnectionManager.getConnection();
            ps = con.prepareStatement(sel);
            ps.setInt(1,id);
            ps.setString(2,name);
            ps.setString(3,banner);
            ps.setDate(4, sqlDate);
            ps.setInt(5,click);
            ps.setInt(6,impr);
            ps.executeUpdate();
            ps.close();
            return true;
        }catch (Exception e){
            e.getMessage();
        }finally {
            ConnectionManager.closeConnection(con);
        }
        return false;
    }
    public static boolean createdBanner(int id,String creative,int click,int impr){

        Connection con = null;
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date utilDate = new Date();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        String sel= "INSERT INTO densouadform.banner (id,creative,created,clicks,impressions) VALUES(?,?,?,?,?)";

        PreparedStatement ps =null;
        try{
            con= ConnectionManager.getConnection();
            ps = con.prepareStatement(sel);
            ps.setInt(1,id);
            ps.setString(2,creative);
            ps.setDate(3, sqlDate);
            ps.setInt(4,click);
            ps.setInt(5,impr);
            ps.executeUpdate();
            ps.close();
            return true;
        }catch (Exception e){
            e.getMessage();
        }finally {
            ConnectionManager.closeConnection(con);
        }
        return false;
    }
}
