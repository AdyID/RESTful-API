import static spark.Spark.*;

public class Main {
    public static void main(String[] args) {
<<<<<<< HEAD
        get("/hello", (req, res) -> "Hello");
        System.out.println("hy");
=======
        get("/campaign/:id", (req, res) ->{
            return "";
        });

>>>>>>> origin/master
    }
}