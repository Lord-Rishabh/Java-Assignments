package assignment.models;

import assignment.config.Constants;

public class ItemRaw extends Item {

  /**
   * The method is used to calculate tax of item of type 'raw'.
   * @param itemPrice represents the price of the item whose tax is to be calculated
   */
  public static double calculateRawTax(double itemPrice) {
    return (itemPrice * Constants.RAW_TAX);
  }
}
