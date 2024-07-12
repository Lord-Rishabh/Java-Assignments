package models;

import static config.Constants.RAW_TAX;

public class ItemRaw extends Item {

    public static double calculateRawTax(double itemPrice) {
        return ( itemPrice * RAW_TAX );
    }
}
