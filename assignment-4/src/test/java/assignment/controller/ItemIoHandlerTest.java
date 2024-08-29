package assignment.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import assignment.config.Secrets;
import assignment.models.Item;
import assignment.models.ItemType;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ItemIoHandlerTest {

  private ItemIoHandler itemIoHandler;
  private PreparedStatement pst;

  /**
   * Initializes ItemIoHandler and PreparedStatement.
   */
  @BeforeEach
  public void itemInitialize() throws Exception {
    itemIoHandler = new ItemIoHandler();
    String url = Secrets.URL;
    String user = Secrets.USER;
    String password = Secrets.PASSWORD;

    Connection con = DriverManager.getConnection(url, user, password);

    String insertSql = "INSERT INTO items (name, price, quantity, type) VALUES (?, ?, ?, ?)";
    pst = con.prepareStatement(insertSql);
  }

  @Test
  public void invalidItemQuantity() {
    String[] args = {
        "-name", "pizza", "-quantity", "invalid", "-price", "120", "-type", "raw"
    };

    RuntimeException exception = assertThrows(RuntimeException.class,
        () -> itemIoHandler.addItemToDb(args, pst));
    assertEquals("Item Quantity is not valid", exception.getMessage());
  }

  @Test
  public void invalidItemPrice() {
    String[] args = {
        "-name", "pizza", "-quantity", "120", "-price", "invalid", "-type", "raw"
    };

    RuntimeException exception = assertThrows(RuntimeException.class,
        () -> itemIoHandler.addItemToDb(args, pst));
    assertEquals("Item Price is not valid", exception.getMessage());
  }

  @Test
  public void invalidItemType() {
    String[] args = {
        "-name", "pizza", "-quantity", "120", "-price", "120", "-type", "invalid"
    };

    RuntimeException exception = assertThrows(RuntimeException.class,
        () -> itemIoHandler.addItemToDb(args, pst));
    assertEquals("Invalid Item Type", exception.getMessage());
  }

  @Test
  public void calculateRawTax() {
    Item item = new Item("pizza", 120, 120, ItemType.RAW);
    double tax = itemIoHandler.calculateTax(item);
    assertEquals(15, tax);
  }

  @Test
  public void calculateManufacturedTax() {
    Item item = new Item("pizza", 120, 120, ItemType.MANUFACTURED);
    double tax = itemIoHandler.calculateTax(item);
    assertEquals(17.7, tax);
  }

  @Test
  public void calculateImportedTax() {
    Item item = new Item("pizza", 120, 120, ItemType.IMPORTED);
    double tax = itemIoHandler.calculateTax(item);
    assertEquals(22, tax);
  }
}

// -name burger -quantity 120 -price 120 -type raw