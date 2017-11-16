/**
 * Created by aezpr on 11/15/2017.
 */
import endpoints.Banner;
import endpoints.Campaign;

import static spark.Spark.*;

public class Main {
    public static void main(String[] args) {
        Campaign campaign = new Campaign();
        get("/campaign/:id", (req, res) -> {
            final int i = Integer.parseInt(req.params(":id"));
            campaign.setId(i);
            System.out.println(i);
            return "Campaign ID is: "+ campaign.getId();

        });
        Banner banner = new Banner();
        get("/banner/:id", (req, res) -> {
            final int i = Integer.parseInt(req.params(":id"));
            banner.setId(i);
            System.out.println(i);
            return "Banner ID is: "+ banner.getId();
        });
    }
}