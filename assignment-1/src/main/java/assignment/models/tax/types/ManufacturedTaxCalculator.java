package assignment.models.tax.types;

import assignment.config.Constants;
import assignment.models.tax.TaxCalculator;

public class ManufacturedTaxCalculator implements TaxCalculator {

  @Override
  public double calculateTax(double itemPrice) {
    double tax1 = Constants.MANUFACTURED_TAX_1 * itemPrice;
    double tax2 = Constants.MANUFACTURED_TAX_2 * (itemPrice + tax1);

    return (tax1 + tax2);
  }
}