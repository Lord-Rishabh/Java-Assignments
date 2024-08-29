package assignment.controller.options;

import static assignment.utils.Validations.validGetNode;

import assignment.controller.NodeController;
import assignment.controller.graph.GraphCycleDetector;
import assignment.models.Node;

import java.util.Scanner;

/**
 * Handles the addition of a new dependency between nodes.
 */
public class AddNewDependency {

  /**
   * Adds a new dependency between nodes, ensuring no cycles are created.
   * @param nodeController the controller managing the nodes
   */
  public static void option(NodeController nodeController) {
    System.out.println("Adding a dependency");

    Node parentNode = getNode(nodeController, "Parent");
    Node childNode = getNode(nodeController, "Child");

    if (GraphCycleDetector.cycleExist(parentNode, childNode)) {
      System.err.println("Adding this dependency is creating a cycle. Please re-enter dependency.");
      option(nodeController);
      return;
    }

    nodeController.addDependency(parentNode, childNode);
    System.out.println("Dependency added successfully.");
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
