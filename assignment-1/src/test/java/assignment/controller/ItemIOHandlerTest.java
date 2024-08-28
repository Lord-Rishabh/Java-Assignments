package assignment.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import assignment.models.Item;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ItemIoHandlerTest {

  private ItemIoHandler itemHandler;

  @BeforeEach
  public void itemInitialize() {
    itemHandler = new ItemIoHandler();
  }

  @Test
  public void duplicateItemName() {
    String[] args1 = {
        "-name", "pizza", "-quantity", "120", "-price", "120", "-type", "raw"
    };
    itemHandler.addItem(args1);
    String[] args2 = {
        "-name", "pizza", "-quantity", "120", "-price", "120", "-type", "raw"
    };

    RuntimeException exception = assertThrows(RuntimeException.class,
        () -> itemHandler.addItem(args2));
    assertEquals("Item name already exists or is null.", exception.getMessage());
  }

  @Test
  public void invalidItemQuantity() {
    String[] args = {
        "-name", "pizza", "-quantity", "invalid", "-price", "120", "-type", "raw"
    };

    RuntimeException exception = assertThrows(RuntimeException.class,
        () -> itemHandler.addItem(args));
    assertEquals("Item Quantity is not valid", exception.getMessage());
  }

  @Test
  public void invalidItemPrice() {
    String[] args = {
        "-name", "pizza", "-quantity", "120", "-price", "invalid", "-type", "raw"
    };

    RuntimeException exception = assertThrows(RuntimeException.class,
        () -> itemHandler.addItem(args));
    assertEquals("Item Price is not valid", exception.getMessage());
  }

  @Test
  public void invalidItemType() {
    String[] args = {
        "-name", "pizza", "-quantity", "120", "-price", "120", "-type", "invalid"
    };

    RuntimeException exception = assertThrows(RuntimeException.class,
        () -> itemHandler.addItem(args));
    assertEquals("Invalid Item Type", exception.getMessage());
  }

  @Test
  public void calculateRawTax() {
    String[] args = {
        "-name", "pizza", "-quantity", "120", "-price", "120", "-type", "raw"
    };
    itemHandler.addItem(args);
    Map<String, Item> items = itemHandler.getItems();
    double tax = itemHandler.calculateTax(items.get("pizza"));
    assertEquals(15, tax);
  }

  @Test
  public void calculateManufacturedTax() {
    String[] args = {
        "-name", "pizza", "-quantity", "120", "-price", "120", "-type", "manufactured"
    };
    itemHandler.addItem(args);
    Map<String, Item> items = itemHandler.getItems();
    double tax = itemHandler.calculateTax(items.get("pizza"));
    assertEquals(17.7, tax);
  }

  @Test
  public void calculateImportedTax() {
    String[] args = {
        "-name", "pizza", "-quantity", "120", "-price", "120", "-type", "imported"
    };
    itemHandler.addItem(args);
    Map<String, Item> items = itemHandler.getItems();
    double tax = itemHandler.calculateTax(items.get("pizza"));
    assertEquals(22, tax);
  }

  @Test
  public void printAllItems() {
    String[] args1 = {
        "-name", "pizza", "-quantity", "120", "-price", "120", "-type", "imported"
    };
    itemHandler.addItem(args1);
    String[] args2 = {
        "-name", "burger", "-quantity", "120", "-price", "120", "-type", "raw"
    };
    itemHandler.addItem(args2);

    itemHandler.printAllItems();
  }
}
