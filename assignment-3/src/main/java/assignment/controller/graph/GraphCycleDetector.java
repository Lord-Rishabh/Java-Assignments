package assignment.controller.graph;

import assignment.models.Node;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * Utility class for detecting cycles in a graph.
 */
public class GraphCycleDetector {

  /**
   * Checks if adding a child to a parent node creates a cycle.
   * @param parent the parent node
   * @param child the child node
   * @return true if a cycle exists, false otherwise
   */
  public static boolean cycleExist(Node parent, Node child) {
    if (parent == null || child == null) {
      return false;
    }
    parent.getChildren().add(child);
    boolean hasCycle = hasCycle(parent);
    parent.getChildren().remove(child);

    return hasCycle;
  }

  /**
   * Checks if a cycle exists starting from the given node
   * Performs a breath-first search to detect a cycle.
   * @param node the starting node
   * @return true if a cycle exists, false otherwise
   */
  public static boolean hasCycle(Node node) {
    boolean validFlag = false;

    if (node == null) {
      return validFlag;
    }

    Queue<Node> queue = new LinkedList<>();
    Set<Node> visited = new HashSet<>();

    queue.add(node);
    while (!queue.isEmpty()) {
      Node curr = queue.poll();

      if (visited.contains(curr)) {
        validFlag = true;
        break;
      }

      visited.add(curr);

      for (Node child : curr.getChildren()) {
        if (visited.contains(child)) {
          validFlag = true;
          break;
        }
        queue.add(child);
      }

      if (validFlag) {
        break;
      }
    }

    return validFlag;
  }
}
