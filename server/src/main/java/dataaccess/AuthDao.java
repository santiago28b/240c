package dataaccess;

import model.AuthData;
import model.UserData;

import java.util.HashSet;

public interface AuthDao {


  void clear();
  String createAuth(UserData user);

  AuthData getAuth(AuthData token);

  void deleteAuth();

}
