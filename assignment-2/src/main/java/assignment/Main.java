package assignment;

import assignment.controller.MenuController;

public class Main {

  /**
   * Main method to start the application.
   *
   * @param args command line arguments
   */
  public static void main(String[] args) {

    MenuController menuController = new MenuController();
    while (true) {
      menuController.showMenu();
      menuController.takeInputAndGoToOption();
    }
  }
}