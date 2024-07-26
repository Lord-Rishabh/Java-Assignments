package assignment.controller.options;

import static assignment.utils.Validations.validGetNode;

import assignment.controller.NodeController;
import assignment.models.Node;

import java.util.Scanner;

/**
 * Handles the retrieval and display of a node's children.
 */
public class GetChildren {

  /**
   * Prompts the user to enter a node ID and prints its children.
   * @param nodeController the controller managing the nodes
   */
  public static void option(NodeController nodeController) {
    Node node = getNode(nodeController);
    printChildren(node);
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
   * Prints the children of the specified node.
   * @param node the node whose children are to be printed
   */
  public static void printChildren(Node node) {
    System.out.println();
    for (Node child : node.getChildren()) {
      System.out.print(child.getId() + " ");
    }
    System.out.println();
  }
}
