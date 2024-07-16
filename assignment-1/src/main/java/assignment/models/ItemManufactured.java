package assignment.models;

import assignment.config.Constants;

public class ItemManufactured {

    public static double calculateManufacturedTax(double itemPrice) {

        double tax1 = Constants.MANUFACTURED_TAX_1 * itemPrice;
        double tax2 = Constants.MANUFACTURED_TAX_2 * ( itemPrice + tax1);

        return (tax1 + tax2);
    }
}
