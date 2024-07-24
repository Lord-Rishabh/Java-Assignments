  package assignment.controller;

  import java.util.HashMap;
  import java.util.Map;
  import java.util.Scanner;

  public class MenuController {

    Map<Integer, Runnable> menuOptions = new HashMap<>();

    public MenuController() {
      NodeController nodeController = new NodeController();
      AddMenuOptions.addOptions(menuOptions, nodeController);
    }
    /**
     * Displays the menu options to the user.
     */
    public void showMenu() {
      System.out.println("Choose any option : ");
      System.out.println("1. Get the immediate parents of a node.");
      System.out.println("2. Get the immediate children of a node.");
      System.out.println("3. Get the ancestors of a node.");
      System.out.println("4. Get the descendants of a node.");
      System.out.println("5. Delete dependency from a tree.");
      System.out.println("6. Delete a node from a tree.");
      System.out.println("7. Add a new dependency to a tree.");
      System.out.println("8. Add a new node to tree.");
      System.out.println("9. Exit");
    }

    /**
     * Takes user input and directs to the appropriate option.
     */
    public void takeInputAndGoToOption() {
      Scanner scan = new Scanner(System.in);
      System.out.print("Enter the chosen option here : ");
      String optionString = scan.nextLine();
      int option;
      try {
        option = Integer.parseInt(optionString);
        goToOption(option);
      } catch (Exception e) {
        System.err.println("Please Enter a valid Option.");
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
