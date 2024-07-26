package assignment.controller.options;

import static assignment.utils.Validations.validGetNode;

import assignment.controller.NodeController;
import assignment.models.Node;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

/**
 * Handles the retrieval and display of a node's ancestors.
 */
public class GetAncestors {

  /**
   * Prompts the user to enter a node ID and prints its ancestors.
   * @param nodeController the controller managing the nodes
   */
  public static void option(NodeController nodeController) {
    Node node = getNode(nodeController);
    printAncestors(node);
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
   * Prints the ancestors of the specified node.
   * @param node the node whose ancestors are to be printed
   */
  private static void printAncestors(Node node) {
    Set<Node> visited = new HashSet<>();
    Queue<Node> queue = new LinkedList<>();
    queue.add(node);

    while (!queue.isEmpty()) {
      Node current = queue.poll();
      for (Node parent : current.getParents()) {
        if (!visited.contains(parent)) {
          queue.add(parent);
          visited.add(parent);
        }
      }
    }

    System.out.print("\nAncestors of node " + node.getId() + " are: ");
    for (Node ancestor : visited) {
      System.out.print(ancestor.getId() + " ");
    }
    System.out.println();

  }
}
