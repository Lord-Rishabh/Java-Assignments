package assignment.controller.options;

import assignment.controller.NodeController;
import assignment.models.Node;

import java.util.Scanner;

import static assignment.utils.Validations.validGetNode;

public class GetChildren {

  public static void option(NodeController nodeController) {
    Node node = getNode(nodeController);
    printChildren(node);
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

  private static void printChildren(Node node) {
    for(Node child : node.getChildren()) {
      System.out.print(child.getId() + " ");
    }
    System.out.println();
  }
}
