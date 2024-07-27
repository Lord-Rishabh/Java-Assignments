package options;

import assignment.controller.NodeController;
import assignment.models.Node;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestDeleteNode {

  @Test
  public void testDeleteNode() {
    NodeController nodeController = new NodeController();
    Node node1 = new Node("1", "one");
    Node node2 = new Node("2", "two");

    nodeController.addNode("1", node1);
    nodeController.addNode("2", node2);

    nodeController.removeNode("1");
    Assertions.assertFalse(nodeController.checkNode("1"));
  }
}
