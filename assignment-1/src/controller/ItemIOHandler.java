package controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import models.Item;


public class ItemIOHandler {

    private Map<String, Item> items = new HashMap<>();

    public void addItem(String[] args) {
        String name = null;
        Double price = null;
        Integer quantity = null;
        String type = null;

        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-name":
                    name = args[++i];
                    break;
                case "-price":
                    price = Double.parseDouble(args[++i]);
                    break;
                case "-quantity":
                    quantity = Integer.parseInt(args[++i]);
                    break;
                case "-type":
                    type = args[++i];
                    break;
                default:
                    throw new RuntimeException("Invalid command line argument: " + args[i]);
            }
        }

        Item item = new Item(name, price, quantity, type);
        items.put(name, item);
    }

    public boolean userWantsToAddItem() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Do you want to enter details of any other item (y/n) : ");
        String userInput = scan.nextLine();
        return userInput.equalsIgnoreCase("y");
    }

    public void getInputFromUserAndAddItem() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter item details : ");
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
            return Item.calculateTax(item);
        }
        catch ( Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void printAllItems() {
        for(Item item : items.values()) {
            Item.printItemDetails(item);
        }
    }
}
