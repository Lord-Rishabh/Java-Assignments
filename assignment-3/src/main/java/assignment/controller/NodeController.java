package assignment.controller;

import assignment.models.Node;

import java.util.HashMap;
import java.util.Map;

/**
 * Manages nodes and their dependencies.
 */
public class NodeController {

  private final Map<String, Node> adjList;

  /**
   * Constructs a NodeController with an empty node list.
   */
  public NodeController() {
    adjList = new HashMap<>();
  }

  /**
   * Adds a node to the controller.
   * @param nodeId the ID of the node
   * @param node   the node to add
   */
  public void addNode(String nodeId, Node node) {
    adjList.put(nodeId, node);
  }

  /**
   * Checks if a node exists in the controller.
   * @param nodeId the ID of the node
   * @return true if the node exists, false otherwise
   */
  public boolean checkNode(String nodeId) {
    return adjList.containsKey(nodeId);
  }

  /**
   * Retrieves a node from the controller.
   * @param nodeId the ID of the node
   * @return the node with the specified ID, or null if not found
   */
  public Node getNode(String nodeId) {
    return adjList.get(nodeId);
  }

  /**
   * Adds a dependency between parent and child nodes.
   * @param parent the parent node
   * @param child  the child node
   */
  public void addDependency(Node parent, Node child) {
    if (parent != null && child != null) {
      parent.getChildren().add(child);
      child.getParents().add(parent);
    }
  }

  /**
   * Removes a node from the controller.
   * @param nodeId the ID of the node to remove
   */
  public void removeNode(String nodeId) {
    adjList.remove(nodeId);
  }
}
