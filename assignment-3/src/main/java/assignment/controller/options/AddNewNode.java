package assignment.controller.options;

import assignment.controller.NodeController;
import assignment.models.Node;

import java.util.Scanner;

public class AddNewNode {

  public static void option(NodeController nodeController ) {

    String nodeId = getNodeId(nodeController);
    String nodeName = getNodeName();
    System.out.print("Do you want to add additional information ? (y/n) : ");
    System.out.println("Enter the information : ");
    Node node = new Node(nodeId, nodeName);
    nodeController.addNode(nodeId, node);
  }

  public static String getNodeId(NodeController nodeController) {
    Scanner scan = new Scanner(System.in);
    System.out.print("Enter the node id : ");
    String currentNodeId = scan.nextLine();
    String nodeId;

    if (currentNodeId.isEmpty()) {
      System.err.println("Node id cannot be empty.");
      nodeId = getNodeId(nodeController);
    } else if (nodeController.checkNode(currentNodeId)) {
      System.err.println("Node with this id already exist.");
      nodeId = getNodeId(nodeController);
    } else {
      nodeId = currentNodeId;
    }
    return nodeId;
  }

  public static String getNodeName() {
    Scanner scan = new Scanner(System.in);
    System.out.print("Enter the node Name : ");
    String currentNodeName = scan.nextLine();
    String nodeName;

    if (currentNodeName.isEmpty()) {
      System.err.println("Node id cannot be empty.");
      nodeName = getNodeName();
    } else {
      nodeName = currentNodeName;
    }
    return nodeName;
  }


}
