package assignment.utils;

import assignment.controller.NodeController;

public class Validations {

  public static boolean validGetNode(NodeController nodeController, String nodeId) {

    boolean flag = true;
    if (nodeId.isEmpty()) {
      System.err.println("Node Id cannot be empty.");
      flag = false;
    } else if (!nodeController.checkNode(nodeId)) {
      System.err.println("Node with this Id does not exist.");
      flag = false;
    }
    return flag;
  }

  public static boolean validAddNode(NodeController nodeController, String nodeId) {
    boolean flag = true;
    if (nodeId.isEmpty()) {
      System.err.println("Node id cannot be empty.");
      flag = false;
    } else if (nodeController.checkNode(nodeId)) {
      System.err.println("Node with this id already exist.");
      flag = false;
    }
    return flag;
  }
}
