package assignment.utils.validations;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static assignment.utils.validations.ValidateAddUser.validAge;

public class ValidAgeTest {

  @Test
  public void testEmptyAge() {
     Assertions.assertFalse(validAge(""));
  }

  @Test
  public void testCharacterAsAge() {
    Assertions.assertFalse(validAge("ab"));
  }

  @Test
  public void testAgeBelowMinAge() {
    Assertions.assertFalse(validAge("2"));
  }

  @Test
  public void testAgeAboveMaxAge() {
    Assertions.assertFalse(validAge("101"));
  }

  @Test
  public void testValidAge() {
    Assertions.assertTrue(validAge("21"));
  }
}
