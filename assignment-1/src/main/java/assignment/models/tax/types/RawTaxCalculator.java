package assignment.models.tax.types;

import assignment.config.Constants;
import assignment.models.tax.TaxCalculator;

public class RawTaxCalculator implements TaxCalculator {

  @Override
  public double calculateTax(double itemPrice) {
    return (itemPrice * Constants.RAW_TAX);
  }
}
