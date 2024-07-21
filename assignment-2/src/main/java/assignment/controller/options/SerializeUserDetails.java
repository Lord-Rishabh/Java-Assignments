package assignment.controller.options;

import assignment.controller.UserController;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

/**
 * Class for serializing user details to a file.
 */
public class SerializeUserDetails {

  /**
   * Prompts the user to save user details and performs the save operation if confirmed.
   *
   * @param userController the controller managing user details
   */
  public static void option(UserController userController) {
    Scanner scan = new Scanner(System.in);
    System.out.println("Do you want to save User Details (y/n)? : ");
    String userInput = scan.next();
    if (userInput.equalsIgnoreCase("y") || userInput.equalsIgnoreCase("yes")) {
      saveUserDetail(userController);
    }
  }

  /**
   * Saves the user details to a file.
   *
   * @param userController the controller managing user details
   */
  public static void saveUserDetail(UserController userController) {
    String filename = "file.ser";
    try {
      FileOutputStream file = new FileOutputStream(filename);
      ObjectOutputStream out = new ObjectOutputStream(file);

      out.writeObject(userController);

      out.close();
      file.close();

      System.out.println("User Details has been saved.");

    } catch (Exception e) {
      System.err.println("IOException is caught");
    }
  }
}
