package assignment.controller.options;

import assignment.controller.NodeController;
import assignment.models.Node;

import java.util.*;

public class GetDescendants {

  public static void option(NodeController nodeController) {
    Node node = getNode(nodeController);
    printDescendants(node);
  }

  private static Node getNode(NodeController nodeController) {
    Scanner scan = new Scanner(System.in);
    System.out.print("Enter the Node Id: ");
    String nodeId = scan.nextLine();

    while (nodeId.isEmpty() || !nodeController.checkNode(nodeId)) {
      if (nodeId.isEmpty()) {
        System.err.println("Node id cannot be empty.");
      } else {
        System.err.println("Node with this id does not exist.");
      }
      System.out.print("Enter the Node Id: ");
      nodeId = scan.nextLine();
    }

    return nodeController.getNode(nodeId);
  }

  private static void printDescendants(Node node) {
    Set<Node> visited = new HashSet<>();
    Queue<Node> queue = new LinkedList<>();
    queue.add(node);

    while(!queue.isEmpty()) {
      Node current = queue.poll();
      for(Node child : current.getChildren()) {
        if(!visited.contains(child)) {
          queue.add(child);
          visited.add(child);
        }
      }
    }

    System.out.println("Descendants of node " + node.getId() + "is : " );
    for (Node ancestor : visited) {
      System.out.print(ancestor.getId() + " ");
    }
  }
}
