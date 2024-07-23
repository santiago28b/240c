package dataaccess;

import model.UserData;

public interface UserDao {



   void clear();

   UserData getUser(String user)throws DataAccessException;

  void CreateUser(UserData user)throws DataAccessException;


}
