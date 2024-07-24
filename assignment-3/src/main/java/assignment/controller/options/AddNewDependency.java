package assignment.controller.options;

import assignment.controller.NodeController;
import assignment.controller.graph.GraphCycleDetector;
import assignment.models.Node;

import java.util.Scanner;

public class AddNewDependency {

  public static void option(NodeController nodeController) {
    System.out.println("Adding a dependency");

    Node parentNode = getNode(nodeController, "Parent");
    Node childNode = getNode(nodeController, "Child");

    if (GraphCycleDetector.cycleExist(nodeController, parentNode, childNode)) {
      System.err.println("Adding this dependency is creating a cycle. Please re-enter dependency.");
      option(nodeController);
      return;
    }

    nodeController.addDependency(parentNode, childNode);
    System.out.println("Dependency added successfully.");
  }

  private static Node getNode(NodeController nodeController, String nodeType) {
    Scanner scan = new Scanner(System.in);
    System.out.print("Enter the " + nodeType + " Node Id: ");
    String nodeId = scan.nextLine();

    while (nodeId.isEmpty() || !nodeController.checkNode(nodeId)) {
      if (nodeId.isEmpty()) {
        System.err.println("Node id cannot be empty.");
      } else {
        System.err.println("Node with this id does not exist.");
      }
      System.out.print("Enter the " + nodeType + " Node Id: ");
      nodeId = scan.nextLine();
    }

    return nodeController.getNode(nodeId);
  }
}
