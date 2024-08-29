package assignment.controller.options;

import static assignment.utils.Validations.validAddNode;

import assignment.controller.NodeController;
import assignment.models.Node;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * Handles the addition of a new node.
 */
public class AddNewNode {

  /**
   * Prompts the user to enter node details and adds the new node to the controller.
   * @param nodeController the controller managing the nodes
   */
  public static void option(NodeController nodeController) {
    String nodeId = getNodeId(nodeController);
    String nodeName = getNodeName();
    Map<String, String> additionalInfo = getAdditionalInfo();

    Node node = new Node(nodeId, nodeName);
    node.setAdditionalInfo(additionalInfo);
    nodeController.addNode(nodeId, node);
  }

  /**
   * Prompts the user to enter a valid node ID.
   * @param nodeController the controller managing the nodes
   * @return the valid node ID entered by the user
   */
  public static String getNodeId(NodeController nodeController) {
    Scanner scan = new Scanner(System.in);
    System.out.print("Enter the node id: ");
    String currentNodeId = scan.nextLine();
    String nodeId;

    if (validAddNode(nodeController, currentNodeId)) {
      nodeId = currentNodeId;
    } else {
      nodeId = getNodeId(nodeController);
    }
    return nodeId;
  }

  /**
   * Prompts the user to enter a node name.
   * @return the node name entered by the user
   */
  public static String getNodeName() {
    Scanner scan = new Scanner(System.in);
    System.out.print("Enter the node name: ");
    String currentNodeName = scan.nextLine();
    String nodeName;

    if (currentNodeName.isEmpty()) {
      System.err.println("Node name cannot be empty.");
      nodeName = getNodeName();
    } else {
      nodeName = currentNodeName;
    }
    return nodeName;
  }

  /**
   * Prompts the user to enter additional information for the node.
   * @return a map of additional information entries
   */
  private static Map<String, String> getAdditionalInfo() {
    Map<String, String> additionalInfo = new TreeMap<>();
    Scanner scan = new Scanner(System.in);
    System.out.print("Do you want to add additional information? (y/n): ");
    String flag = scan.next();

    if (flag.equalsIgnoreCase("y")) {
      System.out.print("Enter the number of information entries you want to add: ");
      String sizeStr = scan.next();
      int sizeOfInfo;

      try {
        sizeOfInfo = Integer.parseInt(sizeStr);
      } catch (NumberFormatException e) {
        System.err.println("Invalid number entered.");
        sizeOfInfo = 0;
      }

      for (int i = 0; i < sizeOfInfo; i++) {
        System.out.print("Enter key: ");
        String key = scan.next();
        System.out.print("Enter value: ");
        String value = scan.next();
        additionalInfo.put(key, value);
      }
    }

    return additionalInfo;
  }
}
