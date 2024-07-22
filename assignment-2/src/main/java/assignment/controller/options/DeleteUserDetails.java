package assignment.controller.options;

import assignment.controller.UserController;

import java.util.Scanner;

/**
 * Class for deleting user details.
 */
public class DeleteUserDetails {

  /**
   * Provides the option to delete a user based on roll number.
   * @param userController the controller managing user details
   */
  public static void option(UserController userController) {
    Scanner scan = new Scanner(System.in);
    System.out.print("Enter the Roll Number of the user you want to delete: ");
    String rollNumber = scan.next();
    deleteUser(userController, rollNumber);
  }

  /**
   * Deletes a user if the roll number is valid.
   * @param userController the controller managing user details
   * @param rollNumber     the roll number of the user to be deleted
   */
  public static void deleteUser(UserController userController, String rollNumber) {
    if (!userController.checkUser(rollNumber)) {
      System.err.println("Invalid Roll Number.");
      option(userController);
    } else {
      userController.deleteUser(rollNumber);
      System.out.println("User with Roll Number " + rollNumber + " has been deleted.");
    }
  }
}
