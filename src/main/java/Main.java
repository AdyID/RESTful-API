import static spark.Spark.*;

public class Main {
    public static void main(String[] args) {

        get("/campaign/:id", (req, res) ->{
            return "Hello";
        });
    }
}