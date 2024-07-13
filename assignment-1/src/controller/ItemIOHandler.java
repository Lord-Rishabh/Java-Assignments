package controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import models.Item;

public class ItemIOHandler {

    private final Map<String, Item> items = new HashMap<>();

    public void addItem(String[] args) {
        String name = null;
        Double price = null;
        Integer quantity = null;
        String type = null;

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

    private String setType(String type) {
        if(type == null)
            throw new RuntimeException("Invalid Item Type");

        return type;
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

        addItem(inputArgs);
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
        System.out.print( item.getName() + " " );
        System.out.print( item.getPrice() + " " );
        System.out.print( item.getQuantity() + " " );
        System.out.print( item.getType() + " " );
        System.out.print( item.getTax() + " " );
        System.out.println( item.getFinalPrice() );
    }
}
