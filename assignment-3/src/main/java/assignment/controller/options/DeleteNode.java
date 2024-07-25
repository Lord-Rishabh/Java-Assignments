package assignment.controller.options;

import assignment.controller.NodeController;
import assignment.models.Node;

import java.util.Scanner;

import static assignment.utils.Validations.validGetNode;

public class DeleteNode {

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

  private static void deleteNode(Node node) {
    for (Node parent : node.getParents()) {
      parent.getChildren().remove(node);
    }

    for (Node child : node.getChildren()) {
      child.getParents().remove(node);
    }
  }
}
