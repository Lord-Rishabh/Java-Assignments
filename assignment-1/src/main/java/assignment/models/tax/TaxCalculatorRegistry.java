package assignment.models.tax;

import assignment.models.ItemType;

import java.util.HashMap;
import java.util.Map;

public class TaxCalculatorRegistry {

  private static final Map<ItemType, TaxCalculator> registry = new HashMap<>();

  public static void setRegistry(ItemType itemType, TaxCalculator taxCalculator) {
    registry.put(itemType, taxCalculator);
  }

  public static TaxCalculator getRegistry(ItemType itemType) {
    return registry.get(itemType);
  }
}
