/**
 * Created by aezpr on 11/15/2017.
 */

import endpoints.Banner;
import endpoints.Campaign;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static spark.Spark.*;

public class Main {
    public static void main(String[] args) {

        staticFileLocation("/public");
        get("/", (req, res) -> {

            res.redirect("main.html");
            return null;
        });

        delete("/campaign/:id", (request, response) -> {
            final int a = Integer.parseInt(request.params(":id"));
            System.out.println(a);wa
            if (deleted("campaign", a) == true)
                return "Campaign " + a + " was deleted!";
            return "";
        });
        get("/campaign", (req, res) -> {
            Campaign campaign = new Campaign();
            final int i = Integer.parseInt(req.queryParams("id"));
            campaign.setId(i);
            System.out.println(i);
            Connection con = null;
            String sel = "SELECT * FROM densouadform.campaign WHERE id =?";
            Date date = null;
            PreparedStatement ps = null;
            try {
                con = ConnectionManager.getConnection();
                ps = con.prepareStatement(sel);
                ps.setInt(1, i);
                ResultSet resultSet = ps.executeQuery();
                while (resultSet.next()) {
                    campaign.setName(resultSet.getString("name"));
                    campaign.setBanners(resultSet.getString("banner"));
                    date = resultSet.getDate("created");
                    campaign.setClicks(resultSet.getInt("clicks"));
                    campaign.setImpressions(resultSet.getInt("impressions"));
                }
            } catch (Exception e) {
                e.getMessage();
            } finally {
                ConnectionManager.closeConnection(con);
            }
            if (campaign.getName() != null) {
                return "The campaign with number: " + i + " exists! </br> " +
                        "ID: " + campaign.getId() + "</br>" +
                        "Name: " + campaign.getName() + "</br>" +
                        "Banner link: " + campaign.getBanners() + "</br>" +
                        "Clicks: " + campaign.getClicks() + "</br>" +
                        "Impressions: " + campaign.getImpressions() + "</br>" +
                        "Date of creation: " + date;
            } else
                return "The Campaign does not exist";

            //http://localhost:4567/campaign/4 this is the way to write it in the address bar
        });


        delete("/banner/:id", (request, response) -> {
            final int a = Integer.parseInt(request.params(":id"));
            System.out.println(a);
            if (deleted("banner", a) == true)
                return "Banner " + a + " was deleted!";
            return "";
        });

        get("/banner", (req, res) -> {
            Banner banner = new Banner();
            Connection con = null;
            String sel = "SELECT * FROM densouadform.banner WHERE id =?";
            final int i = Integer.parseInt(req.queryParams("id"));
            banner.setId(i);
            Date date = null;
            PreparedStatement ps = null;
            try {
                con = ConnectionManager.getConnection();
                ps = con.prepareStatement(sel);
                ps.setInt(1, i);
                ResultSet resultSet = ps.executeQuery();
                while (resultSet.next()) {
                    banner.setCreative(resultSet.getString("creative"));
                    date = resultSet.getDate("created");
                    banner.setClicks(resultSet.getInt("clicks"));
                    banner.setImpressions(resultSet.getInt("impressions"));
                }
            } catch (Exception e) {
                e.getMessage();
            } finally {
                ConnectionManager.closeConnection(con);
            }
            if (banner.getCreative() != null) {
                return "The banner with id: " + i + " exists! </br>"
                        + "ID: " + banner.getId() + "</br>" +
                        " URL: " + banner.getCreative() + "</br>" +
                        " Created: " + date + "</br>" +
                        " Clicks: " + banner.getClicks() + "</br>" +
                        " Impressions: " + banner.getImpressions();
            } else
                return "The Banner does not exist";
        });

        post("/campaign", (req, res) -> {
            String dat = req.body();
            String[] data = dat.split("&");
            Campaign campaign = new Campaign();
            int id = Integer.parseInt(data[0].substring(3));
            String name = data[1].substring(5);
            String bann = data[2].substring(7);
            int click = Integer.parseInt(data[3].substring(6));
            int imp = Integer.parseInt(data[4].substring(11));
            System.out.println("works");
            campaign.setId(id);
            campaign.setName(name);
            campaign.setBanners(bann);
            campaign.setClicks(click);
            campaign.setImpressions(imp);

            if (createdCamp(id, name, bann, click, imp) == true) {
                return "Campaign added succesfull";
            }
            return "Theres been an error while trying to create the Campaign";

        });

        post("/banner", (req, res) -> {
            String dat = req.body();
            String[] data = dat.split("&");
            Banner banner = new Banner();
            final int id = Integer.parseInt(data[0].substring(3));
            final String creative = data[1].substring(9);
            final int click = Integer.parseInt(data[2].substring(6));
            final int imp = Integer.parseInt(data[3].substring(11));

            banner.setId(id);
            banner.setCreative(creative);
            banner.setClicks(click);
            banner.setImpressions(imp);

            if (createdBanner(id, creative, click, imp) == true) {
                return "Banner added succesfull";
            }
            return "Theres been an error while trying to create the Banner";

        });
    }

    public static boolean deleted(String type, int id) {
        Connection con = null;
        String sel = null;
        if (type.equals("campaign")) {
            sel = "DELETE FROM densouadform.campaign WHERE id =?";
        } else if (type.equals("banner")) {
            sel = "DELETE FROM densouadform.banner WHERE id =?";
        }

        PreparedStatement ps = null;
        try {
            con = ConnectionManager.getConnection();
            ps = con.prepareStatement(sel);
            ps.setInt(1, id);
            ps.execute();
            ps.close();
            return true;
        } catch (Exception e) {
            e.getMessage();
        } finally {
            ConnectionManager.closeConnection(con);
        }
        return false;
    }

    public static boolean createdCamp(int id, String name, String banner, int click, int impr) {


        Connection con = null;
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date utilDate = new Date();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        String sel = "INSERT INTO densouadform.campaign (id,name,banner,created,clicks,impressions) VALUES(?,?,?,?,?,?)";

        PreparedStatement ps = null;
        try {
            con = ConnectionManager.getConnection();
            ps = con.prepareStatement(sel);
            ps.setInt(1, id);
            ps.setString(2, name);
            ps.setString(3, banner);
            ps.setDate(4, sqlDate);
            ps.setInt(5, click);
            ps.setInt(6, impr);
            ps.executeUpdate();
            ps.close();
            return true;
        } catch (Exception e) {
            e.getMessage();
        } finally {
            ConnectionManager.closeConnection(con);
        }
        return false;
    }

    public static boolean createdBanner(int id, String creative, int click, int impr) {

        Connection con = null;
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date utilDate = new Date();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        String sel = "INSERT INTO densouadform.banner (id,creative,created,clicks,impressions) VALUES(?,?,?,?,?)";

        PreparedStatement ps = null;
        try {
            con = ConnectionManager.getConnection();
            ps = con.prepareStatement(sel);
            ps.setInt(1, id);
            ps.setString(2, creative);
            ps.setDate(3, sqlDate);
            ps.setInt(4, click);
            ps.setInt(5, impr);
            ps.executeUpdate();
            ps.close();
            return true;
        } catch (Exception e) {
            e.getMessage();
        } finally {
            ConnectionManager.closeConnection(con);
        }
        return false;
    }
}
