package assignment.controller.options;

import assignment.controller.NodeController;
import assignment.models.Node;

import java.util.Scanner;

import static assignment.utils.Validations.validGetNode;

public class GetParents {

  public static void option(NodeController nodeController) {
    System.out.println("Enter the node : ");
    Node node = getNode(nodeController);
    printParents(node);
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

  private static void printParents(Node node) {
    for(Node parent : node.getParents()) {
      System.out.print(parent.getId() + " ");
    }
    System.out.println();
  }
}
