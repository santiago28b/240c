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
  private AuthDao authDao;

  public UserService(MemoryUserDao user, AuthDao token){
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
      userDao.getUser(user.username());
      String token = authDao.createAuth(user);
      return new AuthData(token,user.username());
    }catch (DataAccessException e){
      throw new RuntimeException(e.getMessage());
    }
  }
  public void logout(UserData user) {}

  public void clearData() {
    userDao.clear();
    authDao.clear();
  }
}
