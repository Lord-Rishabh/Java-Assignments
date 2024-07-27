package options;

import assignment.controller.NodeController;
import assignment.controller.options.DeleteDependency;
import assignment.models.Node;

import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestAddDependency {

  @Test
  public void testDeleteNode() {
    NodeController nodeController = new NodeController();
    Node node1 = new Node("1", "one");
    Node node2 = new Node("2", "two");

    nodeController.addNode("1", node1);
    nodeController.addNode("2", node2);

    nodeController.addDependency(node1, node2);

    Set<Node> children = node1.getChildren();
    Assertions.assertTrue(children.contains(node2));
  }
}
