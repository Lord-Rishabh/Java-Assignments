package assignment.models;

import assignment.models.tax.TaxCalculator;
import assignment.models.tax.TaxCalculatorRegistry;
import assignment.models.tax.types.ImportedTaxCalculator;
import assignment.models.tax.types.ManufacturedTaxCalculator;
import assignment.models.tax.types.RawTaxCalculator;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class Item {

  private String name;
  private double price;
  private int quantity;
  private ItemType type;
  private double tax;
  private double finalPrice;

  /**
   * Constructs an Item with the specified details.
   * @param name the name of the item
   * @param price the price of the item
   * @param quantity the quantity of the item
   * @param type the type of the item
   */
  public Item(String name, double price, int quantity, ItemType type) {
    this.name = name;
    this.price = price;
    this.quantity = quantity;
    this.type = type;
  }

  /**
   * Default constructor.
   */
  public Item() {
  }

  /**
   * Calculates the tax for the current item based on its type.
   * @param currItem the current item
   * @return the calculated tax
   */
  public double calculateTax(Item currItem) {
    addItemTypesToRegistry();
    TaxCalculator taxCalculator = TaxCalculatorRegistry.getRegistry(currItem.type);
    return taxCalculator.calculateTax(currItem.price);
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
