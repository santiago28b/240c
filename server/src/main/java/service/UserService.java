package service;

import dataaccess.AuthDao;
import dataaccess.DataAccessException;
import model.AuthData;
import model.UserData;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import dataaccess.MemoryUserDao;
import dataaccess.MemoryAuthDao;


public class UserService {

  private MemoryUserDao userDao;
  private MemoryAuthDao authDao;

  public UserService(MemoryUserDao user, MemoryAuthDao token){
    this.userDao = user;
    this.authDao = token;
  }
  public AuthData register(UserData user) {

      try{
        userDao.CreateUser(user);
        String token = authDao.createAuth(user);
        return new AuthData(token, user.username());
      } catch (DataAccessException e) {
        throw new RuntimeException(e.getMessage());
      }
  }

  public AuthData login(UserData user) {

    try{
      userDao.getData(user);
      String token = authDao.createAuth(user);
      return new AuthData(token,user.username());
    }catch (DataAccessException e){
      throw new RuntimeException(e.getMessage());
    }
  }

  public void logoutUser(String token) {
    try {
      authDao.deleteAuth(token);
    } catch (DataAccessException e){
      throw new RuntimeException(e.getMessage());
    }
  }


  public void clearData() {
    userDao.clear();
    authDao.clear();
  }


}
