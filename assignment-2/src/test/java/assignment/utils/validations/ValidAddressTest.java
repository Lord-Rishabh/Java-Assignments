package assignment.utils.validations;

import static assignment.utils.validations.ValidateAddUser.validAddress;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ValidAddressTest {

  @Test
  public void testEmptyAddress() {
    Assertions.assertFalse(validAddress(""));
  }

  @Test
  public void testAddressBelowMaxLimit() {
    Assertions.assertFalse(validAddress("Ad"));
  }

  @Test
  public void testAddressAboveMaxLimit() {
    Assertions.assertFalse(validAddress("143 F, Route No. 154, Near St.Sebastian Garden, Goa"));
  }

  @Test
  public void testValidAddress() {
    Assertions.assertTrue(validAddress("21 C, Vijay Nagar"));
  }
}
