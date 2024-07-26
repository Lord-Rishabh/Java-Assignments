package assignment.controller.graph;

import assignment.models.Node;

import java.util.HashMap;
import java.util.Map;

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
   * Checks if a cycle exists starting from the given node.
   * @param node the starting node
   * @return true if a cycle exists, false otherwise
   */
  public static boolean hasCycle(Node node) {
    if (node == null) {
      return false;
    }
    Map<Node, Boolean> visitedMap = new HashMap<>();
    return dfs(node, visitedMap);
  }

  /**
   * Performs a depth-first search to detect a cycle.
   * @param node the current node
   * @param visitedMap a map to keep track of visited nodes
   * @return true if a cycle is detected, false otherwise
   */
  private static boolean dfs(Node node, Map<Node, Boolean> visitedMap) {
    if (visitedMap.containsKey(node)) {
      return visitedMap.get(node);
    }

    visitedMap.put(node, true);
    for (Node child : node.getChildren()) {
      if (dfs(child, visitedMap)) {
        return true;
      }
    }
    visitedMap.put(node, false);

    return false;
  }
}
