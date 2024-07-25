package assignment.controller.options;

import assignment.controller.NodeController;
import assignment.models.Node;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import static assignment.utils.Validations.validAddNode;

public class AddNewNode {

  public static void option(NodeController nodeController ) {

    String nodeId = getNodeId(nodeController);
    String nodeName = getNodeName();
    Map<String, String> additionalInfo = getAdditionalInfo();

    Node node = new Node(nodeId, nodeName);
    node.setAdditionalInfo(additionalInfo);
    nodeController.addNode(nodeId, node);
  }

  public static String getNodeId(NodeController nodeController) {
    Scanner scan = new Scanner(System.in);
    System.out.print("Enter the node id : ");
    String currentNodeId = scan.nextLine();
    String nodeId;

    if (validAddNode(nodeController, currentNodeId)) {
      nodeId = currentNodeId;
    } else {
      nodeId = getNodeId(nodeController);
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
