package assignment.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Manages the menu options and user interactions.
 */
public class MenuController {

  private final Map<Integer, Runnable> menuOptions = new HashMap<>();

  /**
   * Constructs a MenuController and initializes menu options.
   */
  public MenuController() {
    NodeController nodeController = new NodeController();
    AddMenuOptions.addOptions(menuOptions, nodeController);
  }

  /**
   * Displays the menu options to the user.
   */
  public void showMenu() {
    System.out.println("Choose any option:");
    System.out.println("1. Get Parents.");
    System.out.println("2. Get Children.");
    System.out.println("3. Get Ancestors.");
    System.out.println("4. Get Descendants.");
    System.out.println("5. Delete Dependency.");
    System.out.println("6. Delete Node.");
    System.out.println("7. Add Dependency.");
    System.out.println("8. Add New Node.");
    System.out.println("9. Exit");
  }

  /**
   * Takes user input and directs to the appropriate option.
   */
  public void takeInputAndGoToOption() {
    Scanner scan = new Scanner(System.in);
    System.out.print("Enter the chosen option here: ");
    String optionString = scan.nextLine();
    int option;

    try {
      option = Integer.parseInt(optionString);
      goToOption(option);
    } catch (NumberFormatException e) {
      System.err.println("Please enter a valid option.");
    }
  }

  /**
   * Processes the chosen option and performs the corresponding action.
   * @param option the option chosen by the user
   */
  private void goToOption(int option) {
    Runnable action = menuOptions.get(option);
    if (action != null) {
      action.run();
    } else {
      System.err.println("Please choose a correct option.");
    }
    System.out.println();
  }
}
