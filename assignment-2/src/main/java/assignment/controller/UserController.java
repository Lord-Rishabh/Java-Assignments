package assignment.controller;

import assignment.models.User;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;


/**
 * Controller class for managing users.
 */
public class UserController implements java.io.Serializable {

  private final Comparator<User> userComparator = (u1, u2) -> {
    int nameComparison = u1.getFullName().compareTo(u2.getFullName());
    if (nameComparison != 0) {
      return nameComparison;
    } else {
      return u1.getRollNumber().compareTo(u2.getRollNumber());
    }
  };

  private final Set<User> users = new TreeSet<>(userComparator);

  /**
   * Adds a user to the collection.
   * @param user the user to be added
   */
  public void addUser(User user) {
    users.add(user);
  }

  /**
   * Checks if a user exists in the collection.
   * @param rollNumber the roll number of the user
   * @return true if the user exists, otherwise false
   */
  public Boolean checkUser(String rollNumber) {
    return users.stream().anyMatch(user -> user.getRollNumber().equals(rollNumber));
  }

  /**
   * Deletes a user from the collection.
   * @param rollNumber the roll number of the user to be deleted
   */
  public void deleteUser(String rollNumber) {
    users.removeIf(user -> user.getRollNumber().equals(rollNumber));
  }

  /**
   * Gets the list of all users.
   * @return a list of users
   */
  public List<User> getUserList() {
    return new ArrayList<>(users);
  }
}
