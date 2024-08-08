package assignment.controller;

import static assignment.config.Constants.ARGS_LENGTH;

import assignment.models.Item;
import assignment.models.ItemType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;

public class ItemIoHandler {

  /**
   * Prompts the user to enter details of another item.
   * @return true if the user wants to add another item, false otherwise
   */
  public boolean userWantsToAddItem() {
    Scanner scan = new Scanner(System.in);
    System.out.print("Do you want to enter details of any other item (y/n) : ");
    String userInput = scan.nextLine();
    return userInput.equalsIgnoreCase("y");
  }

  /**
   * Gets input from the user and adds an item.
   */
  public void getInputFromUserAndAddItem(Connection con, PreparedStatement pst) throws Exception {
    Scanner scan = new Scanner(System.in);
    System.out.print("Enter item details : ");
    String input = scan.nextLine();
    String[] inputArgs = input.split(" ");

    if (inputArgs.length != ARGS_LENGTH) {
      throw new RuntimeException("Item Details cannot be null");
    }

    addItemToDb(inputArgs, con, pst);
  }

  /**
   * Adds an item based on the provided arguments.
   * @param args the arguments containing item details
   */
  public void addItemToDb(String[] args, Connection con, PreparedStatement pst) throws Exception {
    String name = null;
    double price = 0;
    int quantity = 0;
    ItemType type = null;

    for (int i = 0; i < args.length; i++) {
      switch (args[i]) {
        case "-name":
          name = setName(args[++i]);
          break;
        case "-price":
          price = setPrice(args[++i]);
          break;
        case "-quantity":
          quantity = setQuantity(args[++i]);
          break;
        case "-type":
          type = setType(args[++i]);
          break;
        default:
          throw new RuntimeException("Invalid command line argument: " + args[i]);
      }
    }

    Item item = new Item(name, price, quantity, type);
    pst.setString(1, item.getName());
    pst.setDouble(2, item.getPrice());
    pst.setInt(3, item.getQuantity());
    pst.setString(4, item.getType().toString());

    System.out.println("Item inserted successfully.");
  }

  /**
   * Sets the name of the item.
   * @param name the name of the item
   * @return the validated item name
   */
  private String setName(String name) {
    if (name == null) {
      throw new RuntimeException("Item name already exists or is null.");
    }
    return name;
  }

  /**
   * Sets the type of the item.
   * @param type the type of the item
   * @return the validated item type
   */
  private ItemType setType(String type) {
    if (type == null) {
      throw new RuntimeException("Invalid Item Type");
    }
    try {
      return ItemType.valueOf(type.toUpperCase());
    } catch (IllegalArgumentException e) {
      throw new RuntimeException("Invalid Item Type");
    }
  }

  /**
   * Sets the price of the item.
   * @param price the price of the item
   * @return the validated item price
   */
  private double setPrice(String price) {
    if (price == null) {
      throw new IllegalArgumentException("Item price cannot be null");
    }

    try {
      return Double.parseDouble(price);
    } catch (NumberFormatException e) {
      throw new NumberFormatException("Item Price is not valid");
    }
  }

  /**
   * Sets the quantity of the item.
   * @param quantity the quantity of the item
   * @return the validated item quantity
   */
  private int setQuantity(String quantity) {
    if (quantity == null) {
      throw new IllegalArgumentException("Item quantity cannot be null");
    }

    try {
      return Integer.parseInt(quantity);
    } catch (NumberFormatException e) {
      throw new NumberFormatException("Item Quantity is not valid");
    }
  }

  /**
   * Calculates tax and final price for all items.
   */
  public void calculateTaxAndFinalPrice(Item item) throws Exception {
    double tax = calculateTax(item);
    double finalPrice = tax + item.getPrice();

    item.setTax(tax);
    item.setFinalPrice(finalPrice);
  }

  /**
   * Calculates the tax for a given item.
   * @param item : the item for which tax is to be calculated
   * @return the calculated tax
   */
  public double calculateTax(Item item) {
    try {
      return item.calculateTax(item);
    } catch (Exception e) {
      throw new RuntimeException(e.getMessage());
    }
  }

  /**
   * It runs a query to get all the items from database, and add it to itemQueue.
   */
  public void getItems(Connection con,
                       BlockingQueue<Item> itemQueue,
                       Object lock)
      throws Exception {
    String query = "SELECT * FROM ITEMS";
    try {
      PreparedStatement pst = con.prepareStatement(query);
      ResultSet res = pst.executeQuery();

      while (res.next()) {
        String name = res.getString("name");
        double price = res.getDouble("price");
        int quantity = res.getInt("quantity");
        ItemType type = ItemType.valueOf(res.getString("type").toUpperCase());

        System.out.println("Items produced");
        Item item = new Item(name, price, quantity, type);
        itemQueue.add(item);
        Thread.sleep(1);
        synchronized (lock) {
          lock.notify(); // Notify consumer that items are available
        }
      }

    } catch (Exception e) {
      throw new RuntimeException("Error retrieving items from database.");
    }
  }

  /**
   * Prints the details of all items.
   */
  public void printAllItems(BlockingQueue<Item> items) {
    for (Item item : items) {
      printItemDetails(item);
    }
  }

  /**
   * Prints the details of a given item.
   * @param item : the item whose details are to be printed
   */
  public void printItemDetails(Item item) {
    System.out.println("----------------------- ----------------------- -----------------------");
    System.out.print("Name:" + item.getName() + "  ");
    System.out.print("Price:" + item.getPrice() + "  ");
    System.out.print("Quantity:" + item.getQuantity() + "  ");
    System.out.print("Type:" + item.getType() + "  ");
    System.out.print("Tax:" + item.getTax() + "  ");
    System.out.println("Final Price:" + item.getFinalPrice());
  }
}
