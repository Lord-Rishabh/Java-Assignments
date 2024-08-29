package options;

import assignment.controller.NodeController;
import assignment.controller.options.GetDescendants;
import assignment.models.Node;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestGetDescendants {

  @Test
  public void testGetDescendants() {

    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outputStream));

    NodeController nodeController = new NodeController();
    Node node1 = new Node("1", "one");
    Node node2 = new Node("2", "two");
    Node node3 = new Node("3", "three");

    nodeController.addDependency(node1, node2);
    nodeController.addDependency(node2, node3);

    Set<Node> descendants = GetDescendants.getDescendants(node1);
    Assertions.assertTrue(descendants.contains(node3));
  }
}
