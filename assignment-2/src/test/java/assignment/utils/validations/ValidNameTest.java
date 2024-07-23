package assignment.utils.validations;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static assignment.utils.validations.ValidateAddUser.validName;

public class ValidNameTest {

  @Test
  public void testNameBelowMinSize() {
    Assertions.assertFalse(validName("ri"));
  }

  @Test
  public void testNameAboveMaxSize() {
    Assertions.assertFalse(validName("ChinnaSwami MutuSwami VenuGopala Iyer"));
  }

  @Test
  public void testNameWithNumbers() {
    Assertions.assertFalse(validName("Ris1abh"));
  }

  @Test
  public void testEmptyName() {
    Assertions.assertFalse(validName(""));
  }

  @Test
  public void testValidName() {
    Assertions.assertTrue(validName("Rishabh Patel"));
  }
}
