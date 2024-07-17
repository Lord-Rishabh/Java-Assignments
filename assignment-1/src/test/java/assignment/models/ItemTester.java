package assignment.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ItemTester {

  @Test
  public void calculateTax() {
    Item item = new Item("pizza", 120, 120, ItemType.RAW);
    double tax = item.calculateTax(item);
    assertEquals(15, tax);
  }
}
