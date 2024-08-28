package assignment.controller.options;

import static assignment.config.Constants.MAX_ADDRESS_LENGTH;
import static assignment.config.Constants.MAX_NAME_LENGTH;
import static assignment.config.Constants.MAX_ROLL_NUMBER_LENGTH;
import static assignment.config.Constants.MIN_ADDRESS_LENGTH;
import static assignment.config.Constants.MIN_COURSES_SIZE;
import static assignment.config.Constants.MIN_NAME_LENGTH;

import static assignment.utils.validations.ValidateAddUser.validAddress;
import static assignment.utils.validations.ValidateAddUser.validAge;
import static assignment.utils.validations.ValidateAddUser.validCourses;
import static assignment.utils.validations.ValidateAddUser.validName;
import static assignment.utils.validations.ValidateAddUser.validRollNumber;

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
    userController.addUser(user);
  }

  /**
   * Prompts the user to enter their full name and validates it.
   * @return the validated full name
   */
  private static String getFullName() {
    Scanner scan = new Scanner(System.in);
    System.out.print("Enter your Full Name ");
    System.out.print("(char-limit = " + MIN_NAME_LENGTH + "-" + MAX_NAME_LENGTH + "): ");
    String userName = scan.nextLine();

    String name;
    if (validName(userName)) {
      name = userName;
    } else {
      name = getFullName();
    }
    return name;
  }

  /**
   * Prompts the user to enter their age and validates it.
   * @return the validated age
   */
  private static int getAge() {
    Scanner scan = new Scanner(System.in);
    System.out.print("Enter your Age (5-100): ");
    String userAge = scan.nextLine();

    int age;
    if (validAge(userAge)) {
      age = Integer.parseInt(userAge);
    } else {
      age = getAge();
    }

    return age;
  }

  /**
   * Prompts the user to enter their address and validates it.
   * @return the validated address
   */
  private static String getAddress() {
    Scanner scan = new Scanner(System.in);
    System.out.print("Enter your Address ");
    System.out.print("(char-limit = " + MIN_ADDRESS_LENGTH + "-" + MAX_ADDRESS_LENGTH + ") : ");
    String userAddress = scan.nextLine();

    String address;
    if (validAddress(userAddress)) {
      address = userAddress;
    } else {
      address = getAddress();
    }
    return address;
  }

  /**
   * Prompts the user to enter their roll number and validates it.
   * @return the validated roll number
   */
  private static String getRollNumber() {
    Scanner scan = new Scanner(System.in);
    System.out.print("Enter your Roll Number ");
    System.out.print("(char-limit = " + MAX_ROLL_NUMBER_LENGTH + ") : ");
    String userRollNumber = scan.nextLine();

    String rollNumber;
    if (validRollNumber(userRollNumber)) {
      rollNumber = userRollNumber;
    } else {
      rollNumber = getRollNumber();
    }
    return rollNumber;
  }

  /**
   * Prompts the user to enter their courses and validates them.
   * @return a set of validated courses
   */
  private static Set<Character> getCourses() {
    Scanner scan = new Scanner(System.in);

    while (true) {
      System.out.print("Enter all courses separated by a space (min. " + MIN_COURSES_SIZE + "): ");
      String userCourses = scan.nextLine();

      Set<Character> courses = new HashSet<>();
      // courses is being changed with this function
      if (validCourses(userCourses, courses)) {
        return courses;
      }
    }
  }
}
