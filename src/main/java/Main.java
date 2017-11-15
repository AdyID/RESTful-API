/**
 * Created by aezpr on 11/15/2017.
 */
import static spark.Spark.*;

public class Main {
    public static void main(String[] args) {
        int verify = -1; // campaign => verify = 0; if banner => verify = 1;
        int id=0;
        get("/campaign/:id", (req, res) -> {
            return "Campaign ID is: "+ req.params(":id");
        });
        get("/banner/:id", (req, res) -> {
            return "Banner ID is: "+ req.params(":id");
        });
        System.out.println(verify);
        System.out.println(id);
    }
}