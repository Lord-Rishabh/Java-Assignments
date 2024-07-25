package assignment.controller.graph;

import assignment.models.Node;

import java.util.HashMap;
import java.util.Map;

public class GraphCycleDetector {

  public static boolean cycleExist( Node parent, Node child) {
    if (parent == null || child == null) {
      return false;
    }
    parent.getChildren().add(child);
    boolean hasCycle = hasCycle(parent);
    parent.getChildren().remove(child);

    return hasCycle;
  }

  public static boolean hasCycle(Node node) {
    if (node == null) {
      return false;
    }
    Map<Node, Boolean> visitedMap = new HashMap<>();
    return dfs(node, visitedMap);
  }

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
