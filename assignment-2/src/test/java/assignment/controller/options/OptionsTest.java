package assignment.controller.options;

import assignment.controller.UserController;
import assignment.models.User;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * Test class for options handling in the user controller.
 */
public class OptionsTest {

  private final Set<Character> courses = new HashSet<>();
  private static final UserController userController = new UserController();

  /**
   * Sets up test data for courses before each test.
   */
  @BeforeEach
  public void setCourses() {
    courses.add('A');
    courses.add('B');
    courses.add('C');
    courses.add('D');
  }

  /**
   * Tests adding user details and displaying user list.
   */
  @Test
  public void addUserDetails() {
    User user = new User("Rishabh", 21, "Address2", "002", courses);
    AddUserDetails.addUser(userController, user);
    DisplayUserDetails.sortAndPrintUserList(userController, "name");
  }

  /**
   * Tests sorting and displaying user details by various criteria.
   */
  @Test
  public void sortAndDisplayUserDetails() {
    User user1 = new User("Alice", 27, "Address1", "005", courses);
    User user2 = new User("Rishabh", 21, "Address2", "002", courses);
    User user3 = new User("Soumil", 23, "Address3", "003", courses);

    AddUserDetails.addUser(userController, user1);
    AddUserDetails.addUser(userController, user2);
    AddUserDetails.addUser(userController, user3);

    DisplayUserDetails.sortAndPrintUserList(userController, "name");
    DisplayUserDetails.sortAndPrintUserList(userController, "rollNumber");
    DisplayUserDetails.sortAndPrintUserList(userController, "age");
    DisplayUserDetails.sortAndPrintUserList(userController, "address");
  }

  /**
   * Tests deleting user details and verifying the deletion.
   */
  @Test
  public void deleteUserDetails() {
    User user1 = new User("Alice", 27, "Address1", "005", courses);
    User user2 = new User("Rishabh", 21, "Address2", "002", courses);

    AddUserDetails.addUser(userController, user1);
    AddUserDetails.addUser(userController, user2);

    DisplayUserDetails.sortAndPrintUserList(userController, "name");
    DeleteUserDetails.deleteUser(userController, "005");
    DisplayUserDetails.sortAndPrintUserList(userController, "name");
  }

  /**
   * Tests saving user details to a file.
   */
  @Test
  public void saveUserDetails() {
    User user1 = new User("Rishabh", 21, "Address2", "002", courses);
    User user2 = new User("Soumil", 23, "Address3", "003", courses);

    AddUserDetails.addUser(userController, user1);
    AddUserDetails.addUser(userController, user2);

    SerializeUserDetails.saveUserDetail(userController);
  }
}
