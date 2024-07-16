package assignment.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import assignment.models.ItemType;
import assignment.models.Item;
import static assignment.config.Constants.ARGS_LENGTH;

public class ItemIOHandler {

    private final Map<String, Item> items = new HashMap<>();

    public boolean userWantsToAddItem() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Do you want to enter details of any other item (y/n) : ");
        String userInput = scan.nextLine();
        return userInput.equalsIgnoreCase("y");
    }

    public void getInputFromUserAndAddItem() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter item details : ");
        String input = scan.nextLine();
        String[] inputArgs = input.split(" ");

        if(inputArgs.length != ARGS_LENGTH)
            throw new RuntimeException("Item Details cannot be null");

        addItem(inputArgs);
    }

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

    private String setName(String name) {
        if( name == null || items.containsKey(name))
            throw new RuntimeException("Item name already exist or is null.");
        return name;
    }

    private ItemType setType(String type) {
        if(type == null)
            throw new RuntimeException("Invalid Item Type");
        try {
            return ItemType.valueOf(type.toLowerCase());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid Item Type");
        }
    }

    private double setPrice(String price) {
        if(price == null)
            throw new IllegalArgumentException("Item price cannot be null");

        try {
            return Double.parseDouble(price);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Item Price is not valid");
        }
    }

    private int setQuantity(String quantity) {
        if(quantity == null)
            throw new IllegalArgumentException("Item quantity cannot be null");

        try {
            return Integer.parseInt(quantity);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Item Quantity is not valid");
        }
    }

    public void calculateTaxAndFinalPrice() {
        for(Item item : items.values()) {
            double tax = calculateTax(item);
            double finalPrice = tax + item.getPrice();

            item.setTax(tax);
            item.setFinalPrice(finalPrice);
        }
    }

    public double calculateTax(Item item) {
        try {
            return item.calculateTax(item);
        }
        catch ( Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void printAllItems() {
        for(Item item : items.values()) {
            printItemDetails(item);
        }
    }

    public void printItemDetails(Item item) {
        System.out.print( "Item Name:" + item.getName() + "  " );
        System.out.print( "Item Price:" + item.getPrice() + "  " );
        System.out.print( "Item Quantity:" + item.getQuantity() + "  " );
        System.out.print( "Item Type:" + item.getType() + "  " );
        System.out.print( "Item Tax:" + item.getTax() + "  " );
        System.out.println( "Item Final Price:" + item.getFinalPrice() );
    }

    public Map<String, Item> getItems() {
        return items;
    }
}
