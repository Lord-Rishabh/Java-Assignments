package assignment;

import static assignment.config.Constants.ARGS_LENGTH;

import assignment.controller.ItemIoHandler;
import assignment.models.ItemType;
import assignment.models.tax.TaxCalculatorRegistry;
import assignment.models.tax.types.ImportedTaxCalculator;
import assignment.models.tax.types.ManufacturedTaxCalculator;
import assignment.models.tax.types.RawTaxCalculator;

public class Main {

  /**
   * The program starts from the main method.
   * @param args command line arguments passed to the program
   */
  public static void main(String[] args) {

    ItemIoHandler itemHandler = new ItemIoHandler();
    addItemTypesToRegistry();

    if (args.length ==  ARGS_LENGTH) {
      itemHandler.addItem(args);
    }

    while (itemHandler.userWantsToAddItem()) {
      itemHandler.getInputFromUserAndAddItem();
    }

    itemHandler.calculateTaxAndFinalPrice();
    itemHandler.printAllItems();
  }

  /**
   * Adds predefined tax calculators for different item types to the TaxCalculatorRegistry.
   * This method registers specific tax calculators for each item type, allowing the
     calculation of taxes based on the type of item.
   */
  public static void addItemTypesToRegistry() {
    TaxCalculatorRegistry.setRegistry(ItemType.RAW, new RawTaxCalculator());
    TaxCalculatorRegistry.setRegistry(ItemType.MANUFACTURED, new ManufacturedTaxCalculator());
    TaxCalculatorRegistry.setRegistry(ItemType.IMPORTED, new ImportedTaxCalculator());
  }
}