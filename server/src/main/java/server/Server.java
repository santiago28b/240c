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


                //start register endpoint here
        //see if I can convert to Json an object


        //This line initializes the server and can be removed once you have a functioning endpoint 
        //Spark.init();

        Spark.awaitInitialization();
        return Spark.port();
    }

    private Object loginUser(Request request, Response response) {
        String body =request.body();
        UserData newUser = new Gson().fromJson(body, UserData.class);
            try{
                var authData = userService.login(newUser);
                response.status(200);
                response.type("application/json");
                return new Gson().toJson(Map.of("username:",authData.username(),"authToken:", authData.authToken()));
            } catch (RuntimeException e){
                response.status(401);
                response.type("application/json");
                return new Gson().toJson(Map.of("Message",e.getMessage()));
            }

    }

    private Object registerUser(Request request, Response response) {
        String body = request.body();
        UserData newUser = new Gson().fromJson(body, UserData.class);
        try{
            var authdata = userService.register(newUser);
            response.status(200);
            response.type("application/json");
            return new Gson().toJson(Map.of("username:",authdata.username(),"authToken:", authdata.authToken()));
        } catch (RuntimeException e){
            if(newUser.password().equals("")||newUser.email().equals("")){
                response.status(400);
            }else {
                response.status(403);
            }
            response.type("application/json");
            return new Gson().toJson(Map.of("Message", e.getMessage()));
        }
    }

//    private Object usersRegistered(Request req, Response res) {
//        res.type("application/json");
//        return new Gson().toJson(Map.of("users", users));
//    }

    public void stop() {
        Spark.stop();
        Spark.awaitStop();
    }
}
