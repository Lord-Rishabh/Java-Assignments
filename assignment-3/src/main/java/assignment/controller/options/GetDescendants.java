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
 * Handles the retrieval and display of a node's descendants.
 */
public class GetDescendants {

  /**
   * Prompts the user to enter a node ID and prints its descendants.
   * @param nodeController the controller managing the nodes
   */
  public static void option(NodeController nodeController) {
    Node node = getNode(nodeController);
    Set<Node> descendants = getDescendants(node);
    printDescendants(node, descendants);
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
   * Get the descendants of the node.
   * @param node the node whose descendants are to be returned
   * @return set of nodes which are the descendants of the 'node'
   */
  public static Set<Node> getDescendants(Node node) {
    Set<Node> visited = new HashSet<>();
    Queue<Node> queue = new LinkedList<>();
    queue.add(node);

    while (!queue.isEmpty()) {
      Node current = queue.poll();
      for (Node child : current.getChildren()) {
        if (!visited.contains(child)) {
          queue.add(child);
          visited.add(child);
        }
      }
    }

    return visited;

  }

  /**
   * Prints the descendants of the specified node.
   * @param node the node whose descendants are to be printed
   * @param descendants set of nodes which are the descendants
   */
  private static void printDescendants(Node node, Set<Node> descendants) {
    System.out.print("\nDescendants of node " + node.getId() + " are: ");
    for (Node descendant : descendants) {
      System.out.print(descendant.getId() + " ");
    }
    System.out.println();
  }
}
