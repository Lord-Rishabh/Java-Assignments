package assignment.controller.options;

import static assignment.utils.Validations.validGetNode;

import assignment.controller.NodeController;
import assignment.models.Node;

import java.util.Scanner;

/**
 * Handles the retrieval and display of a node's parents.
 */
public class GetParents {

  /**
   * Prompts the user to enter a node ID and prints its parents.
   * @param nodeController the controller managing the nodes
   */
  public static void option(NodeController nodeController) {
    System.out.println("Enter the node: ");
    Node node = getNode(nodeController);
    printParents(node);
  }

  /**
   * Prompts the user to enter a node ID and retrieves the corresponding node.
   * @param nodeController the controller managing the nodes
   * @return the node corresponding to the entered ID
   */
  private static Node getNode(NodeController nodeController) {
    Scanner scan = new Scanner(System.in);
    System.out.print("Enter the Node Id: ");
    String nodeId = scan.next();

    while (!validGetNode(nodeController, nodeId)) {
      System.out.print("Enter the Node Id: ");
      nodeId = scan.next();
    }

    return nodeController.getNode(nodeId);
  }

  /**
   * Prints the parents of the specified node.
   * @param node the node whose parents are to be printed
   */
  private static void printParents(Node node) {
    System.out.println();
    for (Node parent : node.getParents()) {
      System.out.print(parent.getId() + " ");
    }
    System.out.println();
  }
}
