package assignment.controller;

import assignment.controller.options.AddUserDetails;
import assignment.controller.options.DeleteUserDetails;
import assignment.controller.options.DisplayUserDetails;
import assignment.controller.options.SerializeUserDetails;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.Scanner;

/**
 * Controller class for managing the menu and user options.
 */
public class MenuController {

  private static UserController userController = new UserController();

  /**
   * Constructs a MenuController and attempts to load previously saved user data.
   */
  public MenuController() {
    userController = SerializeUserDetails.getSavedUserDetails();
  }

  /**
   * Displays the menu options to the user.
   */
  public void showMenu() {
    System.out.println("Choose any option : ");
    System.out.println("1. Add User details.");
    System.out.println("2. Display User details.");
    System.out.println("3. Delete User details.");
    System.out.println("4. Save User details.");
    System.out.println("5. Exit");
  }

  /**
   * Takes user input and directs to the appropriate option.
   */
  public void takeInputAndGoToOption() {
    Scanner scan = new Scanner(System.in);
    System.out.print("Enter the chosen option here : ");
    int option = scan.nextInt();
    goToOption(option);
  }

  /**
   * Processes the chosen option and performs the corresponding action.
   *
   * @param option the option chosen by the user
   */
  private void goToOption(int option) {
    switch (option) {
      case 1:
        AddUserDetails.option(userController);
        break;
      case 2:
        DisplayUserDetails.option(userController);
        break;
      case 3:
        DeleteUserDetails.option(userController);
        break;
      case 4:
        SerializeUserDetails.option(userController);
        break;
      case 5:
        SerializeUserDetails.option(userController);
        System.exit(0);
        break;
      default:
        System.err.println("Please choose a correct option.");
    }
    System.out.println();
  }
}
