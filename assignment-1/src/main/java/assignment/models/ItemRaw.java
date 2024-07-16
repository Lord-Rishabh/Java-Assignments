package assignment.models;

import assignment.config.Constants;

public class ItemRaw extends Item {

    public static double calculateRawTax(double itemPrice) {
        return ( itemPrice * Constants.RAW_TAX );
    }
}
