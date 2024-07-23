package assignment.utils.validations;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static assignment.utils.validations.ValidateAddUser.validCourses;

public class ValidCourseTest {

  private Set<Character> courses;
  @BeforeEach
  public void setupCourses() {
    courses = new HashSet<>();
  }

  @Test
  public void testEmptyCourses() {
    Assertions.assertFalse(validCourses("", courses));
  }

  @Test
  public void testInvalidCourses() {
    Assertions.assertFalse(validCourses("A B C G", courses));
  }

  @Test
  public void testCoursesBelowMinSize() {
    Assertions.assertFalse(validCourses("a b c", courses));
  }

  @Test
  public void testCoursesInLowerCase() {
    Assertions.assertTrue(validCourses("a b c d", courses));
  }

  @Test
  public void testValidCourses() {
    Assertions.assertTrue(validCourses("A B C D", courses));
  }
}
