package options;

import assignment.controller.NodeController;
import assignment.controller.options.GetParents;
import assignment.models.Node;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class TestGetParents {

  @Test
  public void testGetParents() {

    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outputStream));


    NodeController nodeController = new NodeController();
    Node node1 = new Node("1", "one");
    Node node2 = new Node("2", "two");
    Node node3 = new Node("3", "three");

    nodeController.addDependency(node1, node3);
    nodeController.addDependency(node2, node3);

    GetParents.printParents(node3);

    String printedOutput = outputStream.toString().trim();

    Assertions.assertEquals("2 1", printedOutput);
  }
}
