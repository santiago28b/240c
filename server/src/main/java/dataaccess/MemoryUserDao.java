package dataaccess;

import model.UserData;

import java.util.HashMap;
import java.util.Map;

public class MemoryUserDao implements UserDao{
  private Map<String, UserData> userListMemory = new HashMap<>();

  @Override
  public void clear() {

  }

  @Override
  public UserData getUser(String username) throws DataAccessException {
    if(userListMemory.get(username) == null){
      throw  new DataAccessException("Error: unauthorized");
    }
    return userListMemory.get(username);
  }

  @Override
  public void CreateUser(UserData user) throws DataAccessException {
    if(userListMemory.containsValue(user)){
      throw  new DataAccessException("Error: already taken");
    }else if(user.username().isEmpty()|| user.password().isEmpty()||user.email().isEmpty()){
      throw new DataAccessException("Error: bad request");
    }
    userListMemory.put(user.username(),user);
  }

}
