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
    authListMemory.put(code.toString(),authData);
    return code.toString();
  }

  @Override
  public AuthData getAuth(String token) {

    return authListMemory.get(token);
  }

  @Override
  public void deleteAuth(String authToken) throws DataAccessException {
    AuthData storedToken = authListMemory.get(authToken);
    if(storedToken != null){
      authListMemory.remove(authToken);
    } else{
      throw new DataAccessException("Error: unauthorized");
    }

  }
}
