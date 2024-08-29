package assignment.controller.options;

import static assignment.utils.Validations.validGetNode;

import assignment.controller.NodeController;
import assignment.models.Node;

import java.util.Scanner;

/**
 * Handles the deletion of a node.
 */
public class DeleteNode {

  /**
   * Prompts the user to enter the node to be deleted and removes the node from the controller.
   * @param nodeController the controller managing the nodes
   */
  public static void option(NodeController nodeController) {
    Node currentNode = getNode(nodeController);
    if (currentNode != null) {
      deleteNode(currentNode);
      nodeController.removeNode(currentNode.getId());
      System.out.println("Node deleted successfully.");
    } else {
      System.err.println("Node deletion failed.");
    }
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
   * Deletes the specified node by removing its connections to parent and child nodes.
   * @param node the node to be deleted
   */
  private static void deleteNode(Node node) {
    for (Node parent : node.getParents()) {
      parent.getChildren().remove(node);
    }

    for (Node child : node.getChildren()) {
      child.getParents().remove(node);
    }
  }
}
