package models;

import static config.Constants.*;

public class ItemManufactured {

    public static double calculateManufacturedTax(double itemPrice) {

        double tax1 = MANUFACTURED_TAX_1 * itemPrice;
        double tax2 = MANUFACTURED_TAX_2 * ( itemPrice + tax1);

        return (tax1 + tax2);
    }
}
