package assignment.controller.options;

import assignment.controller.NodeController;
import assignment.models.Node;

import java.util.Scanner;

public class DeleteNode {

  public static void option(NodeController nodeController) {
    Node currentNode = getCurrentNode(nodeController);
    if (currentNode != null) {
      deleteNode(currentNode, nodeController);
      nodeController.removeNode(currentNode.getId());
      System.out.println("Node deleted successfully.");
    } else {
      System.err.println("Node deletion failed.");
    }
  }

  private static Node getCurrentNode(NodeController nodeController) {
    Scanner scan = new Scanner(System.in);
    System.out.print("Enter the Node Id: ");
    String nodeId = scan.nextLine();

    while (nodeId.isEmpty() || !nodeController.checkNode(nodeId)) {
      if (nodeId.isEmpty()) {
        System.err.println("Node id cannot be empty.");
      } else {
        System.err.println("Node with this id does not exist.");
      }
      System.out.print("Enter the Node Id: ");
      nodeId = scan.nextLine();
    }

    return nodeController.getNode(nodeId);
  }

  private static void deleteNode(Node node, NodeController nodeController) {
    for (Node parent : node.getParents()) {
      parent.getChildren().remove(node);
    }

    for (Node child : node.getChildren()) {
      child.getParents().remove(node);
    }
  }
}
