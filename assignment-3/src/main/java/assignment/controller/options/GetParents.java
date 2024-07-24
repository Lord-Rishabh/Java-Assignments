package assignment.controller.options;

import assignment.controller.NodeController;
import assignment.models.Node;

import java.util.Scanner;

public class GetParents {

  public static void option(NodeController nodeController) {
    System.out.println("Enter the node : ");
    Node node = getNode(nodeController);
    printParents(node);
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

  private static void printParents(Node node) {
    for(Node parent : node.getParents()) {
      System.out.print(parent.getId() + " ");
    }
    System.out.println();
  }
}
