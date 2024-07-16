package assignment;

import assignment.controller.ItemIOHandler;
import static assignment.config.Constants.ARGS_LENGTH;

public class Main {
    public static void main(String[] args) {

        ItemIOHandler itemHandler = new ItemIOHandler();

        if(args.length ==  ARGS_LENGTH) {
            itemHandler.addItem(args);
        }

        while(itemHandler.userWantsToAddItem()) {
            itemHandler.getInputFromUserAndAddItem();
        }

        itemHandler.calculateTaxAndFinalPrice();
        itemHandler.printAllItems();
    }
}