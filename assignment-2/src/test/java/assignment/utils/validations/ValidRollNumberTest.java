package assignment.utils.validations;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static assignment.utils.validations.ValidateAddUser.validRollNumber;

public class ValidRollNumberTest {

  @Test
  public void testEmptyRollNumber() {
    Assertions.assertFalse(validRollNumber(""));
  }

  @Test
  public void testRollNumberAboveMaxLimit() {
    Assertions.assertFalse(validRollNumber("21T614881237E21323"));
  }

  @Test
  public void testRollNumberWithDecimal() {
    Assertions.assertFalse(validRollNumber("12.3"));
  }

  @Test
  public void testValidRollNumber() {
    Assertions.assertTrue(validRollNumber("21T6149"));
  }
}
