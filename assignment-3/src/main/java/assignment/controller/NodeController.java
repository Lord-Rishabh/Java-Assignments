package assignment.controller;

import assignment.models.Node;

import java.util.HashMap;
import java.util.Map;

public class NodeController {

  private final Map<String, Node> adjList;

  public NodeController() {
    adjList = new HashMap<>();
  }

  public void addNode(String nodeId, Node node) {
    adjList.put(nodeId, node);
  }

  public boolean checkNode(String nodeId) {
    return adjList.containsKey(nodeId);
  }

  public Node getNode(String nodeId) {
    return adjList.get(nodeId);
  }

  public void addDependency(Node parent, Node child) {
    if (parent != null && child != null) {
      parent.getChildren().add(child);
      child.getParents().add(parent);
    }
  }

  public void removeNode(String nodeId) {
    adjList.remove(nodeId);
  }
}
