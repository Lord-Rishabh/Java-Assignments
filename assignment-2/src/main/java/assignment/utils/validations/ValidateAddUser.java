package assignment.utils.validations;

import static assignment.config.Constants.MAX_ADDRESS_LENGTH;
import static assignment.config.Constants.MAX_AGE;
import static assignment.config.Constants.MAX_NAME_LENGTH;
import static assignment.config.Constants.MAX_ROLL_NUMBER_LENGTH;
import static assignment.config.Constants.MIN_ADDRESS_LENGTH;
import static assignment.config.Constants.MIN_AGE;
import static assignment.config.Constants.MIN_COURSES_SIZE;
import static assignment.config.Constants.MIN_NAME_LENGTH;

import java.util.Set;

/**
 * Utility class for validating user information such as name, age, address,
 * roll number, and courses.
 */
public class ValidateAddUser {

  /**
   * Validates the provided name.
   *
   * @param name the name to validate
   * @return {@code true} if the name is valid, {@code false} otherwise
   */
  public static boolean validName(String name) {
    boolean validFlag = true;
    if (name == null || name.length() < MIN_NAME_LENGTH) {
      System.err.println("Name cannot be less than " + MIN_NAME_LENGTH + " characters.");
      validFlag = false;
    } else if (name.length() > MAX_NAME_LENGTH) {
      System.err.println("Name cannot be more than " + MAX_NAME_LENGTH + " characters long.");
      validFlag = false;
    } else if (!name.matches("[a-zA-Z\\s]+")) {
      System.err.println("Name can only contain alphabets and spaces.");
      validFlag = false;
    }
    return validFlag;
  }

  /**
   * Validates the provided age.
   *
   * @param age the age to validate as a string
   * @return {@code true} if the age is valid, {@code false} otherwise
   */
  public static boolean validAge(String age) {
    boolean validFlag = true;
    if (age.isEmpty()) {
      System.err.println("Age should not be empty.");
      validFlag = false;
    }

    int userAge = 0;
    try {
      userAge = Integer.parseInt(age);
    } catch (NumberFormatException e) {
      System.err.println("Enter a valid age.");
      validFlag = false;
    }

    if (userAge < MIN_AGE || userAge > MAX_AGE) {
      System.err.println("Age must be between " + MIN_AGE + " and " + MAX_AGE + " years.");
      validFlag = false;
    }
    return validFlag;
  }

  /**
   * Validates the provided address.
   *
   * @param address the address to validate
   * @return {@code true} if the address is valid, {@code false} otherwise
   */
  public static boolean validAddress(String address) {
    boolean validFlag = true;
    if (address.length() < MIN_ADDRESS_LENGTH) {
      System.err.println("Address cannot be less than " + MIN_ADDRESS_LENGTH + " characters.");
      validFlag = false;
    } else if (address.length() > MAX_ADDRESS_LENGTH) {
      System.err.println("Address cannot be more than " + MAX_ADDRESS_LENGTH + " characters long.");
      validFlag = false;
    }
    return validFlag;
  }

  /**
   * Validates the provided roll number.
   *
   * @param rollNumber the roll number to validate
   * @return {@code true} if the roll number is valid, {@code false} otherwise
   */
  public static boolean validRollNumber(String rollNumber) {
    boolean validFlag = true;
    if (rollNumber.isEmpty()) {
      System.err.println("Roll Number cannot be empty.");
      validFlag = false;
    } else if (rollNumber.length() > MAX_ROLL_NUMBER_LENGTH) {
      System.err.print("Roll Number cannot be more than ");
      System.out.println(MAX_ROLL_NUMBER_LENGTH + " characters long.");
      validFlag = false;
    } else if (rollNumber.contains(".")) {
      System.err.println("Roll Number cannot contain decimal points.");
      validFlag = false;
    }
    return validFlag;
  }

  /**
   * Validates the provided set of courses.
   *
   * @param courses the set of courses to validate
   * @return {@code true} if the number of courses is valid, {@code false} otherwise
   */
  public static boolean validCourses(Set<Character> courses) {
    boolean validFlag = true;
    if (courses.size() < MIN_COURSES_SIZE) {
      System.err.println("Minimum " + MIN_COURSES_SIZE + " courses should be added.");
      validFlag = false;
    }
    return validFlag;
  }
}
