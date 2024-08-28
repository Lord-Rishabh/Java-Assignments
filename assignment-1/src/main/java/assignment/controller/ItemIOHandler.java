package assignment.controller;

import static assignment.config.Constants.ARGS_LENGTH;

import assignment.models.Item;
import assignment.models.ItemType;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ItemIoHandler implements itemIoHandlerI {

  private final Map<String, Item> items = new HashMap<>();

  /**
   * Prompts the user to enter details of another item.
   * @return true if the user wants to add another item, false otherwise
   */
  public boolean userWantsToAddItem() {
    Scanner scan = new Scanner(System.in);
    System.out.print("Do you want to enter details of any other item (y/n) : ");
    String userInput = scan.nextLine();
    scan.close();
    return userInput.equalsIgnoreCase("y");
  }

  /**
   * Gets input from the user and adds an item.
   */
  public void getInputFromUserAndAddItem() {
    Scanner scan = new Scanner(System.in);
    System.out.print("Enter item details : ");
    String input = scan.nextLine();
    scan.close();
    String[] inputArgs = input.split(" ");

    if (inputArgs.length != ARGS_LENGTH) {
      throw new RuntimeException("Item Details cannot be null");
    }

    addItem(inputArgs);
  }

  /**
   * Adds an item based on the provided arguments.
   * @param args the arguments containing item details
   */
  public void addItem(String[] args) {
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
    items.put(name, item);
  }

  /**
   * Sets the name of the item.
   * @param name the name of the item
   * @return the validated item name
   */
  private String setName(String name) {
    if (name == null || items.containsKey(name)) {
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
  public void calculateTaxAndFinalPrice() {
    for (Item item : items.values()) {
      double tax = calculateTax(item);
      double finalPrice = tax + item.getPrice();

      item.setTax(tax);
      item.setFinalPrice(finalPrice);
    }
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
   * Prints the details of all items.
   */
  public void printAllItems() {
    for (Item item : items.values()) {
      printItemDetails(item);
    }
  }

  /**
   * Prints the details of a given item.
   * @param item : the item whose details are to be printed
   */
  public void printItemDetails(Item item) {
    System.out.print("Name:" + item.getName() + "  ");
    System.out.print("Price:" + item.getPrice() + "  ");
    System.out.print("Quantity:" + item.getQuantity() + "  ");
    System.out.print("Type:" + item.getType() + "  ");
    System.out.print("Tax:" + item.getTax() + "  ");
    System.out.println("Final Price:" + item.getFinalPrice());
  }

  /**
   * Gets all the items.
   * @return a map of item names to items
   */
  public Map<String, Item> getItems() {
    return items;
  }
}
