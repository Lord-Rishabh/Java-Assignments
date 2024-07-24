package assignment.controller.options;

import assignment.controller.NodeController;
import assignment.models.Node;

import java.util.Scanner;

public class GetChildren {

  public static void option(NodeController nodeController) {
    Node node = getNode(nodeController);
    printChildren(node);
  }

  private static Node getNode(NodeController nodeController) {
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

  private static void printChildren(Node node) {
    for(Node child : node.getChildren()) {
      System.out.print(child.getId() + " ");
    }
    System.out.println();
  }
}
