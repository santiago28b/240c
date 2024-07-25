package dataaccess;

import model.AuthData;
import model.UserData;

import java.util.HashSet;

public interface AuthDao {


  void clear();
  String createAuth(UserData user);

  public AuthData getAuth(String token);

  void deleteAuth(String authToken) throws DataAccessException;

}
