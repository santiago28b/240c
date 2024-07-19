package server;

import spark.*;

public class Server {

    public int run(int desiredPort) {
        Spark.port(desiredPort);

        Spark.staticFiles.location("web");

        // Register your endpoints and handle exceptions here.

        Spark.post("/user",this::registerUser);


                //start register endpoint here
        //see if I can convert to Json an object


        //This line initializes the server and can be removed once you have a functioning endpoint 
        Spark.init();

        Spark.awaitInitialization();
        return Spark.port();
    }

    private Object registerUser(Request request, Response response) {
        return null;
    }

    public void stop() {
        Spark.stop();
        Spark.awaitStop();
    }
}
