package assignment.models;
import assignment.models.Item;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ItemTester {

    @Test
    public void calculateTax() {
        Item item = new Item("pizza", 120, 120, ItemType.raw);
        double tax = item.calculateTax(item);
        assertEquals(15, tax);
    }
}
