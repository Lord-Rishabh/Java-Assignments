package assignment;

import assignment.controller.MenuController;

public class Main {
  public static void main(String[] args) {

    MenuController menuController = new MenuController();
    while (true) {
      menuController.showMenu();
      menuController.takeInputAndGoToOption();
    }
  }
}