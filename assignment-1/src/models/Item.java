package models;

import static models.ItemImported.calculateImportedTax;
import static models.ItemManufactured.calculateManufacturedTax;
import static models.ItemRaw.calculateRawTax;

public class Item {

    private String name;
    private double price;
    private int quantity;
    private String type;
    private double tax;
    private double finalPrice;

    public Item(String name, double price, int quantity, String type) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.type = type;
    }

    public Item() {

    };

    public static void printItemDetails(Item item) {
        System.out.print(item.name + " ");
        System.out.print(item.price + " ");
        System.out.print(item.quantity + " ");
        System.out.print(item.type + " ");
        System.out.print(item.tax + " ");
        System.out.println(item.finalPrice);
    }

    public static double calculateTax(Item currItem) {
        return switch (currItem.type) {
            case ("raw") -> calculateRawTax(currItem.price);
            case ("manufactured") -> calculateManufacturedTax(currItem.price);
            case ("imported") -> calculateImportedTax(currItem.price);
            default -> -1;
        };
    }

    public String getName() {
        return name;
    }
    public double getPrice() {
        return price;
    }
    public int getQuantity() {
        return quantity;
    }
    public String getType() {
        return type;
    }
    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }
    public void setFinalPrice(double finalPrice) {
        this.finalPrice = finalPrice;
    }
}
