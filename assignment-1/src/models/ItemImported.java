package models;

import static config.Constants.*;

public class ItemImported {

    public static double calculateImportedTax(double itemPrice) {

        double importDutyTaxCost = IMPORT_DUTY_TAX * itemPrice;
        double itemCostAfterImportDutyTax = importDutyTaxCost + itemPrice;
        double totalTax = importDutyTaxCost;

        if(itemCostAfterImportDutyTax < CASE_1) {
            totalTax += SURGE_CHARGE_BETWEEN_0_TO_100;
        }
        else if(itemCostAfterImportDutyTax < CASE_2) {
            totalTax += SURGE_CHARGE_BETWEEN_100_TO_200;
        }
        else {
            totalTax += itemCostAfterImportDutyTax * SURGE_CHARGE_ABOVE_200_PERCENTAGE;
        }
        return totalTax;
    }
}
