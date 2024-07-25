package assignment.controller.options;

import assignment.controller.NodeController;
import assignment.models.Node;

import java.util.*;

import static assignment.utils.Validations.validGetNode;

public class GetDescendants {

  public static void option(NodeController nodeController) {
    Node node = getNode(nodeController);
    printDescendants(node);
  }

  private static Node getNode(NodeController nodeController) {
    Scanner scan = new Scanner(System.in);
    System.out.print("Enter the Node Id: ");
    String nodeId = scan.next();

    while (!validGetNode(nodeController, nodeId)) {
      System.out.print("Enter the Node Id: ");
      nodeId = scan.next();
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
