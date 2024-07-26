package assignment.controller.options;

import static assignment.utils.Validations.validGetNode;

import assignment.controller.NodeController;
import assignment.models.Node;

import java.util.Scanner;

/**
 * Handles the deletion of a dependency between nodes.
 */
public class DeleteDependency {

  /**
   * Take the user to enter the child and parent nodes and removes the dependency.
   * @param nodeController the controller managing the nodes
   */
  public static void option(NodeController nodeController) {
    System.out.println("Deleting a dependency");

    Node parentNode = getNode(nodeController, "Parent");
    Node childNode = getNode(nodeController, "Child");

    parentNode.getChildren().remove(childNode);
    childNode.getParents().remove(parentNode);
  }

  /**
   * Prompts the user to enter a node ID and retrieves the corresponding node.
   * @param nodeController the controller managing the nodes
   * @param nodeType the type of node to be entered (e.g., "Parent" or "Child")
   * @return the node corresponding to the entered ID
   */
  private static Node getNode(NodeController nodeController, String nodeType) {
    Scanner scan = new Scanner(System.in);
    System.out.print("Enter the " + nodeType + " Node Id: ");
    String nodeId = scan.next();

    while (!validGetNode(nodeController, nodeId)) {
      System.out.print("Enter the " + nodeType + " Node Id: ");
      nodeId = scan.next();
    }

    return nodeController.getNode(nodeId);
  }
}
