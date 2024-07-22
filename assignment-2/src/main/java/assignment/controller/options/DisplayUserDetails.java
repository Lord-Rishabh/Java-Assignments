package assignment.controller.options;

import assignment.controller.UserController;
import assignment.models.User;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 * Class for displaying user details with sorting options.
 */
public class DisplayUserDetails {

  /**
   * Provides options to sort and display user details.
   *
   * @param userController the controller managing user details
   */
  public static void option(UserController userController) {
    String sortCriteria = getSortCriteria();
    sortAndPrintUserList(userController, sortCriteria);
  }

  /**
   * Prompts the user to enter sorting criteria.
   *
   * @return the sorting criteria entered by the user
   */
  private static String getSortCriteria() {
    System.out.print("Enter sorting criteria (name, rollNumber, age, address) : ");
    Scanner scan = new Scanner(System.in);
    return scan.nextLine();
  }

  /**
   * Sorts the user list based on the specified criteria and prints the details.
   *
   * @param userController the controller managing user details
   * @param sortCriteria   the criteria to sort the user list
   */
  public static void sortAndPrintUserList(UserController userController, String sortCriteria) {
    List<User> userList = userController.getUserList();
    Comparator<User> comparator;

    switch (sortCriteria) {
      case "name":
        comparator = Comparator.comparing(User::getFullName);
        break;
      case "rollNumber":
        comparator = Comparator.comparing(User::getRollNumber);
        break;
      case "age":
        comparator = Comparator.comparing(User::getAge);
        break;
      case "address":
        comparator = Comparator.comparing(User::getAddress);
        break;
      default:
        System.out.println("Invalid sort criteria. ");
        System.out.println("Please enter 'name', 'rollNumber', 'age', or 'address'.");
        return;
    }

    userList.sort(comparator);
    printAllUserDetails(userList);
  }

  /**
   * Prints all user details in a formatted manner.
   *
   * @param userList the list of users to print
   */
  private static void printAllUserDetails(List<User> userList) {
    System.out.println("-------------------------------------------------------------------------");
    System.out.println("--------------------------------------");

    System.out.print("Full Name       " + "Roll Number   ");
    System.out.println("Address            " + "Age     " + "Courses");

    System.out.println("-------------------------------------------------------------------------");
    System.out.println("--------------------------------------");

    for (User user : userList) {
      System.out.printf(
          "%-15s %-13s %-18s %-7d %s%n",
          user.getFullName(),
          user.getRollNumber(),
          user.getAddress(),
          user.getAge(),
          user.getCourses()
      );
    }
  }
}
