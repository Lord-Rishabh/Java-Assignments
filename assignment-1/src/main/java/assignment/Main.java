package assignment;

import static assignment.config.Constants.ARGS_LENGTH;

import assignment.controller.ItemIoHandler;

public class Main {

  /**
   * The program starts from the main method.
   * @param args command line arguments passed to the program
   */
  public static void main(String[] args) {

    ItemIoHandler itemHandler = new ItemIoHandler();

    if (args.length ==  ARGS_LENGTH) {
      itemHandler.addItem(args);
    }

    while (itemHandler.userWantsToAddItem()) {
      itemHandler.getInputFromUserAndAddItem();
    }

    itemHandler.calculateTaxAndFinalPrice();
    itemHandler.printAllItems();
  }
}