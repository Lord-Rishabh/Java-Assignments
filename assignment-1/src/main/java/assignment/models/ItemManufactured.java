package assignment.models;

import assignment.config.Constants;

public class ItemManufactured {

  /**
   * The method is used to calculate tax of item of type 'manufactured'.
   * @param itemPrice represents the price of the item whose tax is to be calculated
   */
  public static double calculateManufacturedTax(double itemPrice) {

    double tax1 = Constants.MANUFACTURED_TAX_1 * itemPrice;
    double tax2 = Constants.MANUFACTURED_TAX_2 * (itemPrice + tax1);

    return (tax1 + tax2);
  }
}
