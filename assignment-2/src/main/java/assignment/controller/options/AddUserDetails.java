package assignment.controller.options;

import static assignment.config.Constants.MAX_ADDRESS_LENGTH;
import static assignment.config.Constants.MAX_AGE;
import static assignment.config.Constants.MAX_NAME_LENGTH;
import static assignment.config.Constants.MAX_ROLL_NUMBER_LENGTH;
import static assignment.config.Constants.MIN_ADDRESS_LENGTH;
import static assignment.config.Constants.MIN_AGE;
import static assignment.config.Constants.MIN_COURSES_SIZE;
import static assignment.config.Constants.MIN_NAME_LENGTH;
import static assignment.config.Constants.VALID_COURSES;

import assignment.controller.UserController;
import assignment.models.User;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Class for adding user details.
 */
public class AddUserDetails {

  /**
   * Provides options to add a new user.
   * @param userController the controller managing user details
   */
  public static void option(UserController userController) {
    String fullName = getFullName();
    int age = getAge();
    String rollNumber = getRollNumber();
    String address = getAddress();
    Set<Character> courses = getCourses();

    User user = new User(fullName, age, address, rollNumber, courses);
    addUser(userController, user);
  }

  /**
   * Adds a user to the user controller.
   * @param userController the controller managing user details
   * @param user           the user to be added
   */
  public static void addUser(UserController userController, User user) {
    userController.addUser(user.getRollNumber(), user);
  }

  /**
   * Prompts the user to enter their full name and validates it.
   * @return the validated full name
   */
  private static String getFullName() {
    Scanner scan = new Scanner(System.in);
    System.out.print("Enter your Full Name");
    System.out.println("char-limit = " + MIN_NAME_LENGTH + "-" + MAX_NAME_LENGTH + "): ");
    String userName = scan.nextLine();

    if (userName == null || userName.length() < MIN_NAME_LENGTH) {
      System.err.println("Name cannot be less than " + MIN_NAME_LENGTH + " characters.");
      return getFullName();
    } else if (userName.length() > MAX_NAME_LENGTH) {
      System.err.println("Name cannot be more than " + MAX_NAME_LENGTH + " characters long.");
      return getFullName();
    } else {
      return userName;
    }
  }

  /**
   * Prompts the user to enter their age and validates it.
   * @return the validated age
   */
  private static int getAge() {
    Scanner scan = new Scanner(System.in);
    System.out.println("Enter your Age (5-100): ");
    String userAge = scan.nextLine();

    if (userAge.isEmpty()) {
      System.err.println("Age should not be empty.");
      return getAge();
    }

    int age;
    try {
      age = Integer.parseInt(userAge);
    } catch (NumberFormatException e) {
      System.err.println("Enter a valid age.");
      return getAge();
    }

    if (age < MIN_AGE || age > MAX_AGE) {
      System.err.println("Age must be between " + MIN_AGE + " and " + MAX_AGE + " years.");
      return getAge();
    }

    return age;
  }

  /**
   * Prompts the user to enter their address and validates it.
   * @return the validated address
   */
  private static String getAddress() {
    Scanner scan = new Scanner(System.in);
    System.out.print("Enter your Address");
    System.out.println("char-limit = " + MIN_ADDRESS_LENGTH + "-" + MAX_ADDRESS_LENGTH + ") : ");
    String userAddress = scan.nextLine();

    if (userAddress.length() < MIN_ADDRESS_LENGTH) {
      System.err.println("Address cannot be less than " + MIN_ADDRESS_LENGTH + " characters.");
      return getAddress();
    } else if (userAddress.length() > MAX_ADDRESS_LENGTH) {
      System.err.println("Address cannot be more than " + MAX_ADDRESS_LENGTH + " characters long.");
      return getAddress();
    } else {
      return userAddress;
    }
  }

  /**
   * Prompts the user to enter their roll number and validates it.
   * @return the validated roll number
   */
  private static String getRollNumber() {
    Scanner scan = new Scanner(System.in);
    System.out.println("Enter your Roll Number");
    System.out.println("char-limit = " + MAX_ROLL_NUMBER_LENGTH + ") : ");
    String userRollNumber = scan.nextLine();

    if (userRollNumber.isEmpty()) {
      System.err.println("Roll Number cannot be empty.");
      return getRollNumber();
    } else if (userRollNumber.length() > MAX_ROLL_NUMBER_LENGTH) {
      System.err.print("Roll Number cannot be more than ");
      System.out.println(MAX_ROLL_NUMBER_LENGTH + " characters long.");
      return getRollNumber();
    } else {
      return userRollNumber;
    }
  }

  /**
   * Prompts the user to enter their courses and validates them.
   * @return a set of validated courses
   */
  private static Set<Character> getCourses() {
    Scanner scan = new Scanner(System.in);
    System.out.println("Enter all courses separated by a space (min. " + MIN_COURSES_SIZE + "): ");
    String userCourses = scan.nextLine();
    Set<Character> courses = new HashSet<>();

    for (int i = 0; i < userCourses.length(); i++) {
      char course = userCourses.charAt(i);

      if (i != userCourses.length() - 1 && userCourses.charAt(i + 1) != ' ') {
        System.err.println("Courses should be separated by a single space.");
        return getCourses();
      }

      if (!VALID_COURSES.contains(course)) {
        System.err.println("Invalid Course: " + course);
        return getCourses();
      }

      courses.add(course);
    }

    if (courses.size() < MIN_COURSES_SIZE) {
      System.err.println("Minimum " + MIN_COURSES_SIZE + " courses should be added.");
      return getCourses();
    }

    return courses;
  }
}
