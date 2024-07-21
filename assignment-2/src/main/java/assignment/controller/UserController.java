package assignment.controller;

import assignment.models.User;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Controller class for managing users.
 */
public class UserController implements java.io.Serializable {

  private final Map<String, User> users = new HashMap<>();

  private final Comparator<User> userComparator = new Comparator<User>() {
    @Override
    public int compare(User u1, User u2) {
      int nameComparison = u1.getFullName().compareTo(u2.getFullName());
      if (nameComparison != 0) {
        return nameComparison;
      } else {
        return u1.getRollNumber().compareTo(u2.getRollNumber());
      }
    }
  };

  /**
   * Adds a user to the collection.
   *
   * @param rollNumber the roll number of the user
   * @param user       the user to be added
   */
  public void addUser(String rollNumber, User user) {
    users.put(rollNumber, user);
  }

  /**
   * Checks if a user exists in the collection.
   *
   * @param rollNumber the roll number of the user
   * @return true if the user exists, otherwise false
   */
  public Boolean checkUser(String rollNumber) {
    return users.containsKey(rollNumber);
  }

  /**
   * Deletes a user from the collection.
   *
   * @param rollNumber the roll number of the user to be deleted
   */
  public void deleteUser(String rollNumber) {
    users.remove(rollNumber);
  }

  /**
   * Gets the list of all users.
   *
   * @return a list of users
   */
  public List<User> getUserList() {
    return new ArrayList<>(users.values());
  }
}
