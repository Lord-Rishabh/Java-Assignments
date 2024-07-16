package assignment.models;

import assignment.config.Constants;

public class ItemImported {

    public static double calculateImportedTax(double itemPrice) {

        double importDutyTaxCost = Constants.IMPORT_DUTY_TAX * itemPrice;
        double itemCostAfterImportDutyTax = importDutyTaxCost + itemPrice;
        double totalTax = importDutyTaxCost;

        if(itemCostAfterImportDutyTax < Constants.CASE_1) {
            totalTax += Constants.SURGE_CHARGE_BETWEEN_0_TO_100;
        }
        else if(itemCostAfterImportDutyTax < Constants.CASE_2) {
            totalTax += Constants.SURGE_CHARGE_BETWEEN_100_TO_200;
        }
        else {
            totalTax += itemCostAfterImportDutyTax * Constants.SURGE_CHARGE_ABOVE_200_PERCENTAGE;
        }
        return totalTax;
    }
}
