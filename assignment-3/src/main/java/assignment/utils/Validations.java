package assignment.utils;

import assignment.controller.NodeController;

/**
 * Provides validation methods for node-related operations.
 */
public class Validations {

  /**
   * Validates if a node ID is non-empty and exists in the NodeController.
   * @param nodeController the controller managing the nodes
   * @param nodeId         the ID of the node to validate
   * @return true if the node ID is valid, false otherwise
   */
  public static boolean validGetNode(NodeController nodeController, String nodeId) {
    boolean isValid = true;

    if (nodeId.isEmpty()) {
      System.err.println("Node ID cannot be empty.");
      isValid = false;
    } else if (!nodeController.checkNode(nodeId)) {
      System.err.println("Node with this ID does not exist.");
      isValid = false;
    }

    return isValid;
  }

  /**
   * Validates if a node ID is non-empty and does not already exist in the NodeController.
   * @param nodeController the controller managing the nodes
   * @param nodeId         the ID of the node to validate
   * @return true if the node ID is valid for addition, false otherwise
   */
  public static boolean validAddNode(NodeController nodeController, String nodeId) {
    boolean isValid = true;

    if (nodeId.isEmpty()) {
      System.err.println("Node ID cannot be empty.");
      isValid = false;
    } else if (nodeController.checkNode(nodeId)) {
      System.err.println("Node with this ID already exists.");
      isValid = false;
    }

    return isValid;
  }
}
