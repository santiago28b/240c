package server;

import com.google.gson.Gson;
import model.UserData;
import spark.*;

import java.util.ArrayList;
import java.util.Map;
import service.UserService;
import dataaccess.MemoryUserDao;
import dataaccess.MemoryAuthDao;


public class Server {
    MemoryUserDao userDao = new MemoryUserDao();
    MemoryAuthDao authDao = new MemoryAuthDao();
    private UserService userService = new UserService(userDao,authDao);


  public int run(int desiredPort) {
        Spark.port(desiredPort);

        Spark.staticFiles.location("web");

        // Register your endpoints and handle exceptions here.

        Spark.post("/user",this::registerUser);
        Spark.post("/session",this::loginUser);
        Spark.delete("/db",this::clear);


                //start register endpoint here
        //see if I can convert to Json an object


        //This line initializes the server and can be removed once you have a functioning endpoint 
        //Spark.init();

        Spark.awaitInitialization();
        return Spark.port();
    }

  private Object clear(Request request, Response response) {
            try{
             userService.clearData();
              response.status(200);
              response.type("application/json");
              return new Gson().toJson(Map.of("status", "success"));
            } catch (RuntimeException e){
              response.status(500);
              response.type("application/json");
              return new Gson().toJson(Map.of("Message",e.getMessage()));
            }
  }

  private Object loginUser(Request request, Response response) {
        String body =request.body();
        UserData newUser = new Gson().fromJson(body, UserData.class);
            try{
                var authData = userService.login(newUser);
                response.status(200);
                response.type("application/json");
                return new Gson().toJson(authData);
            } catch (RuntimeException e){
                response.status(401);
                response.type("application/json");
                return new Gson().toJson(Map.of("message",e.getMessage()));
            }

    }

    private Object registerUser(Request request, Response response) {
        String body = request.body();
        UserData newUser = new Gson().fromJson(body, UserData.class);
        try{
            var authdata = userService.register(newUser);
            response.status(200);
            response.type("application/json");
            return new Gson().toJson(authdata);
        } catch (RuntimeException e){
            if(newUser.password() == null||newUser.email()== null){
                response.status(400);
            }else {
                response.status(403);
            }
            response.type("application/json");
            return new Gson().toJson(Map.of("message", e.getMessage()));
        }
    }

    public void stop() {
        Spark.stop();
        Spark.awaitStop();
    }
}
