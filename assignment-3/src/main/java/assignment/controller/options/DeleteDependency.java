package assignment.controller.options;

import assignment.controller.NodeController;
import assignment.models.Node;

import java.util.Scanner;

import static assignment.utils.Validations.validGetNode;

public class DeleteDependency {

  public static void option(NodeController nodeController) {
    System.out.println("Adding a dependency");

    Node parentNode = getNode(nodeController, "Parent");
    Node childNode = getNode(nodeController, "Child");

    parentNode.getChildren().remove(childNode);
    childNode.getParents().remove(parentNode);
  }

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
