import controller.ItemIOHandler;

public class Main {
    public static void main(String[] args) {

        ItemIOHandler itemHandler = new ItemIOHandler();

        itemHandler.addItem(args);
        while(itemHandler.userWantsToAddItem()) {
            itemHandler.getInputFromUserAndAddItem();
        }

        itemHandler.calculateTaxAndFinalPrice();
        itemHandler.printAllItems();
    }
}