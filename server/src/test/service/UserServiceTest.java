package service;

import dataaccess.AuthDao;
import dataaccess.MemoryAuthDao;
import dataaccess.MemoryUserDao;
import model.AuthData;
import model.UserData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

  private UserService userService;
  private MemoryUserDao userDao;
  private MemoryAuthDao authDao;

  @BeforeEach
  void setUp() {
    userDao = new MemoryUserDao();
    authDao = new MemoryAuthDao();
    userService = new UserService(userDao,authDao);
  }

  @Test
  void register() {
    UserData user = new UserData("testuser", "password123", "test@example.com");
    AuthData authData = userService.register(user);

    assertNotNull(authData);
    assertEquals("testuser", authData.username());
  }

  @Test
  void registerDuplicateUserThrowsException() {
    UserData user = new UserData("testuser", "password123", "test@example.com");
    userService.register(user);

    UserData duplicateUser = new UserData("testuser", "password123", "test@example.com");

    Exception exception = assertThrows(RuntimeException.class, () -> {
      userService.register(duplicateUser);
    });

    assertEquals("Error: already taken", exception.getMessage());
  }

  @Test
  void login() {
  }

  @Test
  void logout() {
  }
}