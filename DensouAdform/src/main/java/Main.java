import static spark.Spark.*;

public class Main {
    public static void main(String[] args) {
        get("/hello", (req, res) -> "This is my hell0o message lets prepare for coding");
    }
}