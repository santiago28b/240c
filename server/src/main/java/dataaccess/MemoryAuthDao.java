package dataaccess;

import model.AuthData;
import model.UserData;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MemoryAuthDao implements AuthDao{

  private Map<String, AuthData> authListMemory = new HashMap<>();


  @Override
  public void clear() {

    authListMemory.clear();

  }

  @Override
  public String createAuth(UserData user) {
    UUID code = UUID.randomUUID();
    AuthData authData = new AuthData(code.toString(),user.username());
    authListMemory.put(user.username(),authData);
    return code.toString();
  }

  @Override
  public AuthData getAuth(AuthData token) {
    return authListMemory.get(token);
  }

  @Override
  public void deleteAuth() {

  }
}
